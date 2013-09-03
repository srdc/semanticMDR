package tr.com.srdc.mdr.core.impl.ai;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.NonEnumeratedValueDomain;
import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedValueDomainResource;
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


/**
 * 
 * A Conceptual Domain that is not specified by a list of all valid Value
 * Meanings.
 * 
 * @author serike
 * 
 */
public class NonEnumeratedConceptualDomainImpl extends ConceptualDomainImpl
		implements NonEnumeratedConceptualDomainResource {

/**
	 *  Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createNonEnumeratedConceptualDomain(AdministrationRecordResource, String, StewardshipRelationshipResource, SubmissionRelationshipResource, RegistrationAuthorityResource, AdministeredItemContextResource, String)
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param conceptualDomainAdministrationRecord
	 *            The AdministrationRecord for a {@link ConceptualDomainResource}.
	 * @param dimensionality
	 *            Optional. An expression of measurement without units.
	 * @param submittedBy
	 *            An Administered Item is submitted by an {@link OrganizationResource}
	 *            represented by the {@link SubmissionRelationshipResource}.
	 * @param registeredBy
	 *            An {@link AdministeredItemResource} is registered by a
	 *            {@link RegistrationAuthorityResource}.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @param nonEnumeratedConceptualDomainDescription
	 *            A description or specification of a rule, reference, or range
	 *            for a set of all Value Meanings for the Conceptual Domain.
	 * @param mdrDatabase 
	 * @param administretedBy
	 *            An Administered Item is administered by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link StewardshipRelationshipResource}.
	 * 
	 */
	public NonEnumeratedConceptualDomainImpl(Node n, EnhGraph g,
			AdministrationRecordResource conceptualDomainAdministrationRecord,
			String dimensionality,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having,
			String nonEnumeratedConceptualDomainDescription,
			MDRDatabase mdrDatabase) {
		super(n, g, conceptualDomainAdministrationRecord, dimensionality,
				administeredBy, submittedBy, registeredBy, having, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().NonEnumeratedConceptualDomain);
		setNonEnumeratedConceptualDomainDescription(nonEnumeratedConceptualDomainDescription);
	}

	public NonEnumeratedConceptualDomainImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setNonEnumeratedConceptualDomainDescription(
			String nonEnumeratedConceptualDomainDescription) {
		if (Util.isNull(nonEnumeratedConceptualDomainDescription)) {
			throw new IllegalArgumentException(
					"Non Enumerated Conceptual Domain Description must be specified for Non Enumerated Conceptual Domain.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().nonEnumeratedConceptualDomainDescription,
				mdrDatabase.getUtil().createTypedLiteral(
						nonEnumeratedConceptualDomainDescription));
	}

	@Override
	public String getNonEnumeratedConceptualDomainDescription() {
		return getPropertyValue(
				mdrDatabase.getVocabulary().nonEnumeratedConceptualDomainDescription)
				.asLiteral().getString();
	}

	@Override
	public void addRepresentedByNonEnumeratedConceptualDomainRepresentation(
			NonEnumeratedValueDomainResource representedByNonEnumeratedConceptualDomainRepresentation) {
		if (representedByNonEnumeratedConceptualDomainRepresentation == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().representedByNonEnumeratedConceptualDomainRepresentation,
				representedByNonEnumeratedConceptualDomainRepresentation);
	}

	@Override
	public List<NonEnumeratedValueDomainResource> getRepresentedByNonEnumeratedConceptualDomainRepresentations()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().representedByNonEnumeratedConceptualDomainRepresentation);
		return mdrDatabase.getUtil().createList(it,
				NonEnumeratedValueDomainResource.class);
	}

	@Override
	public void removeRepresentedByNonEnumeratedConceptualDomainRepresentation(
			NonEnumeratedValueDomainResource representedByNonEnumeratedConceptualDomainRepresentation) {
		if (representedByNonEnumeratedConceptualDomainRepresentation == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().representedByNonEnumeratedConceptualDomainRepresentation,
				representedByNonEnumeratedConceptualDomainRepresentation);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public NonEnumeratedConceptualDomainResource asMDRResource() {
		return this;
	}

	@Override
	public String getDomainDescription() {
		return this.getNonEnumeratedConceptualDomainDescription();
	}

	@Override
	public NonEnumeratedValueDomain createNonEnumeratedValueDomain(String name,
			String definition, Datatype dataType) {
		return getContext().createNonEnumeratedValueDomain(this, name,
				definition, dataType);
	}

	@Override
	public boolean isEnumeratedConceptualDomain() {
		return false;
	}

}
