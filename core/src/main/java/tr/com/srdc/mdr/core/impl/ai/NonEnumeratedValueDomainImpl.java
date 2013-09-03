package tr.com.srdc.mdr.core.impl.ai;

import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DatatypeResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.UnitOfMeasureResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * A Value Domain that is specified by a description rather than a list of all
 * Permissible Values.
 * 
 * @author serike
 * 
 */
public class NonEnumeratedValueDomainImpl extends ValueDomainImpl implements
		NonEnumeratedValueDomainResource {

/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createNonEnumeratedValueDomain(AdministrationRecordResource, DatatypeResource, UnitOfMeasureResource, Integer, String, StewardshipRelationshipResource, SubmissionRelationshipResource, RegistrationAuthorityResource, AdministeredItemContextResource, ConceptualDomainResource, String, NonEnumeratedConceptualDomainResource)
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The grapwhich holds all triples.
	 * @param valueDomainAdministrationRecord
	 *            The Administration Record for a {@link ValueDomainResource}.
	 * @param valueDomainDatatype
	 * 		   The Datatype used in a Value Domain.
	 * @param valueDomainUnitOfMeasure
	 * 		   The unit of measure used in a Value Domain.
	 * @param valueDomainMaximumCharacterQuantity
	 * 		   The maximum number of characters to represent the Data Element value.
	 * @param valueDomainFormat
	 * 		   A template for the structure of the presentation of the Value(s).
	 * 		   EXAMPLE – YYYY-MM-DD for a date.
	 * @param administeredBy
	 *            An Administered Item is administered by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link StewardshipRelationshipResource}.
	 * @param submittedBy
	 *            An Administered Item is submitted by an {@link OrganizationResource}
	 *            represented by the {@link SubmissionRelationshipResource}.
	 * @param registeredBy
	 *            An {@link AdministeredItemResource} is registered by a
	 *            {@link RegistrationAuthorityResource}.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @param representingConceptualDomainRepresentation
	 * 			An {@link ValueDomainResource} has to have at least one
	 *            {@link ConceptualDomainResource}.
	 * @param nonEnumeratedDomainDescription
	 *  		   A description or specification of a rule, reference, or range for a 
	 *  		  set of all Permissible Values for the Value Domain.
	 * @param representingNonEnumeratedConceptualDomainRepresentation
	 * 		  An {@link NonEnumeratedValueDomainResource} has to have at least one
	 *           {@link NonEnumeratedConceptualDomainResource}.
	 * @param mdrDatabase 
	 */

	public NonEnumeratedValueDomainImpl(
			Node n,
			EnhGraph g,
			AdministrationRecordResource valueDomainAdministrationRecord,
			DatatypeResource valueDomainDatatype,
			UnitOfMeasureResource valueDomainUnitOfMeasure,
			Integer valueDomainMaximumCharacterQuantity,
			String valueDomainFormat,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having,
			ConceptualDomainResource representingConceptualDomainRepresentation,
			String nonEnumeratedDomainDescription,
			NonEnumeratedConceptualDomainResource representingNonEnumeratedConceptualDomainRepresentation,
			MDRDatabase mdrDatabase) {
		super(n, g, valueDomainAdministrationRecord, valueDomainDatatype,
				valueDomainUnitOfMeasure, valueDomainMaximumCharacterQuantity,
				valueDomainFormat, administeredBy, submittedBy, registeredBy,
				having, representingConceptualDomainRepresentation, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().NonEnumeratedValueDomain);
		setNonEnumeratedDomainDescription(nonEnumeratedDomainDescription);
		setRepresentingNonEnumeratedConceptualDomainRepresentation(representingNonEnumeratedConceptualDomainRepresentation);
	}

	public NonEnumeratedValueDomainImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setNonEnumeratedDomainDescription(
			String nonEnumeratedDomainDescription) {
		if (Util.isNull(nonEnumeratedDomainDescription)) {
			throw new IllegalArgumentException(
					"Non Enumerated Domain Description must be specified for Non Enumerated Value Domain.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().nonEnumeratedDomainDescription,
				mdrDatabase.getUtil().createTypedLiteral(
						nonEnumeratedDomainDescription));
	}

	@Override
	public String getNonEnumeratedDomainDescription() {
		return getPropertyValue(
				mdrDatabase.getVocabulary().nonEnumeratedDomainDescription)
				.asLiteral().getString();
	}

	@Override
	public void setRepresentingNonEnumeratedConceptualDomainRepresentation(
			NonEnumeratedConceptualDomainResource representingNonEnumeratedConceptualDomainRepresentation) {
		if (representingNonEnumeratedConceptualDomainRepresentation == null) {
			throw new IllegalArgumentException(
					"Representing Non Enumerated Value Domain Representation must be specified for Non Enumerated Value Domain.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().representingNonEnumeratedConceptualDomainRepresentation,
				representingNonEnumeratedConceptualDomainRepresentation);
	}

	@Override
	public NonEnumeratedConceptualDomainResource getRepresentingNonEnumeratedConceptualDomainRepresentation() {
		return new NonEnumeratedConceptualDomainImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().representingNonEnumeratedConceptualDomainRepresentation),
				mdrDatabase);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public NonEnumeratedValueDomainResource asMDRResource() {
		return this;
	}

	@Override
	public String getDomainDescription() {
		return this.getNonEnumeratedDomainDescription();
	}

	// @Override
	// public String getDomainDescription() {
	// return this.getNonEnumeratedDomainDescription();
	// }

}
