package tr.com.srdc.mdr.web.rest.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

@Path("/de")
public class DataElementService {
	private static final Logger logger = LoggerFactory
			.getLogger(DataElementService.class);

	private static final String QUERY_FILE_GET_EXTRACTIONS = "../web/src/main/resources/rest/resources-dataelement-get-extractions.rq";

	@GET
	@Path("/{deid}/es")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExtractionSpec(
			@QueryParam("specification-format") String specificationFormat,
			@QueryParam("content-model") String contentModel,
			@PathParam("deid") String deid) {
		Repository repository = RepositoryManager.getInstance().getRepository();
		OntModel ontModel = repository.getMDRDatabase().getOntModel();
		List<String> extractionSpecifications = new ArrayList<String>();

		if (specificationFormat == null || contentModel == null) {
			throw new WebApplicationException(Status.NOT_ACCEPTABLE);
		}

		File getExtractionFile = new File(QUERY_FILE_GET_EXTRACTIONS);
		String queryString = "";
		try {
			queryString = FileUtils.readFileToString(getExtractionFile);
		} catch (IOException e) {
			logger.error("File with context serialization query could not be found ");
			return Response.serverError().build();
		}
		ParameterizedSparqlString query = new ParameterizedSparqlString(
				queryString);
		query.setLiteral("uuid", ResourceFactory.createTypedLiteral(deid));
		query.setLiteral("specFormat",
				ResourceFactory.createTypedLiteral(specificationFormat));
		query.setLiteral("contentModel",
				ResourceFactory.createTypedLiteral(contentModel));
		QueryExecution qe = QueryExecutionFactory.create(query.asQuery(),
				ontModel);

		String spec = "";
		ResultSet rs = qe.execSelect();
		while (rs.hasNext()) {
			QuerySolution qs = rs.next();
			spec = qs.getLiteral("extractionSpec").getString();
			if (spec != null && !spec.equals("")) {
				extractionSpecifications.add(spec);
			}
		}

		return Response.ok(extractionSpecifications).build();
	}
}
