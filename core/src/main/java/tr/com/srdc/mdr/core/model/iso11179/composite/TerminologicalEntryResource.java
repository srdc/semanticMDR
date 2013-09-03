package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * A Terminological Entry applies to an Administered Item in a particular
 * Context. It provides a grouping of Designations and Definitions partitioned
 * into Language Sections, allowing the Administered Item to be named and
 * defined within the Context in multiple languages.
 * 
 * @author anil
 * 
 */
public interface TerminologicalEntryResource extends MDRResource {

	/**
	 * Adds {@link Vocabulary#containingTerminologicalEntryLanguage} property
	 * with given value
	 * 
	 * @param containingTerminologicalEntryLanguage
	 *            if <code>null</code> throws {@link IllegalArgumentException}
	 */
	void addContainingTerminologicalEntryLanguage(
			LanguageSectionResource containingTerminologicalEntryLanguage);

	/**
	 * Removes {@link Vocabulary#containingTerminologicalEntryLanguage} property
	 * with given value <br>
	 * 
	 * @param containingTerminologicalEntryLanguage
	 *            value of
	 *            {@link Vocabulary#containingTerminologicalEntryLanguage}
	 *            property to be removed. There should be at least 1 value for
	 *            the property, throws IllegalArgumentException when try to
	 *            remove all values
	 */
	void removeContainingTerminologicalEntryLanguage(
			LanguageSectionResource containingTerminologicalEntryLanguage);

	/**
	 * 
	 * @return {@link List} of
	 *         {@link Vocabulary#containingTerminologicalEntryLanguage} values
	 * @throws MDRException
	 */
	List<LanguageSectionResource> getContaninigTerminologicalEntryLanguage()
			throws MDRException;

	/**
	 * Adds {@link Vocabulary#terminologicalEntryAdministeredItemContext} with
	 * given value
	 * 
	 * @param terminologicalEntryAdministeredItemContext
	 */
	void addTerminologicalEntryAdministeredItemContext(
			AdministeredItemContextResource terminologicalEntryAdministeredItemContext);

	/**
	 * Removes {@link Vocabulary#terminologicalEntryAdministeredItemContext}
	 * proprety with given value
	 * 
	 * @param terminologicalEntryAdministeredItemContext
	 */
	void removeTerminologicalEntryAdministeredItemContext(
			AdministeredItemContextResource terminologicalEntryAdministeredItemContext);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#terminologicalEntryAdministeredItemContext}
	 *         property values
	 * @throws MDRException
	 */
	List<AdministeredItemContextResource> getTerminologicalEntryAdministeredItemContext()
			throws MDRException;

}