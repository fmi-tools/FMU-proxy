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

#ifndef FMU_PROXY_FMISIMULATION_HPP
#define FMU_PROXY_FMISIMULATION_HPP

#include <vector>
#include <iostream>
#include <fmilib.h>
#include "fmi_definitions.hpp"

namespace fmuproxy { namespace fmi {

    class FmiSimulation {

    public:

        virtual double getCurrentTime() const = 0;
        
        virtual ModelDescription &getModelDescription() const = 0;

        virtual void init() = 0;

        virtual void init(double start) = 0;

        virtual void init(double start, double stop) = 0;

        virtual fmi2_status_t step(double step_size) = 0;

        virtual fmi2_status_t reset() = 0;

        virtual fmi2_status_t terminate() = 0;

        virtual fmi2_value_reference_t get_value_reference(const std::string &name) {
            return getModelDescription().get_value_reference(name);
        }

        virtual fmi2_status_t readInteger(fmi2_value_reference_t vr, fmi2_integer_t &ref) = 0;
        virtual fmi2_status_t readInteger(const std::vector<fmi2_value_reference_t> &vr, std::vector<fmi2_integer_t > &ref) = 0;

        virtual fmi2_status_t writeInteger(fmi2_value_reference_t vr, fmi2_integer_t value) = 0;
        virtual fmi2_status_t writeInteger(const std::vector<fmi2_value_reference_t> &vr, const std::vector<fmi2_integer_t> &value) = 0;

        virtual fmi2_status_t readReal(fmi2_value_reference_t vr, fmi2_real_t &ref) = 0;
        virtual fmi2_status_t readReal(const std::vector<fmi2_value_reference_t> &vr, std::vector<fmi2_real_t > &ref) = 0;

        virtual fmi2_status_t writeReal(fmi2_value_reference_t vr, fmi2_real_t value) = 0;
        virtual fmi2_status_t writeReal(const std::vector<fmi2_value_reference_t> &vr, const std::vector<fmi2_real_t> &value) = 0;

        virtual fmi2_status_t readString(fmi2_value_reference_t vr, fmi2_string_t &ref) = 0;
        virtual fmi2_status_t readString(const std::vector<fmi2_value_reference_t> &vr, std::vector<fmi2_string_t > &ref) = 0;

        virtual fmi2_status_t writeString(fmi2_value_reference_t vr, fmi2_string_t value) = 0;
        virtual fmi2_status_t writeString(const std::vector<fmi2_value_reference_t> &vr, const std::vector<fmi2_string_t> &value) = 0;

        virtual fmi2_status_t readBoolean(fmi2_value_reference_t vr, fmi2_boolean_t &ref) = 0;
        virtual fmi2_status_t readBoolean(const std::vector<fmi2_value_reference_t> &vr, std::vector<fmi2_boolean_t > &ref) = 0;

        virtual fmi2_status_t writeBoolean(fmi2_value_reference_t vr, fmi2_boolean_t value) = 0;
        virtual fmi2_status_t writeBoolean(const std::vector<fmi2_value_reference_t> &vr, const std::vector<fmi2_boolean_t> &value) = 0;

        virtual ~FmiSimulation(){}

    };

}}

#endif //FMU_PROXY_FMISIMULATION_HPP
