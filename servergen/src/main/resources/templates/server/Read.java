
    /**
     * Autogenerated method
     */
    @Override
    public void read{{varName}}( FmiDefinitions.UInt req, StreamObserver<FmiDefinitions.{{returnType}}> responseObserver) {

        int fmuId = req.getValue();
        FmiSimulation fmu = fmus.get(fmuId);
        if (fmu != null) {
            int valueReference = req.getValue();
            FmiDefinitions.{{returnType}} reply = FmiDefinitions.{{returnType}}.newBuilder().setValue(fmu.getVariableAccessor().get{{typeName}}(valueReference)).build();
            responseObserver.onNext(reply);
        } else {
            LOG.warn("No FMU with id: {}", fmuId);
        }

        responseObserver.onCompleted();

    }