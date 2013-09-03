package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrarResource;

/**
 * 
 * A Registration Authority is represented by one or more Registrars. Registrars
 * are the persons who perform the administrative steps to register Administered
 * Items in a Metadata Registry.
 * 
 * @author anil
 * 
 */
public interface Registrar extends MDRNode {

	@Override
	/**
	 * @return the {@link RegistrarResource} version this instance.
	 */
	RegistrarResource asMDRResource();

	/**
	 * 
	 * @return the {@link RegistrationAuthority} represented by this
	 *         {@link Registrar}
	 */
	RegistrationAuthority getRegistrationAuthority();

	Contact getContact();

	String getIdentifier();

}
