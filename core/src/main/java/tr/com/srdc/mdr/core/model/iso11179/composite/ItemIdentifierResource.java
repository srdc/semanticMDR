package tr.com.srdc.mdr.core.model.iso11179.composite;

import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;

/**
 * The composite data type Item Identifier is used to specify the unique
 * identifier for an Administered Item.
 * 
 * @author anil
 * 
 */
public interface ItemIdentifierResource extends MDRResource {

	/**
	 * Method for setting the {@link RegistrationAuthorityIdentifierResource} for
	 * {@link ItemIdentifierResource}.
	 * 
	 * @param itemRegistrationAuthorityIdentifier
	 */
	void setItemRegistrationAuthorityIdentifier(
			RegistrationAuthorityIdentifierResource itemRegistrationAuthorityIdentifier);

	/**
	 * Method for getting the {@link RegistrationAuthorityIdentifierResource} of
	 * {@link ItemIdentifierResource}.
	 * 
	 * @return {@link RegistrationAuthorityIdentifierResource}
	 */
	RegistrationAuthorityIdentifierResource getItemRegistrationAuthorityIdentifier();

	/**
	 * Method for setting the {@link Vocabulary#dataIdentifier} for
	 * {@link ItemIdentifierResource}.
	 * 
	 * @param dataIdentifier
	 */
	void setDataIdentifier(String dataIdentifier);

	/**
	 * Method for getting the {@link Vocabulary#dataIdentifier} of
	 * {@link ItemIdentifierResource}.
	 * 
	 * @return {@link Vocabulary#dataIdentifier}
	 */
	String getDataIdentifier();

	/**
	 * Method for setting the {@link Vocabulary#version} for
	 * {@link ItemIdentifierResource}.
	 * 
	 * @param version
	 */
	void setVersion(String version);

	/**
	 * Method for getting the {@link Vocabulary#version} of
	 * {@link ItemIdentifierResource}.
	 * 
	 * @return {@link Vocabulary#version}
	 */
	String getVersion();

}