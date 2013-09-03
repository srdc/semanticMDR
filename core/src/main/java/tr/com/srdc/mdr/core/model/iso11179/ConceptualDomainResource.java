package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ConceptualDomainRelationshipAssociationResource;



/**
 * 
 * 
 * A set of valid Value Meanings. The Value Meanings may either be enumerated or
 * expressed via a description.
 * 
 * @author anil
 * @author serike
 * @author alp
 * 
 */
public interface ConceptualDomainResource extends AdministeredItemResource,
		ConceptualDomain {

	/**
	 * Set the {@link AdministrationRecordResource} of {@link ConceptualDomainResource}.
	 * 
	 * @param conceptualDomainAdministrationRecord
	 * <br>
	 *            An {@link AdministrationRecordResource} of a
	 *            {@link ConceptualDomainResource}.
	 */
	void setAdministrationRecord(
			AdministrationRecordResource conceptualDomainAdministrationRecord);

	/**
	 * @return the {@link AdministrationRecordResource} of a
	 *         {@link ConceptualDomainResource}
	 */
	AdministrationRecordResource getAdministrationRecord();

	/**
	 * Set the {@link Vocabulary#dimensionality} of {@link ConceptualDomainResource}
	 * .
	 * 
	 * @param dimensionality
	 * <br>
	 *            An {@link Vocabulary#dimensionality} of a
	 *            {@link ConceptualDomainResource}.
	 */
	void setDimensionality(String dimensionality);

	/**
	 * @return the {@link Vocabulary#dimensionality} of a
	 *         {@link ConceptualDomainResource}
	 */
	String getDimensionality();

	/**
	 * Adds
	 * {@link Vocabulary#specifyingDataElementConceptConceptualDomainRelationship}
	 * property with given value
	 * 
	 * @param specifyingDataElementConceptConceptualDomainRelationship
	 *            value of
	 *            {@link Vocabulary#specifyingDataElementConceptConceptualDomainRelationship}
	 *            property
	 */
	void addSpecifyingDataElementConceptConceptualDomainRelationship(
			DataElementConceptResource specifyingDataElementConceptConceptualDomainRelationship);

	/**
	 * 
	 * @return @return {@link List} of
	 *         {@link Vocabulary#specifyingDataElementConceptConceptualDomainRelationship}
	 *         property values
	 */
	List<DataElementConceptResource> getSpecifyingDataElementConceptConceptualDomainRelationships()
			throws MDRException;

	/**
	 * Removes the
	 * {@link Vocabulary#specifyingDataElementConceptConceptualDomainRelationship}
	 * property with given value
	 * 
	 * @param specifyingDataElementConceptConceptualDomainRelationship
	 *            value of
	 *            {@link Vocabulary#specifyingDataElementConceptConceptualDomainRelationship}
	 *            property to be removed.
	 */
	void removeSpecifyingDataElementConceptConceptualDomainRelationship(
			DataElementConceptResource specifyingDataElementConceptConceptualDomainRelationship);

	/**
	 * Adds {@link Vocabulary#relatedToConceptualDomainRelationship} property
	 * with given value
	 * 
	 * @param relatedToConceptualDomainRelationship
	 */
	void addRelatedToConceptualDomainRelationship(
			ConceptualDomainRelationshipAssociationResource relatedToConceptualDomainRelationship);

	/**
	 * Removes {@link Vocabulary#relatedToConceptualDomainRelationship} property
	 * with given value
	 * 
	 * @param relatedToConceptualDomainRelationship
	 */
	void removeRelatedToConceptualDomainRelationship(
			ConceptualDomainRelationshipAssociationResource relatedToConceptualDomainRelationship);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#relatedToConceptualDomainRelationship} property
	 *         values
	 * @throws MDRException
	 */
	List<ConceptualDomainRelationshipAssociationResource> getRelatedToConceptualDomainRelationships()
			throws MDRException;

	/**
	 * Adds {@link Vocabulary#representedByConceptualDomainRepresentation}
	 * property with given value
	 * 
	 * @param representedByConceptualDomainRepresentation
	 */
	void addRepresentedByConceptualDomainRepresentation(
			ValueDomainResource representedByConceptualDomainRepresentation);

	/**
	 * Removes {@link Vocabulary#representedByConceptualDomainRepresentation}
	 * property with given value
	 * 
	 * @param representedByConceptualDomainRepresentation
	 */
	void removeRepresentedByConceptualDomainRepresentation(
			ValueDomainResource representedByConceptualDomainRepresentation);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#representedByConceptualDomainRepresentation}
	 *         property values
	 * @throws MDRException
	 */
	List<ValueDomainResource> getRepresentedByConceptualDomainRepresentations()
			throws MDRException;

}