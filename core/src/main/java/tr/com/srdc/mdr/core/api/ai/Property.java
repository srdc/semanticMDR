package tr.com.srdc.mdr.core.api.ai;

import tr.com.srdc.mdr.core.model.iso11179.PropertyResource;


/**
 * A Property is a characteristic common to all members of an
 * {@link ObjectClass}. It may be any feature that humans naturally use to
 * distinguish one individual object from another. It is the human perception of
 * a single characteristic of an Object Class in the real world. It is
 * conceptual and thus has no particular associated means of representation by
 * which the Property can be communicated.<br>
 * As an {@link AdministeredItem}, a Property carries its own Administration
 * Record information, allowing it to be identified, named, defined and
 * optionally classified within a {@link ClassificationScheme}. A Property may
 * be registered as an Administered Item without necessarily being associated
 * with a {@link DataElementConcept} or, through the latter, an
 * {@link ObjectClass}.
 * 
 * @author anil
 * 
 */

public interface Property extends AdministeredItem {

	@Override
	/**
	 * @return the {@link PropertyResource} version this instance.
	 */
	PropertyResource asMDRResource();

}
