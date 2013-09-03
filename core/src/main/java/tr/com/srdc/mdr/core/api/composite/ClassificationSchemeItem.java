package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.api.ai.ClassificationScheme;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemResource;

/**
 * 
 * A Classification Scheme Item represents an individual item within a
 * Classification Scheme. The Classification Scheme Item may have either a
 * classification scheme item type name, a classification scheme item value, or
 * both.
 * 
 * @author anil
 * 
 */
public interface ClassificationSchemeItem extends MDRNode {

	@Override
	/**
	 * @return the {@link ClassificationSchemeItemResource} version this instance.
	 */
	ClassificationSchemeItemResource asMDRResource();

	/**
	 * 
	 * @return the {@link ClassificationScheme} containing this
	 *         {@link ClassificationSchemeItem}
	 */
	ClassificationScheme getClassificationScheme();

	/**
	 * 
	 * @return the type name of a {@link ClassificationSchemeItem}. If attribute
	 *         does not exist return <code>null</code>
	 */
	String getTypeName();

	/**
	 * 
	 * @return the item value of a {@link ClassificationSchemeItem}. If
	 *         attribute does not exist return <code>null</code>
	 */
	String getItemValue();
}
