/*
 * The MIT License
 *
 * Copyright 2017-2018 Norwegian University of Technology
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

#include <unordered_map>
#include <fmuproxy/grpc/server/grpc_fmu_server.hpp>

#include "../example_util.hpp"

using namespace std;
using namespace fmuproxy::grpc::server;

int main(int argc, char **argv) {
    
    const string fmu_path = "../fmus/2.0/cs/20sim/4.6.4.8004/"
                            "ControlledTemperature/ControlledTemperature.fmu";

    auto fmu = fmi4cpp::fmi2::fmu(fmu_path).as_cs_fmu();
    auto md = fmu->get_model_description();
    unordered_map<string, shared_ptr<fmi4cpp::fmi2::cs_fmu>> fmus = {{md->guid, std::move(fmu)}};
    
    grpc_fmu_server server(fmus, 9080);
    server.start();

    fmuproxy::wait_for_input();
    server.stop();

    return 0;
}
