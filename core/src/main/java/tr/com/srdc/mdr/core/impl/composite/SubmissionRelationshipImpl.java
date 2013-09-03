package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.impl.ai.AdministeredItemImpl;
import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * {@link SubmissionRelationshipImpl} is used to represent the relation between
 * <ul>
 * <li>Submission</li>
 * <li>submitting - submittedBy Relation between {@link OrganizationImpl} and
 * {@link AdministeredItemImpl}</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} is backed by Jena - Triple Store, such an holder class
 * is required to represent n-ary relations
 * 
 * @author anil
 * 
 */
public class SubmissionRelationshipImpl extends AbstractMDRResource implements
		SubmissionRelationshipResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createSubmissionRelationship(OrganizationResource,SubmissionResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param submissionOrganization
	 * @param submitter
	 * @param mdrDatabase
	 */
	public SubmissionRelationshipImpl(Node n, EnhGraph g,
			OrganizationResource submissionOrganization,
			SubmissionResource submitter, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().SubmissionRelationship);
		setSubmissionOrganization(submissionOrganization);
		setSubmitter(submitter);
	}

	public SubmissionRelationshipImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setSubmissionOrganization(
			OrganizationResource submissionOrganization) {
		if (submissionOrganization == null) {
			throw new IllegalArgumentException(
					"An Organization must be specified for Submission Relationship");
		}

		setPropertyValue(mdrDatabase.getVocabulary().submissionOrganization,
				submissionOrganization);
	}

	@Override
	public OrganizationResource getSubmissionOrganization() {
		return new OrganizationImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().submissionOrganization),
				mdrDatabase);
	}

	@Override
	public void setSubmitter(SubmissionResource submitter) {
		if (submitter == null) {
			throw new IllegalArgumentException(
					"Submitter must be specified for the Submission Relationship");
		}
		setPropertyValue(mdrDatabase.getVocabulary().submitter, submitter);
	}

	@Override
	public SubmissionResource getSubmitter() {
		return new SubmissionImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().submitter),
				mdrDatabase);
	}

	@Override
	public void addSubmittedItems(AdministeredItemResource submittedItems) {
		if (submittedItems == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().submittedItems, submittedItems);
	}

	@Override
	public List<AdministeredItemResource> getSubmittedItems()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().submittedItems);
		return mdrDatabase.getUtil().createList(it,
				AdministeredItemResource.class);
	}

	@Override
	public void removeSumittedItem(AdministeredItemResource submittedItems) {
		if (submittedItems == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().submittedItems,
				submittedItems);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public SubmissionRelationshipResource asMDRResource() {
		return this;
	}

}
