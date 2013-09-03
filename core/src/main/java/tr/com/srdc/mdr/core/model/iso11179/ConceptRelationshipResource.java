package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.ConceptRelationship;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * 
 * An Object Class is a set of ideas, abstractions, or things in the real world
 * that can be identified with explicit boundaries and meaning and whose
 * properties and behavior follow the same rules. It may be either a single or a
 * group of associated concepts, abstractions, or things. An Object Class may be
 * a single unit of thought (i.e., Concept) or a set of Concepts in a
 * relationship with each other to form a more complex concept (i.e., Concept
 * Relationship). A Concept and a Concept Relationship are subtypes of an Object
 * Class.
 * 
 * @author anil
 * 
 */
public interface ConceptRelationshipResource extends ObjectClassResource,
		ConceptRelationship {

	/**
	 * Set the concept relationship type description of this
	 * {@link ObjectClassResource}
	 * 
	 * @param conceptRelationshipTypeDescription
	 * <br>
	 *            A description of the type of relationship among two or more
	 *            Concepts
	 */
	void setConceptRelationshipTypeDescription(
			String conceptRelationshipTypeDescription);

	/**
	 * 
	 * @return the concept relationship type description of a
	 *         {@link ObjectClassResource}.<br>
	 */
	String getConceptRelationshipTypeDescription();

	/**
	 * Adds {@link Vocabulary#usingConceptRelationship} property with given
	 * value
	 * 
	 * @param usingConceptRelationship
	 */
	void addUsingConceptRelationship(ConceptResource usingConceptRelationship);

	/**
	 * Removes {@link Vocabulary#usingConceptRelationship} property with given
	 * value
	 * 
	 * @param usingConceptRelationship
	 */
	void removeUsingConceptRelationship(ConceptResource usingConceptRelationship);

	/**
	 * 
	 * @return {@link List} of all {@link Vocabulary#usingConceptRelationship}
	 *         property values
	 * @throws MDRException
	 */
	List<ConceptResource> getUsingConceptRelationships() throws MDRException;

}