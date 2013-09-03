package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.composite.LanguageIdentification;
import tr.com.srdc.mdr.core.api.composite.ReferenceDocument;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ReferenceDocumentResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * An Organization can play one or more roles with respect to a Metadata
 * Registry. The roles currently recognized in this part of ISO/IEC 11179 are:
 * Registration Authority, reference organization, steward (of an Administered
 * Item) – represented by the relationship stewardship – and submitter (of an
 * Administered Item) – represented by the relationship submission
 * 
 * @author anil
 * 
 */
public class OrganizationImpl extends AbstractMDRResource implements
		OrganizationResource {

	private static final Logger logger = LoggerFactory
			.getLogger(OrganizationImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createOrganization(String,String)} to avoid
	 * entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param organizationName
	 *            Name of the Organization
	 * @param organizationMailAddress
	 *            Optional. Mail Address of the Organization
	 * @param mdrDatabase
	 */
	public OrganizationImpl(Node n, EnhGraph g, String organizationName,
			String organizationMailAddress, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Organization);
		setOrganizationName(organizationName);
		setOrganizationMailAddress(organizationMailAddress);
	}

	public OrganizationImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setOrganizationName(String organizationName) {
		if (Util.isNull(organizationName)) {
			throw new IllegalArgumentException(
					"Organization Name must be specified for Organization");
		}
		setPropertyValue(mdrDatabase.getVocabulary().organizationName,
				mdrDatabase.getUtil().createTypedLiteral(organizationName));
	}

	@Override
	public String getOrganizationName() {
		return getPropertyValue(mdrDatabase.getVocabulary().organizationName)
				.asLiteral().getString();
	}

	@Override
	public void setOrganizationMailAddress(String organizationMailAddress) {
		setPropertyValue(
				mdrDatabase.getVocabulary().organizationMailAddress,
				mdrDatabase.getUtil().createTypedLiteral(
						organizationMailAddress));
	}

	@Override
	public String getOrganizationMailAddress() {
		RDFNode organizationMailAddress = getPropertyValue(mdrDatabase
				.getVocabulary().organizationMailAddress);
		if (organizationMailAddress == null) {
			logger.debug("Organization does not have organizationMailAddress");
			return null;
		}
		return organizationMailAddress.asLiteral().getString();
	}

	@Override
	public void addAdministrating(StewardshipRelationshipResource administrating) {
		if (administrating == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().administering, administrating);
	}

	@Override
	public List<StewardshipRelationshipResource> getAdministrating()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().administering);
		return mdrDatabase.getUtil().createList(it,
				StewardshipRelationshipResource.class);
	}

	@Override
	public void removeAdministrating(
			StewardshipRelationshipResource administrating) {
		if (administrating == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().administering,
				administrating);
	}

	@Override
	public void addSubmitting(SubmissionRelationshipResource submitting) {
		if (submitting == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().submitting, submitting);
	}

	@Override
	public List<SubmissionRelationshipResource> getSubmitting()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().submitting);
		return mdrDatabase.getUtil().createList(it,
				SubmissionRelationshipResource.class);
	}

	@Override
	public void removeSubmitting(SubmissionRelationshipResource submitting) {
		if (submitting == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().submitting, submitting);
	}

	@Override
	public void addProviding(ReferenceDocumentResource providing) {
		if (providing == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().providing, providing);
	}

	@Override
	public void removeProviding(ReferenceDocumentResource providing) {
		if (providing == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}

		removeProperty(mdrDatabase.getVocabulary().providing, providing);
	}

	@Override
	public List<ReferenceDocumentResource> getProviding() throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().providing);
		return mdrDatabase.getUtil().createList(it,
				ReferenceDocumentResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public OrganizationResource asMDRResource() {
		return this;
	}

	@Override
	public String getName() {
		return this.getOrganizationName();
	}

	@Override
	public String getMailAddress() {
		return this.getOrganizationMailAddress();
	}

	@Override
	public ReferenceDocument createReferenceDocument(String identifier) {
		return this.createReferenceDocument(identifier, null, null, null);
	}

	@Override
	public ReferenceDocument createReferenceDocument(String identifier,
			String typeDescription,
			LanguageIdentification languageIdentification, String title) {
		ReferenceDocumentResource refDoc = mdrDatabase.getResourceFactory()
				.createReferenceDocument(identifier, this, typeDescription,
						languageIdentification.asMDRResource(), title);
		logger.debug(
				"Reference Document {} created and will be added to Organization",
				identifier);
		this.addProviding(refDoc);
		return refDoc;
	}

}
