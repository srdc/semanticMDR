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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.Context;
import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.ai.DataElementConcept;
import tr.com.srdc.mdr.core.api.ai.EnumeratedValueDomain;
import tr.com.srdc.mdr.core.api.ai.NonEnumeratedValueDomain;
import tr.com.srdc.mdr.core.api.ai.ObjectClass;
import tr.com.srdc.mdr.core.api.ai.Property;
import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.core.store.query.ResourceQueryFactory.TextSearchType;
import tr.com.srdc.mdr.web.html.models.ContextModel;
import tr.com.srdc.mdr.web.html.models.DataElementModel;
import tr.com.srdc.mdr.web.html.models.ObjectClassModel;
import tr.com.srdc.mdr.web.html.models.PropertyModel;
import tr.com.srdc.mdr.web.html.models.ValueDomainModel;
import tr.com.srdc.mdr.web.html.util.WebUtil;

/**
 * @author anil
 * 
 */

@Path("/context/{contextid}")
public class ContextService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContext(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();

		Context context = repository.getContext(contextID);
		return Response.ok(new ContextModel(context)).build();
	}

	@GET
	@Path("/oc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listObjectClasses(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID,
			@QueryParam("limit") @DefaultValue("10") Integer limit,
			@QueryParam("offset") @DefaultValue("0") Integer offset) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);
		List<ObjectClass> ocList = context.getObjectClasses(limit, offset);
		List<ObjectClassModel> ocModelList = new ArrayList<ObjectClassModel>();
		for (ObjectClass oc : ocList) {
			ocModelList.add(new ObjectClassModel(oc));
		}
		return Response.ok(ocModelList).build();
	}

	@GET
	@Path("/oc")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getNumberOfObjectClasses(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);

		int size = context.getNumberOfObjectClasses();
		return Response.ok(String.valueOf(size)).build();
	}

	@POST
	@Path("/oc")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createObjectClass(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID,
			ObjectClassModel objectClassModel) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();

		Context context = repository.getContext(contextID);
		ObjectClass objectClass = context.createObjectClass(
				objectClassModel.getName(), objectClassModel.getDefinition());
		return Response.ok(new ObjectClassModel(objectClass)).build();

	}

	@PUT
	@Path("/oc")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateObjectClass(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID,
			ObjectClassModel objectClassModel) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);
		ObjectClass objectClass = context.getObjectClass(objectClassModel
				.getId());
		// Currently, only name and definition can be updated
		objectClass.setName(objectClassModel.getName());
		objectClass.setDefinition(objectClassModel.getDefinition());
		return Response.ok().build();
	}

	@GET
	@Path("/oc/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchObjectClasses(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID,
			@QueryParam("q") String keyword) {
		// TODO Currently, we accept a single keyword as the query string

		return null;
	}

	@GET
	@Path("/property")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listProperties(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);

		List<Property> properties = context.getProperties();
		List<PropertyModel> propertyList = new ArrayList<PropertyModel>();
		for (Property prop : properties) {
			propertyList.add(new PropertyModel(prop));
		}
		return Response.ok(propertyList).build();
	}

	@PUT
	@Path("/property")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProperty(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID,
			PropertyModel propertyModel) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();

		Context context = repository.getContext(contextID);
		Property property = context.getProperty(propertyModel.getId());
		// Currently, only name and definition can be updated
		property.setName(propertyModel.getName());
		property.setDefinition(propertyModel.getDefinition());
		return Response.ok().build();
	}

	@GET
	@Path("/property/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchProperty(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID,
			@QueryParam("q") String keyword) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);
		List<Property> propertyList = context.searchProperty(keyword,
				TextSearchType.WildCard);
		List<PropertyModel> pList = new ArrayList<PropertyModel>();
		for (Property p : propertyList) {
			pList.add(new PropertyModel(p));
		}
		return Response.ok(pList).build();
	}

	@GET
	@Path("/de")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listDataElements(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID,
			@QueryParam("limit") @DefaultValue("10") Integer limit,
			@QueryParam("offset") @DefaultValue("0") Integer offset) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);
		List<DataElement> dataElements = context.getDataElements(limit, offset);
		List<DataElementModel> dataElementList = new ArrayList<DataElementModel>();
		for (DataElement de : dataElements) {
			dataElementList.add(new DataElementModel(de));
		}
		return Response.ok(dataElementList).build();
	}

	@GET
	@Path("/de")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getNumberOfDataElements(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);

		int size = context.getNumberOfDataElements();
		return Response.ok(String.valueOf(size)).build();
	}

	@POST
	@Path("/de")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createDataElement(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID, DataElementModel deModel) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);

		// WARN ValueDomain and DataElementConcept's should exist in repository
		// before calling this service
		DataElementConcept dec = context.getDataElementConcept(deModel
				.getDataElementConceptID());
		ConceptualDomain conceptualDomain = repository.getConceptualDomain(dec
				.asMDRResource()
				.getHavingDataElementConceptConceptualDomainRelationship()
				.getUniqueID());
		ValueDomain vd = conceptualDomain.getValueDomain(deModel
				.getValueDomainID());
		DataElement de = context.createDataElement(deModel.getName(),
				deModel.getDefinition(), dec, vd);

		return Response.ok(new DataElementModel(de)).build();
	}

	@PUT
	@Path("/de")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDataElement(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID, DataElementModel deModel) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		DataElement de = repository.getDataElement(deModel.getId());
		// TODO which properties of DataElement should be updatable, just name
		// and definition is updateable
		de.setDefinition(deModel.getDefinition());
		de.setName(deModel.getName());
		return Response.ok().build();
	}

	@GET
	@Path("/de/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchDataElements(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID,
			@QueryParam("q") String keyword,
			@QueryParam("limit") @DefaultValue("10") Integer limit,
			@QueryParam("offset") @DefaultValue("0") Integer offset) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);
		List<DataElement> deList = context.searchDataElement(keyword,
				TextSearchType.WildCard, limit, offset);
		List<DataElementModel> deModelList = new ArrayList<DataElementModel>();
		for (DataElement de : deList) {
			deModelList.add(new DataElementModel(de));
		}
		return Response.ok(deModelList).build();
	}

	@GET
	@Path("/de/search")
	@Produces(MediaType.TEXT_PLAIN)
	public Response searchDataElements(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID,
			@QueryParam("q") String keyword) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);
		int size = context.getNumberOfDataElementSearch(keyword,
				TextSearchType.WildCard);
		return Response.ok(String.valueOf(size)).build();
	}

	@GET
	@Path("/vd")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listValueDomains(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID,
			@QueryParam("limit") @DefaultValue("10") Integer limit,
			@QueryParam("offset") @DefaultValue("0") Integer offset) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);
		List<ValueDomain> vdList = context.getValueDomains(limit, offset);

		List<ValueDomainModel> vdModelList = new ArrayList<ValueDomainModel>();
		for (ValueDomain vd : vdList) {
			if (vd instanceof EnumeratedValueDomain) {
				vdModelList.add(new ValueDomainModel(vd, true));
			} else if (vd instanceof NonEnumeratedValueDomain) {
				vdModelList.add(new ValueDomainModel(vd, false));
			}
		}

		return Response.ok(vdModelList).build();
	}

	@GET
	@Path("/vd/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchValueDomains(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID,
			@QueryParam("q") String keyword) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);

		List<ValueDomain> vdList = context.searchValueDomain(keyword,
				TextSearchType.WildCard);
		List<ValueDomainModel> vdModelList = new ArrayList<ValueDomainModel>();
		for (ValueDomain vd : vdList) {
			if (vd instanceof EnumeratedValueDomain) {
				vdModelList.add(new ValueDomainModel(vd, true));
			} else {
				vdModelList.add(new ValueDomainModel(vd, false));
			}
		}

		return Response.ok(vdModelList).build();
	}
}
