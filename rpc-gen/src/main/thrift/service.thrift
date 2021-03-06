namespace java no.ntnu.ihb.fmuproxy.thrift.internal

include "defs.thrift"

service FmuService {

    defs.Status setup_experiment(1: double start, 2: double stop, 3: double tolerance)
    defs.Status enter_initialization_mode()
    defs.Status exit_initialization_mode()
    
    defs.Status step(1: double currentTime, 2: double stepSize)
    defs.Status reset()
    defs.Status terminate()
    void shutdown()

    defs.IntegerRead read_integer(1: defs.ValueReferences vr) throws (1: defs.NoSuchVariableException ex)
    defs.RealRead read_real(1: defs.ValueReferences vr) throws (1: defs.NoSuchVariableException ex)
    defs.StringRead read_string(1: defs.ValueReferences vr) throws (1: defs.NoSuchVariableException ex)
    defs.BooleanRead read_boolean(1: defs.ValueReferences vr) throws (1: defs.NoSuchVariableException ex)
    defs.BulkRead read_all(1: defs.ValueReferences intVr, 2: defs.ValueReferences realVr, 3: defs.ValueReferences boolVr, 4: defs.ValueReferences strVr) throws (1: defs.NoSuchVariableException ex)

    defs.Status write_integer(1: defs.ValueReferences vr, 2: defs.IntArray value) throws (1: defs.NoSuchVariableException ex)
    defs.Status write_real(1: defs.ValueReferences vr, 2: defs.RealArray value) throws (1: defs.NoSuchVariableException ex)
    defs.Status write_string(1: defs.ValueReferences vr, 2: defs.StringArray value) throws (1: defs.NoSuchVariableException ex)
    defs.Status write_boolean(1: defs.ValueReferences vr, 2: defs.BooleanArray value) throws (1: defs.NoSuchVariableException ex)
    defs.Status write_all(1: defs.ValueReferences intVr, 2: defs.IntArray intValue, 3: defs.ValueReferences realVr, 4: defs.RealArray realValue, 5: defs.ValueReferences boolVr, 6: defs.BooleanArray boolValue, 7: defs.ValueReferences strVr, 8: defs.StringArray strValue) throws (1: defs.NoSuchVariableException ex)

}
