package tr.com.srdc.mdr.web.html.models;

import tr.com.srdc.mdr.core.api.ai.AdministeredItem;

/**
 * @author anil
 * 
 */
public class AdministeredItemModel {

	// Coming from AdministrationRecord
	// TODO: Remaning fields of an AdministeredItem may be required in the
	// future
	private String id;
	private String name;
	private String definition;
	private String registrationStatus;
	private String administrativeStatus;
	private String administrativeNote;
	private String explanatoryComment;
	private String contextID;

	public AdministeredItemModel() {
		super();
	}

	public AdministeredItemModel(AdministeredItem item) {
		super();
		this.id = item.getUniqueID();
		this.name = item.getName();
		this.definition = item.getDefinition();
		this.registrationStatus = item.asMDRResource()
				.getAdministrationRecord().getRegistrationStatus();
		this.administrativeStatus = item.asMDRResource()
				.getAdministrationRecord().getAdministrativeStatus();
		this.administrativeNote = item.asMDRResource()
				.getAdministrationRecord().getAdministrativeNote();
		this.explanatoryComment = item.asMDRResource()
				.getAdministrationRecord().getExplanatoryComment();
		this.contextID = item.getContext().getUniqueID();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public String getAdministrativeStatus() {
		return administrativeStatus;
	}

	public void setAdministrativeStatus(String administrativeStatus) {
		this.administrativeStatus = administrativeStatus;
	}

	public String getAdministrativeNote() {
		return administrativeNote;
	}

	public void setAdministrativeNote(String administrativeNote) {
		this.administrativeNote = administrativeNote;
	}

	public String getExplanatoryComment() {
		return explanatoryComment;
	}

	public void setExplanatoryComment(String explanatoryComment) {
		this.explanatoryComment = explanatoryComment;
	}

	public String getContextID() {
		return contextID;
	}

	public void setContextID(String contextID) {
		this.contextID = contextID;
	}
}
