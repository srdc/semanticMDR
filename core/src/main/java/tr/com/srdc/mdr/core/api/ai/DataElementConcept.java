package tr.com.srdc.mdr.core.api.ai;

import java.util.List;

import tr.com.srdc.mdr.core.model.iso11179.DataElementConceptResource;


/**
 * 
 * A Data Element Concept is a concept that can be represented in the form of a
 * data element, described independently of any particular representation. A
 * Data Element Concept may have zero or one Object Class and zero or one
 * Property. The union of a Property and an Object Class provides significance
 * beyond either that of the Property or the Object Class. A Data Element
 * Concept thus has a Definition independent from the Definition of the Object
 * Class or the Property.
 * <p>
 * As an Administered Item, a Data Element Concept carries its own
 * Administration Record information, allowing it to be identified, named,
 * defined and optionally classified within a Classification Scheme. A Data
 * Element Concept may be associated with other Data Element Concepts, via the
 * Data Element Concept Relationship. The nature of the relationship is
 * described using the data element concept relationship type description.
 * <p>
 * A Data Element Concept may be registered as an Administered Item without
 * necessarily being associated with any Data Element, but a Data Element
 * Concept shall be associated with exactly one Conceptual Domain. The
 * Conceptual Domain specifies all valid Value Meanings of a Data Element
 * Concept.
 * 
 * @author anil
 * 
 */
public interface DataElementConcept extends AdministeredItem {

	@Override
	/**
	 * @return the {@link DataElementConceptResource} version this instance.
	 */
	DataElementConceptResource asMDRResource();

	Property getProperty();

	ObjectClass getObjectClass();

	String getPropertyQualifier();

	String getObjectClassQualifier();

	/**
	 * Updates the DataElementConcept's Property with an existing one
	 * 
	 * @param property
	 */
	void setProperty(Property property);

	/**
	 * Updates the DataElementConcept's Property with one which will be created
	 * with given parameters
	 * 
	 * @param name
	 * @param definition
	 */
	void setProperty(String name, String definition);

	void setObjectClass(ObjectClass objectClass);

	void setPropertyQualifier(String qualifier);

	void setObjectClassQualifier(String qualifier);

	/**
	 * Given a {@link ValueDomain}, create a {@link DataElement} with this
	 * {@link DataElementConcept}.
	 * 
	 * @param valueDomain
	 * @return
	 */
	DataElement createDataElement(String name, String definition,
			ValueDomain valueDomain);

	/**
	 * @return List of all {@link DataElement}s of this Data Element Concept
	 */
	List<DataElement> getDataElements();

	/**
	 * @return ConceptualDomain of this DataElementConcept
	 */
	ConceptualDomain getConceptualDomain();

}
