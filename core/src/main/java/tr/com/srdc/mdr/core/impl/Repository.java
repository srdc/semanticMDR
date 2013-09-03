package tr.com.srdc.mdr.core.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.Context;
import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.ai.DataElementConcept;
import tr.com.srdc.mdr.core.api.ai.EnumeratedConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.NonEnumeratedConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.ObjectClass;
import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.api.composite.Contact;
import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.api.composite.Organization;
import tr.com.srdc.mdr.core.impl.ai.ConceptImpl;
import tr.com.srdc.mdr.core.impl.ai.DataElementConceptImpl;
import tr.com.srdc.mdr.core.impl.ai.DataElementImpl;
import tr.com.srdc.mdr.core.impl.ai.EnumeratedConceptualDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.EnumeratedValueDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.NonEnumeratedConceptualDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.NonEnumeratedValueDomainImpl;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.ContextResource;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;
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
import tr.com.srdc.mdr.core.store.query.ResourceQueryFactory;
import tr.com.srdc.mdr.core.store.query.ResourceQueryFactory.TextSearchType;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * @author anil
 * 
 */
public class Repository {

	private static final Logger logger = LoggerFactory
			.getLogger(Repository.class);

	private final MDRDatabase mdrDatabase;
	private final RepositoryDefaults repositoryDefaults;

	private final MDRResourceFactory mdrResourceFactory;

	private ResourceQueryFactory resourceQueryFactory;

	/**
	 * Creates a Repository instance by using given {@link MDRDatabase} as a RDF
	 * Backend
	 * 
	 * @param mdrDatabase
	 *            {@link MDRDatabase} instance which will be used as a RDF
	 *            Backend for the Repository
	 * @throws MDRException
	 */
	public Repository(MDRDatabase mdrDatabase) throws MDRException {
		if (mdrDatabase == null) {
			throw new MDRException(
					"MDRdatabase can not be given null for creating repository.");
		}
		this.mdrDatabase = mdrDatabase;
		this.repositoryDefaults = new RepositoryDefaults(this);

		this.mdrResourceFactory = mdrDatabase.getResourceFactory();
		this.resourceQueryFactory = mdrDatabase.getQueryFactory();
		logger.info("A new Repository is created successfully");
	}

	public MDRDatabase getMDRDatabase() {
		return this.mdrDatabase;
	}

	public RepositoryDefaults getDefaults() {
		return this.repositoryDefaults;
	}

	public Contact getContact(String fullName) {
		return resourceQueryFactory.getContactResource(fullName);
	}

	public Organization getOrganization(String organizationName) {
		return resourceQueryFactory.getOrganizationResource(organizationName);
	}

	/**
	 * Create a {@link ContextResource} on the underlying Ontology and then
	 * return the {@link Context} object.
	 * 
	 * @param name
	 * @param definition
	 * @param contactResource
	 * @param organizationResource
	 * @return
	 */
	public Context createContext(String name, String definition,
			Contact contact, Organization organization) {

		ContactResource contactResource = contact.asMDRResource();
		OrganizationResource organizationResource = organization
				.asMDRResource();

		ItemIdentifierResource itemIdentifierResource = repositoryDefaults
				.createItemIdentifierResource(null);
		AdministrationRecordResource administrationRecordResource = repositoryDefaults
				.createAdministrationRecordResource(itemIdentifierResource);

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
		if (!Util.isNull(definition)) {
			DefinitionResource definitionResource = mdrResourceFactory
					.createDefinition(languageSectionResource, definition,
							true, null);
			languageSectionResource
					.addContainigDefinitionEntry(definitionResource);
		}
		languageSectionResource.addContainingNameEntry(designationResource);
		TerminologicalEntryResource terminologicalEntryResource = mdrResourceFactory
				.createTerminologicalEntry(languageSectionResource);
		AdministeredItemContextResource administeredItemContextResource = mdrResourceFactory
				.createAdministeredItemContext(
						repositoryDefaults.getParentContextResource(),
						terminologicalEntryResource);

		Context context = mdrResourceFactory.createContext(
				administrationRecordResource, definition,
				repositoryDefaults.getLanguageIdentificationResource(),
				stewardshipRelationshipResource,
				submissionRelationshipResource, registrationAuthorityResource,
				administeredItemContextResource);

		logger.info("Context created successfully: {}", name);

		return context;
	}

	/**
	 * @return A list of {@link Context}s available in the {@link MDRDatabase}.
	 */
	public List<Context> getContexts() {
		@SuppressWarnings("unchecked")
		List<Context> contextList = (List<Context>) resourceQueryFactory
				.getContextsOfContext(repositoryDefaults
						.getParentContextResource().getURI());
		logger.info("List of Contexts are retrieved");
		return contextList;
	}

	/**
	 * @param contextID
	 *            Unique ID of the {@link Context} in the {@link MDRDatabase}.
	 * @return The {@link Context}
	 */
	public Context getContext(String contextID) {
		return resourceQueryFactory.getContextResource(contextID);
	}

	// TODO
	// Asagidaki get* leri Context'e almak lazim consistency acisindan.

	/**
	 * @return List of all {@link ConceptualDomain}s which have name and
	 *         definition on parent Context
	 */

	@SuppressWarnings("unchecked")
	public List<ConceptualDomain> getConceptualDomains() {
		ContextResource contextResource = this.getDefaults()
				.getParentContextResource();
		return (List<ConceptualDomain>) mdrDatabase.getQueryFactory()
				.getConceptualDomainsOfContext(contextResource.getURI(), null,
						null);
	}

	/**
	 * 
	 * @param limit
	 *            Total number of results to be returned
	 * @param offset
	 *            The initial point of the results
	 * @return List of {@link ConceptualDomain}s which have name and definition
	 *         on parent Context
	 */
	@SuppressWarnings("unchecked")
	public List<ConceptualDomain> getConceptualDomains(Integer limit,
			Integer offset) {
		ContextResource contextResource = this.getDefaults()
				.getParentContextResource();
		return (List<ConceptualDomain>) mdrDatabase.getQueryFactory()
				.getConceptualDomainsOfContext(contextResource.getURI(), limit,
						offset);
	}

	/**
	 * @param cid
	 *            Unique ID of the {@link ConceptualDomain} in the
	 *            {@link MDRDatabase}
	 * @return The {@link ConceptualDomain}
	 */
	public ConceptualDomain getConceptualDomain(String uniqueID) {
		Resource cdResource = mdrDatabase.getQueryFactory()
				.getAdministeredItem(uniqueID);
		OntClass cd = mdrDatabase.getOntModel()
				.getOntClass(cdResource.getURI());

		if (cd.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedConceptualDomain)) {
			return new EnumeratedConceptualDomainImpl(cd, mdrDatabase);
		} else {
			return new NonEnumeratedConceptualDomainImpl(cd, mdrDatabase);
		}
	}

	/**
	 * Creates a {@link EnumeratedConceptualDomain} named and defined over
	 * parent Context with given arguments
	 */
	public EnumeratedConceptualDomain createEnumeratedConceptualDomain(
			String name, String oid, String definition, String dimensionality) {
		RepositoryDefaults defaults = this.getDefaults();
		ContextResource contextResource = defaults.getParentContextResource();
		MDRResourceFactory mdrResourceFactory = this.mdrDatabase
				.getResourceFactory();
		ItemIdentifierResource itemIdentifierResource = defaults
				.createItemIdentifierResource(oid);
		AdministrationRecordResource administrationRecordResource = defaults
				.createAdministrationRecordResource(itemIdentifierResource);

		ContactResource contactResource = contextResource.getAdministerPerson()
				.asMDRResource();
		OrganizationResource organizationResource = contextResource
				.getAdministerOrganization().asMDRResource();

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
				.createAdministeredItemContext(contextResource,
						terminologicalEntryResource);
		contextResource
				.addAdministeredItemContext(administeredItemContextResource);

		EnumeratedConceptualDomainResource enumeratedConceptualDomainResource = mdrResourceFactory
				.createEnumeratedConceptualDomain(administrationRecordResource,
						dimensionality, stewardshipRelationshipResource,
						submissionRelationshipResource,
						registrationAuthorityResource,
						administeredItemContextResource);

		administeredItemContextResource
				.addGrouping(enumeratedConceptualDomainResource);

		logger.info("EnumeratedConceptualDomain created successfully: {}", name);

		return enumeratedConceptualDomainResource;
	}

	/**
	 * Creates a {@link NonEnumeratedConceptualDomain} named and defined over
	 * parent Context with given arguments
	 */
	public NonEnumeratedConceptualDomain createNonEnumeratedConceptualDomain(
			String name, String definition, String dimensionality) {
		RepositoryDefaults defaults = this.getDefaults();
		ContextResource contextResource = defaults.getParentContextResource();

		MDRResourceFactory mdrResourceFactory = this.mdrDatabase
				.getResourceFactory();
		ItemIdentifierResource itemIdentifierResource = defaults
				.createItemIdentifierResource(null);
		AdministrationRecordResource administrationRecordResource = defaults
				.createAdministrationRecordResource(itemIdentifierResource);

		ContactResource contactResource = contextResource.getAdministerPerson()
				.asMDRResource();
		OrganizationResource organizationResource = contextResource
				.getAdministerOrganization().asMDRResource();

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
				.createAdministeredItemContext(contextResource,
						terminologicalEntryResource);
		contextResource
				.addAdministeredItemContext(administeredItemContextResource);

		// Copy the definition of this resource as the description of this
		// NonEnumeratedConceptualDomain
		NonEnumeratedConceptualDomainResource nonEnumeratedConceptualDomainResource = mdrResourceFactory
				.createNonEnumeratedConceptualDomain(
						administrationRecordResource, dimensionality,
						stewardshipRelationshipResource,
						submissionRelationshipResource,
						registrationAuthorityResource,
						administeredItemContextResource, definition);

		administeredItemContextResource
				.addGrouping(nonEnumeratedConceptualDomainResource);

		logger.info("NonEnumeratedConceptualDomain created successfully: {}",
				name);

		return nonEnumeratedConceptualDomainResource;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Executes a full text search over the Repository to find Conceptual domain by name
	 * @param keyword keyword to be matched with designation of Conceptual Domains
	 * @param searchType Exact / AllWords / AtLeastOne. If given <code>nuull</code> default value is Exact
	 * @return
	 */
	public List<ConceptualDomain> searchConceptualDomain(String keyword,
			TextSearchType searchType) {
		return (List<ConceptualDomain>) mdrDatabase.getQueryFactory()
				.searchConceptualDomain(keyword,
						repositoryDefaults.getParentContextResource().getURI(),
						searchType);
	}

	/**
	 * Executes a text search on the whole Repository to find DataElement whose
	 * name mathes with given keyword
	 * 
	 * @param keyword
	 * @param searchType
	 *            Optional. Exact / AtLeastOne / AllWords
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DataElement> searchDataElement(String keyword,
			TextSearchType searchType, int limit, int offset) {
		return (List<DataElement>) mdrDatabase.getQueryFactory()
				.searchDataElement(keyword, null, searchType, limit, offset);
	}

	/**
	 * Calculates the number of total results to be returned from free text
	 * Query
	 * 
	 * @param keyword
	 *            keyword to be matched with the names of {@link DataElement}s
	 * @param searchType
	 *            Exact, Wildcard, AllWords
	 * @return
	 */
	public int getNumberOfDataElementSearch(String keyword,
			TextSearchType searchType) {
		return mdrDatabase.getQueryFactory().getNumberOfDataElementSearch(
				keyword, null, searchType);
	}

	/**
	 * Given the unique ID, return the {@link ObjectClass}.
	 * 
	 * @param ocID
	 * @return
	 */
	public ObjectClass getObjectClass(String ocID) {
		return new ConceptImpl(mdrDatabase.getQueryFactory()
				.getAdministeredItem(ocID), mdrDatabase);
	}

	/**
	 * Gets the data element with the given unique id. If no data element exists
	 * with the given id, returns null.
	 * 
	 * @param id
	 *            data element unique id
	 * @return
	 */
	public DataElement getDataElement(String id) {
		Resource ai = mdrDatabase.getQueryFactory().getAdministeredItem(id);
		if (ai == null) {
			return null;
		}
		return new DataElementImpl(ai, mdrDatabase);
	}

	/**
	 * Given the identifier and the registration authority, return the
	 * {@link DataElement}.
	 * 
	 * @param id
	 *            data element unique id
	 * @param registrationAuthorityOrganizastionID
	 *            organizationIdentifier of the RegistrationAuthority who
	 *            authors the requested Data Element.
	 * @param version
	 * @return
	 */
	public DataElement getDataElement(String id,
			String registrationAuthorityOrganizationID, String version) {
		Resource ai = mdrDatabase.getQueryFactory().getAdministeredItem(id,
				registrationAuthorityOrganizationID, version);
		if (ai == null) {
			return null;
		}
		return new DataElementImpl(ai, mdrDatabase);
	}

	public DataElementConcept getDataElementConcept(String decID) {
		return new DataElementConceptImpl(mdrDatabase.getQueryFactory()
				.getAdministeredItem(decID), mdrDatabase);
	}

	public ValueDomain getValueDomain(String valueDomainID) {
		Resource resource = mdrDatabase.getQueryFactory().getAdministeredItem(
				valueDomainID);
		OntClass res = mdrDatabase.getOntModel().getOntClass(resource.getURI());
		if (res == null) {
			return null;
		}
		ValueDomain vd = null;
		if (res.hasSuperClass(
				mdrDatabase.getVocabulary().EnumeratedValueDomain, false)) {
			vd = new EnumeratedValueDomainImpl(res, mdrDatabase);

		} else {
			vd = new NonEnumeratedValueDomainImpl(res, mdrDatabase);
		}
		return vd;
	}

	public int getNumberOfConceptualDomains() {
		ContextResource contextResource = this.getDefaults()
				.getParentContextResource();
		return mdrDatabase.getQueryFactory().getNumberOfConceptualDomains(
				contextResource.getURI());
	}

	@SuppressWarnings("unchecked")
	public List<Datatype> getDataTypes() {
		return (List<Datatype>) mdrDatabase.getQueryFactory().getDatatypes();
	}

}
