package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.DataElementConceptResource;



/**
 * 
 * DataElementConceptRelationshipAssociation represents the relation between
 * <ul>
 * <li>Data Element Concept Relationship</li>
 * <li>Association Among Data Element Concepts</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} backed by Jena TripleStore, such an holder class is
 * required for n-ary relations
 * 
 * @author anil
 * 
 */
public interface DataElementConceptRelationshipAssociationResource extends
		MDRResource {

	/**
	 * Sets {@link Vocabulary#describedByDataElementConceptRelationship}
	 * property with given value
	 * 
	 * @param describedByDataElementConceptRelationship
	 */
	void setDescribedByDataElementConceptRelationship(
			DataElementConceptRelationshipResource describedByDataElementConceptRelationship);

	/**
	 * 
	 * @return Value of
	 *         {@link Vocabulary#describedByDataElementConceptRelationship}
	 *         property
	 */
	DataElementConceptRelationshipResource getDescribedByDataElementConceptRelationship();

	/**
	 * Adds {@link Vocabulary#relatingDataElementConceptRelationship} property
	 * with given value
	 * 
	 * @param relatingDataElementConceptRelationship
	 */
	void addRelatingDataElementConceptRelationship(
			DataElementConceptResource relatingDataElementConceptRelationship);

	/**
	 * Removes {@link Vocabulary#relatingDataElementConceptRelationship}
	 * property with given value
	 * 
	 * @param relatingDataElementConceptRelationship
	 */
	void removeRelatingDataElementConceptRelationship(
			DataElementConceptResource relatingDataElementConceptRelationship);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#relatingDataElementConceptRelationship}
	 *         property values
	 * @throws MDRException
	 */
	List<DataElementConceptResource> getRelatingDataElementConceptRelationships()
			throws MDRException;

}