package tr.com.srdc.mdr.web.html.models;

import tr.com.srdc.mdr.core.api.composite.PermissibleValue;

public class PermissibleValueModel {

	private ValueMeaningModel valueMeaning;
	private String valueItem;

	public PermissibleValueModel() {
	}

	public PermissibleValueModel(PermissibleValue pv) {
		super();
		this.valueItem = pv.getValueItem();
		this.valueMeaning = new ValueMeaningModel(pv.asMDRResource()
				.getHasPermissibleValueMeaning());
	}

	public ValueMeaningModel getValueMeaning() {
		return this.valueMeaning;
	}

	public void setValueMeaning(ValueMeaningModel valueMeaning) {
		this.valueMeaning = valueMeaning;
	}

	public String getValueItem() {
		return valueItem;
	}

	public void setValueItem(String valueItem) {
		this.valueItem = valueItem;
	}

}
