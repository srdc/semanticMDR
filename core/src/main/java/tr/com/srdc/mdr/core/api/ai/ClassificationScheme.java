package tr.com.srdc.mdr.core.api.ai;

import tr.com.srdc.mdr.core.api.composite.ClassificationSchemeItem;
import tr.com.srdc.mdr.core.model.iso11179.ClassificationSchemeResource;


/**
 * A Classification Scheme may be a taxonomy, a network, an ontology, or any
 * other terminological system. The classification may also be just a list of
 * controlled vocabulary of property words (or terms). The list might be taken
 * from the "leaf level" of a taxonomy. A Classification Scheme is a sub-type of
 * Administered Item, inheriting its attributes and relationships, which allows
 * it to be identified, named, defined and optionally classified. An
 * Administered Item is named within a specific Context, and may have different
 * names in different Contexts. As an Administered Item itself, a Classification
 * Scheme is also named within one or more Contexts. For an Administered Item to
 * be considered to have a name within a Classification Scheme, the Administered
 * Item and the Classification Scheme must share a common Context.
 * 
 * @author anil
 * 
 */
public interface ClassificationScheme extends AdministeredItem {

	@Override
	/**
	 * @return the {@link ClassificationSchemeResource} version this instance.
	 */
	ClassificationSchemeResource asMDRResource();

//	/**
//	 * @return Type name of the Classification Scheme Instance
//	 */
//	String getSchemeName();
//
//	/**
//	 * Changes the current type name of Classification Scheme instance
//	 * 
//	 * @param name
//	 *            new name to be given to Classification Scheme instance, can
//	 *            not be <code>null</code>
//	 */
//	void setSchemeName(String name);
//
	/**
	 * Creates a {@link ClassificationSchemeItem} with <code>null</code>
	 * attributes since they are optional. Also contained property of
	 * ClassificationSchemeItem is set to this ClassificationScheme.
	 * <p>
	 * Optional attributes can be set after object creation.
	 * 
	 * @param typeName
	 *            Optional.
	 * @param itemValue
	 *            Optional.
	 * 
	 * @return ClassificationSchemeItem contained by this ClassificationScheme
	 */
	ClassificationSchemeItem createClassificationSchemeItem(String typeName,
			String itemValue);
//
//	/**
//	 * @return List of all {@link ClassificationSchemeItem} contained by this
//	 *         ClassificationScheme
//	 */
//	List<ClassificationSchemeItem> getContainedSchemeItems()
//			throws MDRException;
}
