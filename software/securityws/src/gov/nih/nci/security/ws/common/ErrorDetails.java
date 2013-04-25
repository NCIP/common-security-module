/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/**
 * ErrorDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.nih.nci.security.ws.common;

public class ErrorDetails  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    private gov.nih.nci.security.ws.common.ErrorCodes errorCode;

    private java.lang.String errorDescription;

    public ErrorDetails() {
    }

    public ErrorDetails(
           gov.nih.nci.security.ws.common.ErrorCodes errorCode,
           java.lang.String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }


    /**
     * Gets the errorCode value for this ErrorDetails.
     * 
     * @return errorCode
     */
    public gov.nih.nci.security.ws.common.ErrorCodes getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this ErrorDetails.
     * 
     * @param errorCode
     */
    public void setErrorCode(gov.nih.nci.security.ws.common.ErrorCodes errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the errorDescription value for this ErrorDetails.
     * 
     * @return errorDescription
     */
    public java.lang.String getErrorDescription() {
        return errorDescription;
    }


    /**
     * Sets the errorDescription value for this ErrorDetails.
     * 
     * @param errorDescription
     */
    public void setErrorDescription(java.lang.String errorDescription) {
        this.errorDescription = errorDescription;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ErrorDetails)) return false;
        ErrorDetails other = (ErrorDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.errorDescription==null && other.getErrorDescription()==null) || 
             (this.errorDescription!=null &&
              this.errorDescription.equals(other.getErrorDescription())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getErrorDescription() != null) {
            _hashCode += getErrorDescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ErrorDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/common", "ErrorDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/common", "ErrorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/common", "ErrorCodes"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/common", "ErrorDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
