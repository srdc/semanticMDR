package tr.com.srdc.mdr.web.html.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import tr.com.srdc.mdr.core.api.ai.ObjectClass;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectClassModel extends AdministeredItemModel {

	private String parentConceptID;

	public ObjectClassModel() {
		super();
	}

	public ObjectClassModel(ObjectClass objectClass) {
		super(objectClass);

		if (objectClass.getParentConcept() != null) {
			this.parentConceptID = objectClass.getParentConcept().getUniqueID();
		}
	}

	/**
	 * 
	 * @return the parentConceptID
	 */
	public String getParentConceptID() {
		return parentConceptID;
	}

	/**
	 * 
	 * @param parentConceptID
	 *            the parentConceptID to set
	 */
	public void setParentConceptID(String parentConceptID) {
		this.parentConceptID = parentConceptID;
	}

}
