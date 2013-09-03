package tr.com.srdc.mdr.core.impl.ai;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.api.composite.UnitOfMeasure;
import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.impl.composite.DatatypeImpl;
import tr.com.srdc.mdr.core.impl.composite.UnitOfMeasureImpl;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.RepresentationClassResource;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DatatypeResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.UnitOfMeasureResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueDomainRelationshipAssociationResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * A set of Permissible Values. The Value Domain provides representation, but
 * has no implication as to what Data Element Concept the Values may be
 * associated with nor what the Values mean. The Permissible Values may either
 * be enumerated or expressed via a description.
 * 
 * @author pacaci
 * @author mert
 * @author serike
 * @author alp
 * 
 */
public abstract class ValueDomainImpl extends AdministeredItemImpl implements
		ValueDomainResource {

	private static final Logger logger = LoggerFactory
			.getLogger(ValueDomainImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createValueDomain(AdministrationRecordResource, DatatypeResource, UnitOfMeasureResource, Integer, String, StewardshipRelationshipResource, SubmissionRelationshipResource, RegistrationAuthorityResource, AdministeredItemContextResource, ConceptualDomainResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param valueDomainAdministrationRecord
	 *            The Administration Record for a {@link ValueDomainResource}.
	 * @param valueDomainDatatype
	 *            The {@link DatatypeResource} used in a
	 *            {@link ValueDomainResource}.
	 * @param valueDomainUnitOfMeasure
	 *            Optional.The {@link UnitOfMeasureResource} used in a
	 *            {@link ValueDomainResource}.
	 * @param valueDomainMaximumCharacterQuantity
	 *            Optional.The maximum number of characters to represent the
	 *            {@linkData Element} value
	 * @param valueDomainFormat
	 *            Optional.A template for the structure of the presentation of
	 *            the Value(s).
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
	 * 
	 */
	public ValueDomainImpl(
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
			MDRDatabase mdrDatabase) {
		super(n, g, administeredBy, submittedBy, registeredBy, having,
				mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ValueDomain);
		setAdministrationRecord(valueDomainAdministrationRecord);
		setValueDomainDatatype(valueDomainDatatype);
		setValueDomainUnitOfMeasure(valueDomainUnitOfMeasure);
		setValueDomainMaximumCharacterQuantity(valueDomainMaximumCharacterQuantity);
		setValueDomainFormat(valueDomainFormat);
		setRepresentingConceptualDomainRepresentation(representingConceptualDomainRepresentation);

	}

	public ValueDomainImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public AdministrationRecordResource getAdministrationRecord() {
		return new AdministrationRecordImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().valueDomainAdministrationRecord),
				mdrDatabase);
	}

	@Override
	public void setAdministrationRecord(
			AdministrationRecordResource valueDomainAdministrationRecord) {
		if (valueDomainAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be specified for Value Domain.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().valueDomainAdministrationRecord,
				valueDomainAdministrationRecord);

	}

	@Override
	public void setValueDomainDatatype(DatatypeResource valueDomainDatatype) {
		if (valueDomainDatatype == null) {
			throw new IllegalArgumentException(
					"Data Type must be specified for Value Domain.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().valueDomainDatatype,
				valueDomainDatatype);
	}

	@Override
	public DatatypeResource getValueDomainDatatype() {
		return new DatatypeImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().valueDomainDatatype),
				mdrDatabase);
	}

	@Override
	public void setValueDomainUnitOfMeasure(
			UnitOfMeasureResource valueDomainUnitOfMeasure) {
		setPropertyValue(mdrDatabase.getVocabulary().valueDomainUnitOfMeasure,
				valueDomainUnitOfMeasure);
	}

	@Override
	public UnitOfMeasureResource getValueDomainUnitOfMeasure() {
		RDFNode valueDomainUnitOfMeasure = getPropertyValue(mdrDatabase
				.getVocabulary().valueDomainUnitOfMeasure);
		if (valueDomainUnitOfMeasure == null) {
			logger.debug("ValueDomain does not have UnitOfMeasure");
			return null;
		}
		return new UnitOfMeasureImpl(valueDomainUnitOfMeasure.asResource(),
				mdrDatabase);
	}

	@Override
	public void setValueDomainMaximumCharacterQuantity(
			Integer valueDomainMaximumCharacterQuantity) {
		setPropertyValue(
				mdrDatabase.getVocabulary().valueDomainMaximumCharacterQuantity,
				mdrDatabase.getUtil().createTypedLiteral(
						valueDomainMaximumCharacterQuantity));
	}

	@Override
	public Integer getValueDomainMaximumCharacterQuantity() {
		RDFNode valueDomainMaximumCharacterQuantity = getPropertyValue(mdrDatabase
				.getVocabulary().valueDomainMaximumCharacterQuantity);
		if (valueDomainMaximumCharacterQuantity == null) {
			logger.debug("ValueDomain does not have maxiumumCharacterQuantity");
			return null;
		}
		return valueDomainMaximumCharacterQuantity.asLiteral().getInt();
	}

	@Override
	public void setValueDomainFormat(String valueDomainFormat) {
		setPropertyValue(mdrDatabase.getVocabulary().valueDomainFormat,
				mdrDatabase.getUtil().createTypedLiteral(valueDomainFormat));
	}

	@Override
	public String getValueDomainFormat() {
		RDFNode valueDomainFormat = getPropertyValue(mdrDatabase
				.getVocabulary().valueDomainFormat);
		if (valueDomainFormat == null) {
			logger.debug("ValueDomain does not have format");
			return null;
		}
		return valueDomainFormat.asLiteral().getString();
	}

	@Override
	public void setRepresentingConceptualDomainRepresentation(
			ConceptualDomainResource representingConceptualDomainRepresentation) {
		if (representingConceptualDomainRepresentation == null) {
			throw new IllegalArgumentException(
					"Conceptual Domain must be specified for Value Domain.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().representingConceptualDomainRepresentation,
				representingConceptualDomainRepresentation);
	}

	@Override
	public ConceptualDomainResource getRepresentingConceptualDomainRepresentation() {
		RDFNode representingConceptualDomainRepresentation = getPropertyValue(mdrDatabase
				.getVocabulary().representingConceptualDomainRepresentation);
		OntClass ontClass = representingConceptualDomainRepresentation.as(
				OntResource.class).asClass();

		ConceptualDomainResource conceptualDomainIND = null;
		if (ontClass
				.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedConceptualDomain)) {
			conceptualDomainIND = new EnumeratedConceptualDomainImpl(
					representingConceptualDomainRepresentation.asResource(),
					mdrDatabase);
		}
		if (ontClass
				.hasSuperClass(mdrDatabase.getVocabulary().NonEnumeratedConceptualDomain)) {
			conceptualDomainIND = new NonEnumeratedConceptualDomainImpl(
					representingConceptualDomainRepresentation.asResource(),
					mdrDatabase);
		}
		if (conceptualDomainIND == null) {
			throw new IllegalStateException(
					"Property value should have a valid OntClass.");
		}
		return conceptualDomainIND;
	}

	@Override
	public void addRepresentingDataElementRepresentation(
			DataElementResource representingDataElementRepresentation) {
		if (representingDataElementRepresentation == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().representingDataElementRepresentation,
				representingDataElementRepresentation);
	}

	@Override
	public List<DataElementResource> getRepresentingDataElementRepresentations()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().representingDataElementRepresentation);
		return mdrDatabase.getUtil().createList(it, DataElementResource.class);
	}

	@Override
	public void removeRepresentingDataElementRepresentation(
			DataElementResource representingDataElementRepresentation) {
		if (representingDataElementRepresentation == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().representingDataElementRepresentation,
				representingDataElementRepresentation);
	}

	@Override
	public void setTypedByValueDomainRepresentationClass(
			RepresentationClassResource typedByValueDomainRepresentationClass) {
		setPropertyValue(
				mdrDatabase.getVocabulary().typedByValueDomainRepresentationClass,
				typedByValueDomainRepresentationClass);
	}

	@Override
	public RepresentationClassResource getTypedByValueDomainRepresentationClass() {
		RDFNode typedByValueDomainRepresentationClass = getPropertyValue(mdrDatabase
				.getVocabulary().typedByValueDomainRepresentationClass);
		if (typedByValueDomainRepresentationClass == null) {
			logger.debug("ValueDomain is not typed by RepresentationClass");
			return null;
		}
		return new RepresentationClassImpl(
				typedByValueDomainRepresentationClass.asResource(), mdrDatabase);
	}

	@Override
	public void addRelatedToValueDomainRelationship(
			ValueDomainRelationshipAssociationResource relatedToValueDomainRelationship) {
		if (relatedToValueDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().relatedToValueDomainRelationship,
				relatedToValueDomainRelationship);
	}

	@Override
	public void removeRelatedToValueDomainRelationship(
			ValueDomainRelationshipAssociationResource relatedToValueDomainRelationship) {
		if (relatedToValueDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().relatedToValueDomainRelationship,
				relatedToValueDomainRelationship);
	}

	@Override
	public List<ValueDomainRelationshipAssociationResource> getRelatedToValueDomainRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().relatedToValueDomainRelationship);
		return mdrDatabase.getUtil().createList(it,
				ValueDomainRelationshipAssociationResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ValueDomainResource asMDRResource() {
		return this;
	}

	public Datatype getDatatype() {
		return this.getValueDomainDatatype();
	}

	public ConceptualDomain getConceptualDomain() {
		return this.getRepresentingConceptualDomainRepresentation();
	}
	
	public UnitOfMeasure getUnitOfMeasure() {
		return this.getValueDomainUnitOfMeasure();
	}
	
	// @Override
	// public ConceptualDomain getConceptualDomain() {
	// return this.getRepresentingConceptualDomainRepresentation();
	// }
	//
	// @Override
	// public Datatype getDataType() {
	// return this.getValueDomainDatatype();
	// }

}
