
package ihe.qrph.dex._2013;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RetrieveDataElementListResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RetrieveDataElementListResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataElementSummary" type="{urn:ihe:qrph:dex:2013}DataElementSummaryType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveDataElementListResponseType", propOrder = {
    "dataElementSummary"
})
public class RetrieveDataElementListResponseType {

    @XmlElement(name = "DataElementSummary")
    protected List<DataElementSummaryType> dataElementSummary;

    /**
     * Gets the value of the dataElementSummary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataElementSummary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataElementSummary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataElementSummaryType }
     * 
     * 
     */
    public List<DataElementSummaryType> getDataElementSummary() {
        if (dataElementSummary == null) {
            dataElementSummary = new ArrayList<DataElementSummaryType>();
        }
        return this.dataElementSummary;
    }

}
