
package ihe.qrph.dex._2013;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RetrieveMetadataResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RetrieveMetadataResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataElement" type="{urn:ihe:qrph:dex:2013}DataElementType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveMetadataResponseType", propOrder = {
    "dataElement"
})
public class RetrieveMetadataResponseType {

    @XmlElement(name = "DataElement", required = true)
    protected DataElementType dataElement;

    /**
     * Gets the value of the dataElement property.
     * 
     * @return
     *     possible object is
     *     {@link DataElementType }
     *     
     */
    public DataElementType getDataElement() {
        return dataElement;
    }

    /**
     * Sets the value of the dataElement property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataElementType }
     *     
     */
    public void setDataElement(DataElementType value) {
        this.dataElement = value;
    }

}
