package tr.com.srdc.mdr.core.api.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;



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
public interface RegistrationAuthority extends Organization {

	@Override
	/**
	 * @return the {@link RegistrationAuthorityResource} version this instance.
	 */
	RegistrationAuthorityResource asMDRResource();

	/**
	 * 
	 * @return
	 */
	RegistrationAuthorityIdentifier getIdentifier();

	/**
	 * 
	 * @return the unmodifiable list of {@link LanguageIdentification}
	 * @throws MDRException
	 */
	List<LanguageIdentification> getLanguageIdentifications()
			throws MDRException;

}
