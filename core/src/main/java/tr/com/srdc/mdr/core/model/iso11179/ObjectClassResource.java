package tr.com.srdc.mdr.core.model.iso11179;

import tr.com.srdc.mdr.core.api.ai.ObjectClass;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;

/**
 * An Object Class is a set of ideas, abstractions, or things in the real world
 * that can be identified with explicit boundaries and meaning and whose
 * properties and behavior follow the same rules. It may be either a single or a
 * group of associated concepts, abstractions, or things. An Object Class may be
 * a single unit of thought (i.e., Concept) or a set of Concepts in a
 * relationship with each other to form a more complex concept (i.e., Concept
 * Relationship). A Concept and a Concept Relationship are subtypes of an Object
 * Class. Each Concept Relationship carries a concept relationship type
 * description that describes the nature of the relationship.
 * 
 * @author anil
 * 
 */
public interface ObjectClassResource extends AdministeredItemResource, ObjectClass {

	/**
	 * Set the Administration Record of {@link ObjectClassResource}.
	 * 
	 * @param objectClassAdministrationRecord
	 * <br>
	 *            An {@link AdministrationRecordResource} of a {@link ObjectClassResource}
	 *            .
	 */
	void setAdministrationRecord(
			AdministrationRecordResource objectClassAdministrationRecord);

	/**
	 * @return {@link AdministrationRecordResource} the Administration Record of a
	 *         {@link ObjectClassResource}.
	 * 
	 */
	AdministrationRecordResource getAdministrationRecord();

}