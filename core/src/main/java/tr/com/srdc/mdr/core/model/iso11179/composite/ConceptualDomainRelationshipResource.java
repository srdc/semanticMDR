package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.ConceptualDomainRelationship;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * 
 * A Conceptual Domain may be associated with other ConceptualDomains , via the
 * Conceptual Domaint Relationship. The nature of the relationship is described
 * using the Conceptual Domain relationship type description.
 * 
 * @author anil
 * 
 */
public interface ConceptualDomainRelationshipResource extends MDRResource,
		ConceptualDomainRelationship {

	/**
	 * Sets the {@link Vocabulary#conceptualDomainRelationshipTypeDescription}
	 * property with given value
	 * 
	 * @param conceptualDomainRelationshipTypeDescription
	 */
	void setConceptualDomainRelationshipTypeDescription(
			String conceptualDomainRelationshipTypeDescription);

	/**
	 * 
	 * @return Value of
	 *         {@link Vocabulary#conceptualDomainRelationshipTypeDescription}
	 *         property
	 */
	String getConceptualDomainRelationshipTypeDescription();

	/**
	 * Add {@link Vocabulary#describingConceptualDomainRelationship} property
	 * with given value
	 * 
	 * @param describingConceptualDomainRelationship
	 */
	void addDescribingConceptualDomainRelationship(
			ConceptualDomainRelationshipAssociationResource describingConceptualDomainRelationship);

	/**
	 * Remove {@link Vocabulary#describingConceptualDomainRelationship} property
	 * with given value
	 * 
	 * @param describingConceptualDomainRelationship
	 */
	void removeDescribingConceptualDomainRelationship(
			ConceptualDomainRelationshipAssociationResource describingConceptualDomainRelationship);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#describingConceptualDomainRelationship}
	 *         property values
	 * @throws MDRException
	 */
	List<ConceptualDomainRelationshipAssociationResource> getDescribingConceptualDomainRelationships()
			throws MDRException;

}