package no.ntnu.ihb.fmuproxy.crosscheck

import no.ntnu.ihb.fmi4j.common.FmuSlave
import no.ntnu.ihb.fmi4j.importer.Fmu
import no.ntnu.ihb.fmi4j.modeldescription.DefaultExperiment
import no.ntnu.ihb.fmi4j.modeldescription.jaxb.JaxbModelDescriptionParser
import no.ntnu.ihb.fmi4j.util.OsUtil
import no.ntnu.ihb.fmuproxy.FmuId
import no.ntnu.ihb.fmuproxy.grpc.Service
import no.ntnu.ihb.fmuproxy.thrift.ThriftFmuClient
import no.ntnu.ihb.fmuproxy.thrift.ThriftFmuSocketServer
import java.io.File
import java.util.*
import kotlin.system.measureTimeMillis

fun filter(fmuDir: File): Pair<Fmu, DefaultExperiment>? {

    val fmuFile = fmuDir.listFiles().find {
        it.name.endsWith(".fmu")
    } ?: return null

    val refData = fmuDir.listFiles().find {
        it.name.endsWith("ref.csv")
    } ?: return null

    val defaultsData = fmuDir.listFiles().find {
        it.name.endsWith(".opt")
    } ?: return null

    val defaults = CrossCheckOptions.parse(defaultsData.readText())

    val inputData = fmuDir.listFiles().find {
        it.name.endsWith("in.csv")
    }

    if (refData.length() > 1E6) {
        println("FMU Rejected, reason: Reference data > 1MB")
        return null
    } else if (OsUtil.isLinux && "JModelica.org" in fmuDir.absolutePath) {
        println("FMU Rejected, reason: JModelica.org FMUs makes Linux crash.")
        return null
    } else if ("FMUSDK" in fmuDir.absolutePath || "Easy5" in fmuDir.absolutePath && fmuDir.absolutePath.contains("vanderpol", ignoreCase = true)) {
        println("FMU Rejected, reason: FMUSDK/vanDerPol.")
        return null
    } else if (defaults.stepSize == 0.0) {
        println("FMU Rejected, reason: stepSize == 0.0")
        return null
    } else if (inputData != null) {
        println("FMU Rejected, reason: Requires input data.")
        return null
    } else if (JaxbModelDescriptionParser.parse(fmuFile).asCoSimulationModelDescription().attributes.needsExecutionTool) {
        println("FMU Rejected, reason: FMU requires execution tool.")
        return null
    } else if (defaults.stepSize < 1e-4) {
        println("FMU Rejected, reason: StepSize to small.")
        return null
    }

    return Fmu.from(fmuFile) to defaults

}

fun assembleFmus(xcDir: String): List<Pair<Fmu, DefaultExperiment>> {
    val fmus = mutableListOf<Pair<Fmu, DefaultExperiment>>()
    File(xcDir, "fmus/2.0/cs/${OsUtil.currentOS}").listFiles().forEach { vendor ->
        vendor.listFiles().forEach { version ->
            version.listFiles().forEach { fmuDir ->
                filter(fmuDir)?.also {
                    println("Loading fmu $fmuDir")
                    fmus.add(it)
                }
            }
        }

    }
    return fmus.also {
        println("Assembled ${it.size} fmus")
    }
}

fun runSlave(slave: FmuSlave, options: DefaultExperiment): Long {
    return measureTimeMillis {
        slave.setup(options.startTime, options.stopTime)
        slave.enterInitializationMode()
        slave.exitInitializationMode()
        while (slave.simulationTime <= (options.stopTime - options.stepSize)) {
            slave.doStep(options.stepSize)
        }
        slave.terminate()
    }
}

object RunLocal {

    @JvmStatic
    fun main(args: Array<String>) {

        if (args.isEmpty()) {
            throw IllegalArgumentException("Missing path to fmi-cross-check folder!")
        }

        assembleFmus(args[0]).parallelStream().mapToLong { pair ->
            runSlave(pair.first.asCoSimulationFmu().newInstance(), pair.second).also {
                pair.first.close()
            }
        }.sum().also {
            println("Elapsed local: ${it}ms")
        }

    }
}

object ThriftServer {

    @JvmStatic
    fun main(args: Array<String>) {

        if (args.isEmpty()) {
            throw IllegalArgumentException("Missing path to fmi-cross-check folder!")
        }

        ThriftFmuSocketServer().use { server ->
            val xcDefaults = mutableMapOf<FmuId, DefaultExperiment>()
            assembleFmus(args[0]).forEach {
                server.addFmu(it.first)
                xcDefaults[it.first.guid] = it.second
            }
            server.xcDefaults = xcDefaults
            server.start(9090)

            println("Press eny key to exit..")
            if (Scanner(System.`in`).hasNext()) {
                println("Exiting..")
            }

        }

        System.exit(0)

    }

}

object ThriftClient {

    @JvmStatic
    fun main(args: Array<String>) {

        if (args.isEmpty()) {
            throw IllegalArgumentException("Missing host and port!")
        }

        val host = args[0]
        val port = args[1].toInt()

        ThriftFmuClient.socketClient(host, port).use { client1 ->

            client1.availableFmus.parallelStream().mapToLong { avail ->

                var elapsed = 0L
                ThriftFmuClient.socketClient(host, port).use { client2 ->
                    client2.load(avail.first.fmuId).use {
                        elapsed += runSlave(it.newInstance(), avail.second)
                    }
                }
                elapsed

            }.sum().also {
                println("Elapsed remote: ${it}ms")
            }

        }

    }
}

