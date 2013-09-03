package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.ReferenceDocument;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;



/**
 * An Administered Item may be described by one or more Reference Documents. For
 * each Reference Document, the Organization that originated the Reference
 * Document must be identified.
 * 
 * @author anil
 * 
 */
public interface ReferenceDocumentResource extends MDRResource, ReferenceDocument {

	/**
	 * Sets {@link Vocabulary#referenceDocumentIdentifier} property with given
	 * value
	 * 
	 * @param referenceDocumentIdentifier
	 *            value of {@link Vocabulary#referenceDocumentIdentifier}
	 *            property, <br>
	 */
	void setReferenceDocumentIdentifier(String referenceDocumentIdentifier);

	/**
	 * 
	 * @return {@link String} value of
	 *         {@link Vocabulary#referenceDocumentIdentifier} property, <br>
	 */
	String getReferenceDocumentIdentifier();

	/**
	 * Sets {@link Vocabulary#referenceDocumentTypeDescription} property with
	 * given value
	 * 
	 * @param referenceDocumentTypeDescription
	 *            value of {@link Vocabulary#referenceDocumentTypeDescription}
	 *            property, <br>
	 *            Removes property if given <code>null</code>
	 */
	void setReferenceDocumentTypeDescription(
			String referenceDocumentTypeDescription);

	/**
	 * 
	 * @return {@link String} value of
	 *         {@link Vocabulary#referenceDocumentTypeDescription} property, <br>
	 *         If property is not set, then returns <code>null</code>
	 */
	String getReferenceDocumentTypeDescription();

	/**
	 * Adds {@link Vocabulary#referenceDocumentLanguageIdentifier} property with
	 * given value
	 * 
	 * @param referenceDocumentLanguageIdentifier
	 *            value of
	 *            {@link Vocabulary#referenceDocumentLanguageIdentifier}
	 *            property
	 */
	void addReferenceDocumentLanguageIdentifier(
			LanguageIdentificationResource referenceDocumentLanguageIdentifier);

	/**
	 * Removes the {@link Vocabulary#referenceDocumentLanguageIdentifier}
	 * property with given value
	 * 
	 * @param referenceDocumentLanguageIdentifier
	 *            value of
	 *            {@link Vocabulary#referenceDocumentLanguageIdentifier}
	 *            property to be removed
	 */
	void removeReferenceDocumentLanguageIdentifier(
			LanguageIdentificationResource referenceDocumentLanguageIdentifier);

	/**
	 * 
	 * @return {@link List} of
	 *         {@link Vocabulary#referenceDocumentLanguageIdentifier} property
	 *         values
	 * @throws MDRException
	 */
	List<LanguageIdentificationResource> getReferenceDocumentLanguageIdentifiers()
			throws MDRException;

	/**
	 * Sets {@link Vocabulary#referenceDocumentTitle} property with given value
	 * 
	 * @param referenceDocumentTitle
	 *            value of {@link Vocabulary#referenceDocumentTitle} property, <br>
	 *            Removes property if given <code>null</code>
	 */
	void setReferenceDocumentTitle(String referenceDocumentTitle);

	/**
	 * 
	 * @return {@link String} value of {@link Vocabulary#referenceDocumentTitle}
	 *         property, <br>
	 *         If property is not set, then returns <code>null</code>
	 */
	String getReferenceDocumentTitle();

	/**
	 * Adds {@link Vocabulary#providedBy} property with given value
	 * 
	 * @param providedBy
	 *            value of {@link Vocabulary#providedBy} property There should
	 *            be at least 1 value for the property, throws
	 *            IllegalArgumentException when try to remove all values
	 */
	void addProvidedBy(OrganizationResource providedBy);

	/**
	 * Removes the {@link Vocabulary#providedBy} property with given value
	 * 
	 * @param providedBy
	 *            value of {@link Vocabulary#providedBy} property to be removed.
	 *            There should be at least 1 value for the property, throws
	 *            IllegalArgumentException when try to remove all values
	 */
	void removeProvidedBy(OrganizationResource providedBy);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#providedBy} property values
	 */
	List<OrganizationResource> getProvidedBy() throws MDRException;

	/**
	 * Adds {@link Vocabulary#describing} property with given value
	 * 
	 * @param describing
	 *            value of {@link Vocabulary#describing} property.
	 */
	void addDescribing(AdministeredItemResource describing);

	/**
	 * Removes the {@link Vocabulary#describing} property with given value
	 * 
	 * @param describing
	 *            value of {@link Vocabulary#describing} property to be removed.
	 */
	void removeDescribing(AdministeredItemResource describing);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#describing} property values
	 */
	List<AdministeredItemResource> getDescribing() throws MDRException;

}