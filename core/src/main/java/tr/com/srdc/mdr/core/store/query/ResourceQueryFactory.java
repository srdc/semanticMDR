package tr.com.srdc.mdr.core.store.query;

import java.util.ArrayList;
import java.util.List;

import tr.com.srdc.mdr.core.api.ai.Context;
import tr.com.srdc.mdr.core.api.ai.DataElementConcept;
import tr.com.srdc.mdr.core.api.ai.ObjectClass;
import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.impl.ai.ClassificationSchemeImpl;
import tr.com.srdc.mdr.core.impl.ai.ConceptImpl;
import tr.com.srdc.mdr.core.impl.ai.ContextImpl;
import tr.com.srdc.mdr.core.impl.ai.DataElementConceptImpl;
import tr.com.srdc.mdr.core.impl.ai.DataElementImpl;
import tr.com.srdc.mdr.core.impl.ai.EnumeratedConceptualDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.EnumeratedValueDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.NonEnumeratedConceptualDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.NonEnumeratedValueDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.PropertyImpl;
import tr.com.srdc.mdr.core.impl.composite.ContactImpl;
import tr.com.srdc.mdr.core.impl.composite.DatatypeImpl;
import tr.com.srdc.mdr.core.impl.composite.OrganizationImpl;
import tr.com.srdc.mdr.core.impl.composite.ValueMeaningImpl;
import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.iso11179.ClassificationSchemeResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.ContextResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.ObjectClassResource;
import tr.com.srdc.mdr.core.model.iso11179.PropertyResource;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DatatypeResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueMeaningResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public abstract class ResourceQueryFactory {

	public static enum TextSearchType {
		Exact, AllWords, WildCard
	}

	protected final static String PREFIX_RDFS = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> ";
	protected final static String PREFIX_RDF = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
	protected final static String PREFIX_OWL = "PREFIX owl: <http://www.w3.org/2002/07/owl#> ";
	protected final static String PREFIX_XSD = "PREFIX xsd: <" + XSD.getURI()
			+ "> ";
	// TODO : This prefix URI should be retrieved from mdrDatabase.getBaseURI()
	protected final static String PREFIX_MDR = "PREFIX mdr: <"
			+ MDRDatabase.BASE_URI + "> ";

	protected final MDRDatabase mdrDatabase;

	public ResourceQueryFactory(MDRDatabase mdrDatabase) {
		this.mdrDatabase = mdrDatabase;
	}

	abstract protected QueryExecution createQueryExecution(String queryString,
			OntModel ontModel);

	/**
	 * Calculates the number of results after a full text search
	 * 
	 * @param searchKeyword
	 *            search keyword to be matched
	 * @param contextURI
	 *            Optional. URI of the context.
	 * @param searchType
	 *            Optional. if given <code>null</code>, search is done over all @Context
	 *            s.
	 * @return
	 */
	abstract public int getNumberOfDataElementSearch(String searchKeyword,
			String contextURI, TextSearchType searchType);

	/**
	 * Executes a full text search over the database to find matching properties
	 * 
	 * @param searchKeyword
	 *            search keyword to be matched
	 * @param contextURI
	 *            Optional. URI of the context.
	 * @param searchType
	 *            Optional. if given <code>null</code>, search is done over all @Context
	 *            s.
	 * @param limit
	 *            Number of results to be displated
	 * @param offset
	 *            Starting indice of displayed results
	 * @return
	 */
	abstract public List<? super DataElementResource> searchDataElement(
			String searchKeyword, String contextURI, TextSearchType searchType,
			int limit, int offset);

	abstract public List<? super PropertyResource> searchProperty(
			String keyword, String contextURI, TextSearchType searchType);

	/**
	 * SHOULD NOT BE DIRECTLY CALLED BY TH USER, USE REPOSITORY METHODS Executes
	 * a keyword search on the MDRDatabase to find ConceptualDomain over a given
	 * Context
	 * 
	 * @param keyword
	 *            Keyword to be matched with Designation of Conceptual Domain on
	 *            given Context
	 * @param contextURI
	 *            Optional. URI of the context.
	 * @param searchType
	 * @return
	 */
	abstract public List<? super ConceptualDomainResource> searchConceptualDomain(
			String keyword, String contextURI, TextSearchType searchType);

	abstract public List<? super ValueDomainResource> searchValueDomain(
			String keyword, String uri, TextSearchType textSearchType);

	public List<? super ClassificationSchemeResource> getClassificationSchemes(
			String contextURI) {
		List<ClassificationSchemeResource> csList = new ArrayList<ClassificationSchemeResource>();
		ResultSet rs = getClassificationSchemesOfContext(contextURI);
		while (rs.hasNext()) {
			QuerySolution qs = rs.next();
			Resource res = qs.getResource("cs");
			csList.add(new ClassificationSchemeImpl(res, mdrDatabase));
		}
		return csList;
	}

	/**
	 * Method to execute full text search on the MDR to match given string with
	 * the Datatype Names
	 * 
	 * @param datatypeName
	 *            Name of the Datatype which is looked for
	 * @param searchType
	 *            Enumeration for types of Full Text Search
	 * @return
	 */
	public List<? super DatatypeResource> searchDatatype(String datatypeName) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append(PREFIX_XSD)
				.append("SELECT ?datatype FROM <").append(MDRDatabase.BASE_URI)
				.append("> WHERE {")
				.append("?datatype rdfs:subClassOf mdr:Datatype. ")
				.append("?datatype mdr:datatypeName \"").append(datatypeName)
				.append("\"^^xsd:string }");

		List<DatatypeResource> datatypeList = new ArrayList<DatatypeResource>();
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				// here conceptualDomain is checked whether its enumerated or
				// not, proper instantiation is done
				Resource res = qs.getResource("datatype");
				datatypeList.add(new DatatypeImpl(res, mdrDatabase));

			}
		} finally {
			qexec.close();
		}
		return datatypeList;
	}

	public ContactResource getContactResource(String contactName) {
		return new ContactImpl(mdrDatabase.getOntModel().getResource(
				mdrDatabase.getResourceFactory().makeID(
						Abbreviation.Contact.toString(), contactName)),
				mdrDatabase);
	}

	public OrganizationResource getOrganizationResource(String organizationName) {
		return new OrganizationImpl(mdrDatabase.getOntModel()
				.getResource(
						mdrDatabase.getResourceFactory().makeID(
								Abbreviation.Organization.toString(),
								organizationName)), mdrDatabase);
	}

	public ContextResource getContextResource(String uniqueID) {
		return new ContextImpl(mdrDatabase.getOntModel().getResource(
				mdrDatabase.getResourceFactory().makeID(
						Abbreviation.Context.toString(), uniqueID)),
				mdrDatabase);
	}

	public Resource getAdministeredItem(String id) {
		StringBuilder queryString = new StringBuilder()
				.append(PREFIX_MDR)
				.append(PREFIX_RDFS)
				.append("SELECT ?ai FROM <")
				.append(MDRDatabase.BASE_URI)
				.append("> WHERE { ")
				.append("?prop rdfs:subPropertyOf mdr:administeredItemAdministrationRecord. ")
				.append("?ai ?prop ?administrationRecord. ")
				.append("?administrationRecord mdr:administeredItemIdentifier ?ii.")
				.append("?ii mdr:dataIdentifier ?di. FILTER regex (?di, \"")
				.append(id).append("\") .}");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		Resource ai = null;
		try {
			ResultSet rs = qexec.execSelect();
			if (rs.hasNext()) {
				QuerySolution qs = rs.next();
				ai = qs.getResource("ai");
			}
			if (rs.hasNext()) {
				throw new IllegalStateException(
						"Something is wrong. There is more than one AdministeredItems with the same ID.");
			}
		} finally {
			qexec.close();
		}
		return ai;
	}

	public Resource getAdministeredItem(String id,
			String registrationAuthorityOrganizationID, String version) {
		StringBuilder queryString = new StringBuilder()
				.append(PREFIX_MDR)
				.append(PREFIX_RDFS)
				.append(PREFIX_XSD)
				.append("SELECT ?ai FROM <")
				.append(MDRDatabase.BASE_URI)
				.append("> WHERE { ")
				.append("?prop rdfs:subPropertyOf mdr:administeredItemAdministrationRecord. ")
				.append("?ai ?prop ?administrationRecord. ")
				.append("?administrationRecord mdr:administeredItemIdentifier ?ii. ")
				.append("?ii mdr:dataIdentifier \"").append(id)
				.append("\"^^xsd:string. ")
				.append("?ii mdr:itemRegistrationAuthorityIdentifier ?rai. ")
				.append("?rai mdr:organizationIdentifier \"")
				.append(registrationAuthorityOrganizationID)
				.append("\"^^xsd:string. ");
		if (version != null && !version.isEmpty()) {
			queryString.append("?ii mdr:version \"").append(version)
					.append("\"^^xsd:string. ");
			;
		}
		queryString.append("}");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		Resource ai = null;
		try {
			ResultSet rs = qexec.execSelect();
			if (rs.hasNext()) {
				QuerySolution qs = rs.next();
				ai = qs.getResource("ai");
			}
			if (rs.hasNext()) {
				throw new IllegalStateException(
						"Something is wrong. There is more than one AdministeredItems with the same ID and RegistrationAuthorityIdentifier.organizationIdentifier.");
			}
		} finally {
			qexec.close();
		}
		return ai;
	}

	public List<? super ContextResource> getContextsOfContext(String contextURI) {
		List<ContextResource> contextResourceList = new ArrayList<ContextResource>();

		StringBuilder queryString = new StringBuilder().append(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT ?ctx FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?ctx rdfs:subClassOf mdr:Context .")
				.append("?ctx mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(contextURI).append("> .").append("FILTER (?ctx != <")
				.append(contextURI).append("> )  }");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				ContextResource ctx = new ContextImpl(qs.getResource("ctx"),
						this.mdrDatabase);
				contextResourceList.add(ctx);
			}
		} finally {
			qexec.close();
		}
		return contextResourceList;
	}

	public List<? super DataElementResource> getDataElementsOfContext(
			String contextURI, Integer limit, Integer offset) {
		List<DataElementResource> dataElementResourceList = new ArrayList<DataElementResource>();

		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT ?de FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?de rdfs:subClassOf mdr:DataElement .")
				.append("?de mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(contextURI).append("> .").append("}");
		if (limit != null && offset != null) {
			queryString.append(" ORDER BY ?de ").append(" LIMIT ")
					.append(limit).append(" OFFSET ").append(offset);
		}
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());

		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				DataElementResource ctx = new DataElementImpl(
						qs.getResource("de"), this.mdrDatabase);
				dataElementResourceList.add(ctx);
			}
		} finally {
			qexec.close();
		}
		return dataElementResourceList;

	}

	/**
	 * @param uri
	 *            URI of the Context
	 * @return total number of Data Element's on specified Context
	 */
	public int getNumberOfDataElementsOfContext(String uri) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS)
				.append("SELECT (COUNT(DISTINCT ?de) as ?count) FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?de rdfs:subClassOf mdr:DataElement .")
				.append("?de mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(uri).append("> .").append("}");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		int size = 0;
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				size = qs.getLiteral("count").getInt();
			}
		} finally {
			qexec.close();
		}
		return size;
	}

	/**
	 * 
	 * @param contextURI
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<? super ConceptualDomainResource> getConceptualDomainsOfContext(
			String contextURI, Integer limit, Integer offset) {
		List<ConceptualDomainResource> cdList = new ArrayList<ConceptualDomainResource>();
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT ?cd FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?cdClass rdfs:subClassOf mdr:ConceptualDomain .")
				.append("?cd rdfs:subClassOf ?cdClass .")
				.append("?cd mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(contextURI).append("> . }");
		if (limit != null && offset != null) {
			queryString.append(" LIMIT ").append(limit).append(" OFFSET ")
					.append(offset);
		}
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				// here conceptualDomain is checked whether its enumerated or
				// not, proper instantiation is done
				OntClass res = mdrDatabase.getOntModel().getOntClass(
						qs.getResource("cd").getURI());
				if (res.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedConceptualDomain)) {
					cdList.add(new EnumeratedConceptualDomainImpl(res,
							mdrDatabase));
				} else {
					cdList.add(new NonEnumeratedConceptualDomainImpl(res,
							mdrDatabase));
				}

			}
		} finally {
			qexec.close();
		}

		return cdList;
	}

	public List<? super PropertyResource> getPropertiesOfContext(
			String contextURI) {
		List<PropertyResource> propertyList = new ArrayList<PropertyResource>();
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT ?property FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?property rdfs:subClassOf mdr:Property .")
				.append("?property mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(contextURI).append("> .").append("}");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				PropertyResource prop = new PropertyImpl(
						qs.getResource("property"), mdrDatabase);
				propertyList.add(prop);
			}
		} finally {
			qexec.close();
		}
		return propertyList;
	}

	/**
	 * Runs SPARQL Query ove the MDRDatabase to get list of {@link ObjectClass}
	 * es on a specific {@link Context}
	 * 
	 * @param contextURI
	 *            URI of the Context on MDRDatabase
	 * @return List of {@link ObjectClassResource} on the specified Context
	 */
	public List<? super ObjectClassResource> getObjectClassesOfContext(
			String contextURI, Integer limit, Integer offset) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT ?objectClass FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?objectClassClass rdfs:subClassOf mdr:ObjectClass .")
				.append("?objectClass rdfs:subClassOf ?objectClassClass .")
				.append("?objectClass mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(contextURI).append("> . }");
		if (limit != null && offset != null) {
			queryString.append(" LIMIT ").append(limit).append(" OFFSET ")
					.append(offset);
		}
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		List<ObjectClassResource> objectClassResourceList = new ArrayList<ObjectClassResource>();
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				ConceptResource ctx = new ConceptImpl(
						qs.getResource("objectClass"), this.mdrDatabase);
				objectClassResourceList.add(ctx);
			}
		} finally {
			qexec.close();
		}
		return objectClassResourceList;
	}

	/**
	 * With given objectClassURI, tihs method returns a list of all
	 * {@link DataElementConcept}s whose {@link ObjectClass} is given
	 * 
	 * @param objectClassURI
	 *            Unique URI of the ObjectClass
	 * @return {@link DataElementConcept}s created with given ObjectClass
	 */
	public List<? super DataElementConceptResource> getDECSofOC(
			String objectClassURI, Integer limit, Integer offset) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT ?dec FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?dec rdfs:subClassOf mdr:DataElementConcept .")
				.append("?dec mdr:dataElementConceptObjectClass <")
				.append(objectClassURI).append("> .").append("}");
		if (limit != null && offset != null) {
			queryString.append(" LIMIT ").append(limit).append(" OFFSET ")
					.append(offset);
		}
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		List<DataElementConceptResource> decResourceList = new ArrayList<DataElementConceptResource>();
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				DataElementConceptResource ctx = new DataElementConceptImpl(
						qs.getResource("dec"), this.mdrDatabase);
				decResourceList.add(ctx);
			}
		} finally {
			qexec.close();
		}
		return decResourceList;
	}

	public ResultSet getRepresentationClassesOfContext(String contextURI) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT ?rc FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?rc rdfs:subClassOf mdr:RepresentationClass .")
				.append("?rc mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(contextURI).append("> . }");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		return qexec.execSelect();

	}

	public ResultSet getDerivationRulesOfContext(String contextURI) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT ?dr FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?dr rdfs:subClassOf mdr:DerivationRule .")
				.append("?dr mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(contextURI).append("> . }");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		return qexec.execSelect();

	}

	public ResultSet getClassificationSchemesOfContext(String contextURI) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT ?cs FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?cs rdfs:subClassOf mdr:ClassificationScheme .")
				.append("?cs mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(contextURI).append("> . }");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		return qexec.execSelect();

	}

	public List<? super ValueDomainResource> getValueDomainsOfConceptualDomain(
			String cdURI) {
		List<ValueDomainResource> vdList = new ArrayList<ValueDomainResource>();
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT ?vd FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?vdClass rdfs:subClassOf mdr:ValueDomain .")
				.append("?vd rdfs:subClassOf ?vdClass . ")
				.append("?vd mdr:representingConceptualDomainRepresentation <")
				.append(cdURI).append("> .").append("}");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				// here conceptualDomain is checked whether its enumerated or
				// not, proper instantiation is done
				OntClass res = mdrDatabase.getOntModel().getOntClass(
						qs.getResource("vd").getURI());
				if (res.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedValueDomain)) {
					vdList.add(new EnumeratedValueDomainImpl(res, mdrDatabase));
				} else {
					vdList.add(new NonEnumeratedValueDomainImpl(res,
							mdrDatabase));
				}

			}
		} finally {
			qexec.close();
		}

		return vdList;
	}

	public List<? super ValueMeaningResource> listValueMeningsOfCD(
			String cdURI, int limit, int offset) {
		List<ValueMeaningResource> vmList = new ArrayList<ValueMeaningResource>();
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT DISTINCT ?vm FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?vm rdfs:subClassOf mdr:ValueMeaning .")
				.append("?vm mdr:valueMeaningIdentifier ?id . ")
				.append("?vm mdr:containedInValueMeaningSet <").append(cdURI)
				.append("> .").append("} ORDER BY ?id LIMIT ").append(limit)
				.append(" OFFSET ").append(offset);
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				// here conceptualDomain is checked whether its enumerated or
				// not, proper instantiation is done
				OntClass res = mdrDatabase.getOntModel().getOntClass(
						qs.getResource("vm").getURI());
				if (res.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedValueDomain)) {
					vmList.add(new ValueMeaningImpl(res, mdrDatabase));
				} else {
					vmList.add(new ValueMeaningImpl(res, mdrDatabase));
				}

			}
		} finally {
			qexec.close();
		}

		return vmList;
	}

	public int getNumberOfValueMeanings(String conceptualDomainURI) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS)
				.append("SELECT (COUNT(DISTINCT ?vm) as ?count) FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?vm rdfs:subClassOf mdr:ValueMeaning .")
				.append("?vm mdr:containedInValueMeaningSet <")
				.append(conceptualDomainURI).append("> .").append("}");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		int size = 0;
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				size = qs.getLiteral("count").getInt();
			}
		} finally {
			qexec.close();
		}
		return size;

	}

	public int getNumberOfConceptualDomains(String contextURI) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS)
				.append("SELECT (COUNT (DISTINCT ?cd) as ?count) FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?cdClass rdfs:subClassOf mdr:ConceptualDomain .")
				.append("?cd rdfs:subClassOf ?cdClass .")
				.append("?cd mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(contextURI).append("> .").append("}");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		int size = 0;
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				size = qs.getLiteral("count").getInt();
			}
		} finally {
			qexec.close();
		}

		return size;
	}

	public int getNumberOfObjectClasses(String contextURI) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS)
				.append("SELECT (COUNT (DISTINCT ?objectClass) as ?count) FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?objectClassClass rdfs:subClassOf mdr:ObjectClass .")
				.append("?objectClass rdfs:subClassOf ?objectClassClass .")
				.append("?objectClass mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(contextURI).append("> .").append("}");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		int size = 0;
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				size = qs.getLiteral("count").getInt();
			}
		} finally {
			qexec.close();
		}

		return size;
	}

	public int getNumberOfDEC(String objectClassURI) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS)
				.append("SELECT (COUNT (DISTINCT ?dec) as ?count) FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?dec rdfs:subClassOf mdr:DataElementConcept .")
				.append("?dec mdr:dataElementConceptObjectClass <")
				.append(objectClassURI).append("> .").append("}");
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		int size = 0;
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				size = qs.getLiteral("count").getInt();
			}
		} finally {
			qexec.close();
		}

		return size;
	}

	/**
	 * @return all of the {@link Datatype}s
	 */
	public List<? super DatatypeResource> getDatatypes() {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append(PREFIX_XSD)
				.append("SELECT ?datatype FROM <").append(MDRDatabase.BASE_URI)
				.append("> WHERE {")
				.append("?datatype rdfs:subClassOf mdr:Datatype }");

		List<DatatypeResource> datatypeList = new ArrayList<DatatypeResource>();
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				Resource res = qs.getResource("datatype");
				datatypeList.add(new DatatypeImpl(res, mdrDatabase));
			}
		} finally {
			qexec.close();
		}
		return datatypeList;
	}

	public List<? super ValueDomainResource> getValueDomainsOfContext(
			String contextURI, Integer limit, Integer offset) {
		List<ValueDomainResource> valueDomainResourceList = new ArrayList<ValueDomainResource>();

		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_RDFS).append("SELECT ?vd FROM <")
				.append(MDRDatabase.BASE_URI).append("> WHERE {")
				.append("?vdClass rdfs:subClassOf mdr:ValueDomain .")
				.append("?vd rdfs:subClassOf mdr:vdClass . ")
				.append("?vd mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextContext <")
				.append(contextURI).append("> .").append("}");
		if (limit != null && offset != null) {
			queryString.append(" ORDER BY ?de ").append(" LIMIT ")
					.append(limit).append(" OFFSET ").append(offset);
		}
		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());

		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				// here conceptualDomain is checked whether its enumerated or
				// not, proper instantiation is done
				OntClass res = mdrDatabase.getOntModel().getOntClass(
						qs.getResource("cd").getURI());
				if (res.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedValueDomain)) {
					valueDomainResourceList.add(new EnumeratedValueDomainImpl(
							res, mdrDatabase));
				} else {
					valueDomainResourceList
							.add(new NonEnumeratedValueDomainImpl(res,
									mdrDatabase));
				}
			}
		} finally {
			qexec.close();
		}
		return valueDomainResourceList;
	}

}