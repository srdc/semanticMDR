package tr.com.srdc.mdr.core.model.iso11179.composite;

import tr.com.srdc.mdr.core.api.composite.Registrar;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;

/**
 * 
 * A Registration Authority is represented by one or more Registrars. Registrars
 * are the persons who perform the administrative steps to register Administered
 * Items in a Metadata Registry.
 * 
 * @author anil
 * 
 */
public interface RegistrarResource extends MDRResource, Registrar {

	/**
	 * Sets {@link Vocabulary#registrarIdentifier} property with given value
	 * 
	 * @param registrarIdentifier
	 *            value of {@link Vocabulary#registrarIdentifier} property <br>
	 */
	void setRegistrarIdentifier(String registrarIdentifier);

	/**
	 * 
	 * @return {@link String} value of {@link Vocabulary#registrarIdentifier}
	 *         property, <br>
	 */
	String getRegistrarIdentifier();

	/**
	 * Sets {@link Vocabulary#registrarContact} property with given value
	 * 
	 * @param registrarContact
	 *            value of {@link Vocabulary#registrarContact} property <br>
	 */
	void setRegistrarContact(ContactResource registrarContact);

	/**
	 * 
	 * @return {@link ContactResource} object, value of
	 *         {@link Vocabulary#registrarContact} property, <br>
	 */
	ContactResource getRegistrarContact();

	/**
	 * 
	 * @return
	 */
	RegistrationAuthorityResource getRepresent();

	/**
	 * 
	 * @param registrationAuthority
	 */
	void setRepresent(RegistrationAuthorityResource registrationAuthority);

}