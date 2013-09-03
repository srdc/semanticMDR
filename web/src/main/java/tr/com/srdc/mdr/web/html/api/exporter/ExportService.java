/**
 *
 */
package tr.com.srdc.mdr.web.html.api.exporter;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.web.html.util.WebUtil;
import tr.com.srdc.mdr.web.rest.api.util.GraphStream;

import com.hp.hpl.jena.ontology.OntModel;

/**
 * @author Alper
 * 
 */
@Path("/exporter/{exporterType}")
public class ExportService {
	private static final Logger logger = LoggerFactory
			.getLogger(ExportService.class);
	private final GraphStream graphStream = new GraphStream();

	private final Map<String, String> fileNames = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put(MediaType.TEXT_PLAIN, "export.rdf");
			put(WebUtil.MEDIA_TYPE_APPLICATION_NTRIPLE, "export.nt");
			put(WebUtil.MEDIA_TYPE_APPLICATION_RDFJSON, "export.rj");
			put(WebUtil.MEDIA_TYPE_APPLICATION_RDFXML, "export.rdf");
			put(WebUtil.MEDIA_TYPE_APPLICATION_RDFXML_ABBREV, "export.rdf");
			put(WebUtil.MEDIA_TYPE_TEXT_N3, "export.n3");
			put(WebUtil.MEDIA_TYPE_TEXT_TURTLE, "export.ttl");
		}
	};

	@GET
	public Response export(@Context Request request,
			@PathParam("exporterType") String exporterType) {

		logger.info("Exporting database");

		Repository repository = RepositoryManager.getInstance().getRepository();
		OntModel ontModel = repository.getMDRDatabase().getOntModel();

		graphStream.setModel(ontModel);
		graphStream.setLanguage(WebUtil.getSerializationLanguage(exporterType));

		logger.info("Database is exported");

		String fileName = fileNames.get(exporterType);
		if (fileName == null) {
			fileName = "export.rdf";
		}

		return Response
				.ok(graphStream)
				.header("Content-Disposition",
						"attachment; filename=" + fileName).build();
	}
}
