package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;



/**
 * 
 * ValueDomainRelationshipAssociation represents the relation between
 * <ul>
 * <li>ValueDomain Relationship</li>
 * <li>Association Among ValueDomain s</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} backed by Jena TripleStore, such an holder class is
 * required for n-ary relations
 * 
 * @author anil
 * 
 */
public interface ValueDomainRelationshipAssociationResource extends MDRResource {

	/**
	 * Sets {@link Vocabulary#describedByValueDomainRelationship} property with
	 * given value
	 * 
	 * @param describedByValueDomainRelationship
	 */
	void setDescribedByValueDomainRelationship(
			ValueDomainRelationshipResource describedByValueDomainRelationship);

	/**
	 * 
	 * @return Value of {@link Vocabulary#describedByValueDomainRelationship}
	 *         property
	 */
	ValueDomainRelationshipResource getDescribedByValueDomainRelationship();

	/**
	 * Adds {@link Vocabulary#relatingValueDomainRelationship} property with
	 * given value
	 * 
	 * @param relatingValueDomainRelationship
	 */
	void addRelatingValueDomainRelationship(
			ValueDomainResource relatingValueDomainRelationship);

	/**
	 * Removes {@link Vocabulary#relatingValueDomainRelationship} property with
	 * given value
	 * 
	 * @param relatingValueDomainRelationship
	 */
	void removeRelatingValueDomainRelationship(
			ValueDomainResource relatingValueDomainRelationship);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#relatingValueDomainRelationship} property
	 *         values
	 * @throws MDRException
	 */
	List<ValueDomainResource> getRelatingValueDomainRelationships()
			throws MDRException;

}