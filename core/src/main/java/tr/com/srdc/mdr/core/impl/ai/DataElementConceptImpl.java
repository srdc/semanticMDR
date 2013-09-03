package tr.com.srdc.mdr.core.impl.ai;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.ai.ObjectClass;
import tr.com.srdc.mdr.core.api.ai.Property;
import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryDefaults;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.ObjectClassResource;
import tr.com.srdc.mdr.core.model.iso11179.PropertyResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementConceptRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DefinitionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DesignationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ItemIdentifierResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageSectionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrarResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.TerminologicalEntryResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * A Data Element Concept is a concept that can be represented in the form of a
 * data element, described independently of any particular representation.
 * 
 * @author pacaci
 * @author mert
 * @author serike
 * @author alp
 * @author anil
 * 
 */
public class DataElementConceptImpl extends AdministeredItemImpl implements
		DataElementConceptResource {

	private static final Logger logger = LoggerFactory
			.getLogger(DataElementConceptImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createDataElementConcept(AdministrationRecordResource, ObjectClassResource, PropertyResource, String, String)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param dataElementConceptAdministrationRecord
	 *            The Administration Record for a
	 *            {@link DataElementConceptResource} .
	 * @param dataElementConceptObjectClass
	 *            Optional. The designation of an Object Class for a
	 *            {@link DataElementConceptResource}.
	 * @param dataElementConceptProperty
	 *            Optional. The designation of a Property for a
	 *            {@link DataElementConceptResource}.
	 * @param objectClassQualifier
	 *            Optional. A qualifier of the Data Element Concept Object
	 *            Class.
	 * @param propertyQualifier
	 *            Optional. A qualifier of the Data Element Concept Property.
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
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @param havingDataElementConceptConceptualDomainRelationship
	 *            An {@link DataElementConceptResource} has to have at least one
	 *            {@link ConceptualDomainResource}.
	 * @param mdrDatabase
	 * 
	 */
	public DataElementConceptImpl(
			Node n,
			EnhGraph g,
			AdministrationRecordResource dataElementConceptAdministrationRecord,
			ObjectClassResource dataElementConceptObjectClass,
			PropertyResource dataElementConceptProperty,
			String objectClassQualifier,
			String propertyQualifier,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having,
			ConceptualDomainResource havingDataElementConceptConceptualDomainRelationship,
			MDRDatabase mdrDatabase) {
		super(n, g, administeredBy, submittedBy, registeredBy, having,
				mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().DataElementConcept);
		setAdministrationRecord(dataElementConceptAdministrationRecord);
		setDataElementConceptObjectClass(dataElementConceptObjectClass);
		setDataElementConceptProperty(dataElementConceptProperty);
		setObjectClassQualifier(objectClassQualifier);
		setPropertyQualifier(propertyQualifier);
		setHavingDataElementConceptConceptualDomainRelationship(havingDataElementConceptConceptualDomainRelationship);
	}

	public DataElementConceptImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setAdministrationRecord(
			AdministrationRecordResource dataElementConceptAdministrationRecord) {
		if (dataElementConceptAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be specified for Data Element Concept.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().dataElementConceptAdministrationRecord,
				dataElementConceptAdministrationRecord);
	}

	@Override
	public AdministrationRecordResource getAdministrationRecord() {
		return new AdministrationRecordImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().dataElementConceptAdministrationRecord),
				mdrDatabase);
	}

	@Override
	public void setDataElementConceptObjectClass(
			ObjectClassResource dataElementConceptObjectClass) {
		setPropertyValue(
				mdrDatabase.getVocabulary().dataElementConceptObjectClass,
				dataElementConceptObjectClass);
	}

	@Override
	public ObjectClassResource getDataElementConceptObjectClass() {
		RDFNode dataElementConceptObjectClass = getPropertyValue(mdrDatabase
				.getVocabulary().dataElementConceptObjectClass);
		if (dataElementConceptObjectClass == null) {
			logger.debug("DataElemenetConcept does not have an ObjectClass");
			return null;
		}
		OntClass ontClass = dataElementConceptObjectClass.as(OntResource.class)
				.asClass();
		ObjectClassResource objectClassIND = null;
		if (ontClass.hasSuperClass(mdrDatabase.getVocabulary().Concept)) {
			objectClassIND = new ConceptImpl(
					dataElementConceptObjectClass.asResource(), mdrDatabase);
		}
		if (ontClass
				.hasSuperClass(mdrDatabase.getVocabulary().ConceptRelationship)) {
			objectClassIND = new ConceptRelationshipImpl(
					dataElementConceptObjectClass.asResource(), mdrDatabase);
		}
		if (objectClassIND == null) {
			throw new IllegalStateException(
					"Property value should have a valid OntClass.");
		}
		return objectClassIND;
	}

	@Override
	public void setDataElementConceptProperty(
			PropertyResource dataElementConceptProperty) {
		setPropertyValue(
				mdrDatabase.getVocabulary().dataElementConceptProperty,
				dataElementConceptProperty);
	}

	@Override
	public PropertyResource getDataElementConceptProperty() {
		RDFNode dataElementConceptProperty = getPropertyValue(mdrDatabase
				.getVocabulary().dataElementConceptProperty);
		if (dataElementConceptProperty == null) {
			logger.debug("DataElementConcept does not have Property");
			return null;
		}
		return new PropertyImpl(dataElementConceptProperty.asResource(),
				mdrDatabase);
	}

	@Override
	public void setObjectClassQualifier(String objectClassQualifier) {
		setPropertyValue(mdrDatabase.getVocabulary().objectClassQualifier,
				mdrDatabase.getUtil().createTypedLiteral(objectClassQualifier));
	}

	@Override
	public String getObjectClassQualifier() {
		RDFNode objectClassQualifier = getPropertyValue(mdrDatabase
				.getVocabulary().objectClassQualifier);
		if (objectClassQualifier == null) {
			logger.debug("DataElemetConcept does not have an objectClassQualifier");
			return null;
		}
		return objectClassQualifier.asLiteral().getString();
	}

	@Override
	public void setPropertyQualifier(String qualifier) {
		setPropertyValue(mdrDatabase.getVocabulary().propertyQualifier,
				mdrDatabase.getUtil().createTypedLiteral(qualifier));
	}

	@Override
	public String getPropertyQualifier() {
		RDFNode propertyQualifier = getPropertyValue(mdrDatabase
				.getVocabulary().propertyQualifier);
		if (propertyQualifier == null) {
			logger.debug("DataElementConcept does not have an propertyQualifier");
			return null;
		}
		return propertyQualifier.asLiteral().getString();
	}

	@Override
	public void setHavingDataElementConceptConceptualDomainRelationship(
			ConceptualDomainResource havingDataElementConceptConceptualDomainRelationship) {
		if (havingDataElementConceptConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Conceptual Domain must be specified for Data Element Concept.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().havingDataElementConceptConceptualDomainRelationship,
				havingDataElementConceptConceptualDomainRelationship);
	}

	@Override
	public ConceptualDomainResource getHavingDataElementConceptConceptualDomainRelationship() {
		RDFNode havingDataElementConceptConceptualDomainRelationship = getPropertyValue(mdrDatabase
				.getVocabulary().havingDataElementConceptConceptualDomainRelationship);
		OntClass ontClass = havingDataElementConceptConceptualDomainRelationship
				.as(OntResource.class).asClass();
		ConceptualDomainResource conceptualDomainIND = null;
		if (ontClass
				.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedConceptualDomain)) {
			conceptualDomainIND = new EnumeratedConceptualDomainImpl(
					havingDataElementConceptConceptualDomainRelationship
							.asResource(),
					mdrDatabase);
		}
		if (ontClass
				.hasSuperClass(mdrDatabase.getVocabulary().NonEnumeratedConceptualDomain)) {
			conceptualDomainIND = new NonEnumeratedConceptualDomainImpl(
					havingDataElementConceptConceptualDomainRelationship
							.asResource(),
					mdrDatabase);
		}
		if (conceptualDomainIND == null) {
			throw new IllegalStateException(
					"Property value should have a OntClass");
		}
		return conceptualDomainIND;
	}

	@Override
	public void addExpressedByDataElementConceptExpression(
			DataElementResource expressedByDataElementConceptExpression) {
		if (expressedByDataElementConceptExpression == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().expressedByDataElementConceptExpression,
				expressedByDataElementConceptExpression);
	}

	@Override
	public List<DataElementResource> getExpressedByDataElementConceptExpressions()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().expressedByDataElementConceptExpression);
		return mdrDatabase.getUtil().createList(it, DataElementResource.class);
	}

	@Override
	public void removeExpressedByDataElementConceptExpression(
			DataElementResource expressedByDataElementConceptExpression) {
		if (expressedByDataElementConceptExpression == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().expressedByDataElementConceptExpression,
				expressedByDataElementConceptExpression);
	}

	@Override
	public void addRelatedToDataElementConceptRelationship(
			DataElementConceptRelationshipAssociationResource relatedToDataElementConceptRelationship) {
		if (relatedToDataElementConceptRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().relatedToDataElementConceptRelationship,
				relatedToDataElementConceptRelationship);
	}

	@Override
	public void removeRelatedToDataElementConceptRelationship(
			DataElementConceptRelationshipAssociationResource relatedToDataElementConceptRelationship) {
		if (relatedToDataElementConceptRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().relatedToDataElementConceptRelationship,
				relatedToDataElementConceptRelationship);
	}

	@Override
	public List<DataElementConceptRelationshipAssociationResource> getRelatedToDataElementConceptRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().relatedToDataElementConceptRelationship);
		return mdrDatabase.getUtil().createList(it,
				DataElementConceptRelationshipAssociationResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public DataElementConceptResource asMDRResource() {
		return this;
	}

	@Override
	public DataElement createDataElement(String name, String definition,
			ValueDomain valueDomain) {
		return getContext().createDataElement(name, definition, this,
				valueDomain);
	}

	@Override
	public Property getProperty() {
		return this.getDataElementConceptProperty();
	}

	@Override
	public ObjectClass getObjectClass() {
		return this.getDataElementConceptObjectClass();
	}

	@Override
	public void setProperty(Property property) {
		this.setDataElementConceptProperty(property.asMDRResource());
	}

	@Override
	public void setProperty(String name, String definition) {
		Repository repository = RepositoryManager.getInstance().getRepository();
		RepositoryDefaults defaults = repository.getDefaults();

		MDRResourceFactory mdrResourceFactory = this.mdrDatabase
				.getResourceFactory();
		ItemIdentifierResource itemIdentifierResource = defaults
				.createItemIdentifierResource(null);
		AdministrationRecordResource administrationRecordResource = defaults
				.createAdministrationRecordResource(itemIdentifierResource);

		ContactResource contactResource = getAdministerPerson().asMDRResource();
		OrganizationResource organizationResource = getAdministerOrganization()
				.asMDRResource();

		StewardshipRelationshipResource stewardshipRelationshipResource = mdrResourceFactory
				.createStewardshipRelationship(organizationResource,
						mdrResourceFactory.createStewardship(contactResource));

		SubmissionResource submissionResource = mdrResourceFactory
				.createSubmission(contactResource);
		SubmissionRelationshipResource submissionRelationshipResource = mdrResourceFactory
				.createSubmissionRelationship(organizationResource,
						submissionResource);

		RegistrarResource registrarResource = mdrResourceFactory
				.createRegistrar(contactResource.getContactInformation(),
						contactResource);
		RegistrationAuthorityResource registrationAuthorityResource = mdrResourceFactory
				.createRegistrationAuthority(
						defaults.getRegistrationAuthorityIdentifierResource(),
						defaults.getLanguageIdentificationResource(),
						organizationResource.getName(),
						organizationResource.getMailAddress(),
						registrarResource);

		LanguageSectionResource languageSectionResource = mdrResourceFactory
				.createLanguageSection(defaults
						.getLanguageIdentificationResource());
		DesignationResource designationResource = mdrResourceFactory
				.createDesignation(languageSectionResource, name, true);
		languageSectionResource.addContainingNameEntry(designationResource);
		if (!Util.isNull(definition)) {
			DefinitionResource definitionResource = mdrResourceFactory
					.createDefinition(languageSectionResource, definition,
							true, null);
			languageSectionResource
					.addContainigDefinitionEntry(definitionResource);
		}
		TerminologicalEntryResource terminologicalEntryResource = mdrResourceFactory
				.createTerminologicalEntry(languageSectionResource);
		AdministeredItemContextResource administeredItemContextResource = mdrResourceFactory
				.createAdministeredItemContext(this.getContext()
						.asMDRResource(), terminologicalEntryResource);
		this.getContext().asMDRResource()
				.addAdministeredItemContext(administeredItemContextResource);

		PropertyResource propertyResource = mdrResourceFactory.createProperty(
				administrationRecordResource, stewardshipRelationshipResource,
				submissionRelationshipResource, registrationAuthorityResource,
				administeredItemContextResource);

		administeredItemContextResource.addGrouping(propertyResource);

		logger.info("Property created successfully: {}", name);

		this.setDataElementConceptProperty(propertyResource);
	}

	@Override
	public void setObjectClass(ObjectClass objectClass) {
		this.setDataElementConceptObjectClass(objectClass.asMDRResource());
	}

	@Override
	public List<DataElement> getDataElements() {
		List<DataElement> deList = new ArrayList<DataElement>();
		try {
			List<DataElementResource> derList = this
					.getExpressedByDataElementConceptExpressions();
			for (DataElementResource der : derList) {
				deList.add(der);
			}
		} catch (MDRException e) {
			logger.error("Data Elements of {} could not be obtained",
					this.getName(), e);
		}
		return deList;
	}

	@Override
	public ConceptualDomain getConceptualDomain() {
		return this.getHavingDataElementConceptConceptualDomainRelationship();
	}

}
