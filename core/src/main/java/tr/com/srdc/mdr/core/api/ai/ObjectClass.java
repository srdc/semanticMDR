package tr.com.srdc.mdr.core.api.ai;

import java.util.List;

import tr.com.srdc.mdr.core.model.iso11179.ObjectClassResource;


/**
 * An Object Class is a set of ideas, abstractions, or things in the real world
 * that can be identified with explicit boundaries and meaning and whose
 * properties and behavior follow the same rules. It may be either a single or a
 * group of associated concepts, abstractions, or things. An Object Class may be
 * a single unit of thought (i.e., {@link Concept}) or a set of Concepts in a
 * relationship with each other to form a more complex concept (i.e.,
 * {@link ConceptRelationship}). A Concept and a Concept Relationship are
 * subtypes of an Object Class. Each Concept Relationship carries a concept
 * relationship type description that describes the nature of the relationship.<br>
 * As an {@link AdministeredItem}, an Object Class carries its own
 * Administration Record information, allowing it to be identified, named,
 * defined and optionally classified within a {@link ClassificationScheme}. An
 * Object Class may be registered as an Administered Item without necessarily
 * being associated with a {@link DataElementConcept} or, through the latter, a
 * {@link Property}.
 * 
 * @author anil
 */
public interface ObjectClass extends AdministeredItem {

	@Override
	/**
	 * @return the {@link ObjectClassResource} version this instance.
	 */
	ObjectClassResource asMDRResource();

	/**
	 * Create a {@link DataElementConcept} with this {@link ObjectClass} and
	 * given {@link Property}.
	 * 
	 * @param property
	 * @return
	 */
	DataElementConcept createDataElementConcept(Property property,
			ConceptualDomain conceptualDomain, String definition);

	/**
	 * Create a {@link DataElementConcept} with this {@link ObjectClass} and a
	 * newly created {@link Property} with the given propertyName and
	 * propertyDefinition.
	 * 
	 * @param propertyName
	 * @param propertyDefinition
	 * @return
	 */
	DataElementConcept createDataElementConcept(String propertyName,
			String propertyDefinition, ConceptualDomain conceptualDomain,
			String definition);

	/**
	 * Executes SPARQL Query to find {@link DataElementConcept}s created by
	 * using this ObjectClass
	 * 
	 * @return List of all DataElementConcept's having this
	 */
	List<DataElementConcept> getDataElementConcepts();

	/**
	 * 
	 * @param limit
	 *            Total number of results to be returned
	 * @param offset
	 *            The initial point of the results
	 * @return List of DataElementConcept's having this
	 */
	List<DataElementConcept> getDataElementConcepts(Integer limit,
			Integer offset);

	/**
	 * Returns a {@link DataElementConcept} of which unique id is given
	 * 
	 * @param id
	 *            unique identifier of in the repository
	 * @return
	 */
	DataElementConcept getDataElementConcept(String id);

	/**
	 * 
	 * @return total number of Data Element Concepts of this Object Class
	 */
	int getNumberOfDataElementConcepts();

	/**
	 * This method is used to create hierarchy between ObjectClass'es.
	 * 
	 * @param parent
	 *            Optional. Sets given ObjectClass as a parent for this; if
	 *            given <code>null</code>, removes existing value
	 */
	void setParentConcept(ObjectClass parent);

	/**
	 * @return the parent concept of this ObjectClass, in other way returns the
	 *         broader concept
	 */
	ObjectClass getParentConcept();

	/**
	 * Adds given ObjectClass as sub concept for this and sets given
	 * ObjectClass'es parent as this
	 * 
	 * @param oc
	 */
	void addSubConcept(ObjectClass oc);

	/**
	 * @return List of all {@link ObjectClass}es whose parent concept is this
	 */
	List<ObjectClass> getSubConcepts();

}
