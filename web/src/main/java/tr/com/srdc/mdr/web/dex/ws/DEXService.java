package tr.com.srdc.mdr.web.dex.ws;

import ihe.qrph.dex._2013.ContentModelType;
import ihe.qrph.dex._2013.DataElementExchangePortType;
import ihe.qrph.dex._2013.DataElementType;
import ihe.qrph.dex._2013.MappingSpecificationType;
import ihe.qrph.dex._2013.RetrieveDataElementListRequestType;
import ihe.qrph.dex._2013.RetrieveDataElementListResponseType;
import ihe.qrph.dex._2013.RetrieveMetadataRequestType;
import ihe.qrph.dex._2013.RetrieveMetadataResponseType;
import ihe.qrph.dex._2013.ValueDomainType;
import ihe.qrph.dex._2013.ValueSetType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import tr.com.srdc.mdr.core.api.ai.ClassificationScheme;
import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.ai.DataElementConcept;
import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.api.composite.ClassificationSchemeItem;
import tr.com.srdc.mdr.core.api.composite.UnitOfMeasure;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;

public class DEXService implements DataElementExchangePortType {

	@Override
	@WebResult(name = "RetrieveMetadataResponse", targetNamespace = "urn:ihe:qrph:dex:2013", partName = "body")
	@WebMethod(operationName = "RetrieveMetadata", action = "urn:ihe:qrph:dex:2013:RetrieveMetadata")
	public RetrieveMetadataResponseType retrieveMetadata(
			@WebParam(partName = "body", name = "RetrieveMetadataRequest", targetNamespace = "urn:ihe:qrph:dex:2013") RetrieveMetadataRequestType body) {

		String id = body.getId();
		String regAuth = body.getRegistrationAuthority();
		String version = body.getVersion();

		DataElementType dataElement = new DataElementType();
		Repository repository = RepositoryManager.getInstance().getRepository();
		DataElement de = repository.getDataElement(id, regAuth, version);
		if (de == null) {
			dataElement = null;
		} else {
			DataElementConcept dec = de.getDataElementConcept();
			AdministrationRecordResource arr = de.asMDRResource()
					.getAdministrationRecord();
			RegistrationAuthorityResource rar = de.asMDRResource()
					.getRegisteredBy();

			dataElement.setId(de.getUniqueID());
			dataElement.setRegistrationAuthority(rar.getRegistrationAuthorityIdentifier().getOrganizationIdentifier());
			
			dataElement.setContextualDomain(de.getContext().getName());
			dataElement.setCreationDate(toXMLCal(arr.getCreationDate()));
			dataElement.setDefinition(de.getDefinition());
			dataElement.setDisplayName(de.getName());
			dataElement.setEffectiveDate(toXMLCal(arr.getEffectiveDate()));
			dataElement.setExpirationDate(toXMLCal(arr.getUntilDate()));
			
			dataElement.setObjectClass(dec.getObjectClass().getName());
			dataElement.setProperty(dec.getProperty().getName());
			
			dataElement.setRevisionDate(toXMLCal(arr.getLastChangeDate()));
			dataElement.setRevisionNote(arr.getChangeDescription());
			dataElement.setValueDomain(getValueDomain(de));
			dataElement.setVersion(arr.getVersionInfo());
			dataElement.getMappingSpecification().addAll(getMappings(de));
		}

		RetrieveMetadataResponseType response = new RetrieveMetadataResponseType();
		response.setDataElement(dataElement);
		return response;
	}

	private ValueDomainType getValueDomain(DataElement de) {
		ValueDomain vd = de.getValueDomain();
		ConceptualDomain cd = vd.getConceptualDomain();

		ValueSetType valueSet = null;
		if (cd.isEnumeratedConceptualDomain()) {
			valueSet = new ValueSetType();
			valueSet.setDisplayName(cd.getName());
			valueSet.setId(cd.getUniqueID());
			valueSet.setVersion(cd.asMDRResource().getAdministrationRecord()
					.getVersionInfo());
		}

		ValueDomainType valueDomain = new ValueDomainType();
		valueDomain.setDataType(vd.getDatatype().getName());
		UnitOfMeasure unit = vd.getUnitOfMeasure();
		if (unit != null) {
			valueDomain.setUnitOfMeasure(unit.getName());
		}
		valueDomain.setValueSet(valueSet);

		return valueDomain;
	}

	private List<MappingSpecificationType> getMappings(DataElement de) {
		List<ClassificationSchemeItem> specs = de.getExtractionSpecifications();
		List<MappingSpecificationType> mappings = new ArrayList<MappingSpecificationType>();
		for (ClassificationSchemeItem csi : specs) {
			ClassificationScheme cs = csi.getClassificationScheme();
			ContentModelType contentModel = new ContentModelType();
			contentModel.setId(cs.getUniqueID());
			contentModel.setName(cs.getName());

			MappingSpecificationType mapping = new MappingSpecificationType();
			mapping.setContentModel(contentModel);
			mapping.setMappingScript(csi.getItemValue());
			mapping.setType(csi.getTypeName());
			mappings.add(mapping);
		}
		return mappings;
	}

	private XMLGregorianCalendar toXMLCal(Calendar cal) {
		if (cal == null) {
			return null;
		}

		DatatypeFactory dtf = null;
		try {
			dtf = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			return null;
		}

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(cal.getTimeInMillis());
		return dtf.newXMLGregorianCalendar(gc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ihe.qrph.dex._2013.DataElementExchangePortType#retrieveDataElementList
	 * (ihe.qrph.dex._2013.RetrieveDataElementListRequestType)
	 */
	@Override
	@WebResult(name = "RetrieveDataElementListResponse", targetNamespace = "urn:ihe:qrph:dex:2013", partName = "body")
	@WebMethod(operationName = "RetrieveDataElementList", action = "urn:ihe:qrph:dex:2013:RetrieveDataElementList")
	public RetrieveDataElementListResponseType retrieveDataElementList(
			@WebParam(partName = "body", name = "RetrieveDataElementListRequest", targetNamespace = "urn:ihe:qrph:dex:2013") RetrieveDataElementListRequestType body) {
		// TODO Auto-generated method stub
		return null;
	}

}
