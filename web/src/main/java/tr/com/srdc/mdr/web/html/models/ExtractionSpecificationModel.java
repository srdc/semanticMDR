package tr.com.srdc.mdr.web.html.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import tr.com.srdc.mdr.core.MDRConstants;
import tr.com.srdc.mdr.core.api.composite.ClassificationSchemeItem;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractionSpecificationModel {
	private String type; 
	private String modelOID;
	private String modelName;
	private String value;

	public ExtractionSpecificationModel() {
		super();
	}

	public ExtractionSpecificationModel(ClassificationSchemeItem csi) {
		String oid = csi.getClassificationScheme().getUniqueID();
		this.setModelOID(oid);
		this.setValue(csi.getItemValue());
		this.setType(csi.getTypeName());
		this.setModelName(MDRConstants.getContentModelFromOID(oid));
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String name) {
		this.value = name;
	}

	public String getModelOID() {
		return modelOID;
	}

	public void setModelOID(String modelOID) {
		this.modelOID = modelOID;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
}
