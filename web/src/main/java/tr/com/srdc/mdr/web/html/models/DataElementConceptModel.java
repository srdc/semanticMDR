package tr.com.srdc.mdr.web.html.models;

import tr.com.srdc.mdr.core.model.iso11179.DataElementConceptResource;

public class DataElementConceptModel extends AdministeredItemModel {

	private String propertyID;
	private String objectClassID;
	private String objectClassName;
	private String propertyName;
	private String objectClassQualifier;
	private String propertyQualifier;
	private String conceptualDomainID;
	private String propertyDefinition;

	// TODO objectClassDefinition could be included into DataElementConceptModel
	// so that we can manage all update operations from single class
	public DataElementConceptModel() {
		super();
	}

	public DataElementConceptModel(DataElementConceptResource item) {
		super(item);
		this.propertyID = item.getDataElementConceptProperty().getUniqueID();
		this.objectClassID = item.getDataElementConceptObjectClass()
				.getUniqueID();
		this.objectClassName = item.getDataElementConceptObjectClass()
				.getName();
		this.propertyName = item.getDataElementConceptProperty().getName();
		this.objectClassQualifier = item.getObjectClassQualifier();
		this.propertyQualifier = item.getPropertyQualifier();
		this.conceptualDomainID = item
				.getHavingDataElementConceptConceptualDomainRelationship()
				.getUniqueID();
		this.propertyDefinition = item.getDataElementConceptProperty()
				.getDefinition();

	}

	/**
	 * @return the propertyID
	 */
	public String getPropertyID() {
		return propertyID;
	}

	/**
	 * @param propertyID
	 *            the propertyID to set
	 */
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}

	/**
	 * @return the objectClassID
	 */
	public String getObjectClassID() {
		return objectClassID;
	}

	/**
	 * @param objectClassID
	 *            the objectClassID to set
	 */
	public void setObjectClassID(String objectClassID) {
		this.objectClassID = objectClassID;
	}

	/**
	 * @return the objectClassName
	 */
	public String getObjectClassName() {
		return objectClassName;
	}

	/**
	 * @param objectClassName
	 *            the objectClassName to set
	 */
	public void setObjectClassName(String objectClassName) {
		this.objectClassName = objectClassName;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @param propertyName
	 *            the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * @return the objectClassQualifier
	 */
	public String getObjectClassQualifier() {
		return objectClassQualifier;
	}

	/**
	 * @param objectClassQualifier
	 *            the objectClassQualifier to set
	 */
	public void setObjectClassQualifier(String objectClassQualifier) {
		this.objectClassQualifier = objectClassQualifier;
	}

	/**
	 * @return the propertyQualifier
	 */
	public String getPropertyQualifier() {
		return propertyQualifier;
	}

	/**
	 * @param propertyQualifier
	 *            the propertyQualifier to set
	 */
	public void setPropertyQualifier(String propertyQualifier) {
		this.propertyQualifier = propertyQualifier;
	}

	/**
	 * @return the conceptualDomainID
	 */
	public String getConceptualDomainID() {
		return conceptualDomainID;
	}

	/**
	 * @param conceptualDomainID
	 *            the conceptualDomainID to set
	 */
	public void setConceptualDomainID(String conceptualDomainID) {
		this.conceptualDomainID = conceptualDomainID;
	}

	/**
	 * @return the propertyDefinition
	 */
	public String getPropertyDefinition() {
		return propertyDefinition;
	}

	/**
	 * @param propertyDefinition
	 *            the propertyDefinition to set
	 */
	public void setPropertyDefinition(String propertyDefinition) {
		this.propertyDefinition = propertyDefinition;
	}

}
