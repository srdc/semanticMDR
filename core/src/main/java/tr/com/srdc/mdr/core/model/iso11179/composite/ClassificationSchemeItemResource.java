package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.ClassificationSchemeItem;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ClassificationSchemeResource;



/**
 * 
 * A Classification Scheme Item represents an individual item within a
 * Classification Scheme.<br>
 * 
 * @author anil
 * @author mert
 */
public interface ClassificationSchemeItemResource extends MDRResource,
		ClassificationSchemeItem {

	/**
	 * Sets {@link Vocabulary#classificationSchemeItemTypeName} property with
	 * given value
	 * 
	 * @param classificationSchemeItemTypeName
	 *            value of {@link Vocabulary#classificationSchemeItemTypeName}
	 *            property, <br>
	 *            Removes property if given <code>null</code>
	 */
	void setClassificationSchemeItemTypeName(
			String classificationSchemeItemTypeName);

	/**
	 * 
	 * @return {@link String} value of
	 *         {@link Vocabulary#classificationSchemeItemTypeName} property, <br>
	 *         If property is not set, then returns <code>null</code>
	 */
	String getClassificationSchemeItemTypeName();

	/**
	 * Sets {@link Vocabulary#classificationSchemeItemValue} property with given
	 * value
	 * 
	 * @param classificationSchemeItemValue
	 *            value of {@link Vocabulary#classificationSchemeItemValue}
	 *            property,<br>
	 *            Removes property if given <code>null</code>
	 */
	void setClassificationSchemeItemValue(String classificationSchemeItemValue);

	/**
	 * 
	 * @return {@link String} value of
	 *         {@link Vocabulary#classificationSchemeItemValue} property, <br>
	 *         If property is not set, then returns <code>null</code>
	 */
	String getClassificationSchemeItemValue();

	/**
	 * Adds {@link Vocabulary#classifying} property with given value
	 * 
	 * @param administeredItem
	 *            value of {@link Vocabulary#classifying} property
	 */
	void addClassifying(AdministeredItemResource administeredItem);

	/**
	 * Removes the {@link Vocabulary#classifying} property with given value
	 * 
	 * @param administeredItem
	 *            value of {@link Vocabulary#classifying} property to be removed
	 * 
	 */
	void removeClassifying(AdministeredItemResource administeredItem);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#classifying} property values
	 */
	List<AdministeredItemResource> getClassifyings() throws MDRException;

	/**
	 * Sets {@link Vocabulary#containedIn} property with given value and <br>
	 * sets the {@link Vocabulary#containing} property of the
	 * classificationScheme with <code>this</code>
	 * 
	 * @param classificationScheme
	 *            value of {@link Vocabulary#containedIn} property,<br>
	 *            if given <code>null</code> {@link IllegalArgumentException}
	 *            </code>
	 */
	void setContainedIn(ClassificationSchemeResource classificationScheme);

	/**
	 * 
	 * @return {@link ClassificationSchemeResource} object, value of
	 *         {@link Vocabulary#containedIn} property, <br>
	 *         If property is not set, then returns <code>null</code>
	 */
	ClassificationSchemeResource getContainedIn();

	/**
	 * Adds {@link Vocabulary#csiAssociatedIn} property with given value
	 * 
	 * @param csiRelationshipAssociation
	 *            value of {@link Vocabulary#csiAssociatedIn} property
	 */
	void addCSIAssociatedIn(
			ClassificationSchemeItemAssociationResource csiRelationshipAssociation);

	/**
	 * Removed the {@link Vocabulary#csiAssociatedIn} property with given value
	 * 
	 * @param csiRelationshipAssociation
	 *            value of {@link Vocabulary#csiAssociatedIn} property to be
	 *            removed
	 */
	void removeCSIAssociatedIn(
			ClassificationSchemeItemAssociationResource csiRelationshipAssociation);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#csiAssociatedIn} property
	 *         values
	 */
	List<ClassificationSchemeItemAssociationResource> getCSIAssociatedIns()
			throws MDRException;

}