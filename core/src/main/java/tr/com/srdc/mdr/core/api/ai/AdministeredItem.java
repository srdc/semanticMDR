package tr.com.srdc.mdr.core.api.ai;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.api.composite.Contact;
import tr.com.srdc.mdr.core.api.composite.Organization;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;

/**
 * @author anil
 * 
 */
public interface AdministeredItem extends MDRNode {

	@Override
	/**
	 * @return the {@link AdministeredItemResource} version this instance.
	 */
	AdministeredItemResource asMDRResource();

	/**
	 * @return The unique identifier of this {@link AdministeredItem}.
	 */
	String getUniqueID();

	/**
	 * @return The preferred name of the AdministeredItem in a {@link Context}
	 *         where its registered
	 */
	String getName();

	/**
	 * Sets the name of this {@link AdministeredItem}. Since there is only one
	 * name, this methods updates that name.
	 * 
	 * @param definition
	 *            New name
	 */
	void setName(String name);

	/**
	 * @return Definition of the AdministeredItem in a {@link Context} where its
	 *         registered
	 */
	String getDefinition();

	/**
	 * Sets the definition of this {@link AdministeredItem}. Since there is only
	 * one definition, this methods updates that name.
	 * 
	 * @param definition
	 *            New definition
	 */
	void setDefinition(String definition);

	/**
	 * Retrieves the {@link Context} to which this {@link AdministeredItem}
	 * belongs.
	 * 
	 * @return {@link Context} where this item is registered in.
	 */
	Context getContext();

	/**
	 * Retrieves the {@link Organization} which is the administer of this
	 * {@link AdministeredItem} on the {@link Repository}
	 * 
	 * @return {@link Organization} responsible from administration of
	 *         AdministeredItem
	 */
	Organization getAdministerOrganization();

	/**
	 * Retrieves the contact person who is the administer of this
	 * AdministeredItem on {@link Repository}
	 * 
	 * @return {@link Contact} person who is responsbile from administration of
	 *         this AdministeredItem within an Organization
	 */
	Contact getAdministerPerson();

}
