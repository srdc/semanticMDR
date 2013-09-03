package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.RegistrationAuthority;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;



/**
 * A Registration Authority is any Organization authorized to register metadata.
 * A Registration Authority is a subtype of Organization and inherits all of its
 * attributes and relationships. An Administered Item has a Registration
 * Authority that is its owner. A Registration Authority may register many
 * Administered Items.
 * 
 * @author anil
 * 
 */
public interface RegistrationAuthorityResource extends OrganizationResource,
		RegistrationAuthority {

	/**
	 * Sets {@link Vocabulary#registrationAuthorityIdentifier} property with
	 * given value
	 * 
	 * @param registrationAuthorityIdentifier
	 *            value of {@link Vocabulary#registrationAuthorityIdentifier}
	 *            property, <br>
	 */
	void setRegistrationAuthorityIdentifier(
			RegistrationAuthorityIdentifierResource registrationAuthorityIdentifier);

	/**
	 * 
	 * @return {@link RegistrationAuthorityIdentifierResource} object, value of
	 *         {@link Vocabulary#registrationAuthorityIdentifier} property <br>
	 */
	RegistrationAuthorityIdentifierResource getRegistrationAuthorityIdentifier();

	/**
	 * Adds {@link Vocabulary#documentationLanguageIdentifier} property with
	 * given value
	 * 
	 * @param documentationLanguageIdentifier
	 *            value of {@link Vocabulary#documentationLanguageIdentifier}
	 *            property There should be at least 1 value for the property,
	 *            throws IllegalArgumentException when try to remove all values
	 */
	void addDocumentationLanguageIdentifier(
			LanguageIdentificationResource documentationLanguageIdentifier);

	/**
	 * Removes the {@link Vocabulary#documentationLanguageIdentifier} property
	 * with given value
	 * 
	 * @param documentationLanguageIdentifier
	 *            value of {@link Vocabulary#documentationLanguageIdentifier}
	 *            property to be removed. There should be at least 1 value for
	 *            the property, throws IllegalArgumentException when try to
	 *            remove all values
	 */
	void removeDocumentationLanguageIdentifier(
			LanguageIdentificationResource documentationLanguageIdentifier);

	/**
	 * 
	 * @return {@link List} of
	 *         {@link Vocabulary#documentationLanguageIdentifier} property
	 *         values
	 */
	List<LanguageIdentificationResource> getDocumentationLanguageIdentifiers()
			throws MDRException;

	/**
	 * Adds {@link Vocabulary#registering} property with given value
	 * 
	 * @param registering
	 *            value of {@link Vocabulary#registering} property.
	 */
	void addRegistering(AdministeredItemResource registering);

	/**
	 * Removes the {@link Vocabulary#registering} property with given value
	 * 
	 * @param registering
	 *            value of {@link Vocabulary#registering} property to be
	 *            removed.
	 */
	void removeRegistering(AdministeredItemResource registering);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#registering} property values
	 */
	List<AdministeredItemResource> getRegistering() throws MDRException;

	/**
	 * Adds {@link Vocabulary#representedBy} property with given value
	 * 
	 * @param representedBy
	 *            value of {@link Vocabulary#representedBy} property There
	 *            should be at least 1 value for the property, throws
	 *            IllegalArgumentException when try to remove all values
	 */
	void addRepresentedBy(RegistrarResource representedBy);

	/**
	 * Removes the {@link Vocabulary#representedBy} property with given value
	 * 
	 * @param representedBy
	 *            value of {@link Vocabulary#representedBy} property to be
	 *            removed. There should be at least 1 value for the property,
	 *            throws IllegalArgumentException when try to remove all values
	 */
	void removeRepresentedBy(RegistrarResource representedBy);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#representedBy} property values
	 */
	List<RegistrarResource> getRepresentedBy() throws MDRException;

}