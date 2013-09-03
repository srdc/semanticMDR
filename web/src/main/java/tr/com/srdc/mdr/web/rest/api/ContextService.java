package tr.com.srdc.mdr.web.rest.api;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.web.html.util.WebUtil;
import tr.com.srdc.mdr.web.rest.api.util.GraphStream;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

@Path("/context")
public class ContextService {

	private static final Logger logger = LoggerFactory
			.getLogger(ContextService.class);

	private static final String QUERY_FILE_GET_ALL_CONTEXTS = "../web/src/main/resources/rest/resources-context-all.rq";
	private static final String QUERY_FILE_GET_ALL_FROM_CONTEXT = "../web/src/main/resources/rest/resources-dataelement-all-from-context.rq";

	private GraphStream graphStream = new GraphStream();

	@GET
	@Path("/all")
	@Produces({ WebUtil.MEDIA_TYPE_APPLICATION_NTRIPLE,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFJSON,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFXML, MediaType.TEXT_PLAIN,
			WebUtil.MEDIA_TYPE_TEXT_N3, WebUtil.MEDIA_TYPE_TEXT_TURTLE })
	public Response getAllContexts(@Context Request request) {

		Variant variant = request.selectVariant(WebUtil.VARIANTS);
		MediaType mediaType = variant.getMediaType();

		Repository repository = RepositoryManager.getInstance().getRepository();
		OntModel ontModel = repository.getMDRDatabase().getOntModel();

		String queryString;
		File file = new File(QUERY_FILE_GET_ALL_CONTEXTS);
		try {
			queryString = FileUtils.readFileToString(file);
		} catch (IOException e) {
			logger.error("File with context serialization query could not be found ");
			return Response.serverError().build();
		}

		ParameterizedSparqlString query = new ParameterizedSparqlString(
				queryString);
		QueryExecution qe = QueryExecutionFactory.create(query.asQuery(),
				ontModel);
		Model resultModel = qe.execConstruct();

		graphStream.setModel(resultModel);
		graphStream.setLanguage(WebUtil.getSerializationLanguage(mediaType
				.toString()));

		return Response.ok(graphStream).build();
	}

	@GET
	@Path("/{contextid}/de")
	@Produces({ WebUtil.MEDIA_TYPE_APPLICATION_NTRIPLE,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFJSON,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFXML, MediaType.TEXT_PLAIN,
			WebUtil.MEDIA_TYPE_TEXT_N3, WebUtil.MEDIA_TYPE_TEXT_TURTLE })
	public Response getDataElements(@PathParam("contextid") String contextId,
			@Context Request request) {

		Variant variant = request.selectVariant(WebUtil.VARIANTS);
		MediaType mediaType = variant.getMediaType();

		Repository repository = RepositoryManager.getInstance().getRepository();
		OntModel ontModel = repository.getMDRDatabase().getOntModel();

		String queryString;
		File file = new File(QUERY_FILE_GET_ALL_FROM_CONTEXT);
		try {
			queryString = FileUtils.readFileToString(file);
		} catch (IOException e) {
			logger.error("File with context serialization query could not be found ");
			return Response.serverError().build();
		}

		ParameterizedSparqlString query = new ParameterizedSparqlString(
				queryString);
		query.setLiteral("uuid", ResourceFactory.createTypedLiteral(contextId));
		QueryExecution qe = QueryExecutionFactory.create(query.asQuery(),
				ontModel);
		Model resultModel = qe.execConstruct();

		graphStream.setModel(resultModel);
		graphStream.setLanguage(WebUtil.getSerializationLanguage(mediaType
				.toString()));

		return Response.ok(graphStream).build();
	}
}
