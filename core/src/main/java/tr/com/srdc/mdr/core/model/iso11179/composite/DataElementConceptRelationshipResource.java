package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.DataElementConceptRelationship;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * 
 * A Data Element Concept may be associated with other Data Element Concepts,
 * via the Data Element Concept Relationship. The nature of the relationship is
 * described using the data element concept relationship type description.
 * 
 * @author anil
 * 
 */
public interface DataElementConceptRelationshipResource extends MDRResource,
		DataElementConceptRelationship {

	/**
	 * Sets the {@link Vocabulary#dataElementConceptRelationshipTypeDescription}
	 * property with given value
	 * 
	 * @param dataElementConceptRelationshipTypeDescription
	 */
	void setDataElementConceptRelationshipTypeDescription(
			String dataElementConceptRelationshipTypeDescription);

	/**
	 * 
	 * @return Value of
	 *         {@link Vocabulary#dataElementConceptRelationshipTypeDescription}
	 *         property
	 */
	String getDataElementConceptRelationshipTypeDescription();

	/**
	 * Add {@link Vocabulary#describingDataElementConceptRelationship} property
	 * with given value
	 * 
	 * @param describingDataElementConceptRelationship
	 */
	void addDescribingDataElementConceptRelationship(
			DataElementConceptRelationshipAssociationResource describingDataElementConceptRelationship);

	/**
	 * Remove {@link Vocabulary#describingDataElementConceptRelationship}
	 * property with given value
	 * 
	 * @param describingDataElementConceptRelationship
	 */
	void removeDescribingDataElementConceptRelationship(
			DataElementConceptRelationshipAssociationResource describingDataElementConceptRelationship);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#describingDataElementConceptRelationship}
	 *         property values
	 * @throws MDRException
	 */
	List<DataElementConceptRelationshipAssociationResource> getDescribingDataElementConceptRelationships()
			throws MDRException;

}