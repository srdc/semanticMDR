package tr.com.srdc.mdr.web.html.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import tr.com.srdc.mdr.core.api.composite.ClassificationSchemeItem;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MappingModel {
	private String matchType;
	private String termName;
	private String termSystem;
	private String termSystemOID;
	private String termUUID;

	public MappingModel() {
		super();
	}

	public MappingModel(ClassificationSchemeItem csi) {
		setMatchType(csi.getTypeName());
		setTermUUID(csi.getItemValue());
		setTermSystem(csi.getClassificationScheme().getName());
		setTermSystemOID(csi.getClassificationScheme().getUniqueID());
		Repository repository = RepositoryManager.getInstance().getRepository();
		setTermName(repository.getDataElement(termUUID).getName());
		
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String type) {
		this.matchType = type;
	}

	public String getTermUUID() {
		return termUUID;
	}

	public void setTermUUID(String termUUID) {
		this.termUUID = termUUID;
	}

	public String getTermSystem() {
		return termSystem;
	}

	public void setTermSystem(String termSystem) {
		this.termSystem = termSystem;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getTermSystemOID() {
		return termSystemOID;
	}

	public void setTermSystemOID(String termSystemOID) {
		this.termSystemOID = termSystemOID;
	}

}