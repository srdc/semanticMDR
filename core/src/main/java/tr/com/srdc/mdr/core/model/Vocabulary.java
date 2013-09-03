package tr.com.srdc.mdr.core.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.impl.ai.ContextImpl;
import tr.com.srdc.mdr.core.impl.composite.LanguageIdentificationImpl;
import tr.com.srdc.mdr.core.impl.composite.RegistrationAuthorityIdentifierImpl;
import tr.com.srdc.mdr.core.model.iso11179.ContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageIdentificationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityIdentifierResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;


/**
 * @author anil
 * 
 *         ISO 11179-3 based Vocabulary for the ontological resources and
 *         properties.
 * 
 */
public class Vocabulary {

	private static final Logger logger = LoggerFactory
			.getLogger(Vocabulary.class);

	private MDRDatabase mdrDatabase;

	public final OntClass LanguageIdentification;
	public final OntProperty countryIdentifier;
	public final OntProperty languageIdentifier;

	public final OntClass RegistrationAuthorityIdentifier;
	public final OntProperty internationalCodeDesignator;
	public final OntProperty organizationIdentifier;
	public final OntProperty organizationPartIdentifier;
	public final OntProperty opiSource;

	public final OntClass ItemIdentifier;
	public final OntProperty itemRegistrationAuthorityIdentifier;
	public final OntProperty dataIdentifier;
	public final OntProperty version;

	public final OntClass Contact;
	public final OntProperty contactName;
	public final OntProperty contactTitle;
	public final OntProperty contactInformation;

	public final OntClass AdministrationRecord;
	public final OntProperty administeredItemIdentifier;
	public final OntProperty registrationStatus;
	public final OntProperty administrativeStatus;
	public final OntProperty creationDate;
	public final OntProperty lastChangeDate;
	public final OntProperty effectiveDate;
	public final OntProperty untilDate;
	public final OntProperty changeDescription;
	public final OntProperty administrativeNote;
	public final OntProperty explanatoryComment;
	public final OntProperty unresolvedIssue;
	public final OntProperty origin;

	public final OntClass ObjectClass;
	public final OntProperty objectClassAdministrationRecord;

	public final OntClass Property;
	public final OntProperty propertyAdministrationRecord;

	public final OntClass AdministeredItem;
	public final OntProperty administeredItemAdministrationRecord;
	public final OntProperty classifiedBy;
	public final OntProperty submittedBy;
	public final OntProperty administeredBy;
	public final OntProperty registeredBy;
	public final OntProperty having;
	public final OntProperty describedBy;

	public final OntClass DataElementConcept;
	public final OntProperty dataElementConceptAdministrationRecord;
	public final OntProperty dataElementConceptObjectClass;
	public final OntProperty dataElementConceptProperty;
	public final OntProperty objectClassQualifier;
	public final OntProperty propertyQualifier;
	public final OntProperty expressedByDataElementConceptExpression;
	public final OntProperty havingDataElementConceptConceptualDomainRelationship;
	public final OntProperty relatedToDataElementConceptRelationship;

	public final OntClass DataElementConceptRelationship;
	public final OntProperty dataElementConceptRelationshipTypeDescription;
	public final OntProperty describingDataElementConceptRelationship;

	public final OntClass DataElementConceptRelationshipAssociation;
	public final OntProperty describedByDataElementConceptRelationship;
	public final OntProperty relatingDataElementConceptRelationship;

	public final OntClass DataElement;
	public final OntProperty dataElementAdministrationRecord;
	public final OntProperty representationClassQualifier;
	public final OntProperty dataElementPrecision;
	public final OntProperty representedByDataElementRepresentation;
	public final OntProperty expressingDataElementConceptExpression;
	public final OntProperty inputToDerivationInput;
	public final OntProperty derivedFromDerivationOutput;
	public final OntProperty exemplifiedByExemplification;
	public final OntProperty typedByDataElementRepresentationClass;

	public final OntClass Context;
	public final OntProperty contextAdministrationRecord;
	public final OntProperty contextDescription;
	public final OntProperty contextDescriptionLanguageIdentifier;
	public final OntProperty contextAdministeredItemContext;

	public final OntClass ClassificationScheme;
	public final OntProperty classificationSchemeAdministrationRecord;
	public final OntProperty classificationSchemeTypeName;
	public final OntProperty containing;

	public final OntClass ClassificationSchemeItem;
	public final OntProperty classificationSchemeItemTypeName;
	public final OntProperty classificationSchemeItemValue;
	public final OntProperty containedIn;
	public final OntProperty classifying;

	public final OntClass ClassificationSchemeItemRelationship;
	public final OntProperty classificationSchemeItemRelationshipTypeDescription;
	public final OntProperty csiRelationshipAssociation;

	public final OntClass ClassificationSchemeItemAssociation;
	public final OntProperty csiAssociatedIn;
	public final OntProperty csiAssociationRelationship;
	public final OntProperty csiAssociates;

	public final OntClass Organization;
	public final OntProperty organizationName;
	public final OntProperty organizationMailAddress;
	public final OntProperty administering;
	public final OntProperty submitting;
	public final OntProperty providing;

	public final OntClass RegistrationAuthority;
	public final OntProperty registrationAuthorityIdentifier;
	public final OntProperty documentationLanguageIdentifier;
	public final OntProperty registering;
	public final OntProperty representedBy;

	public final OntClass Registrar;
	public final OntProperty registrarIdentifier;
	public final OntProperty registrarContact;
	public final OntProperty represents;

	public final OntClass ReferenceDocument;
	public final OntProperty referenceDocumentIdentifier;
	public final OntProperty referenceDocumentTypeDescription;
	public final OntProperty referenceDocumentLanguageIdentifier;
	public final OntProperty referenceDocumentTitle;
	public final OntProperty describing;
	public final OntProperty providedBy;

	public final OntClass Submission;
	public final OntProperty submissionContact;
	public final OntProperty submits;

	public final OntClass SubmissionRelationship;
	public final OntProperty submitter;
	public final OntProperty submissionOrganization;
	public final OntProperty submittedItems;

	public final OntClass Stewardship;
	public final OntProperty stewardshipContact;
	public final OntProperty administers;

	public final OntClass StewardshipRelationship;
	public final OntProperty administer;
	public final OntProperty stewardshipOrganization;
	public final OntProperty administeredItems;

	public final OntClass AdministeredItemContext;
	public final OntProperty administeredItemContextContext;
	public final OntProperty administeredItemContextTerminologicalEntry;
	public final OntProperty grouping;

	public final OntClass TerminologicalEntry;
	public final OntProperty terminologicalEntryAdministeredItemContext;
	public final OntProperty containingTerminologicalEntryLanguage;

	public final OntClass LanguageSection;
	public final OntProperty partitioning;
	public final OntProperty containingNameEntry;
	public final OntProperty containingDefinitionEntry;
	public final OntProperty languageSectionLanguageIdentifier;

	public final OntClass Designation;
	public final OntProperty namingEntryOf;
	public final OntProperty specificallyReferencing;
	public final OntProperty name;
	public final OntProperty preferredDesignation;

	public final OntClass Definition;
	public final OntProperty definingEntryOf;
	public final OntProperty specificallyUsing;
	public final OntProperty definitionText;
	public final OntProperty preferredDefinition;
	public final OntProperty definitionSourceReference;

	public final OntClass ConceptualDomain;
	public final OntProperty conceptualDomainAdministrationRecord;
	public final OntProperty dimensionality;
	public final OntProperty representedByConceptualDomainRepresentation;
	public final OntProperty specifyingDataElementConceptConceptualDomainRelationship;
	public final OntProperty relatedToConceptualDomainRelationship;

	public final OntClass EnumeratedConceptualDomain;
	public final OntProperty containingValueMeaningSet;

	public final OntClass NonEnumeratedConceptualDomain;
	public final OntProperty nonEnumeratedConceptualDomainDescription;
	public final OntProperty representedByNonEnumeratedConceptualDomainRepresentation;

	public final OntClass UnitOfMeasure;
	public final OntProperty unitOfMeasureName;
	public final OntProperty unitOfMeasurePrecision;

	public final OntClass Datatype;
	public final OntProperty datatypeName;
	public final OntProperty datatypeDescription;
	public final OntProperty datatypeSchemeReference;
	public final OntProperty datatypeAnnotation;

	public final OntClass ValueDomain;
	public final OntProperty representingConceptualDomainRepresentation;
	public final OntProperty valueDomainAdministrationRecord;
	public final OntProperty valueDomainDatatype;
	public final OntProperty valueDomainUnitOfMeasure;
	public final OntProperty valueDomainMaximumCharacterQuantity;
	public final OntProperty valueDomainFormat;
	public final OntProperty representingDataElementRepresentation;
	public final OntProperty typedByValueDomainRepresentationClass;
	public final OntProperty relatedToValueDomainRelationship;

	public final OntClass EnumeratedValueDomain;
	public final OntProperty containingPermissibleValueSet;

	public final OntClass PermissibleValue;
	public final OntProperty permissibleValueBeginDate;
	public final OntProperty permissibleValueEndDate;
	public final OntProperty containedInPermissibleValueSet;
	public final OntProperty hasPermittedValue;
	public final OntProperty hasPermissibleValueMeaning;

	public final OntClass Value;
	public final OntProperty valueItem;
	public final OntProperty usedInPermittedValue;

	public final OntClass ValueMeaning;
	public final OntProperty valueMeaningIdentifier;
	public final OntProperty valueMeaningDescription;
	public final OntProperty valueMeaningBeginDate;
	public final OntProperty valueMeaningEndDate;
	public final OntProperty usedInPermissibleValueMeaning;
	public final OntProperty containedInValueMeaningSet;

	public final OntClass NonEnumeratedValueDomain;
	public final OntProperty nonEnumeratedDomainDescription;
	public final OntProperty representingNonEnumeratedConceptualDomainRepresentation;

	public final OntClass DerivationRule;
	public final OntProperty derivationRuleAdministrationRecord;
	public final OntProperty derivationRuleSpecification;
	public final OntProperty appliedToDerivationRuleApplication;

	public final OntClass DataElementExample;
	public final OntProperty exemplifyingExemplification;
	public final OntProperty dataElementExampleItem;

	public final OntClass DataElementDerivation;
	public final OntProperty applyingDerivationRuleApplication;
	public final OntProperty inputingDerivationInput;
	public final OntProperty derivingDerivationOutput;

	public final OntClass RepresentationClass;
	public final OntProperty representationClassAdministrationRecord;
	public final OntProperty typingValueDomainRepresentationClass;
	public final OntProperty typingDataElementRepresentationClass;

	public final OntClass Concept;
	public final OntProperty usedInConceptRelationship;

	public final OntClass ConceptRelationship;
	public final OntProperty conceptRelationshipTypeDescription;
	public final OntProperty usingConceptRelationship;

	public final OntClass ValueDomainRelationship;
	public final OntProperty valueDomainRelationshipTypeDescription;
	public final OntProperty describingValueDomainRelationship;

	public final OntClass ValueDomainRelationshipAssociation;
	public final OntProperty describedByValueDomainRelationship;
	public final OntProperty relatingValueDomainRelationship;

	public final OntClass ConceptualDomainRelationship;
	public final OntProperty conceptualDomainRelationshipTypeDescription;
	public final OntProperty describingConceptualDomainRelationship;

	public final OntClass ConceptualDomainRelationshipAssociation;
	public final OntProperty describedByConceptualDomainRelationship;
	public final OntProperty relatingConceptualDomainRelationship;

	// Default Classes for the creation of Default/Parent Context
	public final ContextResource mdrContextResource;
	public final RegistrationAuthorityIdentifierResource mdrRegistrationAuthorityIdentifier;
	public final LanguageIdentificationResource mdrLanguageIdentificationResource;

	// End-of Default/Parent Context

	public Vocabulary(MDRDatabase mdrDatabase) {
		this.mdrDatabase = mdrDatabase;

		this.LanguageIdentification = mdrDatabase
				.getClass("LanguageIdentification");
		this.countryIdentifier = mdrDatabase.getProperty("countryIdentifier");
		this.languageIdentifier = mdrDatabase
				.getProperty("languageIdentifier");

		this.RegistrationAuthorityIdentifier = mdrDatabase
				.getClass("RegistrationAuthorityIdentifier");
		this.internationalCodeDesignator = mdrDatabase
				.getProperty("internationalCodeDesignator");
		this.organizationIdentifier = mdrDatabase
				.getProperty("organizationIdentifier");
		this.organizationPartIdentifier = mdrDatabase
				.getProperty("organizationPartIdentifier");
		this.opiSource = mdrDatabase.getProperty("opiSource");

		this.ItemIdentifier = mdrDatabase.getClass("ItemIdentifier");
		this.itemRegistrationAuthorityIdentifier = mdrDatabase
				.getProperty("itemRegistrationAuthorityIdentifier");
		this.dataIdentifier = mdrDatabase.getProperty("dataIdentifier");
		this.version = mdrDatabase.getProperty("version");

		this.Contact = mdrDatabase.getClass("Contact");
		this.contactName = mdrDatabase.getProperty("contactName");
		this.contactTitle = mdrDatabase.getProperty("contactTitle");
		this.contactInformation = mdrDatabase
				.getProperty("contactInformation");

		this.AdministrationRecord = mdrDatabase
				.getClass("AdministrationRecord");
		this.administeredItemIdentifier = mdrDatabase
				.getProperty("administeredItemIdentifier");
		this.registrationStatus = mdrDatabase
				.getProperty("registrationStatus");
		this.administrativeStatus = mdrDatabase
				.getProperty("administrativeStatus");
		this.creationDate = mdrDatabase.getProperty("creationDate");
		this.lastChangeDate = mdrDatabase.getProperty("lastChangeDate");
		this.effectiveDate = mdrDatabase.getProperty("effectiveDate");
		this.untilDate = mdrDatabase.getProperty("untilDate");
		this.changeDescription = mdrDatabase.getProperty("changeDescription");
		this.administrativeNote = mdrDatabase
				.getProperty("administrativeNote");
		this.explanatoryComment = mdrDatabase
				.getProperty("explanatoryComment");
		this.unresolvedIssue = mdrDatabase.getProperty("unresolvedIssue");
		this.origin = mdrDatabase.getProperty("origin");

		this.ObjectClass = mdrDatabase.getClass("ObjectClass");
		this.objectClassAdministrationRecord = mdrDatabase
				.getProperty("objectClassAdministrationRecord");

		this.Property = mdrDatabase.getClass("Property");
		this.propertyAdministrationRecord = mdrDatabase
				.getProperty("propertyAdministrationRecord");

		this.AdministeredItem = mdrDatabase.getClass("AdministeredItem");
		this.administeredItemAdministrationRecord = mdrDatabase
				.getProperty("administeredItemAdministrationRecord");
		this.classifiedBy = mdrDatabase.getProperty("classifiedBy");
		this.submittedBy = mdrDatabase.getProperty("submittedBy");
		this.administeredBy = mdrDatabase.getProperty("administeredBy");
		this.registeredBy = mdrDatabase.getProperty("registeredBy");
		this.having = mdrDatabase.getProperty("having");
		this.describedBy = mdrDatabase.getProperty("describedBy");

		this.DataElementConcept = mdrDatabase.getClass("DataElementConcept");
		this.dataElementConceptAdministrationRecord = mdrDatabase
				.getProperty("dataElementConceptAdministrationRecord");
		this.dataElementConceptObjectClass = mdrDatabase
				.getProperty("dataElementConceptObjectClass");
		this.dataElementConceptProperty = mdrDatabase
				.getProperty("dataElementConceptProperty");
		this.objectClassQualifier = mdrDatabase
				.getProperty("objectClassQualifier");
		this.propertyQualifier = mdrDatabase.getProperty("propertyQualifier");
		this.expressedByDataElementConceptExpression = mdrDatabase
				.getProperty("expressedByDataElementConceptExpression");
		this.havingDataElementConceptConceptualDomainRelationship = mdrDatabase
				.getProperty("havingDataElementConceptConceptualDomainRelationship");
		this.relatedToDataElementConceptRelationship = mdrDatabase
				.getProperty("relatedToDataElementConceptRelationship");

		this.DataElementConceptRelationship = mdrDatabase
				.getClass("DataElementConceptRelationship");
		this.dataElementConceptRelationshipTypeDescription = mdrDatabase
				.getProperty("dataElementConceptRelationshipTypeDescription");
		this.describingDataElementConceptRelationship = mdrDatabase
				.getProperty("describingDataElementConceptRelationship");
		this.DataElementConceptRelationshipAssociation = mdrDatabase
				.getClass("DataElementConceptRelationshipAssociation");
		this.describedByDataElementConceptRelationship = mdrDatabase
				.getProperty("describedByDataElementConceptRelationship");
		this.relatingDataElementConceptRelationship = mdrDatabase
				.getProperty("relatingDataElementConceptRelationship");

		this.DataElement = mdrDatabase.getClass("DataElement");
		this.dataElementAdministrationRecord = mdrDatabase
				.getProperty("dataElementAdministrationRecord");
		this.representationClassQualifier = mdrDatabase
				.getProperty("representationClassQualifier");
		this.dataElementPrecision = mdrDatabase
				.getProperty("dataElementPrecision");
		this.representedByDataElementRepresentation = mdrDatabase
				.getProperty("representedByDataElementRepresentation");
		this.expressingDataElementConceptExpression = mdrDatabase
				.getProperty("expressingDataElementConceptExpression");
		this.inputToDerivationInput = mdrDatabase
				.getProperty("inputToDerivationInput");
		this.derivedFromDerivationOutput = mdrDatabase
				.getProperty("derivedFromDerivationOutput");
		this.exemplifiedByExemplification = mdrDatabase
				.getProperty("exemplifiedByExemplification");
		this.typedByDataElementRepresentationClass = mdrDatabase
				.getProperty("typedByDataElementRepresentationClass");

		this.Context = mdrDatabase.getClass("Context");
		this.contextAdministrationRecord = mdrDatabase
				.getProperty("contextAdministrationRecord");
		this.contextDescription = mdrDatabase
				.getProperty("contextDescription");
		this.contextDescriptionLanguageIdentifier = mdrDatabase
				.getProperty("contextDescriptionLanguageIdentifier");
		this.contextAdministeredItemContext = mdrDatabase
				.getProperty("contextAdministeredItemContext");

		this.ClassificationScheme = mdrDatabase
				.getClass("ClassificationScheme");
		this.classificationSchemeAdministrationRecord = mdrDatabase
				.getProperty("classificationSchemeAdministrationRecord");
		this.classificationSchemeTypeName = mdrDatabase
				.getProperty("classificationSchemeTypeName");
		this.containing = mdrDatabase.getProperty("containing");

		this.ClassificationSchemeItem = mdrDatabase
				.getClass("ClassificationSchemeItem");
		this.classificationSchemeItemTypeName = mdrDatabase
				.getProperty("classificationSchemeItemTypeName");
		this.classificationSchemeItemValue = mdrDatabase
				.getProperty("classificationSchemeItemValue");
		this.containedIn = mdrDatabase.getProperty("containedIn");
		this.classifying = mdrDatabase.getProperty("classifying");

		this.ClassificationSchemeItemRelationship = mdrDatabase
				.getClass("ClassificationSchemeItemRelationship");
		this.classificationSchemeItemRelationshipTypeDescription = mdrDatabase
				.getProperty("classificationSchemeItemRelationshipTypeDescription");
		this.csiRelationshipAssociation = mdrDatabase
				.getProperty("csiRelationshipAssociation");

		this.ClassificationSchemeItemAssociation = mdrDatabase
				.getClass("ClassificationSchemeItemAssociation");
		this.csiAssociatedIn = mdrDatabase.getProperty("csiAssociatedIn");
		this.csiAssociationRelationship = mdrDatabase
				.getProperty("csiAssociationRelationship");
		this.csiAssociates = mdrDatabase.getProperty("csiAssociates");

		this.Organization = mdrDatabase.getClass("Organization");
		this.organizationName = mdrDatabase.getProperty("organizationName");
		this.organizationMailAddress = mdrDatabase
				.getProperty("organizationMailAddress");
		this.administering = mdrDatabase.getProperty("administering");
		this.submitting = mdrDatabase.getProperty("submitting");
		this.providing = mdrDatabase.getProperty("providing");

		this.RegistrationAuthority = mdrDatabase
				.getClass("RegistrationAuthority");
		this.registrationAuthorityIdentifier = mdrDatabase
				.getProperty("registrationAuthorityIdentifier");
		this.documentationLanguageIdentifier = mdrDatabase
				.getProperty("documentationLanguageIdentifier");
		this.registering = mdrDatabase.getProperty("registering");
		this.representedBy = mdrDatabase.getProperty("representedBy");

		this.Registrar = mdrDatabase.getClass("Registrar");
		this.registrarIdentifier = mdrDatabase
				.getProperty("registrarIdentifier");
		this.registrarContact = mdrDatabase.getProperty("registrarContact");
		this.represents = mdrDatabase.getProperty("represents");

		this.ReferenceDocument = mdrDatabase.getClass("ReferenceDocument");
		this.referenceDocumentIdentifier = mdrDatabase
				.getProperty("referenceDocumentIdentifier");
		this.referenceDocumentTypeDescription = mdrDatabase
				.getProperty("referenceDocumentTypeDescription");
		this.referenceDocumentLanguageIdentifier = mdrDatabase
				.getProperty("referenceDocumentLanguageIdentifier");
		this.referenceDocumentTitle = mdrDatabase
				.getProperty("referenceDocumentTitle");
		this.describing = mdrDatabase.getProperty("describing");
		this.providedBy = mdrDatabase.getProperty("providedBy");

		this.Submission = mdrDatabase.getClass("Submission");
		this.submissionContact = mdrDatabase.getProperty("submissionContact");
		this.submits = mdrDatabase.getProperty("submits");

		this.SubmissionRelationship = mdrDatabase
				.getClass("SubmissionRelationship");
		this.submitter = mdrDatabase.getProperty("submitter");
		this.submissionOrganization = mdrDatabase
				.getProperty("submissionOrganization");
		this.submittedItems = mdrDatabase.getProperty("submittedItems");

		this.Stewardship = mdrDatabase.getClass("Stewardship");
		this.stewardshipContact = mdrDatabase
				.getProperty("stewardshipContact");
		this.administers = mdrDatabase.getProperty("administers");

		this.StewardshipRelationship = mdrDatabase
				.getClass("StewardshipRelationship");
		this.administer = mdrDatabase.getProperty("administer");
		this.stewardshipOrganization = mdrDatabase
				.getProperty("stewardshipOrganization");
		this.administeredItems = mdrDatabase.getProperty("administeredItems");

		this.AdministeredItemContext = mdrDatabase
				.getClass("AdministeredItemContext");
		this.administeredItemContextContext = mdrDatabase
				.getProperty("administeredItemContextContext");
		this.administeredItemContextTerminologicalEntry = mdrDatabase
				.getProperty("administeredItemContextTerminologicalEntry");
		this.grouping = mdrDatabase.getProperty("grouping");

		this.TerminologicalEntry = mdrDatabase.getClass("TerminologicalEntry");
		this.terminologicalEntryAdministeredItemContext = mdrDatabase
				.getProperty("terminologicalEntryAdministeredItemContext");
		this.containingTerminologicalEntryLanguage = mdrDatabase
				.getProperty("containingTerminologicalEntryLanguage");

		this.LanguageSection = mdrDatabase.getClass("LanguageSection");
		this.partitioning = mdrDatabase.getProperty("partitioning");
		this.containingNameEntry = mdrDatabase
				.getProperty("containingNameEntry");
		this.containingDefinitionEntry = mdrDatabase
				.getProperty("containingDefinitionEntry");
		this.languageSectionLanguageIdentifier = mdrDatabase
				.getProperty("languageSectionLanguageIdentifier");

		this.Designation = mdrDatabase.getClass("Designation");
		this.namingEntryOf = mdrDatabase.getProperty("namingEntryOf");
		this.specificallyReferencing = mdrDatabase
				.getProperty("specificallyReferencing");
		this.name = mdrDatabase.getProperty("name");
		this.preferredDesignation = mdrDatabase
				.getProperty("preferredDesignation");

		this.Definition = mdrDatabase.getClass("Definition");
		this.definingEntryOf = mdrDatabase.getProperty("definingEntryOf");
		this.specificallyUsing = mdrDatabase.getProperty("specificallyUsing");
		this.definitionText = mdrDatabase.getProperty("definitionText");
		this.preferredDefinition = mdrDatabase
				.getProperty("preferredDefinition");
		this.definitionSourceReference = mdrDatabase
				.getProperty("definitionSourceReference");

		this.ConceptualDomain = mdrDatabase.getClass("ConceptualDomain");
		this.conceptualDomainAdministrationRecord = mdrDatabase
				.getProperty("conceptualDomainAdministrationRecord");
		this.dimensionality = mdrDatabase.getProperty("dimensionality");
		this.representedByConceptualDomainRepresentation = mdrDatabase
				.getProperty("representedByConceptualDomainRepresentation");
		this.specifyingDataElementConceptConceptualDomainRelationship = mdrDatabase
				.getProperty("specifyingDataElementConceptConceptualDomainRelationship");
		this.relatedToConceptualDomainRelationship = mdrDatabase
				.getProperty("relatedToConceptualDomainRelationship");

		this.EnumeratedConceptualDomain = mdrDatabase
				.getClass("EnumeratedConceptualDomain");
		this.containingValueMeaningSet = mdrDatabase
				.getProperty("containingValueMeaningSet");

		this.NonEnumeratedConceptualDomain = mdrDatabase
				.getClass("NonEnumeratedConceptualDomain");
		this.nonEnumeratedConceptualDomainDescription = mdrDatabase
				.getProperty("nonEnumeratedConceptualDomainDescription");
		this.representedByNonEnumeratedConceptualDomainRepresentation = mdrDatabase
				.getProperty("representedByNonEnumeratedConceptualDomainRepresentation");

		this.UnitOfMeasure = mdrDatabase.getClass("UnitOfMeasure");
		this.unitOfMeasureName = mdrDatabase.getProperty("unitOfMeasureName");
		this.unitOfMeasurePrecision = mdrDatabase
				.getProperty("unitOfMeasurePrecision");

		this.Datatype = mdrDatabase.getClass("Datatype");
		this.datatypeName = mdrDatabase.getProperty("datatypeName");
		this.datatypeDescription = mdrDatabase
				.getProperty("datatypeDescription");
		this.datatypeSchemeReference = mdrDatabase
				.getProperty("datatypeSchemeReference");
		this.datatypeAnnotation = mdrDatabase
				.getProperty("datatypeAnnotation");

		this.ValueDomain = mdrDatabase.getClass("ValueDomain");
		this.representingConceptualDomainRepresentation = mdrDatabase
				.getProperty("representingConceptualDomainRepresentation");
		this.valueDomainAdministrationRecord = mdrDatabase
				.getProperty("valueDomainAdministrationRecord");
		this.valueDomainDatatype = mdrDatabase
				.getProperty("valueDomainDatatype");
		this.valueDomainUnitOfMeasure = mdrDatabase
				.getProperty("valueDomainUnitOfMeasure");
		this.valueDomainMaximumCharacterQuantity = mdrDatabase
				.getProperty("valueDomainMaximumCharacterQuantity");
		this.valueDomainFormat = mdrDatabase.getProperty("valueDomainFormat");
		this.representingDataElementRepresentation = mdrDatabase
				.getProperty("representingDataElementRepresentation");
		this.typedByValueDomainRepresentationClass = mdrDatabase
				.getProperty("typedByValueDomainRepresentationClass");
		this.relatedToValueDomainRelationship = mdrDatabase
				.getProperty("relatedToValueDomainRelationship");

		this.EnumeratedValueDomain = mdrDatabase
				.getClass("EnumeratedValueDomain");
		this.containingPermissibleValueSet = mdrDatabase
				.getProperty("containingPermissibleValueSet");

		this.PermissibleValue = mdrDatabase.getClass("PermissibleValue");
		this.permissibleValueBeginDate = mdrDatabase
				.getProperty("permissibleValueBeginDate");
		this.permissibleValueEndDate = mdrDatabase
				.getProperty("permissibleValueEndDate");
		this.containedInPermissibleValueSet = mdrDatabase
				.getProperty("containedInPermissibleValueSet");
		this.hasPermittedValue = mdrDatabase.getProperty("hasPermittedValue");
		this.hasPermissibleValueMeaning = mdrDatabase
				.getProperty("hasPermissibleValueMeaning");

		this.Value = mdrDatabase.getClass("Value");
		this.valueItem = mdrDatabase.getProperty("valueItem");
		this.usedInPermittedValue = mdrDatabase
				.getProperty("usedInPermittedValue");

		this.ValueMeaning = mdrDatabase.getClass("ValueMeaning");
		this.valueMeaningIdentifier = mdrDatabase
				.getProperty("valueMeaningIdentifier");
		this.valueMeaningDescription = mdrDatabase
				.getProperty("valueMeaningDescription");
		this.valueMeaningBeginDate = mdrDatabase
				.getProperty("valueMeaningBeginDate");
		this.valueMeaningEndDate = mdrDatabase
				.getProperty("valueMeaningEndDate");
		this.usedInPermissibleValueMeaning = mdrDatabase
				.getProperty("usedInPermissibleValueMeaning");
		this.containedInValueMeaningSet = mdrDatabase
				.getProperty("containedInValueMeaningSet");

		this.NonEnumeratedValueDomain = mdrDatabase
				.getClass("NonEnumeratedValueDomain");
		this.nonEnumeratedDomainDescription = mdrDatabase
				.getProperty("nonEnumeratedDomainDescription");
		this.representingNonEnumeratedConceptualDomainRepresentation = mdrDatabase
				.getProperty("representingNonEnumeratedConceptualDomainRepresentation");

		this.DerivationRule = mdrDatabase.getClass("DerivationRule");
		this.derivationRuleAdministrationRecord = mdrDatabase
				.getProperty("derivationRuleAdministrationRecord");
		this.derivationRuleSpecification = mdrDatabase
				.getProperty("derivationRuleSpecification");
		this.appliedToDerivationRuleApplication = mdrDatabase
				.getProperty("appliedToDerivationRuleApplication");

		this.DataElementExample = mdrDatabase.getClass("DataElementExample");
		this.exemplifyingExemplification = mdrDatabase
				.getProperty("exemplifyingExemplification");
		this.dataElementExampleItem = mdrDatabase
				.getProperty("dataElementExampleItem");

		this.DataElementDerivation = mdrDatabase
				.getClass("DataElementDerivation");
		this.applyingDerivationRuleApplication = mdrDatabase
				.getProperty("applyingDerivationRuleApplication");
		this.inputingDerivationInput = mdrDatabase
				.getProperty("inputingDerivationInput");
		this.derivingDerivationOutput = mdrDatabase
				.getProperty("derivingDerivationOutput");

		this.RepresentationClass = mdrDatabase.getClass("RepresentationClass");
		this.representationClassAdministrationRecord = mdrDatabase
				.getProperty("representationClassAdministrationRecord");
		this.typingValueDomainRepresentationClass = mdrDatabase
				.getProperty("typingValueDomainRepresentationClass");
		this.typingDataElementRepresentationClass = mdrDatabase
				.getProperty("typingDataElementRepresentationClass");

		this.Concept = mdrDatabase.getClass("Concept");
		this.usedInConceptRelationship = mdrDatabase
				.getProperty("usedInConceptRelationship");

		this.ConceptRelationship = mdrDatabase.getClass("ConceptRelationship");
		this.conceptRelationshipTypeDescription = mdrDatabase
				.getProperty("conceptRelationshipTypeDescription");
		this.usingConceptRelationship = mdrDatabase
				.getProperty("usingConceptRelationship");

		this.ValueDomainRelationship = mdrDatabase
				.getClass("ValueDomainRelationship");
		this.valueDomainRelationshipTypeDescription = mdrDatabase
				.getProperty("valueDomainRelationshipTypeDescription");
		this.describingValueDomainRelationship = mdrDatabase
				.getProperty("describingValueDomainRelationship");

		this.ValueDomainRelationshipAssociation = mdrDatabase
				.getClass("ValueDomainRelationshipAssociation");
		this.describedByValueDomainRelationship = mdrDatabase
				.getProperty("describedByValueDomainRelationship");
		this.relatingValueDomainRelationship = mdrDatabase
				.getProperty("relatingValueDomainRelationship");

		this.ConceptualDomainRelationship = mdrDatabase
				.getClass("ConceptualDomainRelationship");
		this.conceptualDomainRelationshipTypeDescription = mdrDatabase
				.getProperty("conceptualDomainRelationshipTypeDescription");
		this.describingConceptualDomainRelationship = mdrDatabase
				.getProperty("describingConceptualDomainRelationship");

		this.ConceptualDomainRelationshipAssociation = mdrDatabase
				.getClass("ConceptualDomainRelationshipAssociation");
		this.describedByConceptualDomainRelationship = mdrDatabase
				.getProperty("describedByConceptualDomainRelationship");
		this.relatingConceptualDomainRelationship = mdrDatabase
				.getProperty("relatingConceptualDomainRelationship");

		// Default Classes for the creation of Default/Parent Context
		this.mdrContextResource = new ContextImpl(
				mdrDatabase.getClass("MDRContext"), mdrDatabase);
		this.mdrRegistrationAuthorityIdentifier = new RegistrationAuthorityIdentifierImpl(
				mdrDatabase.getClass("MDRRegistrationAuthorityIdentifier"),
				mdrDatabase);
		this.mdrLanguageIdentificationResource = new LanguageIdentificationImpl(
				mdrDatabase.getClass("MDRLanguageIdentification"),
				mdrDatabase);
		// End-of Default/Parent Context

		logger.info("Vocabulary is initalized with {} , ontology URI : {}",
				mdrDatabase.getStoreType(), MDRDatabase.BASE_URI);
	}

	public MDRDatabase getMDRDatabase() {
		return this.mdrDatabase;
	}

}