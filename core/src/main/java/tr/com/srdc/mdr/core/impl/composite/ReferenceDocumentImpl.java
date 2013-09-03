package tr.com.srdc.mdr.core.impl.composite;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.composite.LanguageIdentification;
import tr.com.srdc.mdr.core.api.composite.Organization;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageIdentificationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ReferenceDocumentResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * An Administered Item may be described by one or more Reference Documents. For
 * each Reference Document, the Organization that originated the Reference
 * Document must be identified.
 * 
 * @author anil
 * 
 */
public class ReferenceDocumentImpl extends AbstractMDRResource implements
		ReferenceDocumentResource {

	private static final Logger logger = LoggerFactory
			.getLogger(ReferenceDocumentImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createClassificationSchemeItem(String,String)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param referenceDocumentIdentifier
	 *            Identifier for Reference Document
	 * @param providedBy
	 *            Organization which provides the Documents
	 * @param referenceDocumentTypeDescription
	 *            Optional. Type Descriptor for Reference Document
	 * @param referenceDocumentLanguageIdentifier
	 *            Optional. Language Identifier for the Type Reference Document
	 * @param referenceDocumentTitle
	 *            Optional. Title of the Reference Document
	 * @param mdrDatabase
	 */
	public ReferenceDocumentImpl(Node n, EnhGraph g,
			String referenceDocumentIdentifier,
			OrganizationResource providedBy,
			String referenceDocumentTypeDescription,
			LanguageIdentificationResource referenceDocumentLanguageIdentifier,
			String referenceDocumentTitle, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ReferenceDocument);
		setReferenceDocumentIdentifier(referenceDocumentIdentifier);
		addProvidedBy(providedBy);
		setReferenceDocumentTypeDescription(referenceDocumentTypeDescription);
		if (referenceDocumentLanguageIdentifier != null) {
			addReferenceDocumentLanguageIdentifier(referenceDocumentLanguageIdentifier);
		}
		setReferenceDocumentTitle(referenceDocumentTitle);
	}

	public ReferenceDocumentImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setReferenceDocumentIdentifier(
			String referenceDocumentIdentifier) {
		if (Util.isNull(referenceDocumentIdentifier)) {
			throw new IllegalArgumentException(
					"Reference Document Identifier must be specified for ReferenceDocument");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().referenceDocumentIdentifier,
				mdrDatabase.getUtil().createTypedLiteral(
						referenceDocumentIdentifier));
	}

	@Override
	public String getReferenceDocumentIdentifier() {
		return getPropertyValue(
				mdrDatabase.getVocabulary().referenceDocumentIdentifier)
				.asLiteral().getString();
	}

	@Override
	public void setReferenceDocumentTypeDescription(
			String referenceDocumentTypeDescription) {
		setPropertyValue(
				mdrDatabase.getVocabulary().referenceDocumentTypeDescription,
				mdrDatabase.getUtil().createTypedLiteral(
						referenceDocumentTypeDescription));

	}

	@Override
	public String getReferenceDocumentTypeDescription() {
		RDFNode referenceDocumentTypeDescription = getPropertyValue(mdrDatabase
				.getVocabulary().referenceDocumentTypeDescription);
		if (referenceDocumentTypeDescription == null) {
			logger.debug("ReferenceDocument does not have referenceDocumentTypeDescription");
			return null;
		}
		return referenceDocumentTypeDescription.asLiteral().getString();
	}

	@Override
	public void addReferenceDocumentLanguageIdentifier(
			LanguageIdentificationResource referenceDocumentLanguageIdentifier) {
		if (referenceDocumentLanguageIdentifier != null) {
			addProperty(
					mdrDatabase.getVocabulary().referenceDocumentLanguageIdentifier,
					referenceDocumentLanguageIdentifier);
		} else {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
	}

	@Override
	public void removeReferenceDocumentLanguageIdentifier(
			LanguageIdentificationResource referenceDocumentLanguageIdentifier) {
		if (referenceDocumentLanguageIdentifier == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().referenceDocumentLanguageIdentifier,
				referenceDocumentLanguageIdentifier);
	}

	@Override
	public List<LanguageIdentificationResource> getReferenceDocumentLanguageIdentifiers()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().referenceDocumentLanguageIdentifier);
		return mdrDatabase.getUtil().createList(it,
				LanguageIdentificationResource.class);
	}

	@Override
	public void setReferenceDocumentTitle(String referenceDocumentTitle) {
		setPropertyValue(mdrDatabase.getVocabulary().referenceDocumentTitle,
				mdrDatabase.getUtil()
						.createTypedLiteral(referenceDocumentTitle));
	}

	@Override
	public String getReferenceDocumentTitle() {
		RDFNode referenceDocumentTitle = getPropertyValue(mdrDatabase
				.getVocabulary().referenceDocumentTitle);
		if (referenceDocumentTitle == null) {
			logger.debug("ReferenceDocument does not have referenceDocumentTitle");
			return null;
		}
		return referenceDocumentTitle.asLiteral().getString();
	}

	@Override
	public void addProvidedBy(OrganizationResource providedBy) {
		if (providedBy == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().providedBy, providedBy);
	}

	@Override
	public void removeProvidedBy(OrganizationResource providedBy) {
		if (providedBy == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		if (listProperties(mdrDatabase.getVocabulary().providedBy).toSet()
				.size() <= 1) {
			throw new IllegalArgumentException(
					"At least 1 providedBy must be specified for Reference Document");
		}
		removeProperty(mdrDatabase.getVocabulary().providedBy, providedBy);
	}

	@Override
	public List<OrganizationResource> getProvidedBy() throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().providedBy);
		return mdrDatabase.getUtil().createList(it, OrganizationResource.class);
	}

	@Override
	public void addDescribing(AdministeredItemResource describing) {
		if (describing == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().describing, describing);
	}

	@Override
	public void removeDescribing(AdministeredItemResource describing) {
		if (describing == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}

		removeProperty(mdrDatabase.getVocabulary().describing, describing);
	}

	@Override
	public List<AdministeredItemResource> getDescribing() throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().describing);
		return mdrDatabase.getUtil().createList(it,
				AdministeredItemResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ReferenceDocumentResource asMDRResource() {
		return this;
	}

	@Override
	public String getIdentifier() {
		return this.getReferenceDocumentIdentifier();
	}

	@Override
	public String getTypeDescription() {
		return this.getReferenceDocumentTypeDescription();
	}

	@Override
	public List<LanguageIdentification> getLanguageIdentifications()
			throws MDRException {
		return Collections.<LanguageIdentification> unmodifiableList(this
				.getReferenceDocumentLanguageIdentifiers());
	}

	@Override
	public String getTitle() {
		return this.getReferenceDocumentTitle();
	}

	@Override
	public List<Organization> getProviderOrganizations() throws MDRException {
		return Collections
				.<Organization> unmodifiableList(this.getProvidedBy());
	}

}
