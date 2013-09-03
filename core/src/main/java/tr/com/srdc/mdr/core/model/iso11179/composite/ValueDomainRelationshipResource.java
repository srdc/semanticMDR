package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.ValueDomainRelationship;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * 
 * A Value Domain may be associated with other Value Domains, via the Value
 * Domain Relationship. The nature of the relationship is described using the
 * Value Domain relationship type description.
 * 
 * @author anil
 * 
 */
public interface ValueDomainRelationshipResource extends MDRResource,
		ValueDomainRelationship {

	/**
	 * Sets the {@link Vocabulary#ValueDomainRelationshipTypeDescription}
	 * property with given value
	 * 
	 * @param valueDomainRelationshipTypeDescription
	 */
	void setValueDomainRelationshipTypeDescription(
			String valueDomainRelationshipTypeDescription);

	/**
	 * 
	 * @return Value of
	 *         {@link Vocabulary#ValueDomainRelationshipTypeDescription}
	 *         property
	 */
	String getValueDomainRelationshipTypeDescription();

	/**
	 * Add {@link Vocabulary#describingValueDomainRelationship} property with
	 * given value
	 * 
	 * @param describingValueDomainRelationship
	 */
	void addDescribingValueDomainRelationship(
			ValueDomainRelationshipAssociationResource describingValueDomainRelationship);

	/**
	 * Remove {@link Vocabulary#describingValueDomainRelationship} property with
	 * given value
	 * 
	 * @param describingValueDomainRelationship
	 */
	void removeDescribingValueDomainRelationship(
			ValueDomainRelationshipAssociationResource describingValueDomainRelationship);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#describingValueDomainRelationship} property
	 *         values
	 * @throws MDRException
	 */
	List<ValueDomainRelationshipAssociationResource> getDescribingValueDomainRelationships()
			throws MDRException;

}