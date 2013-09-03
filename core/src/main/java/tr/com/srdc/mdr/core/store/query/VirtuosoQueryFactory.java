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
import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Resource;

public class VirtuosoQueryFactory extends ResourceQueryFactory {

	public VirtuosoQueryFactory(MDRDatabase mdrDatabase) {
		super(mdrDatabase);
	}

	@Override
	protected QueryExecution createQueryExecution(String queryString,
			OntModel ontModel) {
		return VirtuosoQueryExecutionFactory.create(queryString,
				(VirtGraph) this.mdrDatabase.getJenaStore().getGraph());

	}

	@Override
	public int getNumberOfDataElementSearch(String keyword, String contextURI,
			TextSearchType searchType) {
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
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
		// checks if keyword is empty
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
		// checks if keyword is empty
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
		queryString.append(" OPTION (score ?sc) .} ORDER BY ?sc LIMIT ")
				.append(limit).append(" OFFSET ").append(offset);

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
					.append(contextURI).append("> .");
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
				Resource tempRes = this.mdrDatabase.getOntModel().getResource(
						qs.getResource("cd").getURI());
				OntClass res = tempRes.as(OntClass.class);
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
			String uri, TextSearchType searchType) {
		List<ValueDomainResource> vdList = new ArrayList<ValueDomainResource>();
		StringBuilder queryString = new StringBuilder(PREFIX_MDR)
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
		if (!Util.isNull(uri)) {
			queryString.append("?aic mdr:administeredItemContextContext <")
					.append(uri).append("> .");
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
				Resource tempRes = this.mdrDatabase.getOntModel().getResource(
						qs.getResource("cd").getURI());
				OntClass res = tempRes.as(OntClass.class);
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
	 * Creates a text match keyword for exact match according to Virtuoso query
	 * syntax.
	 * 
	 * @param keyword
	 *            Query keywords seperated from each other with whitespaces
	 * @return
	 */
	private String exactMatchKeyword(String keyword) {
		String[] keywords = keyword.split("\\s+");
		StringBuilder queryString = new StringBuilder();
		queryString.append("?name bif:contains '\"");
		for (int i = 0; i < keywords.length - 1; i++) {
			queryString.append(keywords[i]).append(" ");
		}
		queryString.append(keywords[keywords.length - 1]).append(" \"' ");
		return queryString.toString();
	}

	/**
	 * Creates a text match keyword for wild card search according to Virtuoso
	 * query syntax.
	 * 
	 * @param keyword
	 *            Query keywords seperated from each other with whitespaces
	 * @return
	 */
	private String atLeastOneKeyword(String keyword) {
		String[] keywords = keyword.split("\\s+");
		StringBuilder queryString = new StringBuilder();
		queryString.append("?name bif:contains '\"");

		String lastWord = keywords[keywords.length - 1];
		if (lastWord.length() < 4) {
			keyword = concat(keywords);
		}

		if (keyword.length() < 4) {
			queryString.append(keyword).append("\"' ");
		} else {
			// wildcard search
			queryString.append(keyword).append("*\"' ");
		}

		return queryString.toString();
	}

	/**
	 * Creates a text match keyword for matching all words according to Virtuoso
	 * query syntax.
	 * 
	 * @param keyword
	 *            Query keywords seperated from each other with whitespaces
	 * @return
	 */
	private String allWordsKeyword(String keyword) {
		String[] keywords = keyword.split("\\s+");
		StringBuilder queryString = new StringBuilder();
		queryString.append("?name pf:textMatch '");
		for (int i = 0; i < keywords.length - 1; i++) {
			queryString.append(keywords[i]).append(" AND ");
		}
		queryString.append(keywords[keywords.length - 1]).append(" ' ");
		return queryString.toString();
	}

	/**
	 * This method is taken from the TerminologyServer written by anil. Since
	 * Virtuoso Text Index supports wildcard searches only with words longer
	 * than 4 letters, this case should be handled carefully.
	 * 
	 * @param parts
	 *            Concanates array of string into one with spaces in between
	 *            them
	 * @return
	 */
	private String concat(String[] parts) {
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for (int i = 0; i < parts.length - 1; i++) {
			sb.append(parts[i]);
			sb.append(' ');
			flag = true;
		}
		if (!flag)
			return parts[0];
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

}
