package tr.com.srdc.mdr.core.model;

import com.hp.hpl.jena.ontology.OntModel;

/**
 * Abbreviations to identify the URIs of the resources within the
 * {@link OntModel}.
 * 
 * @author anil
 * 
 */
public enum Abbreviation {
	ItemIdentifier("II"), AdministrationRecord("AR"), AdministeredItem("AI"), ClassificationScheme(
			"CS"), ClassificationSchemeItem("CSI"), ClassificationSchemeItemRelationship(
			"CSIR"), Concept("CON"), ConceptualDomain("CD"), ConceptualDomainRelationship(
			"CDR"), ConceptRelationship("CONR"), Contact("CNT"), Context("CX"), DataElement(
			"DE"), DataElementConcept("DEC"), DataElementDerivation("DER"), DataElementExample(
			"DAE"), DataElementConceptRelationship("DECR"), Datatype("DT"), Designation(
			"DES"), Definition("DEF"), DerivationRule("DR"), LanguageSection(
			"LS"), ObjectClass("OC"), Property("PR"), RepresentationClass("RC"), ValueDomain(
			"VD"), Organization("ORG"), RegistrationAuthority("RA"), ReferenceDocument(
			"RD"), Submission("SUB"), Stewardship("STEW"), AdministeredItemContext(
			"AIC"), TerminologicalEntry("TE"), UnitOfMeasure("UOM"), NonEnumeratedConceptualDomain(
			"NECD"), NonEnumeratedValueDomain("NEVD"), EnumeratedConceptualDomain(
			"ECD"), EnumeratedValueDomain("EVD"), Value("VAL"), ValueDomainRelationship(
			"VDR"), ValueMeaning("VM"), PermissibleValue("PVAL");

	private final String abbreviation;

	private Abbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@Override
	public final String toString() {
		return this.abbreviation;
	}
}
