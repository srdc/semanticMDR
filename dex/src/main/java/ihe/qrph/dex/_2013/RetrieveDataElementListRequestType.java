
package ihe.qrph.dex._2013;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RetrieveDataElementListRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RetrieveDataElementListRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="registrationAuthorityContains" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayNameContains" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="definitionContains" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contextualDomainContains" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creationDateBefore" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="creationDateAfter" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="effectiveDateBefore" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="effectiveDateAfter" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="expirationDateBefore" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="expirationDateAfter" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="revisionDateBefore" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="revisionDateAfter" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="objectClassContains" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propertyContains" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataTypeContains" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valueSetID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveDataElementListRequestType", propOrder = {
    "id",
    "registrationAuthorityContains",
    "version",
    "displayNameContains",
    "definitionContains",
    "contextualDomainContains",
    "creationDateBefore",
    "creationDateAfter",
    "effectiveDateBefore",
    "effectiveDateAfter",
    "expirationDateBefore",
    "expirationDateAfter",
    "revisionDateBefore",
    "revisionDateAfter",
    "objectClassContains",
    "propertyContains",
    "dataTypeContains",
    "valueSetID"
})
public class RetrieveDataElementListRequestType {

    protected String id;
    protected String registrationAuthorityContains;
    protected String version;
    protected String displayNameContains;
    protected String definitionContains;
    protected String contextualDomainContains;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creationDateBefore;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creationDateAfter;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDateBefore;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDateAfter;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDateBefore;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDateAfter;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar revisionDateBefore;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar revisionDateAfter;
    protected String objectClassContains;
    protected String propertyContains;
    protected String dataTypeContains;
    protected String valueSetID;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the registrationAuthorityContains property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationAuthorityContains() {
        return registrationAuthorityContains;
    }

    /**
     * Sets the value of the registrationAuthorityContains property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationAuthorityContains(String value) {
        this.registrationAuthorityContains = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the displayNameContains property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayNameContains() {
        return displayNameContains;
    }

    /**
     * Sets the value of the displayNameContains property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayNameContains(String value) {
        this.displayNameContains = value;
    }

    /**
     * Gets the value of the definitionContains property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefinitionContains() {
        return definitionContains;
    }

    /**
     * Sets the value of the definitionContains property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefinitionContains(String value) {
        this.definitionContains = value;
    }

    /**
     * Gets the value of the contextualDomainContains property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContextualDomainContains() {
        return contextualDomainContains;
    }

    /**
     * Sets the value of the contextualDomainContains property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextualDomainContains(String value) {
        this.contextualDomainContains = value;
    }

    /**
     * Gets the value of the creationDateBefore property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDateBefore() {
        return creationDateBefore;
    }

    /**
     * Sets the value of the creationDateBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDateBefore(XMLGregorianCalendar value) {
        this.creationDateBefore = value;
    }

    /**
     * Gets the value of the creationDateAfter property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDateAfter() {
        return creationDateAfter;
    }

    /**
     * Sets the value of the creationDateAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDateAfter(XMLGregorianCalendar value) {
        this.creationDateAfter = value;
    }

    /**
     * Gets the value of the effectiveDateBefore property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDateBefore() {
        return effectiveDateBefore;
    }

    /**
     * Sets the value of the effectiveDateBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDateBefore(XMLGregorianCalendar value) {
        this.effectiveDateBefore = value;
    }

    /**
     * Gets the value of the effectiveDateAfter property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDateAfter() {
        return effectiveDateAfter;
    }

    /**
     * Sets the value of the effectiveDateAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDateAfter(XMLGregorianCalendar value) {
        this.effectiveDateAfter = value;
    }

    /**
     * Gets the value of the expirationDateBefore property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDateBefore() {
        return expirationDateBefore;
    }

    /**
     * Sets the value of the expirationDateBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDateBefore(XMLGregorianCalendar value) {
        this.expirationDateBefore = value;
    }

    /**
     * Gets the value of the expirationDateAfter property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDateAfter() {
        return expirationDateAfter;
    }

    /**
     * Sets the value of the expirationDateAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDateAfter(XMLGregorianCalendar value) {
        this.expirationDateAfter = value;
    }

    /**
     * Gets the value of the revisionDateBefore property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRevisionDateBefore() {
        return revisionDateBefore;
    }

    /**
     * Sets the value of the revisionDateBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRevisionDateBefore(XMLGregorianCalendar value) {
        this.revisionDateBefore = value;
    }

    /**
     * Gets the value of the revisionDateAfter property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRevisionDateAfter() {
        return revisionDateAfter;
    }

    /**
     * Sets the value of the revisionDateAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRevisionDateAfter(XMLGregorianCalendar value) {
        this.revisionDateAfter = value;
    }

    /**
     * Gets the value of the objectClassContains property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectClassContains() {
        return objectClassContains;
    }

    /**
     * Sets the value of the objectClassContains property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectClassContains(String value) {
        this.objectClassContains = value;
    }

    /**
     * Gets the value of the propertyContains property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyContains() {
        return propertyContains;
    }

    /**
     * Sets the value of the propertyContains property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyContains(String value) {
        this.propertyContains = value;
    }

    /**
     * Gets the value of the dataTypeContains property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataTypeContains() {
        return dataTypeContains;
    }

    /**
     * Sets the value of the dataTypeContains property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataTypeContains(String value) {
        this.dataTypeContains = value;
    }

    /**
     * Gets the value of the valueSetID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueSetID() {
        return valueSetID;
    }

    /**
     * Sets the value of the valueSetID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueSetID(String value) {
        this.valueSetID = value;
    }

}
