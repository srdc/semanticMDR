package tr.com.srdc.mdr.core.model.iso11179;

import tr.com.srdc.mdr.core.api.ai.Property;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;

/**
 * 
 * A Property is a characteristic common to all members of an Object Class. It
 * may be any feature that humans naturally use to distinguish one individual
 * object from another. It is the human perception of a single characteristic of
 * an Object Class in the real world. It is conceptual and thus has no
 * particular associated means of representation by which the Property can be
 * communicated.
 * 
 * @author mert
 * 
 */
public interface PropertyResource extends AdministeredItemResource, Property {

	/**
	 * @return the {@link AdministrationRecordResource} of a {@link PropertyResource}.
	 * 
	 */
	AdministrationRecordResource getAdministrationRecord();

	/**
	 * Set the Administration Record of {@link PropertyResource}.
	 * 
	 * @param propertyAdministrationRecord
	 * <br>
	 *            An {@link AdministrationRecordResource} of a {@link PropertyResource}.
	 */
	void setAdministrationRecord(
			AdministrationRecordResource propertyAdministrationRecord);

}