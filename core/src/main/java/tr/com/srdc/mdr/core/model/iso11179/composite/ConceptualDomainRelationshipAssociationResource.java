package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;



/**
 * 
 * ConceptualDomainRelationshipAssociation represents the relation between
 * <ul>
 * <li>ConceptualDomain Relationship</li>
 * <li>Association Among ConceptualDomain s</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} backed by Jena TripleStore, such an holder class is
 * required for n-ary relations
 * 
 * @author anil
 * 
 */
public interface ConceptualDomainRelationshipAssociationResource extends
		MDRResource {

	/**
	 * Sets {@link Vocabulary#describedByConceptualDomainRelationship}
	 * property with given value
	 * 
	 * @param describedByConceptualDomainRelationship
	 */
	void setDescribedByConceptualDomainRelationship(
			ConceptualDomainRelationshipResource describedByConceptualDomainRelationship);

	/**
	 * 
	 * @return Value of
	 *         {@link Vocabulary#describedByConceptualDomainRelationship}
	 *         property
	 */
	ConceptualDomainRelationshipResource getDescribedByConceptualDomainRelationship();

	/**
	 * Adds {@link Vocabulary#relatingConceptualDomainRelationship} property
	 * with given value
	 * 
	 * @param relatingConceptualDomainRelationship
	 */
	void addRelatingConceptualDomainRelationship(
			ConceptualDomainResource relatingConceptualDomainRelationship);

	/**
	 * Removes {@link Vocabulary#relatingConceptualDomainRelationship}
	 * property with given value
	 * 
	 * @param relatingConceptualDomainRelationship
	 */
	void removeRelatingConceptualDomainRelationship(
			ConceptualDomainResource relatingConceptualDomainRelationship);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#relatingConceptualDomainRelationship}
	 *         property values
	 * @throws MDRException
	 */
	List<ConceptualDomainResource> getRelatingConceptualDomainRelationships()
			throws MDRException;

}