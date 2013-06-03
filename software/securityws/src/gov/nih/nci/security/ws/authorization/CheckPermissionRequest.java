/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/**
 * CheckPermissionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.nih.nci.security.ws.authorization;

public class CheckPermissionRequest  implements java.io.Serializable {
    private java.lang.String userName;

    private java.lang.String groupName;

    private java.lang.String objectId;

    private java.lang.String attribute;

    private java.lang.String privilege;

    private java.lang.String applicationContext;

    public CheckPermissionRequest() {
    }

    public CheckPermissionRequest(
           java.lang.String userName,
           java.lang.String groupName,
           java.lang.String objectId,
           java.lang.String attribute,
           java.lang.String privilege,
           java.lang.String applicationContext) {
           this.userName = userName;
           this.groupName = groupName;
           this.objectId = objectId;
           this.attribute = attribute;
           this.privilege = privilege;
           this.applicationContext = applicationContext;
    }


    /**
     * Gets the userName value for this CheckPermissionRequest.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this CheckPermissionRequest.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the groupName value for this CheckPermissionRequest.
     * 
     * @return groupName
     */
    public java.lang.String getGroupName() {
        return groupName;
    }


    /**
     * Sets the groupName value for this CheckPermissionRequest.
     * 
     * @param groupName
     */
    public void setGroupName(java.lang.String groupName) {
        this.groupName = groupName;
    }


    /**
     * Gets the objectId value for this CheckPermissionRequest.
     * 
     * @return objectId
     */
    public java.lang.String getObjectId() {
        return objectId;
    }


    /**
     * Sets the objectId value for this CheckPermissionRequest.
     * 
     * @param objectId
     */
    public void setObjectId(java.lang.String objectId) {
        this.objectId = objectId;
    }


    /**
     * Gets the attribute value for this CheckPermissionRequest.
     * 
     * @return attribute
     */
    public java.lang.String getAttribute() {
        return attribute;
    }


    /**
     * Sets the attribute value for this CheckPermissionRequest.
     * 
     * @param attribute
     */
    public void setAttribute(java.lang.String attribute) {
        this.attribute = attribute;
    }


    /**
     * Gets the privilege value for this CheckPermissionRequest.
     * 
     * @return privilege
     */
    public java.lang.String getPrivilege() {
        return privilege;
    }


    /**
     * Sets the privilege value for this CheckPermissionRequest.
     * 
     * @param privilege
     */
    public void setPrivilege(java.lang.String privilege) {
        this.privilege = privilege;
    }


    /**
     * Gets the applicationContext value for this CheckPermissionRequest.
     * 
     * @return applicationContext
     */
    public java.lang.String getApplicationContext() {
        return applicationContext;
    }


    /**
     * Sets the applicationContext value for this CheckPermissionRequest.
     * 
     * @param applicationContext
     */
    public void setApplicationContext(java.lang.String applicationContext) {
        this.applicationContext = applicationContext;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckPermissionRequest)) return false;
        CheckPermissionRequest other = (CheckPermissionRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.groupName==null && other.getGroupName()==null) || 
             (this.groupName!=null &&
              this.groupName.equals(other.getGroupName()))) &&
            ((this.objectId==null && other.getObjectId()==null) || 
             (this.objectId!=null &&
              this.objectId.equals(other.getObjectId()))) &&
            ((this.attribute==null && other.getAttribute()==null) || 
             (this.attribute!=null &&
              this.attribute.equals(other.getAttribute()))) &&
            ((this.privilege==null && other.getPrivilege()==null) || 
             (this.privilege!=null &&
              this.privilege.equals(other.getPrivilege()))) &&
            ((this.applicationContext==null && other.getApplicationContext()==null) || 
             (this.applicationContext!=null &&
              this.applicationContext.equals(other.getApplicationContext())));
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
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getGroupName() != null) {
            _hashCode += getGroupName().hashCode();
        }
        if (getObjectId() != null) {
            _hashCode += getObjectId().hashCode();
        }
        if (getAttribute() != null) {
            _hashCode += getAttribute().hashCode();
        }
        if (getPrivilege() != null) {
            _hashCode += getPrivilege().hashCode();
        }
        if (getApplicationContext() != null) {
            _hashCode += getApplicationContext().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckPermissionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "CheckPermissionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "UserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "GroupName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "ObjectId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attribute");
        elemField.setXmlName(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "Attribute"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("privilege");
        elemField.setXmlName(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "Privilege"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationContext");
        elemField.setXmlName(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "ApplicationContext"));
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

}
