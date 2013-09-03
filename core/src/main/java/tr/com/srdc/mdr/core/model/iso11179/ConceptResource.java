package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.Concept;
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
public interface ConceptResource extends ObjectClassResource, Concept {

	/**
	 * Adds {@link Vocabulary#usedInConceptRelationship} property with given
	 * value
	 * 
	 * @param usedInConceptRelationship
	 */
	void addUsedInConceptRelationship(
			ConceptRelationshipResource usedInConceptRelationship);

	/**
	 * Removes {@link Vocabulary#usedInConceptRelationship} property with given
	 * value
	 * 
	 * @param usedInConceptRelationship
	 */
	void removeUsedInConceptRelationship(
			ConceptRelationshipResource usedInConceptRelationship);

	/**
	 * 
	 * @return {@link List} of all {@link Vocabulary#usedInConceptRelationship}
	 *         property values
	 * @throws MDRException
	 */
	List<ConceptRelationshipResource> getUsedInConceptRelationships()
			throws MDRException;

}