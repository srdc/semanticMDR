package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * For each Administered Item, an Organization shall be identified as the
 * submitter. This relationship identifies a submission contact for the
 * Administered Item
 * 
 * @author anil
 * 
 */
public class SubmissionImpl extends AbstractMDRResource implements
		SubmissionResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createSubmission(ContactResource)} to avoid
	 * entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param submissionContact
	 *            Submitter Contact Detail of Submission
	 * @param mdrDatabase
	 */
	public SubmissionImpl(Node n, EnhGraph g,
			ContactResource submissionContact, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Submission);
		setSubmissionContact(submissionContact);
	}

	public SubmissionImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setSubmissionContact(ContactResource submissionContact) {
		if (submissionContact == null) {
			throw new IllegalArgumentException(
					"Submission Contact must be specified for Submission");
		}

		setPropertyValue(mdrDatabase.getVocabulary().submissionContact,
				submissionContact);

	}

	@Override
	public ContactResource getSubmissionContact() {
		return new ContactImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().submissionContact),
				mdrDatabase);
	}

	@Override
	public void addSubmits(SubmissionRelationshipResource submits) {
		if (submits == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().submits, submits);
	}

	@Override
	public List<SubmissionRelationshipResource> getSubmits()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().submits);
		return mdrDatabase.getUtil().createList(it,
				SubmissionRelationshipResource.class);
	}

	@Override
	public void removeSubmits(SubmissionRelationshipResource submits) {
		if (submits == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().submits, submits);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public SubmissionResource asMDRResource() {
		return this;
	}

}
