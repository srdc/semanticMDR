package tr.com.srdc.mdr.web.html.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.ai.DataElementConcept;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.web.html.models.DataElementConceptModel;
import tr.com.srdc.mdr.web.html.models.DataElementModel;
import tr.com.srdc.mdr.web.html.util.WebUtil;


@Path("/dec/{decid}")
public class DataElementConceptService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataElementConcept(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("decid") String decID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		DataElementConcept dec = repository.getDataElementConcept(decID);

		return Response.ok(new DataElementConceptModel(dec.asMDRResource()))
				.build();
	}

	@GET
	@Path("/de")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listDataElements(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("decid") String decID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		DataElementConcept dec = repository.getDataElementConcept(decID);
		List<DataElement> deList = dec.getDataElements();

		List<DataElementModel> deModelList = new ArrayList<DataElementModel>();
		for (DataElement de : deList) {
			deModelList.add(new DataElementModel(de));
		}

		return Response.ok(deModelList).build();
	}
}
