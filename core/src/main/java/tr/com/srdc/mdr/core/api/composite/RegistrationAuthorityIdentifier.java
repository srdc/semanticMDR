package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityIdentifierResource;

public interface RegistrationAuthorityIdentifier extends MDRNode {
	// TODO : Setter getter for the attributes of Registration Authority
	// Identifier

	@Override
	/**
	 * @return the {@link RegistrationAuthorityIdentifierResource} version this instance.
	 */
	RegistrationAuthorityIdentifierResource asMDRResource();

}
