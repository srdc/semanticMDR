package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.ClassificationSchemeItemRelationship;
import tr.com.srdc.mdr.core.impl.composite.ClassificationSchemeItemAssociationImpl;
import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * A Classification Scheme Item Relationship associates two or more
 * Classification Scheme Items within a Classification Scheme. Such
 * relationships serve to assist navigation through a large number of
 * Classification Scheme Items.
 * <p>
 * Since {@link Abbreviation} is backed by Jena Triple Store, Classification Scheme
 * Item Relationship and Classification Scheme Items are associated by use of
 * {@link ClassificationSchemeItemAssociationImpl}
 * 
 * @author anil
 * 
 */
public interface ClassificationSchemeItemRelationshipResource extends MDRResource,
		ClassificationSchemeItemRelationship {

	/**
	 * Sets
	 * {@link Vocabulary#classificationSchemeItemRelationshipTypeDescription}
	 * property with given value
	 * 
	 * @param classificationSchemeItemRelationshipTypeDescription
	 *            value of
	 *            {@link Vocabulary#classificationSchemeItemRelationshipTypeDescription}
	 *            property, <br>
	 */
	void setClassificationSchemeItemRelationshipTypeDescription(
			String classificationSchemeItemRelationshipTypeDescription);

	/**
	 * 
	 * @return {@link String} value of
	 *         {@link Vocabulary#classificationSchemeItemValue} property, <br>
	 */
	String getClassificationSchemeItemRelationshipTypeDescription();

	/**
	 * Adds {@link Vocabulary#csiRelationshipAssociation} property with given
	 * value
	 * 
	 * @param csiRelationshipAssociation
	 *            value of {@link Vocabulary#csiRelationshipAssociation}
	 *            property
	 */
	void addCSIRelationshipAssocation(
			ClassificationSchemeItemAssociationImpl csiRelationshipAssociation);

	/**
	 * Removes the {@link Vocabulary#csiRelationshipAssociation} property with
	 * given value
	 * 
	 * @param csiRelationshipAssociation
	 *            value of {@link Vocabulary#csiRelationshipAssociation}
	 *            property to be removed
	 */
	void removeCSIRelationshipAssocation(
			ClassificationSchemeItemAssociationImpl csiRelationshipAssociation);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#csiRelationshipAssociation}
	 *         property values
	 */
	List<ClassificationSchemeItemAssociationImpl> getCSIRelationshipAssocations()
			throws MDRException;

}