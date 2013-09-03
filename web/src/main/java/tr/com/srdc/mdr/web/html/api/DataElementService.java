package tr.com.srdc.mdr.web.html.api;

import java.util.List;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.MDRConstants;
import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.composite.ClassificationSchemeItem;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.core.util.MappingRelation;
import tr.com.srdc.mdr.web.html.models.DataElementModel;
import tr.com.srdc.mdr.web.html.models.ExtractionSpecificationModel;
import tr.com.srdc.mdr.web.html.models.MappingModel;
import tr.com.srdc.mdr.web.html.util.WebUtil;

@Path("/de/{deid}")
public class DataElementService {

	private static final Logger logger = LoggerFactory
			.getLogger(DataElementService.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataElement(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("deid") String dataElementID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		DataElement de = repository.getDataElement(dataElementID);
		if (de == null) {
			return Response.noContent().build();
		}
		return Response.ok(new DataElementModel(de)).build();
	}

	@DELETE
	public Response deleteDataElement(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("deid") String dataElementID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		DataElement de = repository.getDataElement(dataElementID);

		// TODO which relations of Data Element is to be deleted, decide on that
		try {
			de.delete();
		} catch (Exception e) {
			return Response.serverError().build();
		}
		return Response.ok().build();

	}

	@POST
	@Path("/mapping")
	public Response addMapping(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("deid") String dataElementID, MappingModel mapping) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		DataElement de = repository.getDataElement(dataElementID);
		
		DataElement mappedDE = repository.getDataElement(mapping.getTermUUID());

		MappingRelation relation = new MappingRelation();
		relation.setSubjectOID(MDRConstants.getOIDFromContentModel(de.getContext().getName()));
		relation.setRelationType(mapping.getMatchType());
		relation.setObjectOID(mapping.getTermSystemOID());
		de.addMapping(relation, mappedDE);
		logger.debug("{} --> {} mapping is added", dataElementID,
				mapping.getTermUUID());

		return Response.ok().build();
	}

	@DELETE
	@Path("/mapping")
	public Response deleteMApping(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("deid") String dataElementID, MappingModel mapping) {

		// TODO mapping deleteion should be implemented
		return Response.ok().build();
	}

	@POST
	@Path("/extractionspecification")
	public Response addExtractionSpecification(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("deid") String dataElementID,
			ExtractionSpecificationModel extractionSpecification) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		DataElement de = repository.getDataElement(dataElementID);

		de.addExtractionSpecification(extractionSpecification.getModelOID(),
				extractionSpecification.getType(),
				extractionSpecification.getValue());
		logger.debug("{} is added to {} as Extraction Specification",
				extractionSpecification.getValue(), dataElementID);

		return Response.ok().build();
	}

	@DELETE
	@Path("/extractionspecification")
	public Response deleteExtractionSpecification(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("deid") String dataElementID,
			ExtractionSpecificationModel extractionSpecification) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		DataElement de = repository.getDataElement(dataElementID);

		List<ClassificationSchemeItem> extractionSpecifications = de
				.getExtractionSpecifications();
		ClassificationSchemeItem csi2Delete = null;
		// if all three values are equal, then that is the one looked for
		for (ClassificationSchemeItem csi : extractionSpecifications) {
			if (csi.getItemValue().equals(extractionSpecification.getValue())
					&& csi.getTypeName().equals(
							extractionSpecification.getType())
					&& csi.getClassificationScheme().getUniqueID()
							.equals(extractionSpecification.getModelOID())) {
				csi2Delete = csi;
				break;
			}
		}

		if (csi2Delete != null) {
			csi2Delete.delete();
		}

		logger.debug("Extraction specification {} is deleted from {}",
				extractionSpecification.getValue(), de.getName());

		return Response.ok().build();
	}
}
