package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueMeaningResource;

public interface ValueMeaning extends MDRNode {

	@Override
	ValueMeaningResource asMDRResource();

	/**
	 * @return the description for the semantics of this ValueMeaning
	 */
	String getDescription();

	/**
	 * @return Unique Identifier for this ValueMeaning, to be used for matching
	 *         with {@link PermissibleValue}s
	 */
	String getIdentifier();

	/**
	 * Creates a {@link PermissibleValue} by providing a representation for this
	 * ValueMeaning
	 */
	PermissibleValue createPermissibleValue(String valueItem);

}
