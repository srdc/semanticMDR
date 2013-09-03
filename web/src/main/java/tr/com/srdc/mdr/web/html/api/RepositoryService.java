package tr.com.srdc.mdr.web.html.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
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

import eu.salusproject.securityprivacy.authentication.User;

import tr.com.srdc.mdr.core.MDRConstants;
import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.Context;
import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.ai.EnumeratedConceptualDomain;
import tr.com.srdc.mdr.core.api.composite.Contact;
import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.api.composite.Organization;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.core.store.query.ResourceQueryFactory.TextSearchType;
import tr.com.srdc.mdr.web.html.models.ConceptualDomainModel;
import tr.com.srdc.mdr.web.html.models.ContextModel;
import tr.com.srdc.mdr.web.html.models.DataElementModel;
import tr.com.srdc.mdr.web.html.models.DataTypeModel;
import tr.com.srdc.mdr.web.html.util.WebUtil;

@Path("/repository")
public class RepositoryService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listContexts(
			@CookieParam(AuthenticationService.SID) String sessionID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		List<Context> contexts = repository.getContexts();
		List<ContextModel> contextList = new ArrayList<ContextModel>();
		for (Context context : contexts) {
			contextList.add(new ContextModel(context));
		}
		return Response.ok(contextList).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createContext(
			@CookieParam(AuthenticationService.SID) String sessionID,
			ContextModel contextModel) {
		User user = WebUtil.getUser(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();

		Contact contact = repository.getContact(user.getFullName());
		Organization organization = repository.getOrganization(user
				.getAffiliation().getName());

		Context context = repository.createContext(contextModel.getName(),
				contextModel.getDefinition(), contact, organization);
		return Response.ok(new ContextModel(context)).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateContext(
			@CookieParam(AuthenticationService.SID) String sessionID,
			ContextModel contextModel) {
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextModel.getId());
		context.setName(contextModel.getName());
		context.setDefinition(contextModel.getDefinition());
		context.setDescription(contextModel.getDefinition());
		return Response.ok().build();
	}

	@DELETE
	@Path("/{contextid}")
	public Response deleteContext(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("contextid") String contextID) {
		Repository repository = RepositoryManager.getInstance().getRepository();
		Context context = repository.getContext(contextID);
		context.delete();

		return Response.ok().build();
	}

	@Path("/search")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchDataElements(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@QueryParam("q") String keyword,
			@QueryParam("limit") @DefaultValue("10") Integer limit,
			@QueryParam("offset") @DefaultValue("0") Integer offset) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		List<DataElement> deList = repository.searchDataElement(keyword,
				TextSearchType.WildCard, limit, offset);
		List<DataElementModel> deModelList = new ArrayList<DataElementModel>();
		for (DataElement de : deList) {
			deModelList.add(new DataElementModel(de));
		}
		return Response.ok(deModelList).build();
	}

	@Path("/search")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getNumberOfDataElementSearch(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@QueryParam("q") String keyword) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		int size = repository.getNumberOfDataElementSearch(keyword,
				TextSearchType.WildCard);
		return Response.ok(String.valueOf(size)).build();
	}

	@GET
	@Path("/cd")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listConceptualDomains(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@QueryParam("limit") @DefaultValue("10") Integer limit,
			@QueryParam("offset") @DefaultValue("0") Integer offset) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		List<ConceptualDomain> cdList = repository.getConceptualDomains(limit,
				offset);

		List<ConceptualDomainModel> cdModelList = new ArrayList<ConceptualDomainModel>();
		for (ConceptualDomain cd : cdList) {
			if (cd instanceof EnumeratedConceptualDomain) {
				cdModelList.add(new ConceptualDomainModel(cd, true));
			} else {
				cdModelList.add(new ConceptualDomainModel(cd, false));
			}
		}

		return Response.ok(cdModelList).build();
	}

	@GET
	@Path("/cd")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getNumberOfConceptualDomains(
			@CookieParam(AuthenticationService.SID) String sessionID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();

		int size = repository.getNumberOfConceptualDomains();
		return Response.ok(String.valueOf(size)).build();
	}

	@POST
	@Path("/cd")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createConceptualDomain(
			@CookieParam(AuthenticationService.SID) String sessionID,
			ConceptualDomainModel conceptualDomainModel) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();

		// TODO: get the oid of the conceptual domain if it is enumerated

		ConceptualDomain cd = null;
		if (conceptualDomainModel.isEnumerated()) {
			cd = repository.createEnumeratedConceptualDomain(
					conceptualDomainModel.getName(), null,
					conceptualDomainModel.getDefinition(),
					conceptualDomainModel.getDimensionality());
			return Response.ok(new ConceptualDomainModel(cd, true)).build();
		} else {
			cd = repository.createNonEnumeratedConceptualDomain(
					conceptualDomainModel.getName(),
					conceptualDomainModel.getDefinition(),
					conceptualDomainModel.getDimensionality());
			return Response.ok(new ConceptualDomainModel(cd, false)).build();
		}

	}

	@PUT
	@Path("/cd")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateConceptualDomain(
			@CookieParam(AuthenticationService.SID) String sessionID,
			ConceptualDomainModel cdModel) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();

		ConceptualDomain conceptualDomain = repository
				.getConceptualDomain(cdModel.getId());
		// Currently, only name, definition and dimensionality can be updated
		conceptualDomain.setName(cdModel.getName());
		conceptualDomain.setDefinition(cdModel.getDefinition());
		conceptualDomain.setDimensionality(cdModel.getDimensionality());
		return Response.ok().build();
	}

	@GET
	@Path("/cd/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchConceptualDomain(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@QueryParam("q") String keyword) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();

		List<ConceptualDomain> cdList = repository.searchConceptualDomain(
				keyword, TextSearchType.WildCard);

		List<ConceptualDomainModel> cdModelList = new ArrayList<ConceptualDomainModel>();
		for (ConceptualDomain cd : cdList) {
			if (cd instanceof EnumeratedConceptualDomain) {
				cdModelList.add(new ConceptualDomainModel(cd, true));
			} else {
				cdModelList.add(new ConceptualDomainModel(cd, false));
			}
		}

		return Response.ok(cdModelList).build();
	}

	@GET
	@Path("/dt")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listDataTypes(
			@CookieParam(AuthenticationService.SID) String sessionID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();

		List<Datatype> dtList = repository.getDataTypes();

		List<DataTypeModel> dtModelList = new ArrayList<DataTypeModel>();
		for (Datatype de : dtList) {
			dtModelList.add(new DataTypeModel(de));
		}
		return Response.ok(dtModelList).build();
	}

	@GET
	@Path("/mapping/relations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMappingRelations(
			@CookieParam(AuthenticationService.SID) String sessionID) {
		WebUtil.checkUserSession(sessionID);
		List<String> mappingRelations = MDRConstants.getMappingRelations();
		return Response.ok(mappingRelations).build();
	}

}
