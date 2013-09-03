package tr.com.srdc.mdr.web.rest.api.serialize;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
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
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

@Path("/serialize")
public class SerializationService {

	private static final Logger logger = LoggerFactory
			.getLogger(SerializationService.class);

	private GraphStream graphStream = new GraphStream();

	@GET
	@Path("/dex")
	@Produces({ WebUtil.MEDIA_TYPE_APPLICATION_NTRIPLE,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFJSON,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFXML, MediaType.TEXT_PLAIN,
			WebUtil.MEDIA_TYPE_TEXT_N3, WebUtil.MEDIA_TYPE_TEXT_TURTLE })
	public Response dexSerialization(@QueryParam("id") String uuid,
			@Context Request request) {

		Variant variant = request.selectVariant(WebUtil.VARIANTS);
		MediaType mediaType = variant.getMediaType();

		Repository repository = RepositoryManager.getInstance().getRepository();
		OntModel ontModel = repository.getMDRDatabase().getOntModel();
		String queryString;

		File file = new File(
				"../web/src/main/resources/rest/dex-serialization-query.rq");
		try {
			queryString = FileUtils.readFileToString(file);
		} catch (IOException e) {
			logger.error("File with dex serialization query could not be found ");
			return Response.serverError().build();
		}

		ParameterizedSparqlString query = new ParameterizedSparqlString(
				queryString);
		query.setLiteral("uuid", ResourceFactory.createTypedLiteral(uuid));
		QueryExecution qe = QueryExecutionFactory.create(query.asQuery(),
				ontModel);
		Model resultModel = qe.execConstruct();

		graphStream.setModel(resultModel);
		graphStream.setLanguage(WebUtil.getSerializationLanguage(mediaType
				.toString()));

		return Response.ok(graphStream).build();
	}

	/**
	 * 
	 * @param type
	 *            outputFormat "N3", "N-TRIPLE", "RDF/XML-ABBREV" or
	 *            "TURTLE"default: "RDF/XML".
	 * @param uuid
	 * @return
	 */
	@GET
	@Path("/graph")
	@Produces({ WebUtil.MEDIA_TYPE_APPLICATION_NTRIPLE,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFJSON,
			WebUtil.MEDIA_TYPE_APPLICATION_RDFXML, MediaType.TEXT_PLAIN,
			WebUtil.MEDIA_TYPE_TEXT_N3, WebUtil.MEDIA_TYPE_TEXT_TURTLE })
	public Response serialize(@QueryParam("id") String uuid, @QueryParam("uri") String uri,
			@Context Request request) {

		Variant variant = request.selectVariant(WebUtil.VARIANTS);
		MediaType mediaType = variant.getMediaType();

		Repository repository = RepositoryManager.getInstance().getRepository();
		OntModel ontModel = repository.getMDRDatabase().getOntModel();
		
		String query = "";
		if(uuid != null) {
			// get the uri of the resource
			query = "prefix mdr:     <http://www.salusproject.eu/iso11179-3/mdr#> "
					+ "prefix xsd:     <http://www.w3.org/2001/XMLSchema#> "
					+ "SELECT ?resource WHERE { " + "?resource ?op ?ar . "
					+ "?ar mdr:administeredItemIdentifier ?ii . "
					+ "?ii mdr:dataIdentifier \"" + uuid + "\"^^xsd:string. " + "}";

			logger.debug("Query execution: {}", query);
			Query q = QueryFactory.create(query);
			QueryExecution qe = QueryExecutionFactory.create(q, ontModel);
			ResultSet rs = qe.execSelect();

			while (rs.hasNext()) {
				QuerySolution qs = rs.next();
				uri = qs.getResource("resource").getURI();
			}
		}

		if(uri == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		
		// use the uri to construct the model and return its serialization.
		query = "CONSTRUCT WHERE { <" + uri + "> ?p ?o .}";
		Query q2 = QueryFactory.create(query);
		QueryExecution qe2 = QueryExecutionFactory.create(q2, ontModel);
		Model outModel = qe2.execConstruct();

		graphStream.setModel(outModel);
		graphStream.setLanguage(WebUtil.getSerializationLanguage(mediaType
				.toString()));

		return Response.ok(graphStream).build();
	}

}
