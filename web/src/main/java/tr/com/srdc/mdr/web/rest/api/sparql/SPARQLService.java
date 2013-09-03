package tr.com.srdc.mdr.web.rest.api.sparql;

import java.net.URLDecoder;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Variant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.web.html.util.WebUtil;
import tr.com.srdc.mdr.web.rest.api.util.GraphStream;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryException;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;

@Path("/sparql")
public class SPARQLService {

	private static final Logger logger = LoggerFactory
			.getLogger(SPARQLService.class);

	private GraphStream graphStream = new GraphStream();

	/**
	 * Runs SPARQL queries
	 * 
	 * @param type
	 *            Result Format: N3, N-TRIPLE, RDF/XML, RDF/XML-ABBREV, TURTLE
	 * @param query
	 *            Sparql for the query
	 * @return
	 */
	@PUT
	@Produces({ WebUtil.MEDIA_TYPE_APPLICATION_NTRIPLE,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFJSON,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFXML, MediaType.TEXT_PLAIN,
			WebUtil.MEDIA_TYPE_TEXT_N3, WebUtil.MEDIA_TYPE_TEXT_TURTLE })
	public Response query(String query, @Context Request request) {

		Variant variant = request.selectVariant(WebUtil.VARIANTS);
		MediaType mediaType = variant.getMediaType();

		Repository repository = RepositoryManager.getInstance().getRepository();
		OntModel ontModel = repository.getMDRDatabase().getOntModel();
		Query q = null;

		try {
			q = QueryFactory.create(query);
		} catch (QueryException qexc) {
			logger.error("Error during the creation of the SPARQL query", qexc);
			return Response.ok(qexc.getMessage()).build();
		}

		QueryExecution qexec = QueryExecutionFactory.create(q, ontModel);
		Model resultModel = null;
		if (q.isSelectType()) {
			ResultSet resultSet = qexec.execSelect();
			resultModel = ResultSetFormatter.toModel(resultSet);
		} else if (q.isDescribeType()) {
			resultModel = qexec.execDescribe();
		} else if (q.isConstructType()) {
			resultModel = qexec.execConstruct();
		} else if (q.isAskType()) {
			boolean output = qexec.execAsk();
			qexec.close();
			return Response.ok(output).build();
		}
		qexec.close();

		graphStream.setModel(resultModel);
		graphStream.setLanguage(WebUtil.getSerializationLanguage(mediaType
				.toString()));

		return Response.ok(graphStream).build();
	}

	/**
	 * Runs SPARQL queries
	 * 
	 * @param type
	 *            Result Format: N3, N-TRIPLE, RDF/XML, RDF/XML-ABBREV, TURTLE
	 * @param query
	 *            Sparql for the query
	 * @return
	 */
	@GET
	@Produces({ WebUtil.MEDIA_TYPE_APPLICATION_NTRIPLE,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFJSON,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFXML, MediaType.TEXT_PLAIN,
			WebUtil.MEDIA_TYPE_TEXT_N3, WebUtil.MEDIA_TYPE_TEXT_TURTLE })
	public Response selectQuery(@QueryParam("sparql") String query,
			@Context Request request) {

		Variant variant = request.selectVariant(WebUtil.VARIANTS);
		MediaType mediaType = variant.getMediaType();

		Repository repository = RepositoryManager.getInstance().getRepository();
		OntModel ontModel = repository.getMDRDatabase().getOntModel();
		Query q = null;

		try {
			query = URLDecoder.decode(query, "UTF-8");
			q = QueryFactory.create(query);
		} catch (Exception exc) {
			logger.error("Error during the creation of the SPARQL query", exc);
			return Response.serverError().build();
		}

		QueryExecution qexec = QueryExecutionFactory.create(q, ontModel);
		Model resultModel = null;
		if (q.isSelectType()) {
			ResultSet resultSet = qexec.execSelect();
			resultModel = ResultSetFormatter.toModel(resultSet);
		} else {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		qexec.close();

		graphStream.setModel(resultModel);
		graphStream.setLanguage(WebUtil.getSerializationLanguage(mediaType
				.toString()));

		return Response.ok(graphStream).build();
	}
}
