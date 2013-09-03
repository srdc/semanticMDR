package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * 
 * An Organization shall be identified as the steward responsible for
 * administering each Administered Item. This relationship identifies a
 * stewardship contact for the Administered Item.
 * 
 * @author anil
 * 
 */
public interface StewardshipResource extends MDRResource {

	/**
	 * Sets {@link Vocabulary#stewardshipContact} property with given value
	 * 
	 * @param stewardshipContact
	 *            value of {@link Vocabulary#stewardshipContact} property, <br>
	 */
	void setStewardshipContact(ContactResource stewardshipContact);

	/**
	 * 
	 * @return {@link ContactResource} object, value of
	 *         {@link Vocabulary#stewardshipContact} property, <br>
	 */
	ContactResource getStewardshipContact();

	/**
	 * Adds {@link Vocabulary#administers} property with given value
	 * 
	 * @param administers
	 *            value of {@link Vocabulary#administers} property
	 */
	void addAdministers(StewardshipRelationshipResource administers);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#administers} property values
	 */
	List<StewardshipRelationshipResource> getAdministers() throws MDRException;

	/**
	 * Method to remove administers property of {@link StewardshipResource}
	 * 
	 * @param administers
	 */
	void removeAdministers(StewardshipRelationshipResource administers);

}