package tr.com.srdc.mdr.core.api.ai;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.ClassificationSchemeItem;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.util.MappingRelation;

/**
 * A Data Element is considered to be a basic unit of data of interest to an
 * organization. It is a unit of data for which the definition, identification,
 * representation, and permissible values are specified by means of a set of
 * attributes.<br>
 * As an {@link AdministeredItem}, a Data Element carries its own Administration
 * Record information, allowing it to be identified, named, defined and
 * optionally classified in a {@link ClassificationScheme}.<br>
 * A Data Element is formed when a {@link DataElementConcept} is assigned a
 * representation. One of the key components of a representation is the
 * {@link ValueDomain}, i.e., restricted valid values.<br>
 * A Data Element is the association among a {@link DataElementConcept}, a
 * {@link ValueDomain} and optionally a {@link RepresentationClass}. The
 * association of a Data Element with a Representation Class may be either
 * direct, or via the Value Domain A Data Element cannot be registered as an
 * Administered Item without being associated with a Data Element Concept and a
 * Value Domain.<br>
 * A representation class qualifier may be specified, that is used to qualify
 * the name of the data element.<br>
 * A data element precision may be used to specify the number of decimal places
 * permitted in any associated data element values. If not specified, the unit
 * of measure precision from the associated Value Domain shall apply.
 * 
 * @author anil
 * 
 */
public interface DataElement extends AdministeredItem {

	@Override
	/**
	 * @return the {@link DataElementResource} version this instance.
	 */
	DataElementResource asMDRResource();

	/**
	 * @return {@link DataElementConcept} which which forms this DataElement and
	 *         identifies WHAT is the data
	 */
	DataElementConcept getDataElementConcept();

	/**
	 * @return {@link ValueDomain} which forms this DataElement and identifies
	 *         HOW physically the data is
	 */
	ValueDomain getValueDomain();

	/**
	 * 
	 * 
	 * @param oid
	 * 
	 * @param extractionSpecificationType
	 *            Type of the extraction specification. <b>Possible values:
	 *            DataElement.EXTRACTION_SPEC_TYPE_*</b>
	 * @param extractionSpecification
	 *            Extraction expression in the given extraction specification
	 *            type
	 * 
	 */

	/**
	 * Abstract common data elements can be implemented in different content
	 * models. Machine-processable specifications for supported content models
	 * can be added to data elements.
	 * 
	 * @param oid
	 *            oid for the target content model
	 * @param esType
	 *            Type of the extraction specification. XPATH, BRIDG UML...
	 * @param esScript
	 *            The extraction specification script.
	 */
	void addExtractionSpecification(String oid, String esType, String esScript);

	/**
	 * 
	 * @return List of the classification scheme items used for extraction
	 *         specifications. Contains specifications to retrieve the element
	 *         on other content models.
	 */
	List<ClassificationSchemeItem> getExtractionSpecifications();

	/**
	 * Abstract common data elements can be implemented in different content
	 * models. Machine-understandable similarities can be added to data
	 * elements. Adds mappings to both data elements if the relation symmetric
	 * 
	 * @param relation
	 *            The mapping relation between data elements.
	 * @param matchedElement
	 *            The matched data element
	 */
	void addMapping(MappingRelation relation, DataElement matchedElement);

	/**
	 * 
	 * @return List of the classification scheme items used for mappings between
	 *         this data element and other data elements from other terminology
	 *         systems
	 */
	List<ClassificationSchemeItem> getMappings();

	// /**
	// * DataElement's may have {@link DataElementExample}'s which provides
	// * representative samples of the DataElement.<br>
	// * This method is used to create a DataElementExample which provides
	// samples
	// * of this DataElement or a set of DataElements
	// *
	// * @param exampleItem
	// * Example Item as a representative sample for the DataElement
	// * @return newly created DataElementExample providing samples for this
	// * DataElements
	// */
	// DataElementExample createDataElementExample(String exampleItem);
	//
	// /**
	// * @return unmodifiable List of all {@link DataElementExample}'s providing
	// * representative samples for this DataElement
	// */
	// List<DataElementExample> getDataElementExamples() throws MDRException;
	//
	// /**
	// * @return unmodifiable List of all {@link DataElementDerivation}s which
	// * take this DataElement as input
	// * @throws MDRException
	// */
	// List<DataElementDerivation> getInputDerivations() throws MDRException;
	//
	// /**
	// * @return {@link DataElementDerivation} of which this DataElement is
	// output
	// */
	// DataElementDerivation getOutputDerivation();
	//
	// /**
	// * Method to set qualifier for the {@link RepresentationClass} of the
	// * DataElement.<br>
	// * DataElemenet may or may not have a RepresentationClass which act as a
	// * additional ClassificationScheme for DataElements.
	// * RepresentationClassQualifier is used to specify RepresentationClass if
	// it
	// * exists.
	// *
	// * @param qualifier
	// * Optional. New value for the RepresentationClass qualifier
	// * attribute. If given <code>null</code>, removes existing value.
	// */
	// void setClassQualifier(String qualifier);
	//
	// /**
	// * @return Qualifier of the RepresentationClass of DataElement. If such
	// * attribute does not exist, return <code>null</code>.
	// */
	// String getClassQualifier();
	//
	// /**
	// * Method to set precision attribte of DataElement.<br>
	// * Precision is used to specify number of decimal places permitted in any
	// * values of DataElement. If not specified, then precision from
	// * {@link ValueDomain}'s {@link UnitOfMeasure} could be used.
	// *
	// * @param precision
	// * Optinoal. New value for the precision attribute. If given
	// * <code>null</code>, removes existing value.
	// */
	// void setPrecision(int precision);
	//
	// /**
	// * @return Precision of the DataElement. If such attribute does not exist,
	// * return <code>null</code>.
	// */
	// int getPrecision();
}