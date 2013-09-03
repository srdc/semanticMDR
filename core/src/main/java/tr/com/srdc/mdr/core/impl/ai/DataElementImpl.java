package tr.com.srdc.mdr.core.impl.ai;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.MDRConstants;
import tr.com.srdc.mdr.core.api.ai.ClassificationScheme;
import tr.com.srdc.mdr.core.api.ai.Context;
import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.ai.DataElementConcept;
import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.api.composite.ClassificationSchemeItem;
import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.impl.composite.DataElementDerivationImpl;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.RepresentationClassResource;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementDerivationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementExampleResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;
import tr.com.srdc.mdr.core.util.MappingRelation;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

/**
 * A Data Element is considered to be a basic unit of data of interest to an
 * organization. It is a unit of data for which the definition, identification,
 * representation, and permissible values are specified by means of a set of
 * attributes.
 * 
 * @author pacaci
 * @author mert
 * @author serike
 * @author alp
 * @author anil
 * 
 */
public class DataElementImpl extends AdministeredItemImpl implements
		DataElementResource {

	private static final Logger logger = LoggerFactory
			.getLogger(DataElementImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createDataElement(AdministrationRecordResource, String, Integer)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param dataElementAdministrationRecord
	 *            The Administration Record for a {@link DataElementResource}.
	 * @param representationClassQualifier
	 *            Optional. A qualifier to the Representation Class used in
	 *            naming {@link DataElementResource}s and
	 *            {@link ValueDomainResource}s.
	 * @param dataElementPrecision
	 *            Optional. The degree of specificity for a
	 *            {@link DataElementResource}.
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
	 * @param expressingDataElementConceptExpression
	 *            An {@link DataElementResource} has to have at least one
	 *            {@link DataElementConceptResource}
	 * @param representedByDataElementRepresentation
	 *            An {@link DataElementResource} has to have at least one
	 *            {@link ValueDomainResource}
	 * @param mdrDatabase
	 * 
	 */
	public DataElementImpl(Node n, EnhGraph g,
			AdministrationRecordResource dataElementAdministrationRecord,
			String representationClassQualifier, Integer dataElementPrecision,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having,
			DataElementConceptResource expressingDataElementConceptExpression,
			ValueDomainResource representedByDataElementRepresentation,
			MDRDatabase mdrDatabase) {
		super(n, g, administeredBy, submittedBy, registeredBy, having,
				mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().DataElement);
		setAdministrationRecord(dataElementAdministrationRecord);
		setRepresentationClassQualifier(representationClassQualifier);
		setDataElementPrecision(dataElementPrecision);
		setExpressingDataElementConceptExpression(expressingDataElementConceptExpression);
		setRepresentedByDataElementRepresentation(representedByDataElementRepresentation);
	}

	public DataElementImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setAdministrationRecord(
			AdministrationRecordResource dataElementAdministrationRecord) {
		if (dataElementAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be specified for Data Element.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().dataElementAdministrationRecord,
				dataElementAdministrationRecord);
	}

	@Override
	public AdministrationRecordResource getAdministrationRecord() {
		return new AdministrationRecordImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().dataElementAdministrationRecord),
				mdrDatabase);
	}

	@Override
	public void setRepresentationClassQualifier(
			String representationClassQualifier) {
		setPropertyValue(
				mdrDatabase.getVocabulary().representationClassQualifier,
				mdrDatabase.getUtil().createTypedLiteral(
						representationClassQualifier));

	}

	@Override
	public String getRepresentationClassQualifier() {
		RDFNode representationClassQualifier = getPropertyValue(mdrDatabase
				.getVocabulary().representationClassQualifier);
		if (representationClassQualifier == null) {
			logger.debug("DataElement does not have representationClassQualifier");
			return null;
		}
		return representationClassQualifier.asLiteral().getString();
	}

	@Override
	public void setDataElementPrecision(Integer dataElementPrecision) {
		setPropertyValue(mdrDatabase.getVocabulary().dataElementPrecision,
				mdrDatabase.getUtil().createTypedLiteral(dataElementPrecision));

	}

	@Override
	public Integer getDataElementPrecision() {
		RDFNode dataElementPrecision = getPropertyValue(mdrDatabase
				.getVocabulary().dataElementPrecision);
		if (dataElementPrecision == null) {
			logger.debug("DataElement does not have dataElementPrecision");
			return null;
		}
		return dataElementPrecision.asLiteral().getInt();
	}

	@Override
	public void setExpressingDataElementConceptExpression(
			DataElementConceptResource expressingDataElementConceptExpression) {
		if (expressingDataElementConceptExpression == null) {
			throw new IllegalArgumentException(
					"Data Element Concept must be specified for Data Element.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().expressingDataElementConceptExpression,
				expressingDataElementConceptExpression);
	}

	@Override
	public DataElementConceptResource getExpressingDataElementConceptExpression() {
		return new DataElementConceptImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().expressingDataElementConceptExpression),
				mdrDatabase);
	}

	@Override
	public void setRepresentedByDataElementRepresentation(
			ValueDomainResource representedByDataElementRepresentation) {
		if (representedByDataElementRepresentation == null) {
			throw new IllegalArgumentException(
					"Value Domain must be specified for Data Element.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().representedByDataElementRepresentation,
				representedByDataElementRepresentation);
	}

	@Override
	public ValueDomainResource getRepresentedByDataElementRepresentation() {
		RDFNode representedByDataElementRepresentation = getPropertyValue(mdrDatabase
				.getVocabulary().representedByDataElementRepresentation);
		OntClass ontClass = representedByDataElementRepresentation.as(
				OntResource.class).asClass();

		ValueDomainResource valueDomainIND = null;
		if (ontClass
				.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedValueDomain)) {
			valueDomainIND = new EnumeratedValueDomainImpl(
					representedByDataElementRepresentation.asResource(),
					mdrDatabase);
			logger.info("Representer ValueDomain is an EnumeratedValueDomain");
		}
		if (ontClass
				.hasSuperClass(mdrDatabase.getVocabulary().NonEnumeratedValueDomain)) {
			valueDomainIND = new NonEnumeratedValueDomainImpl(
					representedByDataElementRepresentation.asResource(),
					mdrDatabase);
		}
		if (valueDomainIND == null) {
			throw new IllegalStateException(
					"Property value should have a valid OntClass.");
		}
		return valueDomainIND;
	}

	@Override
	public void addInputToDerivationInput(
			DataElementDerivationResource inputToDerivationInput) {
		if (inputToDerivationInput == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().inputToDerivationInput,
				inputToDerivationInput);
	}

	@Override
	public void removeInputToDerivationInput(
			DataElementDerivationResource inputToDerivationInput) {
		if (inputToDerivationInput == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().inputToDerivationInput,
				inputToDerivationInput);
	}

	@Override
	public List<DataElementDerivationResource> getInputToDerivationInputs()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().inputToDerivationInput);
		return mdrDatabase.getUtil().createList(it,
				DataElementDerivationResource.class);
	}

	@Override
	public void setDerivedFromDerivationOutput(
			DataElementDerivationResource derivedFromDerivationOutput) {
		setPropertyValue(
				mdrDatabase.getVocabulary().derivedFromDerivationOutput,
				derivedFromDerivationOutput);
	}

	@Override
	public DataElementDerivationResource getDerivedFromDerivationOutput() {
		Resource derivedFromDerivationOutput = getPropertyResourceValue(mdrDatabase
				.getVocabulary().derivedFromDerivationOutput);
		if (derivedFromDerivationOutput == null) {
			logger.debug("DataElement is not derived from DataElementDerivation");
			return null;
		}
		return new DataElementDerivationImpl(derivedFromDerivationOutput,
				mdrDatabase);
	}

	@Override
	public void addExemplifiedByExemplification(
			DataElementExampleResource exemplifiedByExemplification) {
		if (exemplifiedByExemplification == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().exemplifiedByExemplification,
				exemplifiedByExemplification);
	}

	@Override
	public void removeExemplifiedByExemplification(
			DataElementExampleResource exemplifiedByExemplification) {
		if (exemplifiedByExemplification == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().exemplifiedByExemplification,
				exemplifiedByExemplification);
	}

	@Override
	public List<DataElementExampleResource> getExemplifiedByExemplifications()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().exemplifiedByExemplification);
		return mdrDatabase.getUtil().createList(it,
				DataElementExampleResource.class);
	}

	@Override
	public void setTypedByDataElementRepresentationClass(
			RepresentationClassResource typedByDataElementRepresentationClass) {
		setPropertyValue(
				mdrDatabase.getVocabulary().typedByDataElementRepresentationClass,
				typedByDataElementRepresentationClass);
	}

	@Override
	public RepresentationClassResource getTypedByDataElementRepresentationClass() {
		Resource typedByDataElementRepresentationClass = getPropertyResourceValue(mdrDatabase
				.getVocabulary().typedByDataElementRepresentationClass);
		if (typedByDataElementRepresentationClass == null) {
			logger.debug("DataElement is not typed by RepresentationClass");
			return null;
		}
		return new RepresentationClassImpl(
				typedByDataElementRepresentationClass, mdrDatabase);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public void delete() {
		super.delete();

		this.remove();
	}

	@Override
	public DataElementResource asMDRResource() {
		return this;
	}

	@Override
	public DataElementConcept getDataElementConcept() {
		return this.getExpressingDataElementConceptExpression();
	}

	@Override
	public ValueDomain getValueDomain() {
		return this.getRepresentedByDataElementRepresentation();
	}

	@Override
	public void addExtractionSpecification(String oid, String esType,
			String esScript) {
		Context context = getContext();

		// get the classification
		ClassificationScheme cs = context.getClassificationScheme(oid);
		// there are no ClassificationSchemes for this content model type
		if (cs == null) {
			cs = context.createClassificationScheme(oid,
					MDRConstants.CS_EXTRACTION_SPEC);
		}

		ClassificationSchemeItem csi = cs.createClassificationSchemeItem(
				esType, esScript);
		this.addClassifiedBy(csi.asMDRResource());
	}

	@Override
	public List<ClassificationSchemeItem> getExtractionSpecifications() {
		List<ClassificationSchemeItem> specifications = new ArrayList<ClassificationSchemeItem>();
		try {
			for (ClassificationSchemeItemResource resource : this
					.getClassifiedBy()) {
				if (resource.getClassificationScheme().getDefinition()
						.equals(MDRConstants.CS_EXTRACTION_SPEC)) {
					specifications.add(resource);
				}
			}
		} catch (MDRException e) {
			e.printStackTrace();
			return null;
		}
		return specifications;
	}

	@Override
	public void addMapping(MappingRelation relation, DataElement matchedElement) {
		String skosNS = "http://www.w3.org/2004/02/skos/core#";
		String relationType = relation.getRelationType();

		// here existance of mapping will be checked
		List<ClassificationSchemeItem> existingMappings = this.getMappings();
		for (ClassificationSchemeItem mapping : existingMappings) {
			if (mapping.getItemValue().equals(matchedElement.getUniqueID())
					&& mapping.getTypeName().equals(relation.getRelationType())
					&& mapping.getClassificationScheme().getDefinition()
							.equals(MDRConstants.CS_MAPPING)) {
				// mapping already exists, just return so no mapping will be added
				logger.debug("Already existing mapping " + this.getName() + " "
						+ relationType + " " + matchedElement.getName());
				return;
			}
		}

		Context context = getContext();
		// get the classification
		ClassificationScheme cs = context.getClassificationScheme(relation
				.getObjectOID());
		// there are no ClassificationSchemes for this content model
		if (cs == null) {
			cs = context.createClassificationScheme(relation.getObjectOID(),
					MDRConstants.CS_MAPPING);
		}

		ClassificationSchemeItem csi = cs.createClassificationSchemeItem(
				relationType, matchedElement.getUniqueID());

		String[] relParts = relationType.split(":");
		if (relParts[0].equals("skos")) {
			Property p = ResourceFactory.createProperty(skosNS + relParts[1]);
			this.asMDRResource().addProperty(p, matchedElement.asMDRResource());
			this.addClassifiedBy(csi.asMDRResource());
		} else {
			logger.error("Mapping Relation Namespace is not supported:"
					+ relParts[0]);
		}

		// If the relation is symmetric, add classification scheme item for the
		// other data element
		if (relation.isSymmetric()) {
			// get the classification
			cs = context.getClassificationScheme(relation.getSubjectOID());
			// there are no ClassificationSchemes for this content model
			if (cs == null) {
				cs = context.createClassificationScheme(
						relation.getSubjectOID(), MDRConstants.CS_MAPPING);
			}

			csi = cs.createClassificationSchemeItem(relationType,
					this.getUniqueID());

			if (relParts[0].equals("skos")) {
				Property p = ResourceFactory.createProperty(skosNS
						+ relParts[1]);
				matchedElement.asMDRResource().addProperty(p,
						this.asMDRResource());
				matchedElement.asMDRResource().addClassifiedBy(
						csi.asMDRResource());
			} else {
				logger.error("Mapping Relation Namespace is not supported:"
						+ relParts[0]);
			}
		}
	}

	@Override
	public List<ClassificationSchemeItem> getMappings() {
		List<ClassificationSchemeItem> specifications = new ArrayList<ClassificationSchemeItem>();
		try {
			for (ClassificationSchemeItemResource resource : this
					.getClassifiedBy()) {
				if (resource.getClassificationScheme().getDefinition()
						.equals(MDRConstants.CS_MAPPING)) {
					specifications.add(resource);
				}
			}
		} catch (MDRException e) {
			e.printStackTrace();
			return null;
		}
		return specifications;
	}
}
