package tr.com.srdc.mdr.core.model;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.impl.ai.AdministeredItemImpl;
import tr.com.srdc.mdr.core.impl.ai.ClassificationSchemeImpl;
import tr.com.srdc.mdr.core.impl.ai.ConceptImpl;
import tr.com.srdc.mdr.core.impl.ai.ConceptRelationshipImpl;
import tr.com.srdc.mdr.core.impl.ai.ContextImpl;
import tr.com.srdc.mdr.core.impl.ai.DataElementConceptImpl;
import tr.com.srdc.mdr.core.impl.ai.DataElementImpl;
import tr.com.srdc.mdr.core.impl.ai.DerivationRuleImpl;
import tr.com.srdc.mdr.core.impl.ai.EnumeratedConceptualDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.EnumeratedValueDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.NonEnumeratedConceptualDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.NonEnumeratedValueDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.PropertyImpl;
import tr.com.srdc.mdr.core.impl.ai.RepresentationClassImpl;
import tr.com.srdc.mdr.core.impl.composite.AdministeredItemContextImpl;
import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.impl.composite.ClassificationSchemeItemAssociationImpl;
import tr.com.srdc.mdr.core.impl.composite.ClassificationSchemeItemImpl;
import tr.com.srdc.mdr.core.impl.composite.ClassificationSchemeItemRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.ConceptualDomainRelationshipAssociationImpl;
import tr.com.srdc.mdr.core.impl.composite.ConceptualDomainRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.ContactImpl;
import tr.com.srdc.mdr.core.impl.composite.DataElementConceptRelationshipAssociationImpl;
import tr.com.srdc.mdr.core.impl.composite.DataElementConceptRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.DataElementDerivationImpl;
import tr.com.srdc.mdr.core.impl.composite.DataElementExampleImpl;
import tr.com.srdc.mdr.core.impl.composite.DatatypeImpl;
import tr.com.srdc.mdr.core.impl.composite.DefinitionImpl;
import tr.com.srdc.mdr.core.impl.composite.DesignationImpl;
import tr.com.srdc.mdr.core.impl.composite.ItemIdentifierImpl;
import tr.com.srdc.mdr.core.impl.composite.LanguageIdentificationImpl;
import tr.com.srdc.mdr.core.impl.composite.LanguageSectionImpl;
import tr.com.srdc.mdr.core.impl.composite.OrganizationImpl;
import tr.com.srdc.mdr.core.impl.composite.PermissibleValueImpl;
import tr.com.srdc.mdr.core.impl.composite.ReferenceDocumentImpl;
import tr.com.srdc.mdr.core.impl.composite.RegistrarImpl;
import tr.com.srdc.mdr.core.impl.composite.RegistrationAuthorityIdentifierImpl;
import tr.com.srdc.mdr.core.impl.composite.RegistrationAuthorityImpl;
import tr.com.srdc.mdr.core.impl.composite.StewardshipImpl;
import tr.com.srdc.mdr.core.impl.composite.StewardshipRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.SubmissionImpl;
import tr.com.srdc.mdr.core.impl.composite.SubmissionRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.TerminologicalEntryImpl;
import tr.com.srdc.mdr.core.impl.composite.UnitOfMeasureImpl;
import tr.com.srdc.mdr.core.impl.composite.ValueDomainRelationshipAssociationImpl;
import tr.com.srdc.mdr.core.impl.composite.ValueDomainRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.ValueImpl;
import tr.com.srdc.mdr.core.impl.composite.ValueMeaningImpl;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ClassificationSchemeResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.ContextResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.DerivationRuleResource;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.ObjectClassResource;
import tr.com.srdc.mdr.core.model.iso11179.PropertyResource;
import tr.com.srdc.mdr.core.model.iso11179.RepresentationClassResource;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ConceptualDomainRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ConceptualDomainRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementConceptRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementConceptRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementDerivationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementExampleResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DatatypeResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DefinitionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DesignationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ItemIdentifierResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageIdentificationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageSectionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.PermissibleValueResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ReferenceDocumentResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrarResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityIdentifierResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.TerminologicalEntryResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.UnitOfMeasureResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueDomainRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueDomainRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueMeaningResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;
import tr.com.srdc.mdr.core.util.Country;
import tr.com.srdc.mdr.core.util.Language;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.OntModel;


/**
 * @author anil
 * 
 *         Resources on the associated {@link OntModel}s should be created
 *         through the create methods of this factory.
 * 
 */
public class MDRResourceFactory {

	private static final Logger logger = LoggerFactory
			.getLogger(MDRResourceFactory.class);

	private MDRDatabase mdrDatabase;
	private OntModel ontModel;
	private Vocabulary vocabulary;

	/**
	 * Each {@link MDRResourceFactory} is associated with a unique
	 * {@link MDRDatabase}. Therefore, the methods of this factory will be
	 * effective only on the associated {@link OntModel} of the
	 * {@link MDRDatabase}.
	 * 
	 * @param ontModel
	 */
	public MDRResourceFactory(MDRDatabase mdrDatabase) {
		this.mdrDatabase = mdrDatabase;
		this.ontModel = mdrDatabase.getOntModel();
		this.vocabulary = mdrDatabase.getVocabulary();
		if (this.ontModel == null) {
			String msg = "ResourceFactory cannot be initialized without a valid Jena OntModel";
			logger.error(msg);
			throw new IllegalStateException(msg);
		}
		logger.info("MDRResourceFactory created with {}",
				mdrDatabase.getStoreType());
	}

	/**
	 * @return The associated {@link MDRDatabase}.
	 */
	public MDRDatabase getMDRDatabase() {
		return this.mdrDatabase;
	}

	/**
	 * @return The associated {@link OntModel}.
	 */
	public OntModel getOntModel() {
		return this.ontModel;
	}

	/**
	 * @return The associated {@link Vocabulary}.
	 */
	public Vocabulary getVocabulary() {
		return this.vocabulary;
	}

	public static final String DEFAULT_VERSION = "0.1";

	public String makeID(Object... arr) {
		StringBuilder sb = new StringBuilder(MDRDatabase.BASE_URI);
		for (int i = 0; i < arr.length - 1; i++) {
			sb.append(arr[i]).append("_");
		}
		sb.append(arr[arr.length - 1]);
		String uri = sb.toString();
		// instead of encoding, let's replace all space characters with
		// underscore character
		uri = uri.replaceAll(" ", "_");
		return uri;
	}

	private String generateUniqueID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * The method to create {@link LanguageIdentificationResource} on
	 * {@link Abbreviation}.
	 * 
	 * @param languageIdentifier
	 *            Use the three character alphabetic codes from ISO
	 *            639-2/Terminology, with extensions if required.
	 * @param countryIdentifier
	 *            Optional. Use the three digit numeric codes from ISO 3166-1,
	 *            with extensions if required.
	 * @return {@link LanguageIdentificationResource} on {@link Abbreviation}
	 *         with a specific URI generated from parameters.
	 */
	public LanguageIdentificationResource createLanguageIdentification(
			Language languageIdentifier, Country countryIdentifier) {
		if (languageIdentifier == null) {
			throw new IllegalArgumentException(
					"Language must be specified  for LanguageIdentification.");
		}

		Node node = Node
				.createURI(makeID(languageIdentifier, countryIdentifier));
		LanguageIdentificationResource languageIdentification = new LanguageIdentificationImpl(
				node, (EnhGraph) ontModel, languageIdentifier,
				countryIdentifier, mdrDatabase);
		return languageIdentification;
	}

	/**
	 * The method to create {@link RegistrationAuthorityIdentifierResource} on
	 * {@link Abbreviation}.
	 * 
	 * @param internationalCodeDesignator
	 *            The identifier of an organization identification scheme.
	 * @param organizationIdentifier
	 *            The identifier assigned to an Organization within an
	 *            organization identification scheme, and unique within that
	 *            scheme.The identifier are specified in ISO/IEC 11179-3.
	 * @param organizationPartIdentifier
	 *            Optional. An identifier allocated to a particular organization
	 *            part.The identifier are specified in ISO/IEC 11179-3.
	 * @param opiSource
	 *            Optional. However, must exist if the
	 *            <code>organizationPartIdentifier</code> is specified. The
	 *            source for the organization part identifier. The identifier
	 *            are specified in ISO/IEC 11179-3.
	 * @return {@link RegistrationAuthorityIdentifierResource} on
	 *         {@link Abbreviation} with a specific URI generated from
	 *         Organization Identifier and Organization Part Identifier.
	 */
	public RegistrationAuthorityIdentifierResource createRegistrationAuthorityIdentifier(
			String internationalCodeDesignator, String organizationIdentifier,
			String organizationPartIdentifier, String opiSource) {
		if (Util.isNull(internationalCodeDesignator)) {
			throw new IllegalArgumentException(
					"International Code Designator must be specified for RegistrationAuthorityIdentifier.");
		}
		if (Util.isNull(organizationIdentifier)) {
			throw new IllegalArgumentException(
					"Organization Identifier must be specified for RegistrationAuthorityIdentifier.");
		}
		if (!Util.isNull(organizationPartIdentifier)) {
			if (Util.isNull(opiSource)) {
				throw new IllegalArgumentException(
						"Opi Source must be specified for RegistrationAuthorityIdentifier.");
			}
		}
		Node node = Node.createURI(makeID(organizationIdentifier,
				organizationPartIdentifier));
		RegistrationAuthorityIdentifierResource registrationAuthorityIdentifier = new RegistrationAuthorityIdentifierImpl(
				node, (EnhGraph) ontModel, internationalCodeDesignator,
				organizationIdentifier, organizationPartIdentifier, opiSource,
				mdrDatabase);
		return registrationAuthorityIdentifier;
	}

	/**
	 * Creates an {@link ItemIdentifierResource} by using the default MDR
	 * {@link RegistrationAuthorityIdentifierResource}
	 * 
	 * @param uniqueID
	 *            Optional. If not given overriding method with random ID
	 *            generator is called
	 * @return
	 */
	public ItemIdentifierResource createItemIdentifier() {
		return createItemIdentifier(this.vocabulary.mdrRegistrationAuthorityIdentifier);
	}

	/**
	 * Creates an {@link ItemIdentifierResource} by using the default MDR
	 * {@link RegistrationAuthorityIdentifierResource}
	 * 
	 * @param uniqueID
	 *            Optional. If not given overriding method with random ID
	 *            generator is called
	 * @return
	 */
	public ItemIdentifierResource createItemIdentifier(String uniqueID) {
		return createItemIdentifier(
				this.vocabulary.mdrRegistrationAuthorityIdentifier, uniqueID);
	}

	/**
	 * The method to create {@link ItemIdentifierResource} on
	 * {@link Abbreviation}.
	 * 
	 * @param registrationAuthorityIdentifier
	 *            The identifier for the owning Registration Authority.
	 * @param uniqueID
	 *            OPtional. IF not provided, then overriding method with random
	 *            ID generator is called
	 * @return {@link ItemIdentifierResource} on {@link Abbreviation} with a
	 *         specific URI generated from {@link Abbreviation#ItemIdentifier}.
	 */
	public ItemIdentifierResource createItemIdentifier(
			RegistrationAuthorityIdentifierResource registrationAuthorityIdentifier) {
		if (registrationAuthorityIdentifier == null) {
			throw new IllegalArgumentException(
					"Registration Authority Identifier must be specified for ItemIdentifier.");
		}
		String uniqueID = generateUniqueID();
		Node node = Node.createURI(makeID(
				Abbreviation.ItemIdentifier.toString(), uniqueID));
		ItemIdentifierResource itemIdentifier = new ItemIdentifierImpl(node,
				(EnhGraph) ontModel, registrationAuthorityIdentifier, uniqueID,
				DEFAULT_VERSION, mdrDatabase);
		return itemIdentifier;
	}

	/**
	 * The method to create {@link ItemIdentifierResource} on
	 * {@link Abbreviation}.
	 * 
	 * @param registrationAuthorityIdentifier
	 *            The identifier for the owning Registration Authority.
	 * @param uniqueID
	 *            OPtional. IF not provided, then overriding method with random
	 *            ID generator is called
	 * @return {@link ItemIdentifierResource} on {@link Abbreviation} with a
	 *         specific URI generated from {@link Abbreviation#ItemIdentifier}.
	 */
	public ItemIdentifierResource createItemIdentifier(
			RegistrationAuthorityIdentifierResource registrationAuthorityIdentifier,
			String uniqueID) {
		if (registrationAuthorityIdentifier == null) {
			throw new IllegalArgumentException(
					"Registration Authority Identifier must be specified for ItemIdentifier.");
		}
		Node node = Node.createURI(makeID(
				Abbreviation.ItemIdentifier.toString(), uniqueID));
		ItemIdentifierResource itemIdentifier = new ItemIdentifierImpl(node,
				(EnhGraph) ontModel, registrationAuthorityIdentifier, uniqueID,
				DEFAULT_VERSION, mdrDatabase);
		return itemIdentifier;
	}

	/**
	 * The method to create {@link ContactResource} on {@link Abbreviation}.
	 * 
	 * @param contactInformation
	 *            Information to enable a Contact to be located or communicated
	 *            with.
	 * @param contactName
	 *            The name of the Contact.
	 * @param contactTitle
	 *            Optional. The name of the position held by the Contact.
	 * @return {@link ContactResource} on {@link Abbreviation} with a specific
	 *         URI generated from contactName.
	 */
	public ContactResource createContact(String contactInformation,
			String contactName, String contactTitle) {
		if (Util.isNull(contactInformation)) {
			throw new IllegalArgumentException(
					"Contact Information must be specified  for Contact.");
		}
		if (Util.isNull(contactName)) {
			throw new IllegalArgumentException(
					"Contact Name must be specified  for Contact.");
		}

		Node node = Node.createURI(makeID(Abbreviation.Contact.toString(),
				contactName));
		ContactResource contact = new ContactImpl(node, (EnhGraph) ontModel,
				contactInformation, contactName, contactTitle, mdrDatabase);
		return contact;
	}

	/**
	 * The method to create {@link AdministrationRecordResource} on
	 * {@link Abbreviation}.
	 * 
	 * @param administeredItemIdentifier
	 *            An identifier for an administered item.
	 * @param registrationStatus
	 *            A designation of the status in the registration life-cycle of
	 *            an Administered Item.
	 * @param administrativeStatus
	 *            A designation of the status in the administrative process of a
	 *            Registration Authority for handling registration requests.
	 * @param creationDate
	 *            The date the Administered Item was created.
	 * @param lastChangeDate
	 *            The date the Administered Item was last changed.
	 * @param effectiveDate
	 *            Optional. The date an administered item became/becomes
	 *            available to registry users.
	 * @param untilDate
	 *            Optional. The date an Administered Item is no longer effective
	 *            in the registry.
	 * @param changeDescription
	 *            Optional. However, must exist if the
	 *            <code>lastChangeDate</code> is specified.The description of
	 *            what has changed in the Administered Item since the prior
	 *            version of the Administered Item.
	 * @param administrativeNote
	 *            Optional. Any general note about the Administered Item.
	 * @param explanatoryComment
	 *            Optional. Descriptive comments about the Administered Item.
	 * @param unresolvedIssue
	 *            Optional. Any problem that remains unresolved regarding proper
	 *            documentation of the Administered Item.
	 * @param origin
	 *            Optional. (Administered item) the source (document, project,
	 *            discipline or model) for the Administered Item.
	 * @return {@link AdministrationRecordResource} on {@link Abbreviation} with
	 *         a specific URI generated from
	 *         {@link Abbreviation#AdministrationRecord}.
	 */
	public AdministrationRecordResource createAdministrationRecord(
			ItemIdentifierResource administeredItemIdentifier,
			String registrationStatus, String administrativeStatus,
			Calendar creationDate, Calendar lastChangeDate,
			Calendar effectiveDate, Calendar untilDate,
			String changeDescription, String administrativeNote,
			String explanatoryComment, String unresolvedIssue, String origin) {

		if (administeredItemIdentifier == null) {
			throw new IllegalArgumentException(
					"Administered Item Identifier must be specified for AdministrationRecord.");
		}
		if (Util.isNull(administrativeStatus)) {
			throw new IllegalArgumentException(
					"Administrative Status must be specified for AdministrationRecord.");
		}
		if (Util.isNull(registrationStatus)) {
			throw new IllegalArgumentException(
					"Registration Status must be specified for AdministrationRecord.");
		}
		if (creationDate == null) {
			throw new IllegalArgumentException(
					"Creation Date must be specified for AdministrationRecord.");
		}

		if (lastChangeDate != null) {
			if (Util.isNull(changeDescription)) {
				throw new IllegalArgumentException(
						"Change Description must be specified for AdministrationRecord.");
			}
		}
		String uniqueID = administeredItemIdentifier.getDataIdentifier();
		Node node = Node.createURI(makeID(
				Abbreviation.AdministrationRecord.toString(), uniqueID));
		AdministrationRecordResource administrationRecord = new AdministrationRecordImpl(
				node, (EnhGraph) ontModel, administeredItemIdentifier,
				registrationStatus, administrativeStatus, creationDate,
				lastChangeDate, effectiveDate, untilDate, changeDescription,
				administrativeNote, explanatoryComment, unresolvedIssue,
				origin, mdrDatabase);

		return administrationRecord;
	}

	/**
	 * The method to create {@link PropertyResource} on {@link Abbreviation}.
	 * 
	 * @param propertyAdministrationRecord
	 *            The Administration Record for a Property.
	 * @param administeredBy
	 *            An Administered Item is administered by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link StewardshipRelationshipImpl}.
	 * @param submittedBy
	 *            An Administered Item is submitted by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link SubmissionRelationshipImpl}.
	 * @param registeredBy
	 *            An {@link AdministeredItemImpl} is registered by a
	 *            {@link RegistrationAuthorityImpl} represented by the
	 *            relationship registration.
	 * 
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextImpl}.
	 * @return {@link PropertyResource} on {@link Abbreviation} with a specific
	 *         URI generated from {@link Abbreviation#Property}.
	 */
	public PropertyResource createProperty(
			AdministrationRecordResource propertyAdministrationRecord,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having) {
		if (propertyAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be specified for Property.");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for Classification Scheme");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for ClassificationScheme");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for Classification Scheme");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for Classification Scheme");
		}
		String uniqueID = propertyAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(Abbreviation.Property.toString(),
				uniqueID));
		PropertyResource property = new PropertyImpl(node, (EnhGraph) ontModel,
				propertyAdministrationRecord, administeredBy, submittedBy,
				registeredBy, having, mdrDatabase);
		return property;
	}

	/**
	 * The method to create {@link DataElementResource} on {@link Abbreviation}.
	 * 
	 * @param dataElementAdministrationRecord
	 *            The Administration Record for an Data Element.
	 * @param representationClassQualifier
	 *            Optional. A qualifier to the Representation Class used in
	 *            naming Data Elements and Value Domains.
	 * @param dataElementPrecision
	 *            Optional. The degree of specificity for a Data Element.
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
	 *            {@link RegistrationAuthorityResource} represented by the
	 *            relationship registration.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}
	 * @param expressingDataElementConceptExpression
	 *            An {@link DataElementResource} has to have at least one
	 *            {@link DataElementConceptResource}
	 * @param representedByDataElementRepresentation
	 *            An {@link DataElementResource} has to have at least one
	 *            {@link ValueDomainResource}
	 * @return {@link DataElementResource} on {@link Abbreviation} with a
	 *         specific URI generated from {@link Abbreviation#DataElement}.
	 */
	public DataElementResource createDataElement(
			AdministrationRecordResource dataElementAdministrationRecord,
			String representationClassQualifier, Integer dataElementPrecision,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having,
			DataElementConceptResource expressingDataElementConceptExpression,
			ValueDomainResource representedByDataElementRepresentation) {
		if (dataElementAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Data Element Administration Record must be specified for DataElement.");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for Classification Scheme");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for ClassificationScheme");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for Classification Scheme");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for Classification Scheme");
		}
		if (expressingDataElementConceptExpression == null) {
			throw new IllegalArgumentException(
					"Data Element Concept must be specified for Data Element.");
		}
		if (representedByDataElementRepresentation == null) {
			throw new IllegalArgumentException(
					"Value Domain must be specified for Data Element.");
		}
		String uniqueID = dataElementAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(Abbreviation.DataElement.toString(),
				uniqueID));
		DataElementResource dataElement = new DataElementImpl(node,
				(EnhGraph) ontModel, dataElementAdministrationRecord,
				representationClassQualifier, dataElementPrecision,
				administeredBy, submittedBy, registeredBy, having,
				expressingDataElementConceptExpression,
				representedByDataElementRepresentation, mdrDatabase);
		return dataElement;
	}

	/**
	 * The method to create {@link DataElementConceptResource} on
	 * {@link Abbreviation}.
	 * 
	 * @param dataElementConceptAdministrationRecord
	 *            The Administration Record for a Data Element Concept.
	 * @param dataElementConceptObjectClass
	 *            Optional. The designation of an Object Class for a Data
	 *            Element Concept.
	 * @param dataElementConceptProperty
	 *            Optional. The designation of a Property for a Data Element
	 *            Concept.
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
	 *            {@link RegistrationAuthorityResource} represented by the
	 *            relationship registration.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @param havingDataElementConceptConceptualDomainRelationship
	 *            An {@link DataElementConceptResource} has to have at least one
	 *            {@link ConceptualDomainResource}.
	 * @return {@link DataElementConceptResource} on {@link Abbreviation} with a
	 *         specific URI generated from
	 *         {@link Abbreviation#DataElementConcept}.
	 */
	public DataElementConceptResource createDataElementConcept(
			AdministrationRecordResource dataElementConceptAdministrationRecord,
			ObjectClassResource dataElementConceptObjectClass,
			PropertyResource dataElementConceptProperty,
			String objectClassQualifier,
			String propertyQualifier,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having,
			ConceptualDomainResource havingDataElementConceptConceptualDomainRelationship) {

		if (dataElementConceptAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Data Element Concept Administration Record must be specified for DataElementConcept.");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for Classification Scheme");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for ClassificationScheme");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for Classification Scheme");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for Classification Scheme");
		}
		if (havingDataElementConceptConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Conceptual Domain must be specified for Data Element Concept.");
		}

		String uniqueID = dataElementConceptAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(
				Abbreviation.DataElementConcept.toString(), uniqueID));
		DataElementConceptResource dataElementConcept = new DataElementConceptImpl(
				node, (EnhGraph) ontModel,
				dataElementConceptAdministrationRecord,
				dataElementConceptObjectClass, dataElementConceptProperty,
				objectClassQualifier, propertyQualifier, administeredBy,
				submittedBy, registeredBy, having,
				havingDataElementConceptConceptualDomainRelationship,
				mdrDatabase);
		return dataElementConcept;
	}

	/**
	 * The method to create {@link ContextResource} on {@link Abbreviation}.
	 * 
	 * @param contextAdministrationRecord
	 *            The Administration Record for a Context.
	 * @param contextDescription
	 *            The textual description of the Context.
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
	 *            {@link RegistrationAuthorityResource} represented by the
	 *            relationship registration.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @return {@link ContextResource} on {@link Abbreviation} with a specific
	 *         URI generated from {@link Abbreviation#Context}.
	 */
	public ContextResource createContext(
			AdministrationRecordResource contextAdministrationRecord,
			String contextDescription,
			LanguageIdentificationResource contextDescriptionLanguageIdentifier,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having) {

		if (contextAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Context Administration Record must be specified for Context.");
		}
		if (Util.isNull(contextDescription)) {
			throw new IllegalArgumentException(
					"Context Description must be specified for Context.");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for Classification Scheme");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for ClassificationScheme");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for Classification Scheme");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for Classification Scheme");
		}
		String uniqueID = contextAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(Abbreviation.Context.toString(),
				uniqueID));
		ContextResource context = new ContextImpl(node, (EnhGraph) ontModel,
				contextAdministrationRecord, contextDescription,
				contextDescriptionLanguageIdentifier, administeredBy,
				submittedBy, registeredBy, having, mdrDatabase);
		return context;
	}

	/**
	 * The method to create {@link ClassificationSchemeResource} on
	 * {@link Abbreviation}.
	 * 
	 * @param classificationSchemeAdministrationRecord
	 *            The Administration Record for a Classification Scheme.
	 * @param classificationSchemeTypeName
	 *            The name of the type of Classification Scheme.
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
	 *            {@link RegistrationAuthorityResource} represented by the
	 *            relationship registration.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @return {@link ClassificationSchemeResource} on {@link Abbreviation} with
	 *         a specific URI generated from
	 *         {@link Abbreviation#ClassificationScheme}.
	 */
	public ClassificationSchemeResource createClassificationScheme(
			AdministrationRecordResource classificationSchemeAdministrationRecord,
			String classificationSchemeTypeName,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having) {

		if (classificationSchemeAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Classification Scheme Administration Record must be specified for ClassificationScheme.");
		}
		if (Util.isNull(classificationSchemeTypeName)) {
			throw new IllegalArgumentException(
					"Classification Scheme Type Name must be specified for ClassificationScheme.");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for Classification Scheme");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for ClassificationScheme");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for Classification Scheme");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for Classification Scheme");
		}

		String uniqueID = classificationSchemeAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(
				Abbreviation.ClassificationScheme.toString(), uniqueID));
		ClassificationSchemeResource classificationScheme = new ClassificationSchemeImpl(
				node, (EnhGraph) ontModel,
				classificationSchemeAdministrationRecord,
				classificationSchemeTypeName, administeredBy, submittedBy,
				registeredBy, having, mdrDatabase);
		return classificationScheme;
	}

	/**
	 * The method to create {@link ClassificationSchemeItemResource} on
	 * {@link Abbreviation}
	 * 
	 * @param classificationScheme
	 *            At least one Classification Scheme must be exist for per
	 *            Classification Scheme Item.
	 * @param classificationSchemeItemTypeName
	 *            The name of the type of the Classification Scheme Item.
	 * @param classificationSchemeItemValue
	 *            An instance of a Classification Scheme Item.
	 * @return {@link ClassificationSchemeItemResource} on {@link Abbreviation}
	 *         with a specific URI generated from
	 *         {@link Abbreviation#ClassificationSchemeItem}.
	 */
	public ClassificationSchemeItemResource createClassificationSchemeItem(
			ClassificationSchemeResource classificationScheme,
			String classificationSchemeItemTypeName,
			String classificationSchemeItemValue) {

		if (classificationScheme == null) {
			throw new IllegalArgumentException(
					"Classification Scheme must be specified for ClassificationSchemeItem.");
		}
		Node node = Node.createURI(makeID(
				Abbreviation.ClassificationSchemeItem.toString(), 
				generateUniqueID()));
		ClassificationSchemeItemResource classificationSchemeItem = new ClassificationSchemeItemImpl(
				node, (EnhGraph) ontModel, classificationScheme,
				classificationSchemeItemTypeName,
				classificationSchemeItemValue, mdrDatabase);
		return classificationSchemeItem;
	}

	/**
	 * The method to create {@link ClassificationSchemeItemAssociationResource}
	 * on {@link Abbreviation}.
	 * 
	 * @param classificationSchemeItemRelationship
	 * 
	 * @return {@link ClassificationSchemeItemAssociationResource} on
	 *         {@link Abbreviation} with a specific URI generated from
	 *         {@link Abbreviation#ClassificationSchemeItemRelationship} and
	 *         classificationSchemeItemRelationshipTypeDescription.
	 * 
	 */
	public ClassificationSchemeItemAssociationResource createClassificationSchemeItemAssociation(
			ClassificationSchemeItemRelationshipResource classificationSchemeItemRelationship) {
		if (classificationSchemeItemRelationship == null) {
			throw new IllegalArgumentException(
					"Classification Scheme Item Relationship must be specified for ClassificationSchemeItemAssociation.");
		}
		String uniqueID = generateUniqueID();
		Node node = Node
				.createURI(makeID(
						Abbreviation.ClassificationSchemeItemRelationship
								.toString(),
						classificationSchemeItemRelationship
								.getClassificationSchemeItemRelationshipTypeDescription(),
						uniqueID));
		ClassificationSchemeItemAssociationResource classificationSchemeItemAssociation = new ClassificationSchemeItemAssociationImpl(
				node, (EnhGraph) ontModel,
				classificationSchemeItemRelationship, mdrDatabase);
		return classificationSchemeItemAssociation;

	}

	/**
	 * The method to create {@link ClassificationSchemeItemRelationshipResource}
	 * on {@link Abbreviation}.
	 * 
	 * @param classificationSchemeItemRelationshipTypeDescription
	 * 
	 * @return {@link ClassificationSchemeItemRelationshipResource} on
	 *         {@link Abbreviation} with a specific URI generated from
	 *         {@link Abbreviation#ClassificationSchemeItemRelationship}.
	 */
	public ClassificationSchemeItemRelationshipResource createClassificationSchemeItemRelationship(
			String classificationSchemeItemRelationshipTypeDescription) {
		if (Util.isNull(classificationSchemeItemRelationshipTypeDescription)) {
			throw new IllegalArgumentException(
					"Classification Scheme Item Relationship Type Description must be specified for ClassificationSchemeItemRelationship.");
		}
		Node node = Node.createURI(makeID(
				Abbreviation.ClassificationSchemeItemRelationship.toString(),
				classificationSchemeItemRelationshipTypeDescription));
		ClassificationSchemeItemRelationshipResource classificationSchemeItemRelationship = new ClassificationSchemeItemRelationshipImpl(
				node, (EnhGraph) ontModel,
				classificationSchemeItemRelationshipTypeDescription,
				mdrDatabase);
		return classificationSchemeItemRelationship;

	}

	/**
	 * The method to create {@link OrganizationResource} on {@link Abbreviation}
	 * 
	 * @param organizationName
	 *            Name of the Organization
	 * @param organizationMailAddress
	 *            Optional. Mail Address of the Organization
	 * @return {@link OrganizationResource} on {@link Abbreviation} with a
	 *         specific URI generated from {@link Abbreviation#Organization} and
	 *         parameters
	 */
	public OrganizationResource createOrganization(String organizationName,
			String organizationMailAddress) {
		if (Util.isNull(organizationName)) {
			throw new IllegalArgumentException(
					"Organization Name must be specified for the Organization");
		}
		Node node = Node.createURI(makeID(Abbreviation.Organization.toString(),
				organizationName));
		OrganizationResource organization = new OrganizationImpl(node,
				(EnhGraph) ontModel, organizationName, organizationMailAddress,
				mdrDatabase);
		return organization;
	}

	/**
	 * The method to create {@link RegistrationAuthorityResource} on
	 * {@link Abbreviation}
	 * 
	 * @param registrationAuthorityIdentifier
	 *            Identifier of Registration Authority
	 * @param documentationLanguageIdentifier
	 *            Language Identification for Documentation
	 * @param organizationName
	 *            Name of the Organization
	 * @param organizationMailAddress
	 *            Optional. Mail Address of the Organization
	 * @param representedBy
	 *            Person who perform the administrative steps to register
	 *            Administered Items
	 * 
	 * @return {@link RegistrationAuthorityResource} on {@link Abbreviation}
	 *         with a specific URI generated from
	 *         {@link Abbreviation#RegistrationAuthority} and parameter
	 *         <code>registrationAuthorityIdentifier</code>
	 */
	public RegistrationAuthorityResource createRegistrationAuthority(
			RegistrationAuthorityIdentifierResource registrationAuthorityIdentifier,
			LanguageIdentificationResource documentationLanguageIdentifier,
			String organizationName, String organizationMailAddress,
			RegistrarResource representedBy) {
		if (registrationAuthorityIdentifier == null) {
			throw new IllegalArgumentException(
					"Registration Authority Identifier must be specified for RegistrationAuthority");
		}
		if (documentationLanguageIdentifier == null) {
			throw new IllegalArgumentException(
					"Documentation Language Identifier must be specified for RegistrationAuthority");
		}
		if (Util.isNull(organizationName)) {
			throw new IllegalArgumentException(
					"Organization Name must be specified for the RegistrationAuthority");
		}
		if (representedBy == null) {
			throw new IllegalArgumentException(
					"Registrar must be specified for RegistrationAuthoity");
		}

		Node node = Node
				.createURI(makeID(Abbreviation.RegistrationAuthority,
						registrationAuthorityIdentifier
								.getOrganizationIdentifier(),
						registrationAuthorityIdentifier
								.getOrganizationPartIdentifier()));
		RegistrationAuthorityResource registrationAuthority = new RegistrationAuthorityImpl(
				node, (EnhGraph) ontModel, registrationAuthorityIdentifier,
				documentationLanguageIdentifier, organizationName,
				organizationMailAddress, representedBy, mdrDatabase);
		return registrationAuthority;
	}

	/**
	 * Method to create {@link RegistrarResource} on {@link Abbreviation}
	 * 
	 * @param registrarIdentifier
	 *            Identifier for the Registrar
	 * @param registrarContact
	 *            Contact information for the Registrar
	 * @return {@link RegistrarResource} on {@link Abbreviation} with a specific
	 *         URI generated from <code>registrarIdentifier</code> and
	 *         {@link ContactResource#getContactName()}
	 */
	public RegistrarResource createRegistrar(String registrarIdentifier,
			ContactResource registrarContact) {
		if (Util.isNull(registrarIdentifier)) {
			throw new IllegalArgumentException(
					"Registrar Identifier must be specified for Registrar");
		}
		if (registrarContact == null) {
			throw new IllegalArgumentException(
					"Registrar Contact must be specified for Registrar");
		}

		Node node = Node.createURI(makeID(registrarIdentifier,
				registrarContact.getContactName()));

		RegistrarResource registrar = new RegistrarImpl(node,
				(EnhGraph) ontModel, registrarIdentifier, registrarContact,
				mdrDatabase);
		return registrar;
	}

	/**
	 * Method to create {@link ReferenceDocumentResource} on
	 * {@link Abbreviation}
	 * 
	 * @param referenceDocumentIdentifier
	 *            Identifier for the Reference Document.
	 * @param providedBy
	 *            Organization which provides the Documents
	 * @param referenceDocumentTypeDescription
	 *            Optional. Type Desctiptor for the Reference Document.
	 * 
	 * @param referenceDocumentLanguageIdentifier
	 *            Optional. Language Identifier for the Reference Document.
	 * 
	 * @param referenceDocumentTitle
	 *            Optional. Title of the Reference Document
	 * @return {@link ReferenceDocumentResource} on {@link Abbreviation} with a
	 *         specific URI generated from
	 *         {@link Abbreviation#ReferenceDocument} and
	 *         <code>referenceDocumentIdentifier</code>
	 */
	public ReferenceDocumentResource createReferenceDocument(
			String referenceDocumentIdentifier,
			OrganizationResource providedBy,
			String referenceDocumentTypeDescription,
			LanguageIdentificationResource referenceDocumentLanguageIdentifier,
			String referenceDocumentTitle) {
		if (Util.isNull(referenceDocumentIdentifier)) {
			throw new IllegalArgumentException(
					"Reference Document Identifier must be specified for ReferenceDocument.");
		}
		if (providedBy == null) {
			throw new IllegalArgumentException(
					"Providing Organization must be specified for the ReferenceDocuments");
		}
		Node node = Node.createURI(makeID(Abbreviation.ReferenceDocument,
				referenceDocumentIdentifier));

		ReferenceDocumentResource referenceDocument = new ReferenceDocumentImpl(
				node, (EnhGraph) ontModel, referenceDocumentIdentifier,
				providedBy, referenceDocumentTypeDescription,
				referenceDocumentLanguageIdentifier, referenceDocumentTitle,
				mdrDatabase);
		return referenceDocument;
	}

	/**
	 * Method to create {@link SubmissionResource} on {@link Abbreviation}
	 * 
	 * @param submissionContact
	 *            Submitter Contact Details of The Submission
	 * @return {@link SubmissionResource} on {@link Abbreviation} with a
	 *         specific URI generated from {@value Abbreviation#Submission} and
	 *         {@link ContactResource#getContactName()}
	 */
	public SubmissionResource createSubmission(ContactResource submissionContact) {
		if (submissionContact == null) {
			throw new IllegalArgumentException(
					"Submission Contact must be specified for Submission");
		}
		Node node = Node.createURI(makeID(Abbreviation.Submission,
				submissionContact.getContactName()));
		SubmissionResource submission = new SubmissionImpl(node,
				(EnhGraph) ontModel, submissionContact, mdrDatabase);
		return submission;
	}

	/**
	 * Method to create {@link SubmissionRelationshipResource} on
	 * {@link Abbreviation}
	 * 
	 * @param submissionOrganization
	 *            Submitter Organization
	 * @param submitter
	 *            Contact Details of Submitter from Organization
	 * @return {@link SubmissionRelationshipResource} on {@link Abbreviation}
	 *         with a specific URI generated from
	 *         {@link Abbreviation#Submission} and parameters
	 */
	public SubmissionRelationshipResource createSubmissionRelationship(
			OrganizationResource submissionOrganization,
			SubmissionResource submitter) {
		if (submissionOrganization == null) {
			throw new IllegalArgumentException(
					"Organization must be specified for the SubmissionRelationship");
		}
		if (submitter == null) {
			throw new IllegalArgumentException(
					"Submission must be specified for the SubmissionRelationship");
		}
		Node node = Node.createURI(makeID(Abbreviation.Submission,
				submissionOrganization.getOrganizationName(), submitter
						.getSubmissionContact().getContactName()));
		SubmissionRelationshipResource submissionRelationship = new SubmissionRelationshipImpl(
				node, (EnhGraph) ontModel, submissionOrganization, submitter,
				mdrDatabase);
		return submissionRelationship;
	}

	/**
	 * Method to create {@link StewardshipResource} on {@link Abbreviation}
	 * 
	 * @param stewardshipContact
	 *            Steward Responsible Contact Details of The Stewardship
	 * @return {@link StewardshipResource} on {@link Abbreviation} with a
	 *         specific URI generated from {@value Abbreviation#Stewardship} and
	 *         {@link ContactResource#getContactName()}
	 */
	public StewardshipResource createStewardship(
			ContactResource stewardshipContact) {
		if (stewardshipContact == null) {
			throw new IllegalArgumentException(
					"Stewardship Contact must be specified for Stewardship");
		}
		Node node = Node.createURI(makeID(Abbreviation.Stewardship,
				stewardshipContact.getContactName()));
		StewardshipResource stewardship = new StewardshipImpl(node,
				(EnhGraph) ontModel, stewardshipContact, mdrDatabase);
		return stewardship;
	}

	/**
	 * Method to create {@link StewardshipRelationshipResource} on
	 * {@link Abbreviation}
	 * 
	 * @param administerOrganization
	 *            Administer Organization
	 * @param administer
	 *            Contact Details of Administer from Organization
	 * @return {@link StewardshipRelationshipResource} on {@link Abbreviation}
	 *         with a specific URI generated from
	 *         {@link Abbreviation#Stewardship} and parameters
	 */
	public StewardshipRelationshipResource createStewardshipRelationship(
			OrganizationResource administerOrganization,
			StewardshipResource administer) {
		if (administerOrganization == null) {
			throw new IllegalArgumentException(
					"Organization must be specified for the StewardshipRelationship");
		}
		if (administer == null) {
			throw new IllegalArgumentException(
					"Submission must be specified for the StewardshipRelationship");
		}
		Node node = Node.createURI(makeID(Abbreviation.Stewardship,
				administerOrganization.getOrganizationName(), administer
						.getStewardshipContact().getContactName()));
		StewardshipRelationshipResource stewardshipRelationship = new StewardshipRelationshipImpl(
				node, (EnhGraph) ontModel, administerOrganization, administer,
				mdrDatabase);
		return stewardshipRelationship;
	}

	/**
	 * Method to create {@link AdministeredItemContextResource} on
	 * {@link Abbreviation}. {@link AdministeredItemContextResource} is created
	 * to handle the related n-ary relation which exist in the ISO-11179-3
	 * standard.
	 * 
	 * @param context
	 *            The {@link ContextResource} with which this created
	 *            {@link AdministeredItemContextResource} will be associated.
	 * @param terminologicalEntry
	 *            The {@link TerminologicalEntryResource} with which this
	 *            created {@link AdministeredItemContextResource} will be
	 *            associated.
	 * @return {@link AdministeredItemContextIIND} on {@link Abbreviation} with
	 *         a specific URI genereted from
	 *         {@link Abbreviation#AdministeredItemContext} and uniqueID of the
	 *         associated {@link ContextResource}.
	 */
	public AdministeredItemContextResource createAdministeredItemContext(
			ContextResource context,
			TerminologicalEntryResource terminologicalEntry) {
		if (context == null) {
			throw new IllegalArgumentException(
					"Context must be specified for the AdministeredItemContext");
		}
		if (terminologicalEntry == null) {
			throw new IllegalArgumentException(
					"TerminologicalEntry must be specified for the AdministeredItemContext");
		}
		Node node = Node.createURI(makeID(
				Abbreviation.AdministeredItemContext.toString(),
				generateUniqueID()));
		AdministeredItemContextResource administeredItemContext = new AdministeredItemContextImpl(
				node, (EnhGraph) ontModel, context, terminologicalEntry,
				mdrDatabase);
		return administeredItemContext;
	}

	/**
	 * Method to create {@link TerminologicalEntryResource} on
	 * {@link Abbreviation}.
	 * 
	 * @param languageSection
	 *            {@link LanguageSectionResource} identifying the language which
	 *            is a member of this {@link TerminologicalEntryResource}.
	 * @return The {@link TerminologicalEntryResource} which may hold several
	 *         {@link LanguageSectionResource}s (through
	 *         {@link TerminologicalEntryResource#addContainingTerminologicalEntryLanguage(LanguageSectionResource)}
	 *         , each may give information in different languages.
	 */
	public TerminologicalEntryResource createTerminologicalEntry(
			LanguageSectionResource languageSection) {
		if (languageSection == null) {
			throw new IllegalArgumentException(
					"LanguageSection must be specified for the AdministeredItemContext; it must have at least one LanguageSection");
		}
		String uniqueID = generateUniqueID();
		Node node = Node.createURI(makeID(Abbreviation.TerminologicalEntry,
				uniqueID));
		TerminologicalEntryResource terminologicalEntry = new TerminologicalEntryImpl(
				node, (EnhGraph) ontModel, languageSection, mdrDatabase);
		return terminologicalEntry;
	}

	/**
	 * Method to create {@link LanguageSectionResource} on {@link Abbreviation}
	 * 
	 * @param languageSectionLanguageIdentifier
	 *            {@link LanguageIdentificationResource} identifying the
	 *            LanguageSection.
	 * @return {@link LanguageSectionResource} on {@link Abbreviation} with a
	 *         specific URI genereted from {@link Abbreviation#LanguageSection}
	 *         and parameter
	 */
	public LanguageSectionResource createLanguageSection(
			LanguageIdentificationResource languageSectionLanguageIdentifier) {
		if (languageSectionLanguageIdentifier == null) {
			throw new IllegalArgumentException(
					"Language Identification must be specified for Language Section");
		}
		String uniqueID = generateUniqueID();
		Node node = Node.createURI(makeID(Abbreviation.LanguageSection,
				uniqueID));
		LanguageSectionResource languageSection = new LanguageSectionImpl(node,
				(EnhGraph) ontModel, languageSectionLanguageIdentifier,
				mdrDatabase);
		return languageSection;
	}

	/**
	 * Method to create {@link DesignationResource} on {@link Abbreviation}
	 * 
	 * @param namingEntryOf
	 *            Language section of which Designation belongs to
	 * @param name
	 *            Name
	 * @param preferredDesignation
	 *            Optional. Default value is <code>false</code> if not given
	 * @return {@link DesignationResource} on {@link Abbreviation} with a
	 *         specific URı generated from {@link Abbreviation#Designation} and
	 *         name
	 */
	public DesignationResource createDesignation(
			LanguageSectionResource namingEntryOf, String name,
			boolean preferredDesignation) {
		if (namingEntryOf == null) {
			throw new IllegalArgumentException(
					"Language Section must be specified for Designation.");
		}
		if (Util.isNull(name)) {
			throw new IllegalArgumentException(
					"Name must be specified for Designation.");
		}
		String uniqueID = generateUniqueID();
		Node node = Node.createURI(makeID(Abbreviation.Designation.toString(),
				uniqueID));
		DesignationResource designation = new DesignationImpl(node,
				(EnhGraph) ontModel, namingEntryOf, name, preferredDesignation,
				mdrDatabase);
		return designation;
	}

	public DesignationResource createDesignation(
			LanguageSectionResource namingEntryOf, String name) {
		return createDesignation(namingEntryOf, name, false);
	}

	/**
	 * Method to create {@link DefinitionResource} on {@link Abbreviation}
	 * 
	 * @param definingEntryOf
	 *            Language section of which Definiton belongs to
	 * @param definitionText
	 *            Definition Test
	 * @param preferredDefinition
	 *            Optional. default value <code>false</code> if not given
	 * @param definitionSourceReference
	 *            Optional.
	 * @return {@link DefinitionResource} on {@link Abbreviation} with a
	 *         specific URI generated from {@link Abbreviation#Definition} and
	 *         <code>definitionText</code>
	 */
	public DefinitionResource createDefinition(
			LanguageSectionResource definingEntryOf, String definitionText,
			boolean preferredDefinition,
			ReferenceDocumentResource definitionSourceReference) {
		if (definingEntryOf == null) {
			throw new IllegalArgumentException(
					"Language Section must be specified for Definition");
		}
		if (Util.isNull(definitionText)) {
			throw new IllegalArgumentException(
					"Definition Text must be specified for definition");
		}
		String uniqueID = generateUniqueID();
		Node node = Node.createURI(makeID(Abbreviation.Definition.toString(),
				uniqueID));
		DefinitionResource definition = new DefinitionImpl(node,
				(EnhGraph) ontModel, definingEntryOf, definitionText,
				preferredDefinition, definitionSourceReference, mdrDatabase);
		return definition;
	}

	public DefinitionResource createDefinition(
			LanguageSectionResource definingEntryOf, String definitionText,
			ReferenceDocumentResource definitionSourceReference) {
		return createDefinition(definingEntryOf, definitionText, false,
				definitionSourceReference);
	}

	/**
	 * Method to create {@link DatatypeResource} on {@link Abbreviation}
	 * 
	 * @param datatypeName
	 *            name of the Datatype
	 * @param datatypeDescription
	 *            Optional. Description for Datatype
	 * @param datatypeSchemeReference
	 *            External Scheme Reference for Datatype
	 * @param datatypeAnnotation
	 *            Optional.
	 * @return {@link DatatypeResource} on {@link Abbreviation} with a specific
	 *         URI generated from {@link Abbreviation#Datatype} and parameters
	 */
	public DatatypeResource createDatatype(String datatypeName,
			String datatypeDescription, String datatypeSchemeReference,
			String datatypeAnnotation) {
		if (Util.isNull(datatypeName)) {
			throw new IllegalArgumentException(
					"Name must be specified for Datatype.");
		}
		if (Util.isNull(datatypeSchemeReference)) {
			throw new IllegalArgumentException(
					"Scheme Reference must be specified for Datatype");
		}

		Node node = Node.createURI(makeID(Abbreviation.Datatype.toString(),
				datatypeName, datatypeSchemeReference));
		DatatypeResource datatype = new DatatypeImpl(node, (EnhGraph) ontModel,
				datatypeName, datatypeDescription, datatypeSchemeReference,
				datatypeAnnotation, mdrDatabase);
		return datatype;
	}

	/**
	 * Method to create {@link UnitOfMeasureResource} on {@link Abbreviation}
	 * 
	 * @param unitOfMeasureName
	 * @param unitOfMeasurePrecision
	 * @return {@link UnitOfMeasureResource} on {@link Abbreviation} with a
	 *         specific URI generated from {@link Abbreviation#UnitOfMeasure}
	 *         and parameters
	 */
	public UnitOfMeasureResource createUnitOfMeasure(String unitOfMeasureName,
			Integer unitOfMeasurePrecision) {
		if (Util.isNull(unitOfMeasureName)) {
			throw new IllegalArgumentException(
					"Name must be speicified for UnitOfMeasure");
		}
		if (unitOfMeasurePrecision == null) {
			throw new IllegalArgumentException(
					"Precision must be specified for UnitOfMeasure");
		}

		Node node = Node.createURI(makeID(
				Abbreviation.UnitOfMeasure.toString(), unitOfMeasureName,
				unitOfMeasurePrecision.toString()));
		UnitOfMeasureResource unitOfMeasure = new UnitOfMeasureImpl(node,
				(EnhGraph) ontModel, unitOfMeasureName, unitOfMeasurePrecision,
				mdrDatabase);
		return unitOfMeasure;
	}

	/**
	 * Method to create {@link DataElementExampleResource} on
	 * {@link Abbreviation}
	 * 
	 * @param dataElementExampleItem
	 * @param exemplifyingExemplification
	 *            {@link DataElementResource} which is exemplified by
	 * @return {@link DataElementExampleResource} on {@link Abbreviation} with a
	 *         specific URI generated from
	 *         {@link Abbreviation#DataElementExample} and uniqueID
	 */
	public DataElementExampleResource createDataElementExample(
			String dataElementExampleItem,
			DataElementResource exemplifyingExemplification) {
		if (Util.isNull(dataElementExampleItem)) {
			throw new IllegalArgumentException(
					"An Item must be specified for DataElementExample.");
		}
		if (exemplifyingExemplification == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added");
		}

		String uniqueID = generateUniqueID();
		Node node = Node.createURI(makeID(
				Abbreviation.DataElementExample.toString(), uniqueID));
		DataElementExampleResource dataElementExample = new DataElementExampleImpl(
				node, (EnhGraph) ontModel, dataElementExampleItem,
				exemplifyingExemplification, mdrDatabase);
		return dataElementExample;
	}

	/**
	 * Method to create {@link DataElementDerivationResource} on
	 * {@link Abbreviation}
	 * 
	 * @param applyingDerivationRuleApplication
	 *            DerivationRule which is applying
	 * @param inputingDerivationInput
	 *            DataElement which is input to Derivation
	 * @param derivingDerivationOutput
	 *            DataElement which is derived with Derivation
	 * @return {@link DataElementDerivationResource} on {@link Abbreviation}
	 *         with a specific URI generated from
	 *         {@link Abbreviation#DataElementDerivation} and parameter
	 *         DerivationRule
	 */
	public DataElementDerivationResource createDataElementDerivation(
			DerivationRuleResource applyingDerivationRuleApplication,
			DataElementResource inputingDerivationInput,
			DataElementResource derivingDerivationOutput) {
		if (applyingDerivationRuleApplication == null) {
			throw new IllegalArgumentException(
					"Derivation Rule must be specified for DataElementDerivation.");
		}
		if (inputingDerivationInput == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		if (derivingDerivationOutput == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		String uniqueID = applyingDerivationRuleApplication
				.getAdministrationRecord().getAdministeredItemIdentifier()
				.getDataIdentifier();
		Node node = Node.createURI(makeID(
				Abbreviation.DataElementDerivation.toString(), uniqueID));

		DataElementDerivationResource dataElementDerivation = new DataElementDerivationImpl(
				node, (EnhGraph) ontModel, applyingDerivationRuleApplication,
				inputingDerivationInput, derivingDerivationOutput, mdrDatabase);
		return dataElementDerivation;
	}

	/**
	 * Method to create {@link DerivationRuleResource} on {@link Abbreviation}
	 * 
	 * @param derivationRuleSpecification
	 * @param derivationRuleAdministrationRecord
	 * @param administeredBy
	 * @param submittedBy
	 * @param registeredBy
	 * @param having
	 * @return {@link DerivationRuleResource} on {@link Abbreviation} with a
	 *         specific URI generated from {@link Abbreviation#DerivationRule}
	 *         and AdministrationRecord
	 */
	public DerivationRuleResource createDerivationRule(
			String derivationRuleSpecification,
			AdministrationRecordResource derivationRuleAdministrationRecord,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having) {
		if (Util.isNull(derivationRuleSpecification)) {
			throw new IllegalArgumentException(
					"Specification should be specified for DerivationRule");
		}
		if (derivationRuleAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be specified for Property.");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for Classification Scheme");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for ClassificationScheme");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for Classification Scheme");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for Classification Scheme");
		}
		String uniqueID = derivationRuleAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(
				Abbreviation.DerivationRule.toString(), uniqueID));
		DerivationRuleResource derivationRule = new DerivationRuleImpl(node,
				(EnhGraph) ontModel, derivationRuleSpecification,
				derivationRuleAdministrationRecord, administeredBy,
				submittedBy, registeredBy, having, mdrDatabase);
		return derivationRule;
	}

	/**
	 * The method to create {@link NonEnumeratedConceptualDomainResource} on
	 * {@link Abbreviation}.
	 * 
	 * @param conceptualDomainAdministrationRecord
	 *            The AdministrationRecord for a
	 *            {@link ConceptualDomainResource}.
	 * @param dimensionality
	 *            Optional. An expression of measurement without units.
	 * @param administretedBy
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
	 * @param nonEnumeratedConceptualDomainDescription
	 *            A description or specification of a rule, reference, or range
	 *            for a set of all Value Meanings for the Conceptual Domain.
	 * 
	 */
	public NonEnumeratedConceptualDomainResource createNonEnumeratedConceptualDomain(
			AdministrationRecordResource conceptualDomainAdministrationRecord,
			String dimensionality,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having,
			String nonEnumeratedConceptualDomainDescription) {
		if (conceptualDomainAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be speicified for NonEnumeratedConceptualDomain");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for NonEnumeratedConceptualDomain");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for NonEnumeratedConceptualDomain");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for NonEnumeratedConceptualDomain");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for NonEnumeratedConceptualDomain");
		}
		if (Util.isNull(nonEnumeratedConceptualDomainDescription)) {
			throw new IllegalArgumentException(
					"Non Enumerated Conceptual Domain Description must be specified for NonEnumeratedConceptualDomain");
		}
		String uniqueID = conceptualDomainAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node
				.createURI(makeID(
						Abbreviation.NonEnumeratedConceptualDomain.toString(),
						uniqueID));
		NonEnumeratedConceptualDomainResource nonEnumeratedConceptualDomain = new NonEnumeratedConceptualDomainImpl(
				node, (EnhGraph) ontModel,
				conceptualDomainAdministrationRecord, dimensionality,
				administeredBy, submittedBy, registeredBy, having,
				nonEnumeratedConceptualDomainDescription, mdrDatabase);
		return nonEnumeratedConceptualDomain;
	}

	/**
	 * The method to create {@link NonEnumeratedValueDomainResource} on
	 * {@link Abbreviation}
	 * 
	 * @param valueDomainAdministrationRecord
	 *            The Administration Record for a {@link ValueDomainResource}.
	 * @param valueDomainDatatype
	 *            The Datatype used in a Value Domain.
	 * @param valueDomainUnitOfMeasure
	 *            The unit of measure used in a Value Domain.
	 * @param valueDomainMaximumCharacterQuantity
	 *            The maximum number of characters to represent the Data Element
	 *            value.
	 * @param valueDomainFormat
	 *            A template for the structure of the presentation of the
	 *            Value(s). EXAMPLE – YYYY-MM-DD for a date.
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
	 * @param representingConceptualDomainRepresentation
	 *            An {@link ValueDomainResource} has to have at least one
	 *            {@link ConceptualDomainResource}.
	 * @param nonEnumeratedDomainDescription
	 *            A description or specification of a rule, reference, or range
	 *            for a set of all Permissible Values for the Value Domain.
	 * @param representingNonEnumeratedConceptualDomainRepresentation
	 *            An {@link NonEnumeratedValueDomainResource} has to have at
	 *            least one {@link NonEnumeratedConceptualDomainResource}.
	 */

	public NonEnumeratedValueDomainResource createNonEnumeratedValueDomain(
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
			NonEnumeratedConceptualDomainResource representingNonEnumeratedConceptualDomainRepresentation) {
		if (valueDomainAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be speicified for NonEnumeratedValueDomain");
		}
		if (valueDomainDatatype == null) {
			throw new IllegalArgumentException(
					"Data Type must be speicified for NonEnumeratedValueDomain");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for NonEnumeratedValueDomain");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for NonEnumeratedValueDomain");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for NonEnumeratedValueDomain");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for NonEnumeratedValueDomain");
		}
		if (representingConceptualDomainRepresentation == null) {
			throw new IllegalArgumentException(
					"Conceptual Domain must be specified for NonEnumeratedValueDomain");
		}
		if (Util.isNull(nonEnumeratedDomainDescription)) {
			throw new IllegalArgumentException(
					"Non Enumerated Domain Description must be specified for NonEnumeratedValueDomain");
		}
		if (representingNonEnumeratedConceptualDomainRepresentation == null) {
			throw new IllegalArgumentException(
					"Non Enumerated Conceptual Domain must be specified for NonEnumeratedValueDomain");
		}
		String uniqueID = valueDomainAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(
				Abbreviation.NonEnumeratedValueDomain.toString(), uniqueID));
		NonEnumeratedValueDomainResource nonEnumeratedValueDomain = new NonEnumeratedValueDomainImpl(
				node, (EnhGraph) ontModel, valueDomainAdministrationRecord,
				valueDomainDatatype, valueDomainUnitOfMeasure,
				valueDomainMaximumCharacterQuantity, valueDomainFormat,
				administeredBy, submittedBy, registeredBy, having,
				representingConceptualDomainRepresentation,
				nonEnumeratedDomainDescription,
				representingNonEnumeratedConceptualDomainRepresentation,
				mdrDatabase);
		return nonEnumeratedValueDomain;
	}

	/**
	 * Method to create {@link EnumeratedValueDomainResource} on
	 * {@link Abbreviation}
	 * 
	 * @param valueDomainAdministrationRecord
	 * @param valueDomainDatatype
	 * @param valueDomainUnitOfMeasure
	 * @param valueDomainMaximumCharacterQuantity
	 * @param valueDomainFormat
	 * @param administeredBy
	 * @param submittedBy
	 * @param registeredBy
	 * @param having
	 * @param representingConceptualDomainRepresentation
	 * @param containingPermissibleValueSet
	 * @return {@link EnumeratedValueDomainResource} on {@link Abbreviation}
	 *         with a specific URI generated from
	 *         {@link Abbreviation#EnumeratedValueDomain} and
	 *         valueDomainAdministrationRecord
	 */
	public EnumeratedValueDomainResource createEnumeratedValueDomain(
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
			List<PermissibleValueResource> containingPermissibleValueSet) {
		if (valueDomainAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be speicified for EnumeratedValueDomain");
		}
		if (valueDomainDatatype == null) {
			throw new IllegalArgumentException(
					"Data Type must be speicified for EnumeratedValueDomain");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for EnumeratedValueDomain");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for EnumeratedValueDomain");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for EnumeratedValueDomain");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for EnumeratedValueDomain");
		}
		if (representingConceptualDomainRepresentation == null) {
			throw new IllegalArgumentException(
					"Conceptual Domain must be specified for EnumeratedValueDomain");
		}
		String uniqueID = valueDomainAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(
				Abbreviation.EnumeratedValueDomain.toString(), uniqueID));
		EnumeratedValueDomainResource enumeratedValueDomain = new EnumeratedValueDomainImpl(
				node, (EnhGraph) ontModel, valueDomainAdministrationRecord,
				valueDomainDatatype, valueDomainUnitOfMeasure,
				valueDomainMaximumCharacterQuantity, valueDomainFormat,
				administeredBy, submittedBy, registeredBy, having,
				representingConceptualDomainRepresentation,
				containingPermissibleValueSet, mdrDatabase);
		return enumeratedValueDomain;
	}

	/**
	 * Method to create {@link PermissibleValueResource} on {@link Abbreviation}
	 * 
	 * @param permissibleValueBeginDate
	 *            Calendar Object specifying begni date of value
	 * @param permissibleValueEndDate
	 *            Optional.
	 * @param hasPermittedValue
	 *            Value of the Enumeration
	 * @param hasPermissibleValueMeaning
	 *            Meaning of the Enumeration Value
	 * @return {@link PermissibleValueResource} on {@link Abbreviation} with a
	 *         specific URI generated from {@link Abbreviation#PermissibleValue}
	 *         and parameters
	 */
	public PermissibleValueResource createPermissibleValue(
			Calendar permissibleValueBeginDate,
			Calendar permissibleValueEndDate, ValueResource hasPermittedValue,
			ValueMeaningResource hasPermissibleValueMeaning) {
		if (permissibleValueBeginDate == null) {
			throw new IllegalArgumentException(
					"Permissible Value Begin Date must be specified for PermissibleValue");
		}
		if (hasPermittedValue == null) {
			throw new IllegalArgumentException(
					"Value must be specified for PermissibleValue.");
		}
		if (hasPermissibleValueMeaning == null) {
			throw new IllegalArgumentException(
					"Value Meaning must be specified for PermissibleValue.");
		}

		Node node = Node.createURI(makeID(
				Abbreviation.PermissibleValue.toString(),
				hasPermissibleValueMeaning.getValueMeaningIdentifier(),
				hasPermittedValue.getValueItem()));
		PermissibleValueResource permissibleValue = new PermissibleValueImpl(
				node, (EnhGraph) ontModel, permissibleValueBeginDate,
				permissibleValueEndDate, hasPermittedValue,
				hasPermissibleValueMeaning, mdrDatabase);
		return permissibleValue;
	}

	/**
	 * Method to create {@link ValueResource} on {@link Abbreviation}
	 * 
	 * @param valueItem
	 *            Actual value associated with Enumeration Permissible Value
	 * @return {@link ValueResource} on {@link Abbreviation} with a specific URI
	 *         generated {@link Abbreviation#Value} and <code>valueItem</code>
	 */
	public ValueResource createValue(String valueItem) {
		if (Util.isNull(valueItem)) {
			throw new IllegalArgumentException(
					"Value Item must be specified for Value.");
		}
		Node node = Node.createURI(makeID(Abbreviation.Value.toString(),
				valueItem));
		ValueResource value = new ValueImpl(node, (EnhGraph) ontModel,
				valueItem, mdrDatabase);
		return value;
	}

	/**
	 * Method to create {@link ValueMeaningResource} on {@link Abbreviation}
	 * 
	 * @param valueMeaningIdentifier
	 * @param valueMeaningDescription
	 *            Optional.
	 * @param valueMeaningBeginDate
	 * @param valueMeaningEndDate
	 *            Optional.
	 * @param containedInValueMeaningSet
	 * @return {@link ValueMeaningResource} on {@link Abbreviation} with a
	 *         specific URI generated from {@link Abbreviation#ValueMeaning} and
	 *         <code>valueMeaningIdentifier</code>
	 */
	public ValueMeaningResource createValueMeaning(
			String valueMeaningIdentifier, String valueMeaningDescription,
			Calendar valueMeaningBeginDate, Calendar valueMeaningEndDate,
			EnumeratedConceptualDomainResource containedInValueMeaningSet) {
		if (Util.isNull(valueMeaningIdentifier)) {
			throw new IllegalArgumentException(
					"Identifier should be specified for ValueMeaning.");
		}
		if (valueMeaningBeginDate == null) {
			throw new IllegalArgumentException(
					"Begin Date should be specified for ValueMeaning");
		}
		if (containedInValueMeaningSet == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value for the property to be added.");
		}

		Node node = Node.createURI(makeID(Abbreviation.ValueMeaning.toString(),
				valueMeaningIdentifier, valueMeaningDescription));
		ValueMeaningResource valueMeaning = new ValueMeaningImpl(node,
				(EnhGraph) ontModel, valueMeaningIdentifier,
				valueMeaningDescription, valueMeaningBeginDate,
				valueMeaningEndDate, containedInValueMeaningSet, mdrDatabase);
		return valueMeaning;
	}

	/**
	 * The method to create {@link EnumeratedConceptualDomainResource} on
	 * {@link Abbreviation}.
	 * 
	 * @param conceptualDomainAdministrationRecord
	 *            The AdministrationRecord for a
	 *            {@link ConceptualDomainResource}.
	 * @param dimensionality
	 *            Optional. An expression of measurement without units.
	 * @param administretedBy
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
	 * 
	 */
	public EnumeratedConceptualDomainResource createEnumeratedConceptualDomain(
			AdministrationRecordResource conceptualDomainAdministrationRecord,
			String dimensionality,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having) {
		if (conceptualDomainAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be speicified for EnumeratedConceptualDomain");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for EnumeratedConceptualDomain");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for EnumeratedConceptualDomain");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for EnumeratedConceptualDomain");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for EnumeratedConceptualDomain");
		}

		String uniqueID = conceptualDomainAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(
				Abbreviation.EnumeratedConceptualDomain.toString(), uniqueID));
		EnumeratedConceptualDomainResource enumeratedConceptualDomain = new EnumeratedConceptualDomainImpl(
				node, (EnhGraph) ontModel,
				conceptualDomainAdministrationRecord, dimensionality,
				administeredBy, submittedBy, registeredBy, having, mdrDatabase);
		return enumeratedConceptualDomain;
	}

	/**
	 * The method to create {@link ConceptResource} on {@link Abbreviation}.
	 * 
	 * @param objectClassAdministrationRecord
	 *            The Administration Record for an Object Class.
	 * @param conceptRelationshipTypeDescription
	 *            A description of the type of relationship among two or more
	 *            Concepts.
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
	 *            {@link RegistrationAuthorityResource} represented by the
	 *            relationship registration.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @return {@link ConceptResource} on {@link Abbreviation} with a specific
	 *         URI generated from {@link Abbreviation#Concept}.
	 */
	public ConceptResource createConcept(
			AdministrationRecordResource objectClassAdministrationRecord,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having) {

		if (objectClassAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Object Class Administration Record must be specified for ObjectClass.");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for ObjectClass");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for ObjectClass");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for ObjectClass");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for ObjectClass");
		}
		String uniqueID = objectClassAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(Abbreviation.Concept.toString(),
				uniqueID));
		ConceptResource concept = new ConceptImpl(node, (EnhGraph) ontModel,
				objectClassAdministrationRecord, administeredBy, submittedBy,
				registeredBy, having, mdrDatabase);
		return concept;
	}

	/**
	 * The method to create {@link ConceptRelationshipResource} on
	 * {@link Abbreviation}.
	 * 
	 * @param objectClassAdministrationRecord
	 *            The Administration Record for an Object Class.
	 * @param conceptRelationshipTypeDescription
	 *            A description of the type of relationship among two or more
	 *            Concepts.
	 * @param administeredBy
	 *            An Administered Item is administered by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link StewardshipRelationshipResource}.
	 * @param conceptRelationshipTypeDescription
	 *            Type Description for the relationship between Concept and
	 *            specifies the Relationship
	 * @param submittedBy
	 *            An Administered Item is submitted by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link SubmissionRelationshipResource}.
	 * @param registeredBy
	 *            An {@link AdministeredItemResource} is registered by a
	 *            {@link RegistrationAuthorityResource} represented by the
	 *            relationship registration.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @return {@link ConceptRelationshipResource} on {@link Abbreviation} with
	 *         a specific URI generated from
	 *         {@link Abbreviation#ConceptRelationship}.
	 */
	public ConceptRelationshipResource createConceptRelationship(
			AdministrationRecordResource objectClassAdministrationRecord,
			String conceptRelationshipTypeDescription,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having) {

		if (objectClassAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Object Class Administration Record must be specified for ObjectClass.");
		}
		if (Util.isNull(conceptRelationshipTypeDescription)) {
			throw new IllegalArgumentException(
					"Type Description must be specified for ConceptRelationship.");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"StewardshipRelationship must be specified for ObjectClass");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"SubmissionRelationship must be specified for ObjectClass");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for ObjectClass");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for ObjectClass");
		}
		String uniqueID = objectClassAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(
				Abbreviation.ConceptRelationship.toString(), uniqueID));
		ConceptRelationshipResource conceptRelationship = new ConceptRelationshipImpl(
				node, (EnhGraph) ontModel, objectClassAdministrationRecord,
				conceptRelationshipTypeDescription, administeredBy,
				submittedBy, registeredBy, having, mdrDatabase);
		return conceptRelationship;
	}

	/**
	 * Method to create {@link DataElementConceptRelationshipResource} on
	 * {@link Abbreviation}
	 * 
	 * @param dataElementConceptRelationshipTypeDescription
	 * @return {@link DataElementConceptRelationshipResource} on
	 *         {@link Abbreviation} with a specific URI generated from
	 *         {@link Abbreviation#DataElementConceptRelationship
	 */
	public DataElementConceptRelationshipResource createDataElementConceptRelationship(
			String dataElementConceptRelationshipTypeDescription) {
		if (Util.isNull(dataElementConceptRelationshipTypeDescription)) {
			throw new IllegalArgumentException(
					"Type Description must be specified for DataElementConceptRelationship.");
		}
		Node node = Node.createURI(makeID(
				Abbreviation.DataElementConceptRelationship.toString(),
				dataElementConceptRelationshipTypeDescription));
		DataElementConceptRelationshipResource dataElementConceptRelationship = new DataElementConceptRelationshipImpl(
				node, (EnhGraph) ontModel,
				dataElementConceptRelationshipTypeDescription, mdrDatabase);
		return dataElementConceptRelationship;
	}

	/**
	 * Method to create
	 * {@link DataElementConceptRelationshipAssociationResource} on
	 * {@link Abbreviation}
	 * 
	 * @param describedByDataElementConceptRelationship
	 *            DataElementConceptRelationship which has type description for
	 *            the association
	 * @return {@link DataElementConceptRelationshipAssociationResource} on
	 *         {@link Abbreviation} with a specific URI generated from
	 *         {@link Abbreviation#DataElementConceptRelationship}, parameters
	 *         and unique UUID
	 */
	public DataElementConceptRelationshipAssociationResource createDataElementConceptRelationshipAssociation(
			DataElementConceptRelationshipResource describedByDataElementConceptRelationship) {
		if (describedByDataElementConceptRelationship == null) {
			throw new IllegalArgumentException(
					"DataElementConceptRelationship must be specified for DataElementConceptRelationshipAssociation.");
		}
		String uniqueId = generateUniqueID();
		Node node = Node.createURI(makeID(
				Abbreviation.DataElementConceptRelationship.toString(),
				describedByDataElementConceptRelationship
						.getDataElementConceptRelationshipTypeDescription(),
				uniqueId));
		DataElementConceptRelationshipAssociationResource dataElementConceptRelationshipAssociation = new DataElementConceptRelationshipAssociationImpl(
				node, (EnhGraph) ontModel,
				describedByDataElementConceptRelationship, mdrDatabase);
		return dataElementConceptRelationshipAssociation;
	}

	/**
	 * Method to create {@link ConceptualDomainRelationshipResource} on
	 * {@link Abbreviation}
	 * 
	 * @param ConceptualDomainRelationshipTypeDescription
	 * @return {@link ConceptualDomainRelationshipResource} on
	 *         {@link Abbreviation} with a specific URI generated from
	 *         {@link Abbreviation#ConceptualDomainRelationship
	 */
	public ConceptualDomainRelationshipResource createConceptualDomainRelationship(
			String conceptualDomainRelationshipTypeDescription) {
		if (Util.isNull(conceptualDomainRelationshipTypeDescription)) {
			throw new IllegalArgumentException(
					"Type Description must be specified for ConceptualDomainRelationship.");
		}
		Node node = Node.createURI(makeID(
				Abbreviation.ConceptualDomainRelationship.toString(),
				conceptualDomainRelationshipTypeDescription));
		ConceptualDomainRelationshipResource conceptualDomainRelationship = new ConceptualDomainRelationshipImpl(
				node, (EnhGraph) ontModel,
				conceptualDomainRelationshipTypeDescription, mdrDatabase);
		return conceptualDomainRelationship;
	}

	/**
	 * Method to create {@link ConceptualDomainRelationshipAssociationResource}
	 * on {@link Abbreviation}
	 * 
	 * @param describedByConceptualDomainRelationship
	 *            ConceptualDomainRelationship which has type description for
	 *            the association
	 * @return {@link ConceptualDomainRelationshipAssociationResource} on
	 *         {@link Abbreviation} with a specific URI generated from
	 *         {@link Abbreviation#ConceptualDomainRelationship}, parameters and
	 *         unique UUID
	 */
	public ConceptualDomainRelationshipAssociationResource createConceptualDomainRelationshipAssociation(
			ConceptualDomainRelationshipResource describedByConceptualDomainRelationship) {
		if (describedByConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"ConceptualDomainRelationship must be specified for ConceptualDomainRelationshipAssociation.");
		}
		String uniqueId = generateUniqueID();
		Node node = Node.createURI(makeID(
				Abbreviation.ConceptualDomainRelationship.toString(),
				describedByConceptualDomainRelationship
						.getConceptualDomainRelationshipTypeDescription(),
				uniqueId));
		ConceptualDomainRelationshipAssociationResource conceptualDomainRelationshipAssociation = new ConceptualDomainRelationshipAssociationImpl(
				node, (EnhGraph) ontModel,
				describedByConceptualDomainRelationship, mdrDatabase);
		return conceptualDomainRelationshipAssociation;
	}

	/**
	 * Method to create {@link ValueDomainRelationshipResource} on
	 * {@link Abbreviation}
	 * 
	 * @param ValueDomainRelationshipTypeDescription
	 * @return {@link ValueDomainRelationshipResource} on {@link Abbreviation}
	 *         with a specific URI generated from
	 *         {@link Abbreviation#ValueDomainRelationship
	 */
	public ValueDomainRelationshipResource createValueDomainRelationship(
			String valueDomainRelationshipTypeDescription) {
		if (Util.isNull(valueDomainRelationshipTypeDescription)) {
			throw new IllegalArgumentException(
					"Type Description must be specified for ValueDomainRelationship.");
		}
		Node node = Node.createURI(makeID(
				Abbreviation.ValueDomainRelationship.toString(),
				valueDomainRelationshipTypeDescription));
		ValueDomainRelationshipResource valueDomainRelationship = new ValueDomainRelationshipImpl(
				node, (EnhGraph) ontModel,
				valueDomainRelationshipTypeDescription, mdrDatabase);
		return valueDomainRelationship;
	}

	/**
	 * Method to create {@link ValueDomainRelationshipAssociationResource} on
	 * {@link Abbreviation}
	 * 
	 * @param describedByValueDomainRelationship
	 *            ValueDomainRelationship which has type description for the
	 *            association
	 * @return {@link ValueDomainRelationshipAssociationResource} on
	 *         {@link Abbreviation} with a specific URI generated from
	 *         {@link Abbreviation#ValueDomainRelationship}, parameters and
	 *         unique UUID
	 */
	public ValueDomainRelationshipAssociationResource createValueDomainRelationshipAssociation(
			ValueDomainRelationshipResource describedByValueDomainRelationship) {
		if (describedByValueDomainRelationship == null) {
			throw new IllegalArgumentException(
					"ValueDomainRelationship must be specified for ValueDomainRelationshipAssociation.");
		}
		String uniqueId = generateUniqueID();
		Node node = Node.createURI(makeID(Abbreviation.ValueDomainRelationship
				.toString(), describedByValueDomainRelationship
				.getValueDomainRelationshipTypeDescription(), uniqueId));
		ValueDomainRelationshipAssociationResource valueDomainRelationshipAssociation = new ValueDomainRelationshipAssociationImpl(
				node, (EnhGraph) ontModel, describedByValueDomainRelationship,
				mdrDatabase);
		return valueDomainRelationshipAssociation;
	}

	public RepresentationClassResource createRepresentationClass(
			AdministrationRecordResource representationClassAdministrationRecord,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having) {
		if (representationClassAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"AdministrationRecord must be specified for Representation Class.");
		}
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"Stewardship must be specified for RepresentationClass.");
		}
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"Submission must be specified for RepresentationClass.");
		}
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for RepresentationClass.");
		}
		if (having == null) {
			throw new IllegalArgumentException(
					"Administered Item Context must be specified for RepresentationClass.");
		}
		String uniqueID = representationClassAdministrationRecord
				.getAdministeredItemIdentifier().getDataIdentifier();
		Node node = Node.createURI(makeID(
				Abbreviation.RepresentationClass.toString(), uniqueID));
		return new RepresentationClassImpl(node, (EnhGraph) ontModel,
				representationClassAdministrationRecord, administeredBy,
				submittedBy, registeredBy, having, mdrDatabase);
	}

}
