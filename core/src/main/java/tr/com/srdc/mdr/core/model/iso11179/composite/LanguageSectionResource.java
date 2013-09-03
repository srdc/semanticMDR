package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.LanguageSection;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * If a registry supports multiple languages, the language(s) associated with
 * particular names and definitions need to be identified. A Language Section
 * partitions a Terminological Entry by Language. A language section language
 * identifier identifies the Language associated with a particular Language
 * Section. A Language Section contains zero or more Designations. A Language
 * Section contains zero or more Definitions.
 * 
 * @author anil
 * 
 */
public interface LanguageSectionResource extends MDRResource, LanguageSection {

	/**
	 * Sets {@link Vocabulary#languageSectionLanguageIdentifier} property with
	 * given value
	 * 
	 * @param languageSectionLanguageIdentifier
	 * 
	 */
	void setLanguageSectionLanguageIdentifier(
			LanguageIdentificationResource languageSectionLanguageIdentifier);

	/**
	 * 
	 * @return Value of {@link Vocabulary#languageSectionLanguageIdentifier}
	 *         property
	 */
	LanguageIdentificationResource getLanguageSectionLanguageIdentifier();

	/**
	 * Adds {@link Vocabulary#partitioning} property with given value
	 * 
	 * @param partitioning
	 *            if <code>null</code>, throws {@link IllegalArgumentException}
	 */
	void addPartitioning(TerminologicalEntryResource partitioning);

	/**
	 * Removes {@link Vocabulary#partitioning} property with given value
	 * 
	 * @param partitioning
	 *            if <code>null</code>, throw {@link IllegalArgumentException}
	 */
	void removePartitioning(TerminologicalEntryResource partitioning);

	/**
	 * 
	 * @return {@link List} of all {@link Vocabulary#partitioning} proprety
	 *         values
	 * @throws MDRException
	 */
	List<TerminologicalEntryResource> getPartitionings() throws MDRException;

	/**
	 * Adds {@link Vocabulary#containingNameEntry} property with given value
	 * 
	 * @param containingNameEntry
	 *            if <code>null</code>, throws {@link IllegalArgumentException}
	 */
	void addContainingNameEntry(DesignationResource containingNameEntry);

	/**
	 * Removes {@link Vocabulary#containingNameEntry} property with given value
	 * 
	 * @param containingNameEntry
	 *            if <code>null</code>, throw {@link IllegalArgumentException}
	 */
	void removeContainingNameEntry(DesignationResource containingNameEntry);

	/**
	 * 
	 * @return {@link List} of all {@link Vocabulary#containingNameEntry}
	 *         proprety values
	 * @throws MDRException
	 */
	List<DesignationResource> getContainingNameEntry() throws MDRException;

	/**
	 * Adds {@link Vocabulary#containingDefinitionEntry} property with given
	 * value
	 * 
	 * @param containingDefinitionEntry
	 *            if <code>null</code>, throws {@link IllegalArgumentException}
	 */
	void addContainigDefinitionEntry(DefinitionResource containingDefinitionEntry);

	/**
	 * Removes {@link Vocabulary#containingDefinitionEntry} property with given
	 * value
	 * 
	 * @param containingDefinitionEntry
	 *            if <code>null</code>, throw {@link IllegalArgumentException}
	 */
	void removeContainigDefinitionEntry(DefinitionResource containingDefinitionEntry);

	/**
	 * 
	 * @return {@link List} of all {@link Vocabulary#containingDefinitionEntry}
	 *         proprety values
	 * @throws MDRException
	 */
	List<DefinitionResource> getContainigDefinitionEntry() throws MDRException;

}