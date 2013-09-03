package tr.com.srdc.mdr.core.model.iso11179.composite;

import tr.com.srdc.mdr.core.api.composite.Contact;
import tr.com.srdc.mdr.core.impl.composite.ContactImpl;
import tr.com.srdc.mdr.core.model.MDRResource;

/**
 * The composite data type Contact is used to specify the contact information
 * for registrar contact, stewardship contact and submission contact.
 * 
 * @author anil
 * 
 */
public interface ContactResource extends MDRResource, Contact {

	/**
	 * Set the contact information of {@link ContactImpl}.
	 * 
	 * @param contactInformation
	 * <br>
	 *            The contact information of a {@link ContactImpl}.
	 */
	void setContactInformation(String contactInformation);

	/**
	 * @return the contact information of a {@link ContactImpl}.<br>
	 * 
	 */
	String getContactInformation();

	/**
	 * Set the contact name of this {@link ContactImpl}.
	 * 
	 * @param contactName
	 * <br>
	 *            The contact information of a {@link ContactImpl}.
	 */
	void setContactName(String contactName);

	/**
	 * @return the contact name of a {@link ContactImpl}.<br>
	 * 
	 */
	String getContactName();

	/**
	 * Set the contact title of this {@link ContactImpl}.
	 * 
	 * @param contactInformation
	 * <br>
	 *            The contact title of a {@link ContactImpl}.
	 */
	void setContactTitle(String contactTitle);

	/**
	 * @return the contact title of a {@link ContactImpl}.<br>
	 * 
	 */
	String getContactTitle();

}