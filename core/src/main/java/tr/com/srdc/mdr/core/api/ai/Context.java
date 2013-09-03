package tr.com.srdc.mdr.core.api.ai;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.api.composite.Designation;
import tr.com.srdc.mdr.core.api.composite.PermissibleValue;
import tr.com.srdc.mdr.core.model.iso11179.ContextResource;
import tr.com.srdc.mdr.core.store.query.ResourceQueryFactory.TextSearchType;

/**
 * @author anil
 * 
 */
public interface Context extends AdministeredItem {

	@Override
	/**
	 * @return the {@link ContextResource} version this instance.
	 */
	ContextResource asMDRResource();

	@Override
	/**
	 * Returns the name of this Context. ISO11179 allows assigning several names
	 * to a {@link Context} (actually to all {@link AdministeredItem}s) within
	 * several {@link Context}s. However, in semantic MDR we currently assign
	 * only one name to any {@link AdministeredItem}.
	 * 
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Sets the name of this {@link Context}. Since there is only one name, this
	 * methods updates that name.
	 */
	void setName(String name);

	/**
	 * @return Description of this {@link Context}.
	 */
	String getDescription();

	/**
	 * Sets the description for this {@link Context}. Since there is only one
	 * description, this method updates that description.
	 */
	void setDescription(String description);

	/**
	 * @return List of all {@link DataElement}s which have a name and definition
	 *         in this Context.
	 */
	List<DataElement> getDataElements(Integer limit, Integer offset);

	/**
	 * Creates a {@link DataElement} with given arguments on this Context
	 * 
	 * @param name
	 *            Name of DataElement on this Context
	 * @param definition
	 *            Definition for the DataElement on this Context
	 * @param dec
	 *            DataElementConcept describing the concept independent from any
	 *            particular representation
	 * @param vd
	 *            Provides representation for the DataElement
	 * @return
	 */
	DataElement createDataElement(String name, String definition,
			DataElementConcept dec, ValueDomain vd);

	/**
	 * Calculates the total number of text search
	 * 
	 * @param keyword
	 * @param searchType
	 *            Exact, Wildcard, AllOfTheWords
	 * @return
	 */
	int getNumberOfDataElementSearch(String keyword, TextSearchType searchType);

	/**
	 * Executes a full text search on the Repository for {@link DataElement}s of
	 * this context
	 * 
	 * @param keyword
	 * @param searchType
	 *            Exact, Wildcard, AllOfTheWords
	 * @param limit
	 *            Total number of results to be displayed
	 * @param offset
	 *            Starting indice of the results to be displayed
	 * @return
	 */
	List<DataElement> searchDataElement(String keyword,
			TextSearchType searchType, int limit, int offset);

	/**
	 * @return List of all {@link ObjectClass}s which have a name and definition
	 *         in this Context.
	 */
	List<ObjectClass> getObjectClasses();

	/**
	 * 
	 * @param limit
	 *            Total number of results to be returned
	 * @param offset
	 *            The initial point of the results
	 * @return List of {@link ObjectClass}s which are grouped under this Context
	 */
	List<ObjectClass> getObjectClasses(Integer limit, Integer offset);

	/**
	 * Given the unique id, return the {@link ObjectClass} within this
	 * {@link Context}.
	 * 
	 * @param id
	 * @return
	 */
	ObjectClass getObjectClass(String uniqeID);

	/**
	 * Creates an {@link ObjectClass} on this Context with specified parameters.
	 * By default, {@link Context}{@link #createConcept(String, String)} is
	 * called to create and {@link ObjectClass}. The contact and organization
	 * information is received from the {@link Context} which this
	 * {@link ObjectClass} is being created on. Contact and Organization will be
	 * obtained from the "administer" fields of the corresponding context.
	 * 
	 * @param name
	 *            The name of the {@link ObjectClass}. Required.
	 * @param definition
	 *            The definition of the {@link ObjectClass}. Optional, can be
	 *            <code>null</code>.
	 * @return
	 */
	ObjectClass createObjectClass(String name, String definition);

	/**
	 * Creates a {@link Concept} within this {@link Context} with the given name
	 * and definition.
	 * 
	 * @param name
	 * @param definition
	 * @return
	 */
	Concept createConcept(String name, String definition);

	/**
	 * @return List of all {@link Property} which have name and definition in
	 *         this Context
	 */
	List<Property> getProperties();

	/**
	 * Given the unique ID, return the {@link Property} within this
	 * {@link Context}.
	 * 
	 * @param uniqueID
	 * @return
	 */
	Property getProperty(String uniqueID);

	/**
	 * Executes a full text search over the MDRDatabase for {@link Property}s of
	 * this context
	 * 
	 * @param keyword
	 *            argument to full text search
	 * @param searchType
	 *            Enumeration, Exact, AllWords or AtLeastOne
	 * @return Search Result as an ArrayList
	 */
	List<Property> searchProperty(String keyword, TextSearchType searchType);

	/**
	 * Creates an {@link DataElementConcept} on this Context with specified
	 * parameters. This method creates a new Property resource to be used in the
	 * creation of the {@link DataElementConcept}.
	 * 
	 * @param propertyName
	 *            The name of the {@link Property}. Required.
	 * @param propertyDefinition
	 *            The definition of the {@link Property}. Optional, can be
	 *            <code>null</code>.
	 * @return
	 */
	DataElementConcept createDataElementConcept(ObjectClass objectClass,
			String propertyName, String propertyDefinition,
			ConceptualDomain conceptulDomain);

	/**
	 * Creates an {@link DataElementConcept} on this Context with specified
	 * parameters. This method uses an existing {@link Property} to create the
	 * {@link DataElementConcept}.
	 * 
	 * @param property
	 * @return
	 */
	DataElementConcept createDataElementConcept(ObjectClass objectClass,
			Property property, ConceptualDomain conceptulDomain);

	/**
	 * Returns {@link DataElementConcept} on this Context whose ID is specified
	 * with gievn argument
	 * 
	 * @param id
	 * @return
	 */
	DataElementConcept getDataElementConcept(String id);

	/**
	 * Create an {@link EnumeratedValueDomain} out of the given
	 * {@link EnumeratedConceptualDomain} within this {@link Context} with the
	 * given name and definition.
	 * 
	 * @param enumeratedConceptualDomain
	 * @param name
	 * @param definition
	 * @return
	 */
	EnumeratedValueDomain createEnumeratedValueDomain(
			EnumeratedConceptualDomain enumeratedConceptualDomain, String name,
			String definition, Datatype dataType,
			List<PermissibleValue> permissibleValues);

	/**
	 * Create a {@link NonEnumeratedValueDomain} out of the given
	 * {@link NonEnumeratedConceptualDomain} within this {@link Context} with
	 * the given name and definition. Definition is copied to the description of
	 * the created {@link NonEnumeratedValueDomain}.
	 * 
	 * @param nonEnumeratedConceptualDomain
	 * @param name
	 * @param definition
	 * @return
	 */
	NonEnumeratedValueDomain createNonEnumeratedValueDomain(
			NonEnumeratedConceptualDomain nonEnumeratedConceptualDomain,
			String name, String definition, Datatype dataType);

	/**
	 * 
	 * @return total number of ObjectClasses of this Context
	 */
	int getNumberOfObjectClasses();

	/**
	 * Creates a classification scheme. The classification scheme has a name and
	 * type
	 * 
	 * @param oid
	 *            Oid of the content model: It is 2.16.840.1.113883.10.20.1 for
	 *            HL7 CCD
	 * @param type
	 *            Type of the classification scheme (ExtractionSpecification or
	 *            BioPortal)
	 * @return
	 */
	ClassificationScheme createClassificationScheme(String oid, String type);

	/**
	 * Retrieve the {@link ClassificationScheme} with the given name
	 * 
	 * @param csid
	 *            Id of the classification scheme (typically oid for the content
	 *            model is used)
	 * @return
	 */
	ClassificationScheme getClassificationScheme(String csid);

	/**
	 * @return total number of Data Elements on this Context
	 */
	int getNumberOfDataElements();

	/**
	 * Returns list of Value Domain's registered on this context and meets
	 * requirements specified by given parameters
	 * 
	 * @param keyword
	 *            String which will be matched with Value Domain's
	 *            {@link Designation}
	 * @param textSearchType
	 *            AllWords, Exact or Wildcard. Specified by
	 *            {@link TextSearchType}
	 * @return List of ValueDomain's as the result of text search done by given
	 *         parameters In resulting list, all items are ValueDomains;
	 *         however, their correct type (enumerated or nonenumerated) can be
	 *         tested using <code>instanceof</code>
	 */
	List<ValueDomain> searchValueDomain(String keyword,
			TextSearchType textSearchType);

	/**
	 * @param limit
	 *            total number of items to be returned
	 * @param offset
	 *            offset for the result set
	 * @return List of {@link ValueDomain} which have naming and definition on
	 *         this Context
	 */
	List<ValueDomain> getValueDomains(Integer limit, Integer offset);

	/**
	 * Returns a list of Common Data Elements. In case of nested CDE structure,
	 * this method should return the top-level CDEs in the Content Model. For
	 * example, this can be an issue in Content Models expressed with an XML
	 * Schema. There might be only one top-level element and then other elements
	 * can be nested under the top-level element recursively. Then, these first
	 * level children of the top-level element (top-level element being the
	 * ObjectClass and the children with proper Property-ValueDomain links) will
	 * be returned with this method. On the other hand, an XML Schema may have a
	 * number of top-level elements. Then these top-level elements will be
	 * retrieved (each top-level element being an ObjectClass) as a result of
	 * this method.
	 * 
	 * @return
	 */
	// List<CDE> getCDEs();

	/**
	 * Returns a list of all identified CDEs within this Content Model. This
	 * method should return a complete CDE list without caring about the
	 * granularity of the CDEs.
	 * 
	 * @return
	 */
	// List<CDE> getAllCDEs();

}
