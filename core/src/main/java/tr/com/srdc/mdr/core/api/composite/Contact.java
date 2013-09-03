package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;

/**
 * The composite datatype Contact is used to specify the contact information for
 * registrar contact, stewardship contact and submission contact.
 * 
 * @author anil
 * 
 */
public interface Contact extends MDRNode {

	@Override
	/**
	 * @return the {@link ContactResource} version this instance.
	 */
	ContactResource asMDRResource();

	/**
	 * @return the name of the contact
	 */
	String getName();

	/**
	 * 
	 * @return the title of a {@link Contact}. If the attribute does not exist
	 *         return <code>null</code>
	 */
	String getTitle();

}
