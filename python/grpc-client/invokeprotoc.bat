python -m grpc_tools.protoc --proto_path=../../rpc-definitions/proto --python_out=. --grpc_python_out=. ../../rpc-definitions/proto/definitions.proto ../../rpc-definitions/proto/service.proto
