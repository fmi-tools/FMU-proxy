package no.ntnu.ihb.fmuproxy

import info.laht.yajrpc.net.http.RpcHttpClient
import info.laht.yajrpc.net.tcp.RpcTcpClient
import info.laht.yajrpc.net.ws.RpcWebSocketClient
import info.laht.yajrpc.net.zmq.RpcZmqClient
import no.ntnu.ihb.fmi4j.importer.Fmu
import no.ntnu.ihb.fmuproxy.grpc.GrpcFmuClient
import no.ntnu.ihb.fmuproxy.grpc.GrpcFmuServer
import no.ntnu.ihb.fmuproxy.jsonrpc.*
import no.ntnu.ihb.fmuproxy.jsonrpc.service.RpcFmuService
import no.ntnu.ihb.fmuproxy.thrift.ThriftFmuClient
import no.ntnu.ihb.fmuproxy.thrift.ThriftFmuServlet
import no.ntnu.ihb.fmuproxy.thrift.ThriftFmuSocketServer
import no.ntnu.sfi.fmuproxy.TestUtils
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.OS
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.time.Duration

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestProxy {

    companion object {

        private val LOG: Logger = LoggerFactory.getLogger(TestProxy::class.java)

        private val fmu = Fmu.from(File(TestUtils.getTEST_FMUs(),
                "2.0/cs/20sim/4.6.4.8004/ControlledTemperature/ControlledTemperature.fmu"))

        private const val stepSize: Double = 1.0 / 100
        private const val stopTime: Double = 1.0
        private const val host = "localhost"

        private val testTimeout = Duration.ofSeconds(10)

    }

    private val proxy = FmuProxyBuilder(fmu).apply {
        addServer(GrpcFmuServer().apply { addFmu(fmu) })
        addServer(ThriftFmuSocketServer().apply { addFmu(fmu) })
        addServer(ThriftFmuServlet().apply { addFmu(fmu) })
        RpcFmuService().apply { addFmu(fmu) }.also {
            addServer(FmuProxyJsonWsServer(it))
            addServer(FmuProxyJsonTcpServer(it))
            addServer(FmuProxyJsonZmqServer(it))
            if (!OS.LINUX.isCurrentOs) {
                addServer(FmuProxyJsonHttpServer(it))
            }
        }

    }.build().apply {
        start()
    }


    @AfterAll
    fun tearDown() {
        Assertions.assertTimeout(Duration.ofSeconds(10)){
            proxy.stop()
            fmu.close()
        }
    }

    @Test
    fun testGrpc() {

        Assertions.assertTimeout(testTimeout) {
            proxy.getPortFor<GrpcFmuServer>()?.also { port ->

                GrpcFmuClient(host, port).load(fmu.guid).use { client ->

                    val mdLocal = fmu.modelDescription
                    val mdRemote = client.modelDescription

                    Assertions.assertEquals(mdLocal.guid, mdRemote.guid)
                    Assertions.assertEquals(mdLocal.modelName, mdRemote.modelName)
                    Assertions.assertEquals(mdLocal.fmiVersion, mdRemote.fmiVersion)

                    client.newInstance().use { instance ->

                        runSlave(instance, stepSize, stopTime).also {
                            LOG.info("gRPC duration: ${it}ms")
                        }

                    }
                }
            }
        }

    }

    @Test
    fun testThriftSocket() {

        Assertions.assertTimeout(testTimeout) {
            proxy.getPortFor<ThriftFmuSocketServer>()?.also { port ->
                ThriftFmuClient.socketClient(host, port).load(fmu.guid).use { client ->

                    val mdLocal = fmu.modelDescription
                    val mdRemote = client.modelDescription

                    Assertions.assertEquals(mdLocal.guid, mdRemote.guid)
                    Assertions.assertEquals(mdLocal.modelName, mdRemote.modelName)
                    Assertions.assertEquals(mdLocal.fmiVersion, mdRemote.fmiVersion)

                    client.newInstance().use { instance ->

                        runSlave(instance, stepSize, stopTime).also {
                            LOG.info("Thrift (socket) duration: ${it}ms")
                        }

                    }
                }
            }
        }

    }

    @Test
    fun testThriftServlet() {

        disableLog4jLoggers()

        Assertions.assertTimeout(testTimeout) {
            proxy.getPortFor<ThriftFmuServlet>()?.also { port ->
                ThriftFmuClient.servletClient(host, port).load(fmu.guid).use { client ->

                    val mdLocal = fmu.modelDescription
                    val mdRemote = client.modelDescription

                    Assertions.assertEquals(mdLocal.guid, mdRemote.guid)
                    Assertions.assertEquals(mdLocal.modelName, mdRemote.modelName)
                    Assertions.assertEquals(mdLocal.fmiVersion, mdRemote.fmiVersion)

                    client.newInstance().use { instance ->

                        runSlave(instance, stepSize, stopTime).also {
                            LOG.info("Thrift (servlet) duration: ${it}ms")
                        }

                    }
                }
            }
        }

    }

    private fun testJsonRpc(client: JsonRpcFmuClient) {

        val mdLocal = fmu.modelDescription

        Assertions.assertTimeout(testTimeout) {
            client.use {
                LOG.info("Testing client of type ${client.implementationName}")

                Assertions.assertEquals(mdLocal.guid, client.guid)
                Assertions.assertEquals(mdLocal.modelName, client.modelName)
                Assertions.assertEquals(mdLocal.fmiVersion, client.modelDescription.fmiVersion)

                client.newInstance().use { instance ->
                    runSlave(instance, stepSize, stopTime).also {
                        LOG.info("${client.implementationName} duration: ${it}ms")
                    }
                }
            }
        }

    }

    @Test
    @DisabledOnOs(OS.LINUX)
    fun testHttpJsonRpc() {
        RpcHttpClient(host, proxy.getPortFor<FmuProxyJsonHttpServer>()!!).let {
            testJsonRpc(JsonRpcFmuClient(fmu.guid, it))
        }
    }

    @Test
    fun testWsJsonRpc() {
        RpcWebSocketClient(host, proxy.getPortFor<FmuProxyJsonWsServer>()!!).let {
            testJsonRpc(JsonRpcFmuClient(fmu.guid, it))
        }
    }

    @Test
    fun testTcpJsonRpc() {
        RpcTcpClient(host, proxy.getPortFor<FmuProxyJsonTcpServer>()!!).let {
            testJsonRpc(JsonRpcFmuClient(fmu.guid, it))
        }
    }

    @Test
    fun testZmqJsonRpc() {
        RpcZmqClient(host, proxy.getPortFor<FmuProxyJsonZmqServer>()!!).let {
            testJsonRpc(JsonRpcFmuClient(fmu.guid, it))
        }
    }

}