package tr.com.srdc.mdr.core.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.salusproject.securityprivacy.authentication.Organization;
import eu.salusproject.securityprivacy.authentication.User;
import eu.salusproject.securityprivacy.authentication.db.Database;
import eu.salusproject.securityprivacy.authentication.db.DatabaseException;

import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.model.ISODataType;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.ContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DatatypeResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ItemIdentifierResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageIdentificationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityIdentifierResource;
import tr.com.srdc.mdr.core.store.query.ResourceQueryFactory;
import tr.com.srdc.mdr.core.store.query.ResourceQueryFactory.TextSearchType;
import tr.com.srdc.mdr.core.util.Status;

public class RepositoryDefaults {

	private static final Logger logger = LoggerFactory
			.getLogger(Repository.class);

	private final Repository repository;
	private final MDRResourceFactory resourceFactory;

	private static final String ISO_21090 = "ISO21090";
	private static final String ISO_11404 = "ISO11404";

	private Map<ISODataType, Datatype> isoDatatypes;

	private HashMap<ISODataType, ConceptualDomain> isoDatatypesConceptualDomains;

	public RepositoryDefaults(Repository repository) throws MDRException {
		this.repository = repository;
		this.resourceFactory = this.repository.getMDRDatabase()
				.getResourceFactory();
		// initDefaultResources();
	}

	public void initDefaultResources() throws MDRException {
		initOrganizations();
		initUsers();
		boolean initialized = initDatatypes();
		initConceptualDomains(initialized);
	}

	/**
	 * @param initialized
	 *            indicates whether conceptual domains should be created or
	 *            retrieved, if true conceptual domains are created, if false
	 *            they are just retrieved
	 */
	private void initConceptualDomains(boolean initialized) {
		isoDatatypesConceptualDomains = new HashMap<ISODataType, ConceptualDomain>();

		if (initialized) {
			isoDatatypesConceptualDomains
					.put(ISODataType.BOOLEAN,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.BOOLEAN.toString(),
									"Non Enumerated Conceptual Domain for Boolean",
									""));
			isoDatatypesConceptualDomains.put(ISODataType.CHARACTER, repository
					.createNonEnumeratedConceptualDomain(
							ISODataType.CHARACTER.toString(),
							"Non Enumerated Conceptual Domain for Character",
							""));
			isoDatatypesConceptualDomains.put(ISODataType.STRING, repository
					.createNonEnumeratedConceptualDomain(
							ISODataType.STRING.toString(),
							"Non Enumerated Conceptual Domain for String", ""));
			isoDatatypesConceptualDomains.put(ISODataType.TIME, repository
					.createNonEnumeratedConceptualDomain(
							ISODataType.TIME.toString(),
							"Non Enumerated Conceptual Domain for Time", ""));
			isoDatatypesConceptualDomains
					.put(ISODataType.INTEGER,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.INTEGER.toString(),
									"Non Enumerated Conceptual Domain for Integer",
									""));
			isoDatatypesConceptualDomains.put(ISODataType.REAL, repository
					.createNonEnumeratedConceptualDomain(
							ISODataType.REAL.toString(),
							"Non Enumerated Conceptual Domain for Real", ""));
			isoDatatypesConceptualDomains
					.put(ISODataType.CD,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.CD.toString(),
									"Enumerated Conceptual Domain for Concept Descriptor",
									""));
			isoDatatypesConceptualDomains
					.put(ISODataType.CD_CV,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.CD_CV.toString(),
									"Non Enumerated Conceptual Domain for Coded Description",
									""));
			isoDatatypesConceptualDomains
					.put(ISODataType.TS_DATE,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.TS_DATE.toString(),
									"Non Enumerated Conceptual Domain for TS Date",
									""));
			isoDatatypesConceptualDomains.put(ISODataType.TS_DATETIME,
					repository.createNonEnumeratedConceptualDomain(
							ISODataType.TS_DATETIME.toString(),
							"Non Enumerated Conceptual Domain for TS Datetime",
							""));
			isoDatatypesConceptualDomains
					.put(ISODataType.II,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.II.toString(),
									"Non Enumerated Conceptual Domain for Item Identifier",
									""));
			isoDatatypesConceptualDomains
					.put(ISODataType.INT_NONNEG,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.INT_NONNEG.toString(),
									"Non Enumerated Conceptual Domain for Non Negative Integer",
									""));
			isoDatatypesConceptualDomains
					.put(ISODataType.PQ,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.PQ.toString(),
									"Non Enumerated Conceptual Domain for Dimensioned quantity expressing the result of measuring.",
									""));
			isoDatatypesConceptualDomains
					.put(ISODataType.PQ_TIME,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.PQ_TIME.toString(),
									"Non Enumerated Conceptual Domain for Dimensioned quantity expressing the result of time measuring",
									""));
			isoDatatypesConceptualDomains
					.put(ISODataType.IVL_TS,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.IVL_TS.toString(),
									"Non Enumerated Conceptual Domain for consecutive values of TS Datetime",
									""));
			isoDatatypesConceptualDomains
					.put(ISODataType.IVL_PQ,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.IVL_PQ.toString(),
									"Non Enumerated Conceptual Domain for consecutive values of ordered physical quantity",
									""));
			isoDatatypesConceptualDomains
					.put(ISODataType.PIVL_TS,
							repository.createNonEnumeratedConceptualDomain(
									ISODataType.PIVL_TS.toString(),
									"Non Enumerated Conceptual Domain for periodic consecutive values of TS Datetime",
									""));
		} else {
			isoDatatypesConceptualDomains.put(
					ISODataType.BOOLEAN,
					repository.searchConceptualDomain(
							ISODataType.BOOLEAN.toString(),
							TextSearchType.Exact).get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.CHARACTER,
					repository.searchConceptualDomain(
							ISODataType.CHARACTER.toString(),
							TextSearchType.Exact).get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.STRING,
					repository
							.searchConceptualDomain(
									ISODataType.STRING.toString(),
									TextSearchType.Exact).get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.TIME,
					repository.searchConceptualDomain(
							ISODataType.TIME.toString(), TextSearchType.Exact)
							.get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.INTEGER,
					repository.searchConceptualDomain(
							ISODataType.INTEGER.toString(),
							TextSearchType.Exact).get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.REAL,
					repository.searchConceptualDomain(
							ISODataType.REAL.toString(), TextSearchType.Exact)
							.get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.CD,
					repository.searchConceptualDomain(
							ISODataType.CD.toString(), TextSearchType.Exact)
							.get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.CD_CV,
					repository.searchConceptualDomain(
							ISODataType.CD_CV.toString(), TextSearchType.Exact)
							.get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.TS_DATETIME,
					repository.searchConceptualDomain(
							ISODataType.TS_DATETIME.toString(),
							TextSearchType.Exact).get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.TS_DATE,
					repository.searchConceptualDomain(
							ISODataType.TS_DATE.toString(),
							TextSearchType.Exact).get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.II,
					repository.searchConceptualDomain(
							ISODataType.II.toString(), TextSearchType.Exact)
							.get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.INT_NONNEG,
					repository.searchConceptualDomain(
							ISODataType.INT_NONNEG.toString(),
							TextSearchType.Exact).get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.PQ,
					repository.searchConceptualDomain(
							ISODataType.PQ.toString(), TextSearchType.Exact)
							.get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.PQ_TIME,
					repository.searchConceptualDomain(
							ISODataType.PQ_TIME.toString(),
							TextSearchType.Exact).get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.IVL_TS,
					repository
							.searchConceptualDomain(
									ISODataType.IVL_TS.toString(),
									TextSearchType.Exact).get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.IVL_PQ,
					repository
							.searchConceptualDomain(
									ISODataType.IVL_PQ.toString(),
									TextSearchType.Exact).get(0));
			isoDatatypesConceptualDomains.put(
					ISODataType.PIVL_TS,
					repository.searchConceptualDomain(
							ISODataType.PIVL_TS.toString(),
							TextSearchType.Exact).get(0));

		}

		repository.getMDRDatabase().sync();
		logger.info("NonEnumeratedConceptualDomain's for default ISO Datatypes are created successfully");
	}

	private boolean initDatatypes() {
		// TODO: We can put these datatypes directly to the ISO11179 ontology.
		isoDatatypes = new HashMap<ISODataType, Datatype>();
		List<Datatype> datatypeList = repository.getDataTypes();
		isoDatatypes.put(ISODataType.BOOLEAN,
				createDatatype(ISODataType.BOOLEAN.toString(), ISO_11404));
		isoDatatypes.put(ISODataType.CHARACTER,
				createDatatype(ISODataType.CHARACTER.toString(), ISO_11404));
		isoDatatypes.put(ISODataType.STRING,
				createDatatype(ISODataType.STRING.toString(), ISO_11404));
		isoDatatypes.put(ISODataType.TIME,
				createDatatype(ISODataType.TIME.toString(), ISO_11404));
		isoDatatypes.put(ISODataType.INTEGER,
				createDatatype(ISODataType.INTEGER.toString(), ISO_11404));
		isoDatatypes.put(ISODataType.REAL,
				createDatatype(ISODataType.REAL.toString(), ISO_21090));
		isoDatatypes.put(ISODataType.CD,
				createDatatype(ISODataType.CD.toString(), ISO_21090));
		isoDatatypes.put(ISODataType.CD_CV,
				createDatatype(ISODataType.CD_CV.toString(), ISO_21090));
		isoDatatypes.put(ISODataType.TS_DATE,
				createDatatype(ISODataType.TS_DATE.toString(), ISO_21090));
		isoDatatypes.put(ISODataType.TS_DATETIME,
				createDatatype(ISODataType.TS_DATETIME.toString(), ISO_21090));
		isoDatatypes.put(ISODataType.II,
				createDatatype(ISODataType.II.toString(), ISO_21090));
		isoDatatypes.put(ISODataType.INT_NONNEG,
				createDatatype(ISODataType.INT_NONNEG.toString(), ISO_21090));
		isoDatatypes.put(ISODataType.PQ,
				createDatatype(ISODataType.PQ.toString(), ISO_21090));
		isoDatatypes.put(ISODataType.PQ_TIME,
				createDatatype(ISODataType.PQ_TIME.toString(), ISO_21090));
		isoDatatypes.put(ISODataType.IVL_TS,
				createDatatype(ISODataType.IVL_TS.toString(), ISO_21090));
		isoDatatypes.put(ISODataType.IVL_PQ,
				createDatatype(ISODataType.IVL_PQ.toString(), ISO_21090));
		isoDatatypes.put(ISODataType.PIVL_TS,
				createDatatype(ISODataType.PIVL_TS.toString(), ISO_21090));

		repository.getMDRDatabase().sync();
		logger.info("Default Datatypes from ISO 11404 and ISO 21090 are created.");
		if (datatypeList.containsAll(isoDatatypes.values())) {
			return false;
		}
		return true;
	}

	private void initOrganizations() throws MDRException {
		List<Organization> organizationList = null;
		try {
			organizationList = Database.getInstance().getAllOrganizations();
		} catch (DatabaseException e) {
			throw new MDRException(
					"Cannot init the Organization resources from the Auth database",
					e);
		}
		for (Organization organization : organizationList) {
			resourceFactory.createOrganization(organization.getName(),
					organization.getEmail());
		}
		logger.info("Default OrganizationResources are created successfully for the default users.");
	}

	private void initUsers() throws MDRException {
		List<User> userList = null;
		try {
			userList = Database.getInstance().getAllUsers();
		} catch (DatabaseException e) {
			throw new MDRException(
					"Cannot init the User resources from the Auth database", e);
		}
		for (User user : userList) {
			resourceFactory.createContact(user.getEmail(), user.getFullName(),
					user.getTitle());
		}
		logger.info("Default ContactResources are created successfully for the default users.");
	}

	private final Status registrationStatus = Status.Qualified;
	private final Status administrativeStatus = Status.Qualified;

	/**
	 * @return ItemIdentifier with given ID
	 */
	public ItemIdentifierResource createItemIdentifierResource(String uniqueID) {
		if (uniqueID == null) {
			return resourceFactory.createItemIdentifier();
		} else {
			return resourceFactory.createItemIdentifier(uniqueID);
		}
	}

	public RegistrationAuthorityIdentifierResource getRegistrationAuthorityIdentifierResource() {
		return this.resourceFactory.getVocabulary().mdrRegistrationAuthorityIdentifier;
	}

	public LanguageIdentificationResource getLanguageIdentificationResource() {
		return this.resourceFactory.getVocabulary().mdrLanguageIdentificationResource;
	}

	public ContextResource getParentContextResource() {
		return this.resourceFactory.getVocabulary().mdrContextResource;
	}

	public AdministrationRecordResource createAdministrationRecordResource(
			ItemIdentifierResource itemIdentifierResource) {
		return resourceFactory.createAdministrationRecord(
				itemIdentifierResource, registrationStatus.toString(),
				administrativeStatus.toString(), Calendar.getInstance(), null,
				null, null, null, null, null, null, null);
	}

	public Status getRegistrationStatus() {
		return registrationStatus;
	}

	public Status getAdministrativeStatus() {
		return administrativeStatus;
	}

	/**
	 * @return The map of the {@link Datatype}s created for ISO11404 and
	 *         ISO21090
	 */
	public Map<ISODataType, Datatype> getISODatatypes() {
		return isoDatatypes;
	}

	/**
	 * @return Map of {@link ConceptualDomain}s created for ISO11404 and
	 *         ISO21090 Datatypes
	 */
	public HashMap<ISODataType, ConceptualDomain> getISOConceptualDomains() {
		return isoDatatypesConceptualDomains;
	}

	/**
	 * Creates a new {@link Datatype} with given name from given schema, if
	 * exist one wit given name and scheme, return existing one
	 *
	 * @param name
	 *            Name of the Datatype to be created
	 * @param schemeReference
	 *            Reference to scheme where datatype is retrieved
	 */
	public Datatype createDatatype(String name, String schemeReference) {
		return resourceFactory.createDatatype(name, "ISO 11179 Datatype for "
				+ name, schemeReference, null);
	}

	/**
	 * Executes a full text query on the MDR to find a datatype
	 *
	 * @param name
	 *            Exact name of the Datatype to be looked for
	 * @return Result of the search, if not matches, return <code>null</code>
	 */
	public Datatype searchDatatype(String name) {
		ResourceQueryFactory queryFactory = repository.getMDRDatabase()
				.getQueryFactory();
		List<? super DatatypeResource> dtList = queryFactory
				.searchDatatype(name);
		if (dtList.isEmpty()) {
			logger.error("There is no datatype matching with {}", name);
			return null;
		}
		return (Datatype) dtList.get(0);
	}

}
