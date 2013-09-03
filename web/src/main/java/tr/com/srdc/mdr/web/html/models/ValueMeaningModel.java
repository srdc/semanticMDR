package tr.com.srdc.mdr.web.html.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueMeaningResource;


public class ValueMeaningModel {
	private static final Logger logger = LoggerFactory
			.getLogger(ValueMeaningModel.class);
	private String id;
	private String description;
	private String conceptualDomainID;

	public ValueMeaningModel() {
		super();
	}

	public ValueMeaningModel(ValueMeaningResource valueMeaning) {
		super();
		this.id = valueMeaning.getValueMeaningIdentifier();
		this.description = valueMeaning.getValueMeaningDescription();
		try {
			this.conceptualDomainID = valueMeaning
					.getContainedInValueMeaningSets().get(0).getUniqueID();
		} catch (MDRException e) {
			logger.error(
					"Containing Enumerated Conceptual Domain of could not be retrieved",
					id);
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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

}
