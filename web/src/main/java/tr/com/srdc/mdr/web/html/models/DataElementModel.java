package tr.com.srdc.mdr.web.html.models;

import java.util.ArrayList;
import java.util.List;

import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.composite.ClassificationSchemeItem;


public class DataElementModel extends AdministeredItemModel {

	private String dataElementConceptName;
	private String dataElementConceptID;
	private String valueDomainName;
	private String valueDomainID;
	private List<ExtractionSpecificationModel> extractionSpecs;
	private List<MappingModel> mappings; 

	public DataElementModel() {
		super();
	}

	public DataElementModel(DataElement dataElement) {
		super(dataElement);

		this.dataElementConceptName = dataElement.getDataElementConcept()
				.getName();
		this.dataElementConceptID = dataElement.getDataElementConcept()
				.getUniqueID();

		this.valueDomainName = dataElement.getValueDomain().getName();
		this.valueDomainID = dataElement.getValueDomain().getUniqueID();
		this.extractionSpecs = new ArrayList<ExtractionSpecificationModel>();
		this.mappings = new ArrayList<MappingModel>();
		List<ClassificationSchemeItem> extractionSpecs = dataElement
				.getExtractionSpecifications();
		if (extractionSpecs != null) {
			for (ClassificationSchemeItem item : extractionSpecs) {
				this.extractionSpecs
						.add(new ExtractionSpecificationModel(item));
			}
		}
		List<ClassificationSchemeItem> mappings = dataElement.getMappings();
		if (mappings != null) {
			for (ClassificationSchemeItem item : mappings) {
				this.mappings.add(new MappingModel(item));
			}
		}
	}

	/**
	 * @return the dataElementConceptName
	 */
	public String getDataElementConceptName() {
		return dataElementConceptName;
	}

	/**
	 * @param dataElementConceptName
	 *            the dataElementConceptName to set
	 */
	public void setDataElementConceptName(String dataElementConceptName) {
		this.dataElementConceptName = dataElementConceptName;
	}

	/**
	 * @return the dataElementConceptID
	 */
	public String getDataElementConceptID() {
		return dataElementConceptID;
	}

	/**
	 * @param dataElementConceptID
	 *            the dataElementConceptID to set
	 */
	public void setDataElementConceptID(String dataElementConceptID) {
		this.dataElementConceptID = dataElementConceptID;
	}

	/**
	 * @return the valueDomainName
	 */
	public String getValueDomainName() {
		return valueDomainName;
	}

	/**
	 * @param valueDomainName
	 *            the valueDomainName to set
	 */
	public void setValueDomainName(String valueDomainName) {
		this.valueDomainName = valueDomainName;
	}

	/**
	 * @return the valueDomainID
	 */
	public String getValueDomainID() {
		return valueDomainID;
	}

	/**
	 * @param valueDomainID
	 *            the valueDomainID to set
	 */
	public void setValueDomainID(String valueDomainID) {
		this.valueDomainID = valueDomainID;
	}

	/**
	 * 
	 * @return the extraction specificiations
	 */
	public List<ExtractionSpecificationModel> getExtractionSpecs() {
		return extractionSpecs;
	}

	/**
	 * 
	 * @param extractionSpecs
	 *            the extraction specifications to set
	 */
	public void setExtractionSpecs(
			List<ExtractionSpecificationModel> extractionSpecs) {
		this.extractionSpecs = extractionSpecs;
	}

	/**
	 * 
	 * @return the mappings on other term systems
	 */
	public List<MappingModel> getMappings() {
		return mappings;
	}

	/**
	 * 
	 * @param mappings
	 *            the mapping list to set
	 */
	public void setMappings(List<MappingModel> mappings) {
		this.mappings = mappings;
	}
}
