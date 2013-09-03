package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.Context;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageIdentificationResource;



/**
 * A Context defines the scope within which the subject data has meaning. A
 * Context may be a business domain, an information subject area, an information
 * system, a database, file, data model, standard document, or any other
 * environment determined by the owner of the registry
 * 
 * @author anil
 * 
 */
public interface ContextResource extends AdministeredItemResource, Context {

	/**
	 * Set the {@link AdministrationRecordResource} of this {@link ContextResource}.
	 * 
	 * @param propertyAdministrationRecord
	 * <br>
	 *            An {@link AdministrationRecordResource} of a {@link ContextResource}.
	 */
	void setAdministrationRecord(
			AdministrationRecordResource contextAdministrationRecord);

	/**
	 * @return the {@link AdministrationRecordResource} of a {@link ContextResource}.
	 * 
	 */
	AdministrationRecordResource getAdministrationRecord();

	/**
	 * @return the {@link Vocabulary#contextDescription} of a {@link ContextResource}
	 * 
	 */
	String getContextDescription();

	/**
	 * Sets the {@link Vocabulary#contextDescription} of a {@link ContextResource}.
	 * 
	 * @param contextDescription
	 */
	void setContextDescription(String contextDescription);

	/**
	 * 
	 * @return the {@link Vocabulary#contextDescriptionLanguageIdentifier} of a
	 *         {@link ContextResource}
	 */
	LanguageIdentificationResource getContextDescriptionLanguageIdentifier();

	/**
	 * Sets the {@link Vocabulary#contextDescriptionLanguageIdentifier} of a
	 * {@link ContextResource}.
	 * 
	 * @param contextDescriptionLanguageIdentifier
	 */
	void setContextDescriptionLanguageIdentifier(
			LanguageIdentificationResource contextDescriptionLanguageIdentifier);

	/**
	 * Adds {@link Vocabulary#contextAdministeredItemContext} property with
	 * given value
	 * 
	 * @param administeredItemContext
	 *            value of {@link Vocabulary#contextAdministeredItemContext}
	 *            property
	 */
	void addAdministeredItemContext(
			AdministeredItemContextResource administeredItemContext);

	/**
	 * Removes {@link Vocabulary#contextAdministeredItemContext} property with
	 * given value
	 * 
	 * @param administeredItemContext
	 *            value of {@link Vocabulary#contextAdministeredItemContext}
	 *            property to be removed
	 */
	void removeAdministeredItemContext(
			AdministeredItemContextResource administeredItemContext);

	/**
	 * Method for getting the list of
	 * {@link Vocabulary#contextAdministeredItemContext} property values of the
	 * {@link AdministeredItemContextResource}
	 * 
	 * @return List of all {@link Vocabulary#contextAdministeredItemContext}
	 *         property values
	 */
	List<AdministeredItemContextResource> getAdministeredItemContexts()
			throws MDRException;

}