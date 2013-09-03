package tr.com.srdc.mdr.web.html.api;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tr.com.srdc.mdr.core.api.ai.Property;
import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.core.impl.ai.PropertyImpl;
import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.store.MDRDatabase;
import tr.com.srdc.mdr.web.html.models.PropertyModel;
import tr.com.srdc.mdr.web.html.util.WebUtil;


@Path("/property/{propertyid}")
public class PropertyService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProperty(
			@CookieParam(AuthenticationService.SID) String sessionID,
			@PathParam("propertyid") String propertyID) {
		WebUtil.checkUserSession(sessionID);
		Repository repository = RepositoryManager.getInstance().getRepository();
		MDRDatabase mdrDatabase = repository.getMDRDatabase();

		Property property = new PropertyImpl(mdrDatabase.getOntModel()
				.getResource(
						mdrDatabase.getResourceFactory().makeID(
								Abbreviation.Property.toString(), propertyID)),
				mdrDatabase);
		return Response.ok(new PropertyModel(property)).build();
	}

}
