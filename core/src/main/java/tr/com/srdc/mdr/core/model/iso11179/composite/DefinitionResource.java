package tr.com.srdc.mdr.core.model.iso11179.composite;

import tr.com.srdc.mdr.core.api.composite.Definition;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;

/**
 * The Definition class provides the defining entry of a Language Section in the
 * Terminological Entry for an Administered Item in a particular Context. In
 * other words, it is where the definition for an Administered Item is specified
 * in a particular language for a particular Context. Where multiple Definitions
 * are provided within the same Language Section, one of them may be specified
 * as the preferred definition.
 * 
 * @author anil
 * 
 */
public interface DefinitionResource extends MDRResource, Definition {

	/**
	 * Sets {@link Vocabulary#definingEntryOf} property with given value
	 * 
	 * @param definingEntryOf
	 */
	void setDefiningEntryOf(LanguageSectionResource definingEntryOf);

	/**
	 * 
	 * @return value of {@link Vocabulary#definingEntryOf} property
	 */
	LanguageSectionResource getDefiningEntryOf();

	/**
	 * Sets {@link Vocabulary#definitionText} property with given String
	 * 
	 * @param definitionText
	 *            Name of the Definition
	 */
	void setDefinitionText(String definitionText);

	/**
	 * 
	 * @return value of the {@link Vocabulary#definitionText} property
	 */
	String getDefinitionText();

	/**
	 * Sets {@link Vocabulary#preferredDefinition} property with given value
	 * 
	 * @param preferredDefinition
	 *            <code>true</code> or <code>false</code>
	 */
	void setPreferredDefinition(boolean preferredDefinition);

	/**
	 * 
	 * @return <code>true</code> if Definition is selected as Preferred
	 *         Definition, <br>
	 *         <code>false</code> otherwise
	 */
	boolean isPreferredDefinition();

	/**
	 * Sets {@link Vocabulary#definitionSourceReference} property with given
	 * value
	 * 
	 * @param definitionSourceReference
	 *            if given <code>null</code>, removes property
	 */
	void setDefinitionSourceReference(
			ReferenceDocumentResource definitionSourceReference);

	/**
	 * 
	 * @return value of the {@link Vocabulary#definitionSourceReference}
	 *         property, <code>null</code> if such property does not exists
	 */
	ReferenceDocumentResource getDefinitionSourceReference();

	/**
	 * Sets {@link Vocabulary#specificallyUsing}
	 * 
	 * @param specificallyUsing
	 *            if given <code>null</code>, removes property
	 */
	void setSpecificallyUsing(DesignationResource specificallyUsing);

	/**
	 * 
	 * @return value of the {@link Vocabulary#specificallyUsing} property,
	 *         <code>null</code> if such property does not exist
	 */
	DesignationResource getSpecificallyUsing();

}