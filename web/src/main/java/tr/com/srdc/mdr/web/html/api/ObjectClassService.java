package tr.com.srdc.mdr.web.html.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.Context;
import tr.com.srdc.mdr.core.api.ai.DataElementConcept;
import tr.com.srdc.mdr.core.api.ai.ObjectClass;
import tr.com.srdc.mdr.core.api.ai.Property;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.web.html.models.DataElementConceptModel;
import tr.com.srdc.mdr.web.html.models.ObjectClassModel;
import tr.com.srdc.mdr.web.html.util.WebUtil;

@Path("/oc/{ocid}")
public class ObjectClassService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getObjectClass(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("ocid") String ocID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		ObjectClass objectClass = repository.getObjectClass(ocID);
		return Response.ok(new ObjectClassModel(objectClass)).build();
	}

	@GET
	@Path("/parent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getParentConcept(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("ocid") String ocID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		ObjectClass objectClass = repository.getObjectClass(ocID);
		ObjectClass parent = objectClass.getParentConcept();

		if (parent == null) {
			return Response.noContent().build();
		}

		return Response.ok(new ObjectClassModel(parent)).build();
	}

	@GET
	@Path("/subconcept")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubConcepts(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("ocid") String ocID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		if (Util.isNull(ocID)) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		ObjectClass objectClass = repository.getObjectClass(ocID);
		List<ObjectClassModel> ocModelList = new ArrayList<ObjectClassModel>();

		List<ObjectClass> subConceptList = objectClass.getSubConcepts();
		for (ObjectClass oc : subConceptList) {
			ocModelList.add(new ObjectClassModel(oc));
		}
		return Response.ok(ocModelList).build();
	}

	@GET
	@Path("/dec")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listDataElementConcepts(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("ocid") String objectClassID,
			@QueryParam("limit") @DefaultValue("10") Integer limit,
			@QueryParam("offset") @DefaultValue("0") Integer offset) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		ObjectClass objectClass = repository.getObjectClass(objectClassID);
		List<DataElementConcept> decList = objectClass.getDataElementConcepts(
				limit, offset);
		List<DataElementConceptModel> decModelList = new ArrayList<DataElementConceptModel>();
		for (DataElementConcept dec : decList) {
			decModelList.add(new DataElementConceptModel(dec.asMDRResource()));
		}

		return Response.ok(decModelList).build();
	}

	@GET
	@Path("/dec")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getNumberOfDataElementConcepts(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("ocid") String objectClassID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		ObjectClass objectClass = repository.getObjectClass(objectClassID);

		int size = objectClass.getNumberOfDataElementConcepts();
		return Response.ok(String.valueOf(size)).build();
	}

	@POST
	@Path("/dec")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createDataElementConcept(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("ocid") String objectClassID,
			DataElementConceptModel decModel) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(decModel.getContextID());
		ObjectClass oc = context.getObjectClass(objectClassID);
		ConceptualDomain cd = repository.getConceptualDomain(decModel
				.getConceptualDomainID());
		DataElementConcept dec = null;
		// if propertyID is not defined, than it means new property will be
		// created
		if (!Util.isNull(decModel.getPropertyID())) {
			Property property = context.getProperty(decModel.getPropertyID());
			dec = oc.createDataElementConcept(property, cd,
					decModel.getDefinition());
		} else {
			dec = oc.createDataElementConcept(decModel.getPropertyName(),
					decModel.getPropertyDefinition(), cd,
					decModel.getDefinition());
		}
		return Response.ok(new DataElementConceptModel(dec.asMDRResource()))
				.build();
	}

	@PUT
	@Path("/dec")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDataElementConcept(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("ocid") String objectClassID,
			DataElementConceptModel decModel) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(decModel.getContextID());
		ObjectClass oc = context.getObjectClass(objectClassID);
		DataElementConcept dec = oc.getDataElementConcept(decModel.getId());

		dec.setPropertyQualifier(decModel.getPropertyQualifier());
		dec.setObjectClassQualifier(dec.getObjectClassQualifier());
		dec.setDefinition(decModel.getDefinition());
		dec.setName(decModel.getName());
		// means property of object class is not changed
		if (dec.getProperty().getUniqueID().equals(decModel.getPropertyID())) {
			dec.getProperty().setName(decModel.getPropertyName());
			dec.getProperty().setDefinition(decModel.getPropertyDefinition());
		} else { // means new property is assigned for object class
			if (!Util.isNull(decModel.getPropertyID())) {
				// already existing property is assigned
				Property property = context.getProperty(decModel
						.getPropertyID());
				dec.setProperty(property);
			} else { // new property will be created and will be assigned
				dec.setProperty(decModel.getPropertyName(),
						decModel.getPropertyDefinition());
			}

		}
		return Response.ok().build();
	}
}
