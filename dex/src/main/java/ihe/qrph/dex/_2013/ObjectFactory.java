
package ihe.qrph.dex._2013;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ihe.qrph.dex._2013 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RetrieveMetadataRequest_QNAME = new QName("urn:ihe:qrph:dex:2013", "RetrieveMetadataRequest");
    private final static QName _RetrieveDataElementListRequest_QNAME = new QName("urn:ihe:qrph:dex:2013", "RetrieveDataElementListRequest");
    private final static QName _RetrieveMetadataResponse_QNAME = new QName("urn:ihe:qrph:dex:2013", "RetrieveMetadataResponse");
    private final static QName _RetrieveDataElementListResponse_QNAME = new QName("urn:ihe:qrph:dex:2013", "RetrieveDataElementListResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ihe.qrph.dex._2013
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveDataElementListResponseType }
     * 
     */
    public RetrieveDataElementListResponseType createRetrieveDataElementListResponseType() {
        return new RetrieveDataElementListResponseType();
    }

    /**
     * Create an instance of {@link RetrieveDataElementListRequestType }
     * 
     */
    public RetrieveDataElementListRequestType createRetrieveDataElementListRequestType() {
        return new RetrieveDataElementListRequestType();
    }

    /**
     * Create an instance of {@link RetrieveMetadataResponseType }
     * 
     */
    public RetrieveMetadataResponseType createRetrieveMetadataResponseType() {
        return new RetrieveMetadataResponseType();
    }

    /**
     * Create an instance of {@link RetrieveMetadataRequestType }
     * 
     */
    public RetrieveMetadataRequestType createRetrieveMetadataRequestType() {
        return new RetrieveMetadataRequestType();
    }

    /**
     * Create an instance of {@link ValueSetType }
     * 
     */
    public ValueSetType createValueSetType() {
        return new ValueSetType();
    }

    /**
     * Create an instance of {@link ValueDomainType }
     * 
     */
    public ValueDomainType createValueDomainType() {
        return new ValueDomainType();
    }

    /**
     * Create an instance of {@link DataElementSummaryType }
     * 
     */
    public DataElementSummaryType createDataElementSummaryType() {
        return new DataElementSummaryType();
    }

    /**
     * Create an instance of {@link MappingSpecificationType }
     * 
     */
    public MappingSpecificationType createMappingSpecificationType() {
        return new MappingSpecificationType();
    }

    /**
     * Create an instance of {@link ContentModelType }
     * 
     */
    public ContentModelType createContentModelType() {
        return new ContentModelType();
    }

    /**
     * Create an instance of {@link DataElementType }
     * 
     */
    public DataElementType createDataElementType() {
        return new DataElementType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveMetadataRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:ihe:qrph:dex:2013", name = "RetrieveMetadataRequest")
    public JAXBElement<RetrieveMetadataRequestType> createRetrieveMetadataRequest(RetrieveMetadataRequestType value) {
        return new JAXBElement<RetrieveMetadataRequestType>(_RetrieveMetadataRequest_QNAME, RetrieveMetadataRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveDataElementListRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:ihe:qrph:dex:2013", name = "RetrieveDataElementListRequest")
    public JAXBElement<RetrieveDataElementListRequestType> createRetrieveDataElementListRequest(RetrieveDataElementListRequestType value) {
        return new JAXBElement<RetrieveDataElementListRequestType>(_RetrieveDataElementListRequest_QNAME, RetrieveDataElementListRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveMetadataResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:ihe:qrph:dex:2013", name = "RetrieveMetadataResponse")
    public JAXBElement<RetrieveMetadataResponseType> createRetrieveMetadataResponse(RetrieveMetadataResponseType value) {
        return new JAXBElement<RetrieveMetadataResponseType>(_RetrieveMetadataResponse_QNAME, RetrieveMetadataResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveDataElementListResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:ihe:qrph:dex:2013", name = "RetrieveDataElementListResponse")
    public JAXBElement<RetrieveDataElementListResponseType> createRetrieveDataElementListResponse(RetrieveDataElementListResponseType value) {
        return new JAXBElement<RetrieveDataElementListResponseType>(_RetrieveDataElementListResponse_QNAME, RetrieveDataElementListResponseType.class, null, value);
    }

}
