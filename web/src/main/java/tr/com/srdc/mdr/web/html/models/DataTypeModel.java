package tr.com.srdc.mdr.web.html.models;

import tr.com.srdc.mdr.core.api.composite.Datatype;

public class DataTypeModel {

	private String datatypeName;
	private String schemeReference;

	public DataTypeModel() {
	}

	public DataTypeModel(Datatype datatype) {
		super();
		this.datatypeName = datatype.getName();
		this.schemeReference = datatype.getSchemeReference();
	}

	public String getDatatypeName() {
		return datatypeName;
	}

	public void setDatatypeName(String datatypeName) {
		this.datatypeName = datatypeName;
	}

	public String getSchemeReference() {
		return schemeReference;
	}

	public void setSchemeReference(String schemeReference) {
		this.schemeReference = schemeReference;
	}

}
