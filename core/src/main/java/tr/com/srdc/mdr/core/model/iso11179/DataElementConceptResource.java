package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.DataElementConcept;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementConceptRelationshipAssociationResource;



/**
 * A Data Element Concept is a concept that can be represented in the form of a
 * data element, described independently of any particular representation.
 * 
 * @author pacaci
 * @author mert
 * @author serike
 * @author alp
 * @author anil
 * 
 */
public interface DataElementConceptResource extends AdministeredItemResource,
		DataElementConcept {

	/**
	 * Set the {@link AdministrationRecordResource} of this
	 * {@link DataElementConceptResource}.
	 * 
	 * @param propertyAdministrationRecord
	 * <br>
	 *            An {@link AdministrationRecordResource} of a
	 *            {@link DataElementConceptResource}.
	 */
	void setAdministrationRecord(
			AdministrationRecordResource dataElementConceptAdministrationRecord);

	/**
	 * @return the {@link AdministrationRecordResource} of a
	 *         {@link DataElementConceptResource}.
	 * 
	 */
	AdministrationRecordResource getAdministrationRecord();

	/**
	 * Set the {@link Vocabulary#dataElementConceptObjectClass} of this
	 * {@link DataElementConceptResource}.
	 * 
	 * @param dataElementConceptObjectClass
	 * <br>
	 *            An {@link ObjectClassResource} of a {@link DataElementConceptResource} .
	 */
	void setDataElementConceptObjectClass(
			ObjectClassResource dataElementConceptObjectClass);

	/**
	 * @return the {@link ObjectClassResource} of a {@link DataElementConceptResource}.
	 * @throws MDRException
	 * 
	 */
	ObjectClassResource getDataElementConceptObjectClass();

	/**
	 * Set the {@link Vocabulary#dataElementConceptProperty} of this
	 * {@link DataElementConceptResource}.
	 * 
	 * @param dataElementConceptProperty
	 * <br>
	 *            An {@link PropertyResource} of a {@link DataElementConceptResource}.
	 */
	void setDataElementConceptProperty(PropertyResource dataElementConceptProperty);

	/**
	 * @return the {@link PropertyResource} of a {@link DataElementConceptResource}.
	 * 
	 */
	PropertyResource getDataElementConceptProperty();

	/**
	 * Set the {@link Vocabulary#objectClassQualifier} of this
	 * {@link DataElementConceptResource}.
	 * 
	 * @param objectClassQualifier
	 */
	void setObjectClassQualifier(String objectClassQualifier);

	/**
	 * @return the {@link Vocabulary#objectClassQualifier} of a
	 *         {@link DataElementConceptResource}.
	 * 
	 */
	String getObjectClassQualifier();

	/**
	 * Set the {@link Vocabulary#propertyQualifier} of this
	 * {@link DataElementConceptResource}.
	 * 
	 * @param qualifier
	 */
	void setPropertyQualifier(String qualifier);

	/**
	 * @return the {@link Vocabulary#propertyQualifier} of a
	 *         {@link DataElementConceptResource}.
	 * 
	 */
	String getPropertyQualifier();

	/**
	 * Set the
	 * {@link Vocabulary#havingDataElementConceptConceptualDomainRelationship}
	 * of this {@link DataElementConceptResource}.
	 * 
	 * @param havingDataElementConceptConceptualDomainRelationship
	 */
	void setHavingDataElementConceptConceptualDomainRelationship(
			ConceptualDomainResource havingDataElementConceptConceptualDomainRelationship);

	/**
	 * @return the
	 *         {@link Vocabulary#havingDataElementConceptConceptualDomainRelationship}
	 *         of a {@link DataElementConceptResource}.
	 * 
	 */
	ConceptualDomainResource getHavingDataElementConceptConceptualDomainRelationship();

	/**
	 * Adds {@link Vocabulary#expressedByDataElementConceptExpression} property
	 * with given value
	 * 
	 * @param submitting
	 *            value of
	 *            {@link Vocabulary#expressedByDataElementConceptExpression}
	 *            property
	 */
	void addExpressedByDataElementConceptExpression(
			DataElementResource expressedByDataElementConceptExpression);

	/**
	 * 
	 * @return {@link List} of
	 *         {@link Vocabulary#expressedByDataElementConceptExpression}
	 *         property values
	 */
	List<DataElementResource> getExpressedByDataElementConceptExpressions()
			throws MDRException;

	/**
	 * Removes the {@link Vocabulary#expressedByDataElementConceptExpression}
	 * property with given value
	 * 
	 * @param providing
	 *            value of
	 *            {@link Vocabulary#expressedByDataElementConceptExpression}
	 *            property to be removed.
	 */
	void removeExpressedByDataElementConceptExpression(
			DataElementResource expressedByDataElementConceptExpression);

	/**
	 * Adds {@link Vocabulary#relatedToDataElementConceptRelationship} property
	 * with given value
	 * 
	 * @param relatedToDataElementConceptRelationship
	 */
	void addRelatedToDataElementConceptRelationship(
			DataElementConceptRelationshipAssociationResource relatedToDataElementConceptRelationship);

	/**
	 * Removes {@link Vocabulary#relatedToDataElementConceptRelationship}
	 * property with given value
	 * 
	 * @param relatedToDataElementConceptRelationship
	 */
	void removeRelatedToDataElementConceptRelationship(
			DataElementConceptRelationshipAssociationResource relatedToDataElementConceptRelationship);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#relatedToDataElementConceptRelationship}
	 *         property values
	 * @throws MDRException
	 */
	List<DataElementConceptRelationshipAssociationResource> getRelatedToDataElementConceptRelationships()
			throws MDRException;

}