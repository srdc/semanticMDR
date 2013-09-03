package tr.com.srdc.mdr.web.html.models;

import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;

public class ConceptualDomainModel extends AdministeredItemModel {

	private boolean enumerated;

	private String dimensionality;

	public ConceptualDomainModel() {
		super();
		this.enumerated = false;
	}

	public ConceptualDomainModel(ConceptualDomain conceptualDomain,
			boolean enumerated) {
		super(conceptualDomain);
		this.enumerated = enumerated;
		this.dimensionality = conceptualDomain.getDimensionality();
	}

	public boolean isEnumerated() {
		return enumerated;
	}

	public void setEnumerated(boolean enumerated) {
		this.enumerated = enumerated;
	}

	public String getDimensionality() {
		return dimensionality;
	}

	public void setDimensionality(String dimensionality) {
		this.dimensionality = dimensionality;
	}

}
