package tr.com.srdc.mdr.core.impl.ai;

import java.util.List;

import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.RepresentationClassResource;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


public class RepresentationClassImpl extends AdministeredItemImpl implements
		RepresentationClassResource {

	/**
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param representationClassAdministrationRecord
	 *            Administration Record for the Administered Item
	 * @param administeredBy
	 *            An Administered Item is administered by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link StewardshipRelationshipResource}.
	 * @param submittedBy
	 *            An Administered Item is submitted by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link SubmissionRelationshipResource}.
	 * @param registeredBy
	 *            An {@link AdministeredItemResource} is registered by a
	 *            {@link RegistrationAuthorityResource}.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @param mdrDatabase
	 */
	public RepresentationClassImpl(
			Node n,
			EnhGraph g,
			AdministrationRecordResource representationClassAdministrationRecord,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having, MDRDatabase mdrDatabase) {
		super(n, g, administeredBy, submittedBy, registeredBy, having,
				mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().RepresentationClass);
		setAdministrationRecord(representationClassAdministrationRecord);
	}

	public RepresentationClassImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public AdministrationRecordResource getAdministrationRecord() {
		return new AdministrationRecordImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().representationClassAdministrationRecord),
				mdrDatabase);
	}

	@Override
	public void setAdministrationRecord(
			AdministrationRecordResource representationClassAdministrationRecord) {
		if (representationClassAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be specified for Representation Class.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().representationClassAdministrationRecord,
				representationClassAdministrationRecord);
	}

	@Override
	public void addTypingValueDomainRepresentationClass(
			ValueDomainResource typingValueDomainRepresentationClass) {
		if (typingValueDomainRepresentationClass == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().typingValueDomainRepresentationClass,
				typingValueDomainRepresentationClass);
	}

	@Override
	public List<ValueDomainResource> getTypingValueDomainRepresentationClasses()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().typingValueDomainRepresentationClass);
		return mdrDatabase.getUtil().createList(it, ValueDomainResource.class);
	}

	@Override
	public void removeTypingValueDomainRepresentationClasses(
			ValueDomainResource typingValueDomainRepresentationClass) {
		if (typingValueDomainRepresentationClass == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().typingValueDomainRepresentationClass,
				typingValueDomainRepresentationClass);
	}

	@Override
	public void addTypingDataElementRepresentationClass(
			DataElementResource typingDataElementRepresentationClass) {
		if (typingDataElementRepresentationClass == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value for the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().typingDataElementRepresentationClass,
				typingDataElementRepresentationClass);
	}

	@Override
	public void removeTypingDataElementRepresentationClass(
			DataElementResource typingDataElementRepresentationClass) {
		if (typingDataElementRepresentationClass == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value for the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().typingDataElementRepresentationClass,
				typingDataElementRepresentationClass);
	}

	@Override
	public List<DataElementResource> getTypingDataElementRepresentationClasses()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().typingDataElementRepresentationClass);
		return mdrDatabase.getUtil().createList(it, DataElementResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public RepresentationClassResource asMDRResource() {
		return this;
	}
}
