/*
 * The MIT License
 *
 * Copyright 2017. Norwegian University of Technology
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING  FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package no.mechatronics.sfi.fmu_proxy.json_rpc

import info.laht.yaj_rpc.RpcHandler
import info.laht.yaj_rpc.net.http.RpcHttpServer
import info.laht.yaj_rpc.net.tcp.RpcTcpServer
import info.laht.yaj_rpc.net.ws.RpcWebSocketServer
import info.laht.yaj_rpc.net.zmq.RpcZmqServer
import no.mechatronics.sfi.fmu_proxy.net.FmuProxyServer


class FmuProxyJsonHttpServer(
        handler: RpcHandler
): RpcHttpServer(handler), FmuProxyServer {

    override val simpleName = "jsonRpc/http"

}

class FmuProxyJsonWsServer(
        handler: RpcHandler
): RpcWebSocketServer(handler), FmuProxyServer {

    override val simpleName = "jsonRpc/ws"

}

class FmuProxyJsonTcpServer(
        handler: RpcHandler
): RpcTcpServer(handler), FmuProxyServer {

    override val simpleName = "jsonRpc/tcp"

}

class FmuProxyJsonZmqServer(
        handler: RpcHandler
): RpcZmqServer(handler), FmuProxyServer {

    override val simpleName = "jsonRpc/zmq"

}