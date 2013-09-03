package tr.com.srdc.mdr.web.html.api;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tr.com.srdc.mdr.core.api.ai.EnumeratedValueDomain;
import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.web.html.models.ValueDomainModel;
import tr.com.srdc.mdr.web.html.util.WebUtil;


@Path("/vd/{vdid}")
public class ValueDomainService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getValueDomain(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("vdid") String valueDomainID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		ValueDomain vd = repository.getValueDomain(valueDomainID);
		if (vd == null) {
			return Response.noContent().build();
		}

		if (vd instanceof EnumeratedValueDomain) {
			return Response.ok(new ValueDomainModel(vd, true)).build();
		} else {
			return Response.ok(new ValueDomainModel(vd, false)).build();
		}
	}

}
