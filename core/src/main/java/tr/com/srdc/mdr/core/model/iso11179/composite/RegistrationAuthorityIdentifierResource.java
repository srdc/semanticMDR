package tr.com.srdc.mdr.core.model.iso11179.composite;

import tr.com.srdc.mdr.core.api.composite.RegistrationAuthorityIdentifier;
import tr.com.srdc.mdr.core.impl.composite.RegistrationAuthorityIdentifierImpl;
import tr.com.srdc.mdr.core.model.MDRResource;

/**
 * The composite data type Registration Authority Identifier is used to uniquely
 * identify a Registration Authority.
 * 
 * @author anil
 * 
 */
public interface RegistrationAuthorityIdentifierResource extends MDRResource,
		RegistrationAuthorityIdentifier {

	/**
	 * Set the International Code Designator of
	 * {@link RegistrationAuthorityIdentifierImpl}.
	 * 
	 * @param internationalCodeDesignator
	 * <br>
	 *            The International Code Designator of
	 *            {@link RegistrationAuthorityIdentifierImpl}.
	 */
	void setInternationalCodeDesignator(String internationalCodeDesignator);

	/**
	 * 
	 * @return the International Code Designator of
	 *         {@link RegistrationAuthorityIdentifierImpl}.
	 */
	String getInternationalCodeDesignator();

	/**
	 * Set the Organization Identifier of
	 * {@link RegistrationAuthorityIdentifierImpl}.
	 * 
	 * @param organizationIdentifier
	 * <br>
	 *            The Organization Identifier of
	 *            {@link RegistrationAuthorityIdentifierImpl}.
	 */
	void setOrganizationIdentifier(String organizationIdentifier);

	/**
	 * 
	 * @return the organization identifier of
	 *         {@link RegistrationAuthorityIdentifierImpl}.
	 */
	String getOrganizationIdentifier();

	/**
	 * Set Organization Part Indentifier and Opi Source of
	 * {@link RegistrationAuthorityIdentifierImpl}. Opi source must be set when
	 * Organization Part Identifier is specified.
	 * 
	 * @param organizationPartIdentifier
	 * <br>
	 *            The Organization Part Identifier of
	 *            {@link RegistrationAuthorityIdentifierImpl}.
	 * @param opiSource
	 * <br>
	 *            The opiSource of {@link RegistrationAuthorityIdentifierImpl}.
	 */
	void setOrganizationPartIdentifier(String organizationPartIdentifier,
			String opiSource);

	/**
	 * 
	 * @return The Organization Part Identifier of
	 *         {@link RegistrationAuthorityIdentifierImpl}.
	 */
	String getOrganizationPartIdentifier();

	/**
	 * 
	 * @return The Opi Source of {@link RegistrationAuthorityIdentifierImpl}.
	 */
	String getOpiSource();

}