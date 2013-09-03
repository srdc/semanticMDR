
package ihe.qrph.dex._2013;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MappingSpecificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MappingSpecificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contentModel" type="{urn:ihe:qrph:dex:2013}ContentModelType"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mappingScript" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MappingSpecificationType", propOrder = {
    "contentModel",
    "type",
    "mappingScript"
})
public class MappingSpecificationType {

    @XmlElement(required = true)
    protected ContentModelType contentModel;
    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String mappingScript;

    /**
     * Gets the value of the contentModel property.
     * 
     * @return
     *     possible object is
     *     {@link ContentModelType }
     *     
     */
    public ContentModelType getContentModel() {
        return contentModel;
    }

    /**
     * Sets the value of the contentModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentModelType }
     *     
     */
    public void setContentModel(ContentModelType value) {
        this.contentModel = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the mappingScript property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMappingScript() {
        return mappingScript;
    }

    /**
     * Sets the value of the mappingScript property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMappingScript(String value) {
        this.mappingScript = value;
    }

}
