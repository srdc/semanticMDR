package tr.com.srdc.mdr.core.impl.ai;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.MDRConstants;
import tr.com.srdc.mdr.core.api.ai.AdministeredItem;
import tr.com.srdc.mdr.core.api.ai.ClassificationScheme;
import tr.com.srdc.mdr.core.api.ai.Concept;
import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.ai.DataElementConcept;
import tr.com.srdc.mdr.core.api.ai.EnumeratedConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.EnumeratedValueDomain;
import tr.com.srdc.mdr.core.api.ai.NonEnumeratedConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.NonEnumeratedValueDomain;
import tr.com.srdc.mdr.core.api.ai.ObjectClass;
import tr.com.srdc.mdr.core.api.ai.Property;
import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.api.composite.PermissibleValue;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryDefaults;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.impl.composite.LanguageIdentificationImpl;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.ContextResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.PropertyResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DefinitionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DesignationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ItemIdentifierResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageIdentificationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageSectionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.PermissibleValueResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrarResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.TerminologicalEntryResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;
import tr.com.srdc.mdr.core.store.query.ResourceQueryFactory.TextSearchType;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * A Context defines the scope within which the subject data has meaning. A
 * Context may be a business domain, an information subject area, an information
 * system, a database, file, data model, standard document, or any other
 * environment determined by the owner of the registry
 * 
 * @author anil
 * 
 */
public class ContextImpl extends AdministeredItemImpl implements
		ContextResource {

	private static final Logger logger = LoggerFactory
			.getLogger(ContextImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createContext(AdministrationRecordResource,String,LanguageIdentificationResource)}
	 * to avoid entering illstates.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param contextAdministrationRecord
	 *            The Administration Record for a {@link ContextResource}.
	 * @param contextDescription
	 *            The textual description of the {@link ContextResource}.
	 * @param contextDescriptionLanguageIdentifier
	 *            Optional. The identifier of the language used in the context
	 *            description.
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
	public ContextImpl(
			Node n,
			EnhGraph g,
			AdministrationRecordResource contextAdministrationRecord,
			String contextDescription,
			LanguageIdentificationResource contextDescriptionLanguageIdentifier,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having, MDRDatabase mdrDatabase) {
		super(n, g, administeredBy, submittedBy, registeredBy, having,
				mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Context);
		setPropertyValue(
				mdrDatabase.getVocabulary().contextAdministrationRecord,
				contextAdministrationRecord);
		addLiteral(mdrDatabase.getVocabulary().contextDescription,
				contextDescription);
		if (contextDescriptionLanguageIdentifier != null) {
			setPropertyValue(
					mdrDatabase.getVocabulary().contextDescriptionLanguageIdentifier,
					contextDescriptionLanguageIdentifier);
		}
	}

	public ContextImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setAdministrationRecord(
			AdministrationRecordResource contextAdministrationRecord) {
		if (contextAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be specified for Context.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().contextAdministrationRecord,
				contextAdministrationRecord);
	}

	@Override
	public AdministrationRecordResource getAdministrationRecord() {
		return new AdministrationRecordImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().contextAdministrationRecord),
				mdrDatabase);
	}

	@Override
	public String getContextDescription() {
		return getPropertyValue(mdrDatabase.getVocabulary().contextDescription)
				.asLiteral().getString();
	}

	@Override
	public void setContextDescription(String contextDescription) {
		if (Util.isNull(contextDescription)) {
			throw new IllegalArgumentException(
					"Context Description must be specified for Context");
		}
		setPropertyValue(mdrDatabase.getVocabulary().contextDescription,
				mdrDatabase.getUtil().createTypedLiteral(contextDescription));
	}

	@Override
	public LanguageIdentificationResource getContextDescriptionLanguageIdentifier() {
		RDFNode contextDescriptionLanguageIdentifier = getPropertyResourceValue(mdrDatabase
				.getVocabulary().contextDescriptionLanguageIdentifier);
		if (contextDescriptionLanguageIdentifier == null) {
			logger.debug("Context does not have languageIdentifier");
			return null;
		}
		return new LanguageIdentificationImpl(
				contextDescriptionLanguageIdentifier.asResource(), mdrDatabase);
	}

	@Override
	public void setContextDescriptionLanguageIdentifier(
			LanguageIdentificationResource contextDescriptionLanguageIdentifier) {
		setPropertyValue(
				mdrDatabase.getVocabulary().contextDescriptionLanguageIdentifier,
				contextDescriptionLanguageIdentifier);
	}

	@Override
	public void addAdministeredItemContext(
			AdministeredItemContextResource administeredItemContext) {
		if (administeredItemContext == null) {
			throw new IllegalArgumentException(
					"Null is not permitted as a value for the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().contextAdministeredItemContext,
				administeredItemContext);
	}

	@Override
	public void removeAdministeredItemContext(
			AdministeredItemContextResource administeredItemContext) {
		if (administeredItemContext == null) {
			throw new IllegalArgumentException(
					"Null is not permitted as a value for the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().contextAdministeredItemContext,
				administeredItemContext);
	}

	@Override
	public List<AdministeredItemContextResource> getAdministeredItemContexts()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().contextAdministeredItemContext);
		return mdrDatabase.getUtil().createList(it,
				AdministeredItemContextResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ContextResource asMDRResource() {
		return this;
	}

	@Override
	public void delete() {
		try {
			List<AdministeredItemContextResource> aicList = this
					.getAdministeredItemContexts();
			List<AdministeredItem> aiList = new ArrayList<AdministeredItem>();
			for (AdministeredItemContextResource aic : aicList) {
				aiList.add(aic.getGroupings().get(0));
			}

			for (AdministeredItem ai : aiList) {
				ai.delete();
			}

			super.delete();
		} catch (MDRException e) {
			logger.error("All the items in Context could not be deleted");
		}

		this.remove();
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public String getDescription() {
		return getContextDescription();
	}

	@Override
	public void setDescription(String description) {
		setContextDescription(description);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataElement> getDataElements(Integer limit, Integer offset) {
		return (List<DataElement>) mdrDatabase.getQueryFactory()
				.getDataElementsOfContext(getURI(), limit, offset);
	}

	@Override
	public DataElement createDataElement(String name, String definition,
			DataElementConcept dec, ValueDomain vd) {
		// TODO need to check if conceptual domains are same??

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
				.createAdministeredItemContext(this,
						terminologicalEntryResource);
		this.addAdministeredItemContext(administeredItemContextResource);

		DataElementResource de = mdrResourceFactory.createDataElement(
				administrationRecordResource, null, null,
				stewardshipRelationshipResource,
				submissionRelationshipResource, registrationAuthorityResource,
				administeredItemContextResource, dec.asMDRResource(),
				vd.asMDRResource());
		vd.asMDRResource().addRepresentingDataElementRepresentation(de);
		dec.asMDRResource().addExpressedByDataElementConceptExpression(de);

		administeredItemContextResource.addGrouping(de);
		return de;
	}

	@Override
	public int getNumberOfDataElements() {
		return mdrDatabase.getQueryFactory().getNumberOfDataElementsOfContext(
				this.getURI());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObjectClass> getObjectClasses() {
		return (List<ObjectClass>) mdrDatabase.getQueryFactory()
				.getObjectClassesOfContext(getURI(), null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObjectClass> getObjectClasses(Integer limit, Integer offset) {
		return (List<ObjectClass>) mdrDatabase.getQueryFactory()
				.getObjectClassesOfContext(getURI(), limit, offset);
	}

	@Override
	public ObjectClass getObjectClass(String uniqueID) {
		return new ConceptImpl(mdrDatabase.getQueryFactory()
				.getAdministeredItem(uniqueID), mdrDatabase);
	}

	@Override
	public ObjectClass createObjectClass(String name, String definition) {
		return createConcept(name, definition);
	}

	@Override
	public Concept createConcept(String name, String definition) {
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
				.createAdministeredItemContext(this,
						terminologicalEntryResource);
		this.addAdministeredItemContext(administeredItemContextResource);

		ConceptResource conceptResource = mdrResourceFactory.createConcept(
				administrationRecordResource, stewardshipRelationshipResource,
				submissionRelationshipResource, registrationAuthorityResource,
				administeredItemContextResource);

		administeredItemContextResource.addGrouping(conceptResource);

		logger.debug("ObjectClass/Concept created successfully: {}", name);

		return conceptResource;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Property> getProperties() {
		return (List<Property>) mdrDatabase.getQueryFactory()
				.getPropertiesOfContext(getURI());
	}

	@Override
	public Property getProperty(String uniqueID) {
		return new PropertyImpl(mdrDatabase.getQueryFactory()
				.getAdministeredItem(uniqueID), mdrDatabase);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Property> searchProperty(String keyword,
			TextSearchType searchType) {
		return (List<Property>) mdrDatabase.getQueryFactory().searchProperty(
				keyword, this.getURI(), searchType);
	}

	private Property createProperty(String propertyName,
			String propertyDefinition) {
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
				.createDesignation(languageSectionResource, propertyName, true);
		languageSectionResource.addContainingNameEntry(designationResource);
		if (!Util.isNull(propertyDefinition)) {
			DefinitionResource definitionResource = mdrResourceFactory
					.createDefinition(languageSectionResource,
							propertyDefinition, true, null);
			languageSectionResource
					.addContainigDefinitionEntry(definitionResource);
		}
		TerminologicalEntryResource terminologicalEntryResource = mdrResourceFactory
				.createTerminologicalEntry(languageSectionResource);
		AdministeredItemContextResource administeredItemContextResource = mdrResourceFactory
				.createAdministeredItemContext(this,
						terminologicalEntryResource);
		this.addAdministeredItemContext(administeredItemContextResource);

		PropertyResource propertyResource = mdrResourceFactory.createProperty(
				administrationRecordResource, stewardshipRelationshipResource,
				submissionRelationshipResource, registrationAuthorityResource,
				administeredItemContextResource);

		administeredItemContextResource.addGrouping(propertyResource);

		logger.debug("Property created successfully: {}", propertyName);

		return propertyResource;
	}

	@Override
	public DataElementConcept createDataElementConcept(ObjectClass objectClass,
			String propertyName, String propertyDefinition,
			ConceptualDomain conceptulDomain) {
		return createDataElementConcept(objectClass,
				createProperty(propertyName, propertyDefinition),
				conceptulDomain);
	}

	@Override
	public DataElementConcept createDataElementConcept(ObjectClass objectClass,
			Property property, ConceptualDomain conceptualDomain) {
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

		String decName = objectClass.getName() + " " + property.getName();
		String decDefinition = "Data Element Concept of " + decName;

		LanguageSectionResource languageSectionResource = mdrResourceFactory
				.createLanguageSection(defaults
						.getLanguageIdentificationResource());
		DesignationResource designationResource = mdrResourceFactory
				.createDesignation(languageSectionResource, decName, true);
		languageSectionResource.addContainingNameEntry(designationResource);
		if (!Util.isNull(decDefinition)) {
			DefinitionResource definitionResource = mdrResourceFactory
					.createDefinition(languageSectionResource, decDefinition,
							true, null);
			languageSectionResource
					.addContainigDefinitionEntry(definitionResource);
		}
		TerminologicalEntryResource terminologicalEntryResource = mdrResourceFactory
				.createTerminologicalEntry(languageSectionResource);
		AdministeredItemContextResource administeredItemContextResource = mdrResourceFactory
				.createAdministeredItemContext(this,
						terminologicalEntryResource);
		this.addAdministeredItemContext(administeredItemContextResource);

		ConceptualDomainResource conceptualDomainResource = conceptualDomain
				.asMDRResource();

		DataElementConceptResource dataElementConceptResource = mdrResourceFactory
				.createDataElementConcept(administrationRecordResource,
						objectClass.asMDRResource(), property.asMDRResource(),
						null, null, stewardshipRelationshipResource,
						submissionRelationshipResource,
						registrationAuthorityResource,
						administeredItemContextResource,
						conceptualDomainResource);

		conceptualDomainResource
				.addSpecifyingDataElementConceptConceptualDomainRelationship(dataElementConceptResource);
		administeredItemContextResource.addGrouping(dataElementConceptResource);

		logger.debug("DataElementConcept created successfully: {}", decName);

		return dataElementConceptResource;
	}

	@Override
	public EnumeratedValueDomain createEnumeratedValueDomain(
			EnumeratedConceptualDomain enumeratedConceptualDomain, String name,
			String definition, Datatype dataType,
			List<PermissibleValue> permissibleValues) {

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
				.createAdministeredItemContext(this,
						terminologicalEntryResource);
		this.addAdministeredItemContext(administeredItemContextResource);

		List<PermissibleValueResource> permissibleValueResources = new ArrayList<PermissibleValueResource>(
				permissibleValues.size());
		for (PermissibleValue pv : permissibleValues) {
			permissibleValueResources.add(pv.asMDRResource());
		}

		EnumeratedConceptualDomainResource enumeratedConceptualDomainResource = enumeratedConceptualDomain
				.asMDRResource();

		EnumeratedValueDomainResource enumeratedValueDomainResource = mdrResourceFactory
				.createEnumeratedValueDomain(administrationRecordResource,
						dataType.asMDRResource(), null, null, null,
						stewardshipRelationshipResource,
						submissionRelationshipResource,
						registrationAuthorityResource,
						administeredItemContextResource,
						enumeratedConceptualDomainResource,
						permissibleValueResources);

		for (PermissibleValueResource pvr : permissibleValueResources) {
			pvr.addContainedInPermissibleValueSet(enumeratedValueDomainResource);
		}

		administeredItemContextResource
				.addGrouping(enumeratedValueDomainResource);

		enumeratedConceptualDomainResource
				.addRepresentedByConceptualDomainRepresentation(enumeratedValueDomainResource);

		logger.debug("EnumeratedValueDomain created successfully: {}", name);

		return enumeratedValueDomainResource;
	}

	@Override
	public NonEnumeratedValueDomain createNonEnumeratedValueDomain(
			NonEnumeratedConceptualDomain nonEnumeratedConceptualDomain,
			String name, String definition, Datatype dataType) {
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
				.createAdministeredItemContext(this,
						terminologicalEntryResource);
		this.addAdministeredItemContext(administeredItemContextResource);

		NonEnumeratedConceptualDomainResource nonEnumeratedConceptualDomainResource = nonEnumeratedConceptualDomain
				.asMDRResource();

		// Copy the definition of this resource as the description of this
		// NonEnumeratedValueDomainResource
		NonEnumeratedValueDomainResource nonEnumeratedValueDomainResource = mdrResourceFactory
				.createNonEnumeratedValueDomain(administrationRecordResource,
						dataType.asMDRResource(), null, null, null,
						stewardshipRelationshipResource,
						submissionRelationshipResource,
						registrationAuthorityResource,
						administeredItemContextResource,
						nonEnumeratedConceptualDomainResource, definition,
						nonEnumeratedConceptualDomainResource);

		administeredItemContextResource
				.addGrouping(nonEnumeratedValueDomainResource);

		nonEnumeratedConceptualDomainResource
				.addRepresentedByConceptualDomainRepresentation(nonEnumeratedValueDomainResource);
		nonEnumeratedConceptualDomainResource
				.addRepresentedByNonEnumeratedConceptualDomainRepresentation(nonEnumeratedValueDomainResource);

		logger.debug("EnumeratedValueDomain created successfully: {}", name);

		return nonEnumeratedValueDomainResource;
	}

	@Override
	public DataElementConcept getDataElementConcept(String id) {
		return new DataElementConceptImpl(mdrDatabase.getQueryFactory()
				.getAdministeredItem(id), mdrDatabase);
	}

	@Override
	public int getNumberOfDataElementSearch(String keyword,
			TextSearchType searchType) {
		return this.mdrDatabase.getQueryFactory().getNumberOfDataElementSearch(
				keyword, this.getURI(), searchType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataElement> searchDataElement(String keyword,
			TextSearchType searchType, int limit, int offset) {
		return (List<DataElement>) this.mdrDatabase.getQueryFactory()
				.searchDataElement(keyword, this.getURI(), searchType, limit,
						offset);
	}

	@Override
	public int getNumberOfObjectClasses() {
		return mdrDatabase.getQueryFactory().getNumberOfObjectClasses(getURI());
	}

	/**
	 * Creates a {@link ClassificationScheme} named and defined over parent
	 * Context with given arguments
	 */
	@Override
	public ClassificationScheme createClassificationScheme(String oid,
			String type) {
		String name = MDRConstants.getContentModelFromOID(oid);

		Repository repository = RepositoryManager.getInstance().getRepository();
		RepositoryDefaults repositoryDefaults = repository.getDefaults();
		MDRResourceFactory mdrResourceFactory = this.mdrDatabase
				.getResourceFactory();

		ItemIdentifierResource itemIdentifierResource = repositoryDefaults
				.createItemIdentifierResource(oid);
		AdministrationRecordResource administrationRecordResource = repositoryDefaults
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
				.createRegistrationAuthority(repositoryDefaults
						.getRegistrationAuthorityIdentifierResource(),
						repositoryDefaults.getLanguageIdentificationResource(),
						organizationResource.getName(), organizationResource
								.getMailAddress(), registrarResource);

		LanguageSectionResource languageSectionResource = mdrResourceFactory
				.createLanguageSection(repositoryDefaults
						.getLanguageIdentificationResource());
		DesignationResource designationResource = mdrResourceFactory
				.createDesignation(languageSectionResource, name, true);
		if (!Util.isNull(type)) {
			DefinitionResource definitionResource = mdrResourceFactory
					.createDefinition(languageSectionResource, type, true, null);
			languageSectionResource
					.addContainigDefinitionEntry(definitionResource);
		}
		languageSectionResource.addContainingNameEntry(designationResource);

		TerminologicalEntryResource terminologicalEntryResource = mdrResourceFactory
				.createTerminologicalEntry(languageSectionResource);
		AdministeredItemContextResource administeredItemContextResource = mdrResourceFactory
				.createAdministeredItemContext(this,
						terminologicalEntryResource);
		this.addAdministeredItemContext(administeredItemContextResource);

		ClassificationScheme scheme = mdrResourceFactory
				.createClassificationScheme(administrationRecordResource, name,
						stewardshipRelationshipResource,
						submissionRelationshipResource,
						registrationAuthorityResource,
						administeredItemContextResource);
		administeredItemContextResource.addGrouping(scheme.asMDRResource());

		logger.debug("Classification Scheme created successfully: {}", name);

		return scheme;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ClassificationScheme getClassificationScheme(String csid) {
		List<ClassificationScheme> csList = (List<ClassificationScheme>) mdrDatabase
				.getQueryFactory().getClassificationSchemes(this.getURI());
		for (ClassificationScheme cs : csList) {
			if (cs.getUniqueID().equals(csid)) {
				return cs;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ValueDomain> searchValueDomain(String keyword,
			TextSearchType textSearchType) {
		return (List<ValueDomain>) this.mdrDatabase.getQueryFactory()
				.searchValueDomain(keyword, this.getURI(), textSearchType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ValueDomain> getValueDomains(Integer limit, Integer offset) {
		return (List<ValueDomain>) mdrDatabase.getQueryFactory()
				.getValueDomainsOfContext(getURI(), limit, offset);
	}
}
