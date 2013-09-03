package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.ClassificationSchemeResource;



/**
 * 
 * Classificcation Scheme Itme Association represents the relation between
 * <ul>
 * <li>Classificication Sheme Item Relationship</li>
 * <li>Association Among Classification Scheme Items</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} backed by Jena TripleStore, such an holder class is
 * required for n-ary relations
 * 
 * @author anil
 */
public interface ClassificationSchemeItemAssociationResource extends MDRResource {

	/**
	 * Sets {@link Vocabulary#csiAssociationRelationship} property with given
	 * value
	 * 
	 * @param classificationSchemeItemRelationship
	 *            value of {@link Vocabulary#csiAssociationRelationship}
	 *            property, <br>
	 */
	void setClassificationSchemeItemRelationship(
			ClassificationSchemeItemRelationshipResource classificationSchemeItemRelationship);

	/**
	 * 
	 * @return {@link ClassificationSchemeResource} object, value of
	 *         {@link Vocabulary#csiAssociationRelationship} property, <br>
	 *         If property is not set, then returns <code>null</code>
	 */
	ClassificationSchemeItemRelationshipResource getClassificationSchemeItemRelationship();

	/**
	 * Adds {@link Vocabulary#csiAssociates} property with given value
	 * 
	 * @param classificationSchemeItem
	 *            value of {@link Vocabulary#csiAssociates} property
	 */
	void addCSIAssociates(ClassificationSchemeItemResource classificationSchemeItem);

	/**
	 * Removes the {@link Vocabulary#csiAssociates} property with given value
	 * 
	 * @param classificationSchemeItem
	 *            value of {@link Vocabulary#csiAssociates} property to be
	 *            removed
	 */
	void removeCSIAssociated(
			ClassificationSchemeItemResource classificationSchemeItem);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#csiAssociatedIn} property
	 *         values
	 */
	List<ClassificationSchemeItemResource> getCSIAssociates() throws MDRException;

}