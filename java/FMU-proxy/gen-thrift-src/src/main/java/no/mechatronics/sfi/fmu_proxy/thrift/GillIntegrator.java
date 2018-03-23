/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package no.mechatronics.sfi.fmu_proxy.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2018-03-23")
public class GillIntegrator implements org.apache.thrift.TBase<GillIntegrator, GillIntegrator._Fields>, java.io.Serializable, Cloneable, Comparable<GillIntegrator> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GillIntegrator");

  private static final org.apache.thrift.protocol.TField STEP_SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("step_size", org.apache.thrift.protocol.TType.DOUBLE, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new GillIntegratorStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new GillIntegratorTupleSchemeFactory();

  private double step_size; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    STEP_SIZE((short)1, "step_size");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // STEP_SIZE
          return STEP_SIZE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __STEP_SIZE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.STEP_SIZE, new org.apache.thrift.meta_data.FieldMetaData("step_size", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GillIntegrator.class, metaDataMap);
  }

  public GillIntegrator() {
  }

  public GillIntegrator(
    double step_size)
  {
    this();
    this.step_size = step_size;
    setStep_sizeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GillIntegrator(GillIntegrator other) {
    __isset_bitfield = other.__isset_bitfield;
    this.step_size = other.step_size;
  }

  public GillIntegrator deepCopy() {
    return new GillIntegrator(this);
  }

  @Override
  public void clear() {
    setStep_sizeIsSet(false);
    this.step_size = 0.0;
  }

  public double getStep_size() {
    return this.step_size;
  }

  public GillIntegrator setStep_size(double step_size) {
    this.step_size = step_size;
    setStep_sizeIsSet(true);
    return this;
  }

  public void unsetStep_size() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __STEP_SIZE_ISSET_ID);
  }

  /** Returns true if field step_size is set (has been assigned a value) and false otherwise */
  public boolean isSetStep_size() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __STEP_SIZE_ISSET_ID);
  }

  public void setStep_sizeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __STEP_SIZE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case STEP_SIZE:
      if (value == null) {
        unsetStep_size();
      } else {
        setStep_size((java.lang.Double)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case STEP_SIZE:
      return getStep_size();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case STEP_SIZE:
      return isSetStep_size();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof GillIntegrator)
      return this.equals((GillIntegrator)that);
    return false;
  }

  public boolean equals(GillIntegrator that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_step_size = true;
    boolean that_present_step_size = true;
    if (this_present_step_size || that_present_step_size) {
      if (!(this_present_step_size && that_present_step_size))
        return false;
      if (this.step_size != that.step_size)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(step_size);

    return hashCode;
  }

  @Override
  public int compareTo(GillIntegrator other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetStep_size()).compareTo(other.isSetStep_size());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStep_size()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.step_size, other.step_size);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("GillIntegrator(");
    boolean first = true;

    sb.append("step_size:");
    sb.append(this.step_size);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class GillIntegratorStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public GillIntegratorStandardScheme getScheme() {
      return new GillIntegratorStandardScheme();
    }
  }

  private static class GillIntegratorStandardScheme extends org.apache.thrift.scheme.StandardScheme<GillIntegrator> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GillIntegrator struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // STEP_SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.step_size = iprot.readDouble();
              struct.setStep_sizeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, GillIntegrator struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(STEP_SIZE_FIELD_DESC);
      oprot.writeDouble(struct.step_size);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GillIntegratorTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public GillIntegratorTupleScheme getScheme() {
      return new GillIntegratorTupleScheme();
    }
  }

  private static class GillIntegratorTupleScheme extends org.apache.thrift.scheme.TupleScheme<GillIntegrator> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GillIntegrator struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetStep_size()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetStep_size()) {
        oprot.writeDouble(struct.step_size);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GillIntegrator struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.step_size = iprot.readDouble();
        struct.setStep_sizeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

