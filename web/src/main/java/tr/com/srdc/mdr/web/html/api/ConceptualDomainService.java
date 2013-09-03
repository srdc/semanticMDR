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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.EnumeratedConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.EnumeratedValueDomain;
import tr.com.srdc.mdr.core.api.ai.NonEnumeratedConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.api.composite.PermissibleValue;
import tr.com.srdc.mdr.core.api.composite.ValueMeaning;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.core.model.iso11179.composite.DatatypeResource;
import tr.com.srdc.mdr.web.html.models.ConceptualDomainModel;
import tr.com.srdc.mdr.web.html.models.PermissibleValueModel;
import tr.com.srdc.mdr.web.html.models.ValueDomainModel;
import tr.com.srdc.mdr.web.html.models.ValueMeaningModel;
import tr.com.srdc.mdr.web.html.util.WebUtil;


@Path("/cd/{cid}")
public class ConceptualDomainService {
	private static final Logger logger = LoggerFactory
			.getLogger(ConceptualDomainService.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConceptualDomain(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("cid") String cid) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();

		ConceptualDomain cd = repository.getConceptualDomain(cid);
		if (cd instanceof EnumeratedConceptualDomain) {
			return Response.ok(new ConceptualDomainModel(cd, true)).build();
		} else {
			return Response.ok(new ConceptualDomainModel(cd, false)).build();
		}

	}

	@GET
	@Path("/vm")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listValueMeanings(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("cid") String conceptualDomainID,
			@QueryParam("limit") @DefaultValue("10") Integer limit,
			@QueryParam("offset") @DefaultValue("0") Integer offset) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		ConceptualDomain cd = repository
				.getConceptualDomain(conceptualDomainID);
		if (cd instanceof NonEnumeratedConceptualDomain) {
			logger.error(
					"{} is not EnumeratedConceptualDomain, so no ValueMeaning",
					conceptualDomainID);
			return Response.serverError().build();
		}
		EnumeratedConceptualDomain ecd = (EnumeratedConceptualDomain) cd;
		List<ValueMeaning> vmList;
		if (limit != null && offset != null) {
			vmList = ecd.getValueMeanings(limit, offset);
		} else {
			vmList = ecd.getValueMeanings();
		}
		List<ValueMeaningModel> vmModelList = new ArrayList<ValueMeaningModel>();
		for (ValueMeaning vm : vmList) {
			vmModelList.add(new ValueMeaningModel(vm.asMDRResource()));
		}
		return Response.ok(vmModelList).build();
	}

	@GET
	@Path("/vm")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getNumberOfValueMeanings(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("cid") String conceptualDomainID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		ConceptualDomain cd = repository
				.getConceptualDomain(conceptualDomainID);
		if (cd instanceof NonEnumeratedConceptualDomain) {
			logger.error(
					"{} is not EnumeratedConceptualDomain, so no ValueMeaning",
					conceptualDomainID);
			return Response.serverError().build();
		}
		EnumeratedConceptualDomain ecd = (EnumeratedConceptualDomain) cd;
		int size = ecd.getNumberOfValueMeanings();
		return Response.ok(String.valueOf(size)).build();
	}

	@POST
	@Path("/vm")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createValueMeaning(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("cid") String conceptualDomainID,
			ValueMeaningModel valueMeaningModel) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		ConceptualDomain cd = repository
				.getConceptualDomain(conceptualDomainID);
		if (cd instanceof NonEnumeratedConceptualDomain) {
			logger.error(
					"{} is not EnumeratedConceptualDomain, so no ValueMeaning",
					conceptualDomainID);
			return Response.serverError().build();
		}

		EnumeratedConceptualDomain ecd = (EnumeratedConceptualDomain) cd;
		ValueMeaning vm = ecd.addValueMeaning(valueMeaningModel.getId(),
				valueMeaningModel.getDescription());
		return Response.ok(new ValueMeaningModel(vm.asMDRResource())).build();
	}

	@DELETE
	@Path("/vm")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteValueMeaning(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("cid") String conceptualDomainID,
			ValueMeaningModel valueMeaningModel) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		ConceptualDomain cd = repository
				.getConceptualDomain(conceptualDomainID);
		if (cd instanceof NonEnumeratedConceptualDomain) {
			logger.error(
					"{} is not EnumeratedConceptualDomain, so no ValueMeaning",
					conceptualDomainID);
			return Response.serverError().build();
		}
		// TODO it iterates over whole ValueMeaning set, not efficient, find
		// another way to do it
		EnumeratedConceptualDomain ecd = (EnumeratedConceptualDomain) cd;
		List<ValueMeaning> vmList = ecd.getValueMeanings();
		for (ValueMeaning vm : vmList) {
			if (valueMeaningModel.getId().equals(vm.getIdentifier())) {
				vm.delete();
				return Response.ok().build();
			}
		}
		return Response.noContent().build();
	}

	@GET
	@Path("/vd")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listValueDomains(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("cid") String cid) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		List<ValueDomainModel> vdModelList = new ArrayList<ValueDomainModel>();
		ConceptualDomain cd = repository.getConceptualDomain(cid);

		List<ValueDomain> vdList = cd.getValueDomains();

		for (ValueDomain vd : vdList) {
			if (vd instanceof EnumeratedValueDomain) {
				vdModelList.add(new ValueDomainModel(vd, true));
			} else {
				vdModelList.add(new ValueDomainModel(vd, false));
			}
		}

		return Response.ok(vdModelList).build();
	}

	@POST
	@Path("/vd")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createValueDomain(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("cid") String cid, ValueDomainModel valueDomain) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		ConceptualDomain cd = repository.getConceptualDomain(cid);
		ValueDomain vd = null;
		Datatype dataType = repository
				.getMDRDatabase()
				.getResourceFactory()
				.createDatatype(valueDomain.getDataType().getDatatypeName(),
						null, valueDomain.getDataType().getSchemeReference(),
						null);
		if (cd instanceof EnumeratedConceptualDomain
				&& valueDomain.isEnumerated()) {
			EnumeratedConceptualDomain ecd = (EnumeratedConceptualDomain) cd;
			List<PermissibleValue> permissibleValues = new ArrayList<PermissibleValue>();
			List<PermissibleValueModel> permissibleValueModels = valueDomain
					.getPermissibleValues();

			for (PermissibleValueModel pvModel : permissibleValueModels) {
				permissibleValues.add(ecd.getValueMeaning(
						pvModel.getValueMeaning().getId())
						.createPermissibleValue(pvModel.getValueItem()));
			}
			vd = ecd.createEnumeratedValueDomain(valueDomain.getName(),
					valueDomain.getDefinition(), dataType, permissibleValues);

			return Response.ok(new ValueDomainModel(vd, true)).build();
		} else if (cd instanceof NonEnumeratedConceptualDomain
				&& !valueDomain.isEnumerated()) {
			vd = ((NonEnumeratedConceptualDomain) cd)
					.createNonEnumeratedValueDomain(valueDomain.getName(),
							valueDomain.getDefinition(), dataType);

			return Response.ok(new ValueDomainModel(vd, false)).build();
		} else {
			logger.error(
					"{} ValueDomain's type should be consistent with ConceptualDomain's type",
					valueDomain.getName());
			return Response.serverError().build();
		}
	}

	@PUT
	@Path("/vd")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateValueDomain(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("cid") String cid, ValueDomainModel valueDomain) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		ConceptualDomain cd = repository.getConceptualDomain(cid);
		ValueDomain vd = cd.getValueDomain(valueDomain.getId());

		vd.setName(valueDomain.getName());
		vd.setDefinition(valueDomain.getDefinition());
		DatatypeResource dataType = repository
				.getMDRDatabase()
				.getResourceFactory()
				.createDatatype(valueDomain.getDataType().getDatatypeName(),
						null, valueDomain.getDataType().getSchemeReference(),
						null);
		vd.asMDRResource().setValueDomainDatatype(dataType);

		if (valueDomain.isEnumerated()) {
			EnumeratedConceptualDomain ecd = (EnumeratedConceptualDomain) cd;
			List<PermissibleValue> pvList = ((EnumeratedValueDomain) vd)
					.getPermissibleValues();
			for (PermissibleValue pv : pvList) {
				pv.delete();
			}
			List<PermissibleValue> permissibleValues = new ArrayList<PermissibleValue>();
			List<PermissibleValueModel> permissibleValueModels = valueDomain
					.getPermissibleValues();

			for (PermissibleValueModel pvModel : permissibleValueModels) {
				permissibleValues.add(ecd.getValueMeaning(
						pvModel.getValueMeaning().getId())
						.createPermissibleValue(pvModel.getValueItem()));
			}
			((EnumeratedValueDomain) vd).addPermissibleValue(permissibleValues
					.toArray(new PermissibleValue[permissibleValues.size()]));
		}

		return Response.ok().build();
	}
}
