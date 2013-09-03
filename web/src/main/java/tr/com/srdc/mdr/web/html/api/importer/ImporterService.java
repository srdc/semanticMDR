package tr.com.srdc.mdr.web.html.api.importer;

import java.util.List;

import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.MDRConstants;
import tr.com.srdc.mdr.core.api.ai.Context;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.importers.api.ContentModelImporter;
import tr.com.srdc.mdr.importers.api.impl.ImporterException;
import tr.com.srdc.mdr.importers.ddl.omop.OMOPImporter;
import tr.com.srdc.mdr.importers.mapping.MappingsImporter;
import tr.com.srdc.mdr.importers.ontology.bridg.BRIDGImporter;
import tr.com.srdc.mdr.importers.ontology.cdisc.cdash.CDASHImporter;
import tr.com.srdc.mdr.importers.ontology.cdisc.sdtm.SDTMImporter;
import tr.com.srdc.mdr.importers.ontology.hitsp.HITSPC154Importer;
import tr.com.srdc.mdr.importers.ontology.saluscim.SALUSCIMImporter;
import tr.com.srdc.mdr.web.html.api.AuthenticationService;
import tr.com.srdc.mdr.web.html.util.WebUtil;

import com.hp.hpl.jena.shared.AlreadyExistsException;

import eu.salusproject.securityprivacy.authentication.User;

@Path("/importer/{importertype}")
public class ImporterService {

	private static final Logger logger = LoggerFactory
			.getLogger(ImporterService.class);

	@POST
	public Response importModel(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("importertype") String importerType) {
		User user = WebUtil.getUser(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();

		ContactResource contactResource = repository.getMDRDatabase()
				.getQueryFactory().getContactResource(user.getFullName());
		OrganizationResource organizationResource = repository.getMDRDatabase()
				.getQueryFactory()
				.getOrganizationResource(user.getAffiliation().getName());

		ContentModelImporter importer = null;

		if (importerType.equals("all")) {

			try {
				List<Context> contextList = repository.getContexts();
				boolean omopImported = false;
				boolean sdtmImported = false;
				boolean cdashImported = false;
				boolean hitspImported = false;
				boolean bridgImported = false;
				boolean salusCimImported = false;
				for (Context c : contextList) {
					if (c.getName().equals(MDRConstants.CONTENT_MODEL_OMOP)) {
						logger.error("Context already imported: {} ",
								MDRConstants.CONTENT_MODEL_OMOP);
						omopImported = true;
					}
					if (c.getName().equals(MDRConstants.CONTENT_MODEL_BRIDG)) {
						logger.error("Context already imported:  {} ",
								MDRConstants.CONTENT_MODEL_BRIDG);
						bridgImported = true;
					}
					if (c.getName().equals(MDRConstants.CONTENT_MODEL_CDASH)) {
						logger.error("Context already imported:  {} ",
								MDRConstants.CONTENT_MODEL_CDASH);
						cdashImported = true;
					}
					if (c.getName().equals(
							MDRConstants.CONTENT_MODEL_HITSP_C154)) {
						logger.error("Context already imported:  {} ",
								MDRConstants.CONTENT_MODEL_HITSP_C154);
						hitspImported = true;
					}
					if (c.getName().equals(MDRConstants.CONTENT_MODEL_SDTM)) {
						logger.error("Context already imported:  {} ",
								MDRConstants.CONTENT_MODEL_SDTM);
						sdtmImported = true;
					}
					if (c.getName()
							.equals(MDRConstants.CONTENT_MODEL_SALUS_CIM)) {
						logger.error("Context already imported:  {} ",
								MDRConstants.CONTENT_MODEL_SALUS_CIM);
						salusCimImported = true;
					}
				}

				if (!omopImported) {
					new OMOPImporter(repository, contactResource,
							organizationResource).run();
				}
				if (!sdtmImported) {
					new SDTMImporter(repository, contactResource,
							organizationResource).run();
				}
				if (!cdashImported) {
					new CDASHImporter(repository, contactResource,
							organizationResource).run();
				}
				if (!hitspImported) {
					new HITSPC154Importer(repository, contactResource,
							organizationResource).run();
				}
				if (!bridgImported) {
					new BRIDGImporter(repository, contactResource,
							organizationResource).run();
				}
				if (!salusCimImported) {
					new SALUSCIMImporter(repository, contactResource,
							organizationResource).run();
				}
				new MappingsImporter(repository, contactResource,
						organizationResource).run();
			} catch (AlreadyExistsException e) {
				logger.error("Context already exists");
				return Response.status(455).build();
			} catch (ImporterException e) {
				logger.error("Resources cannot be created!", e);
				return Response.serverError().build();
			}
			return Response.ok().build();

		} else if (importerType.equals("omop")) {
			importer = new OMOPImporter(repository, contactResource,
					organizationResource);
		} else if (importerType.equals("sdtm")) {
			importer = new SDTMImporter(repository, contactResource,
					organizationResource);
		} else if (importerType.equals("cdash")) {
			importer = new CDASHImporter(repository, contactResource,
					organizationResource);
		} else if (importerType.equals("hitsp")) {
			importer = new HITSPC154Importer(repository, contactResource,
					organizationResource);
		} else if (importerType.equals("bridg")) {
			importer = new BRIDGImporter(repository, contactResource,
					organizationResource);
		} else if (importerType.equals("mappings")) {
			importer = new MappingsImporter(repository, contactResource,
					organizationResource);
		} else if (importerType.equals("saluscim")) {
			importer = new SALUSCIMImporter(repository, contactResource,
					organizationResource);
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}

		try {
			importer.run();
		} catch (AlreadyExistsException e) {
			logger.error("Context already exists");
			return Response.status(455).build();
		} catch (ImporterException e) {
			logger.error("Resources cannot be created!", e);
			return Response.serverError().build();
		}
		return Response.ok().build();
	}

}
