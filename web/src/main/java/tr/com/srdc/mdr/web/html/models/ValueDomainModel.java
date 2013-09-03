package tr.com.srdc.mdr.web.html.models;

import java.util.ArrayList;
import java.util.List;

import tr.com.srdc.mdr.core.api.ai.EnumeratedValueDomain;
import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.api.composite.PermissibleValue;


public class ValueDomainModel extends AdministeredItemModel {

	private DataTypeModel dataType;
	private String conceptualDomainID;
	private boolean enumerated;
	private List<PermissibleValueModel> permissibleValues;

	public ValueDomainModel() {
		super();
	}

	public ValueDomainModel(ValueDomain valueDomain, boolean enumerated) {
		super(valueDomain);
		this.dataType = new DataTypeModel(valueDomain.getDatatype());
		this.conceptualDomainID = valueDomain.getConceptualDomain()
				.getUniqueID();
		this.enumerated = enumerated;
		if (enumerated) {
			List<PermissibleValue> pvList = ((EnumeratedValueDomain) valueDomain)
					.getPermissibleValues();
			List<PermissibleValueModel> pvModelList = new ArrayList<PermissibleValueModel>();
			for (PermissibleValue pv : pvList) {
				pvModelList.add(new PermissibleValueModel(pv));
			}
			this.permissibleValues = pvModelList;
		}
	}

	public String getConceptualDomainID() {
		return conceptualDomainID;
	}

	public void setConceptualDomainID(String conceptualDomainID) {
		this.conceptualDomainID = conceptualDomainID;
	}

	public DataTypeModel getDataType() {
		return dataType;
	}

	public void setDataType(DataTypeModel dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the enumerated
	 */
	public boolean isEnumerated() {
		return enumerated;
	}

	/**
	 * @param enumerated the enumerated to set
	 */
	public void setEnumerated(boolean enumerated) {
		this.enumerated = enumerated;
	}

	/**
	 * @return the permissibleValues
	 */
	public List<PermissibleValueModel> getPermissibleValues() {
		return permissibleValues;
	}

	/**
	 * @param permissibleValues the permissibleValues to set
	 */
	public void setPermissibleValues(List<PermissibleValueModel> permissibleValues) {
		this.permissibleValues = permissibleValues;
	}
}
