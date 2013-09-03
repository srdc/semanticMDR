package tr.com.srdc.mdr.core.api.ai;

import tr.com.srdc.mdr.core.model.iso11179.ConceptRelationshipResource;


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
public interface ConceptRelationship extends ObjectClass {

	@Override
	/**
	 * @return the {@link ConceptRelationshipResource} version this instance.
	 */
	ConceptRelationshipResource asMDRResource();

//	/**
//	 * @return Description for the type of relatinoship over the set of
//	 *         {@link Concept}s
//	 */
//	String getTypeDescription();
//
//	/**
//	 * Adds a {@link Concept} to the set of related Concepts of this
//	 * ConceptRelationship
//	 * 
//	 * @param concept
//	 *            {@link Concept} to add related set
//	 */
//	void addRelatedConcept(Concept concept);
//
//	/**
//	 * @return List of all Concepts which are related through this
//	 *         ConceptRelationship
//	 * @throws MDRException 
//	 */
//	List<Concept> getRelatedConcepts() throws MDRException;
}
