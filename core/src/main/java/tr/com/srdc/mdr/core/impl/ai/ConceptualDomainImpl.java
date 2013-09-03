package tr.com.srdc.mdr.core.impl.ai;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ConceptualDomainRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * 
 * A set of valid Value Meanings. The Value Meanings may either be enumerated or
 * expressed via a description.
 * 
 * @author anil
 * @author serike
 * @author alp
 * 
 */
public abstract class ConceptualDomainImpl extends AdministeredItemImpl
		implements ConceptualDomainResource {

	private static final Logger logger = LoggerFactory
			.getLogger(ConceptualDomainImpl.class);

	/**
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param conceptualDomainAdministrationRecord
	 *            The AdministrationRecord for a
	 *            {@link ConceptualDomainResource}.
	 * @param dimensionality
	 *            Optional. An expression of measurement without units.
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
	 * @param administretedBy
	 *            An Administered Item is administered by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link StewardshipRelationshipResource}.
	 * 
	 */
	public ConceptualDomainImpl(Node n, EnhGraph g,
			AdministrationRecordResource conceptualDomainAdministrationRecord,
			String dimensionality,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having, MDRDatabase mdrDatabase) {
		super(n, g, administeredBy, submittedBy, registeredBy, having,
				mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ConceptualDomain);
		setAdministrationRecord(conceptualDomainAdministrationRecord);
		setDimensionality(dimensionality);
	}

	public ConceptualDomainImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setAdministrationRecord(
			AdministrationRecordResource conceptualDomainAdministrationRecord) {
		if (conceptualDomainAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be specified for Conceptual Domain.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().conceptualDomainAdministrationRecord,
				conceptualDomainAdministrationRecord);
	}

	@Override
	public AdministrationRecordResource getAdministrationRecord() {
		return new AdministrationRecordImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().conceptualDomainAdministrationRecord),
				mdrDatabase);
	}

	@Override
	public void setDimensionality(String dimensionality) {
		setPropertyValue(mdrDatabase.getVocabulary().dimensionality,
				mdrDatabase.getUtil().createTypedLiteral(dimensionality));
	}

	@Override
	public String getDimensionality() {
		RDFNode dimensionality = getPropertyValue(mdrDatabase.getVocabulary().dimensionality);
		if (dimensionality == null) {
			logger.debug("ConceptualDomain does not have dimensionality");
			return null;
		}
		return dimensionality.asLiteral().getString();
	}

	@Override
	public void addSpecifyingDataElementConceptConceptualDomainRelationship(
			DataElementConceptResource specifyingDataElementConceptConceptualDomainRelationship) {
		if (specifyingDataElementConceptConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().specifyingDataElementConceptConceptualDomainRelationship,
				specifyingDataElementConceptConceptualDomainRelationship);
	}

	@Override
	public List<DataElementConceptResource> getSpecifyingDataElementConceptConceptualDomainRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().specifyingDataElementConceptConceptualDomainRelationship);
		return mdrDatabase.getUtil().createList(it,
				DataElementConceptResource.class);
	}

	@Override
	public void removeSpecifyingDataElementConceptConceptualDomainRelationship(
			DataElementConceptResource specifyingDataElementConceptConceptualDomainRelationship) {
		if (specifyingDataElementConceptConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().specifyingDataElementConceptConceptualDomainRelationship,
				specifyingDataElementConceptConceptualDomainRelationship);
	}

	@Override
	public void addRelatedToConceptualDomainRelationship(
			ConceptualDomainRelationshipAssociationResource relatedToConceptualDomainRelationship) {
		if (relatedToConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().relatedToConceptualDomainRelationship,
				relatedToConceptualDomainRelationship);
	}

	@Override
	public void removeRelatedToConceptualDomainRelationship(
			ConceptualDomainRelationshipAssociationResource relatedToConceptualDomainRelationship) {
		if (relatedToConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().relatedToConceptualDomainRelationship,
				relatedToConceptualDomainRelationship);
	}

	@Override
	public List<ConceptualDomainRelationshipAssociationResource> getRelatedToConceptualDomainRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().relatedToConceptualDomainRelationship);
		return mdrDatabase.getUtil().createList(it,
				ConceptualDomainRelationshipAssociationResource.class);
	}

	@Override
	public void addRepresentedByConceptualDomainRepresentation(
			ValueDomainResource representedByConceptualDomainRepresentation) {
		if (representedByConceptualDomainRepresentation == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().representedByConceptualDomainRepresentation,
				representedByConceptualDomainRepresentation);
	}

	@Override
	public void removeRepresentedByConceptualDomainRepresentation(
			ValueDomainResource representedByConceptualDomainRepresentation) {
		if (representedByConceptualDomainRepresentation == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().representedByConceptualDomainRepresentation,
				representedByConceptualDomainRepresentation);
	}

	@Override
	public List<ValueDomainResource> getRepresentedByConceptualDomainRepresentations()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().representedByConceptualDomainRepresentation);
		return mdrDatabase.getUtil().createList(it, ValueDomainResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ConceptualDomainResource asMDRResource() {
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ValueDomain> getValueDomains() {
		return (List<ValueDomain>) mdrDatabase.getQueryFactory()
				.getValueDomainsOfConceptualDomain(this.getURI());
	}

	@Override
	public ValueDomain getValueDomain(String valueDomainID) {
		Resource vdResource = mdrDatabase.getQueryFactory()
				.getAdministeredItem(valueDomainID);
		OntClass vd = mdrDatabase.getOntModel()
				.getOntClass(vdResource.getURI());
		if (vd.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedValueDomain,
				false)) {
			return new EnumeratedValueDomainImpl(vd, mdrDatabase);
		} else {
			return new NonEnumeratedValueDomainImpl(vd, mdrDatabase);
		}
	}

}
