package tr.com.srdc.mdr.core.store.query;

import java.util.ArrayList;
import java.util.List;

import tr.com.srdc.mdr.core.impl.ai.DataElementImpl;
import tr.com.srdc.mdr.core.impl.ai.EnumeratedConceptualDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.EnumeratedValueDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.NonEnumeratedConceptualDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.NonEnumeratedValueDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.PropertyImpl;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.PropertyResource;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class TDBQueryFactory extends ResourceQueryFactory {

	private static final String PREFIX_PF = "PREFIX pf: <http://jena.hpl.hp.com/ARQ/property#> ";

	public TDBQueryFactory(MDRDatabase mdrDatabase) {
		super(mdrDatabase);
	}

	@Override
	protected QueryExecution createQueryExecution(String queryString,
			OntModel ontModel) {
		return QueryExecutionFactory.create(queryString,
				this.mdrDatabase.getOntModel());
	}

	@Override
	public int getNumberOfDataElementSearch(String keyword, String contextURI,
			TextSearchType searchType) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_PF)
				.append(PREFIX_RDFS)
				.append("SELECT (COUNT (DISTINCT ?de) as ?count) FROM <")
				.append(MDRDatabase.BASE_URI)
				.append("> WHERE {")
				.append("?de rdfs:subClassOf mdr:DataElement .")
				.append("?de mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextTerminologicalEntry ?te .")
				.append("?te mdr:containingTerminologicalEntryLanguage ?ls .")
				.append("?ls mdr:containingNameEntry ?designation .")
				.append("?designation mdr:name ?name .");
		if (!Util.isNull(contextURI)) {
			queryString.append("?aic mdr:administeredItemContextContext <")
					.append(contextURI).append("> .");
		}
		if (keyword.matches("\\s*")) {
			return 0;
		}
		if (searchType == null || searchType.equals(TextSearchType.Exact)) {
			queryString.append(exactMatchKeyword(keyword));
		}

		else if (searchType.equals(TextSearchType.WildCard)) {
			queryString.append(atLeastOneKeyword(keyword));
		}

		else {
			queryString.append(allWordsKeyword(keyword));
		}
		queryString.append(" .}");

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

	@Override
	public List<? super DataElementResource> searchDataElement(String keyword,
			String contextURI, TextSearchType searchType, int limit, int offset) {
		List<DataElementResource> dataElementList = new ArrayList<DataElementResource>();
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_PF)
				.append(PREFIX_RDFS)
				.append("SELECT ?de FROM <")
				.append(MDRDatabase.BASE_URI)
				.append("> WHERE {")
				.append("?de rdfs:subClassOf mdr:DataElement .")
				.append("?de mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextTerminologicalEntry ?te .")
				.append("?te mdr:containingTerminologicalEntryLanguage ?ls .")
				.append("?ls mdr:containingNameEntry ?designation .")
				.append("?designation mdr:name ?name .");
		if (!Util.isNull(contextURI)) {
			queryString.append("?aic mdr:administeredItemContextContext <")
					.append(contextURI).append("> .");
		}
		if (keyword.matches("\\s*")) {
			return dataElementList;
		}
		if (searchType == null || searchType.equals(TextSearchType.Exact)) {
			queryString.append(exactMatchKeyword(keyword));
		}

		else if (searchType.equals(TextSearchType.WildCard)) {
			queryString.append(atLeastOneKeyword(keyword));
		}

		else {
			queryString.append(allWordsKeyword(keyword));
		}
		queryString.append(" .} ORDER BY ?score LIMIT ").append(limit)
				.append(" OFFSET ").append(offset);

		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				DataElementResource de = new DataElementImpl(
						qs.getResource("de"), mdrDatabase);
				dataElementList.add(de);
			}
		} finally {
			qexec.close();
		}
		return dataElementList;
	}

	@Override
	public List<? super PropertyResource> searchProperty(String keyword,
			String contextURI, TextSearchType searchType) {
		List<PropertyResource> propertyList = new ArrayList<PropertyResource>();
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_PF)
				.append(PREFIX_RDFS)
				.append("SELECT ?property FROM <")
				.append(MDRDatabase.BASE_URI)
				.append("> WHERE {")
				.append("?property rdfs:subClassOf mdr:Property .")
				.append("?property mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextTerminologicalEntry ?te .")
				.append("?te mdr:containingTerminologicalEntryLanguage ?ls .")
				.append("?ls mdr:containingNameEntry ?designation .")
				.append("?designation mdr:name ?name .");
		if (!Util.isNull(contextURI)) {
			queryString.append("?aic mdr:administeredItemContextContext <")
					.append(contextURI).append("> . ");
		}
		if (keyword.matches("\\s*")) {
			return propertyList;
		}
		if (searchType == null || searchType.equals(TextSearchType.Exact)) {
			queryString.append(exactMatchKeyword(keyword));
		}

		else if (searchType.equals(TextSearchType.WildCard)) {
			queryString.append(atLeastOneKeyword(keyword));
		}

		else {
			queryString.append(allWordsKeyword(keyword));
		}
		queryString.append(" .}");
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

	@Override
	public List<? super ConceptualDomainResource> searchConceptualDomain(
			String keyword, String contextURI, TextSearchType searchType) {
		List<ConceptualDomainResource> cdList = new ArrayList<ConceptualDomainResource>();
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_PF)
				.append(PREFIX_RDFS)
				.append("SELECT ?cd FROM <")
				.append(MDRDatabase.BASE_URI)
				.append("> WHERE {")
				.append("?cdClass rdfs:subClassOf mdr:ConceptualDomain .")
				.append("?cd rdfs:subClassOf ?cdClass .")
				.append("?cd mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextTerminologicalEntry ?te .")
				.append("?te mdr:containingTerminologicalEntryLanguage ?ls .")
				.append("?ls mdr:containingNameEntry ?designation .")
				.append("?designation mdr:name ?name .");
		if (!Util.isNull(contextURI)) {
			queryString.append("?aic mdr:administeredItemContextContext <")
					.append(contextURI).append("> .");
		}
		if (keyword.matches("\\s*")) {
			return cdList;
		}
		if (searchType == null || searchType.equals(TextSearchType.Exact)) {
			queryString.append(exactMatchKeyword(keyword));
		}

		else if (searchType.equals(TextSearchType.WildCard)) {
			queryString.append(atLeastOneKeyword(keyword));
		}

		else {
			queryString.append(allWordsKeyword(keyword));
		}
		queryString.append(" .}");

		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				// here conceptualDomain is checked whether its enumerated or
				// not, proper instantiation is done
				OntClass res = qs.getResource("cd").as(OntClass.class);
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

	@Override
	public List<? super ValueDomainResource> searchValueDomain(String keyword,
			String contextURI, TextSearchType searchType) {
		List<ValueDomainResource> vdList = new ArrayList<ValueDomainResource>();
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
				.append(PREFIX_PF)
				.append(PREFIX_RDFS)
				.append("SELECT ?cd FROM <")
				.append(MDRDatabase.BASE_URI)
				.append("> WHERE {")
				.append("?cdClass rdfs:subClassOf mdr:ValueDomain .")
				.append("?cd rdfs:subClassOf ?cdClass .")
				.append("?cd mdr:having ?aic .")
				.append("?aic mdr:administeredItemContextTerminologicalEntry ?te .")
				.append("?te mdr:containingTerminologicalEntryLanguage ?ls .")
				.append("?ls mdr:containingNameEntry ?designation .")
				.append("?designation mdr:name ?name .");
		if (!Util.isNull(contextURI)) {
			queryString.append("?aic mdr:administeredItemContextContext <")
					.append(contextURI).append("> .");
		}
		if (keyword.matches("\\s*")) {
			return vdList;
		}
		if (searchType == null || searchType.equals(TextSearchType.Exact)) {
			queryString.append(exactMatchKeyword(keyword));
		}

		else if (searchType.equals(TextSearchType.WildCard)) {
			queryString.append(atLeastOneKeyword(keyword));
		}

		else {
			queryString.append(allWordsKeyword(keyword));
		}
		queryString.append(" .}");

		QueryExecution qexec = this.createQueryExecution(
				queryString.toString(), this.mdrDatabase.getOntModel());
		try {
			ResultSet rs = qexec.execSelect();
			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				// here conceptualDomain is checked whether its enumerated or
				// not, proper instantiation is done
				OntClass res = qs.getResource("cd").as(OntClass.class);
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

	/**
	 * Creates a text match keyword for exact match according to LARQ query
	 * syntax.
	 * 
	 * @param keyword
	 *            Query keywords seperated from each other with whitespaces
	 * @return
	 */
	private String exactMatchKeyword(String keyword) {
		String[] keywords = keyword.split("\\s+");
		StringBuilder queryString = new StringBuilder();
		queryString.append("(?name ?score) pf:textMatch '\"");
		for (int i = 0; i < keywords.length - 1; i++) {
			queryString.append(keywords[i]).append(" ");
		}
		queryString.append(keywords[keywords.length - 1]).append(" \"~0' ");
		return queryString.toString();
	}

	/**
	 * Creates a text match keyword for matching all words according to LARQ
	 * query syntax.
	 * 
	 * @param keyword
	 *            Query keywords seperated from each other with whitespaces
	 * @return
	 */
	private String allWordsKeyword(String keyword) {
		String[] keywords = keyword.split("\\s+");
		StringBuilder queryString = new StringBuilder();
		queryString.append("(?name ?score) pf:textMatch '");
		for (int i = 0; i < keywords.length - 1; i++) {
			queryString.append(keywords[i]).append(" AND ");
		}
		queryString.append(keywords[keywords.length - 1]).append(" '");
		return queryString.toString();
	}

	/**
	 * Creates a text match keyword for wildcard matching according to LARQ
	 * query syntax.
	 * 
	 * @param keyword
	 *            Query keywords seperated from each other with whitespaces
	 * @return
	 */
	private String atLeastOneKeyword(String keyword) {
		String[] keywords = keyword.split("\\s+");
		StringBuilder queryString = new StringBuilder();
		queryString.append("(?name ?score) pf:textMatch '");

		for (int i = 0; i < keywords.length - 1; i++) {
			queryString.append(keywords[i]).append(" ");
		}
		queryString.append(keywords[keywords.length - 1]).append("* ' ");
		return queryString.toString();
	}

}
