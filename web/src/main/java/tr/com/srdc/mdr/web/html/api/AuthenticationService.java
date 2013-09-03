package tr.com.srdc.mdr.web.html.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.salusproject.securityprivacy.authentication.AuthenticationException;
import eu.salusproject.securityprivacy.authentication.AuthenticationManager;
import eu.salusproject.securityprivacy.authentication.Session;
import eu.salusproject.securityprivacy.authentication.User;

import tr.com.srdc.mdr.web.html.models.LoginModel;
import tr.com.srdc.mdr.web.html.util.WebUtil;

@Path("/auth")
public class AuthenticationService {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationService.class);

	public static final String SID = "SID";
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginModel loginModel) {
		Session session = null;
		try {
			session = AuthenticationManager.getInstance().login(
					loginModel.getUsername(), loginModel.getPassword(),
					loginModel.isStaySignedIn());
		} catch (AuthenticationException e) {
			logger.error("Cannot create a session for the user {}",
					loginModel.getUsername(), e);
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		if (session == null) {
			throw new WebApplicationException(Response
					.status(Status.UNAUTHORIZED)
					.entity("username/password incorrect.").build());
		}
		return Response.ok(session).build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(@CookieParam(SID) String sessionID) {
		boolean status = false;
		try {
			status = AuthenticationManager.getInstance()
					.logoutUserFromSessionID(sessionID);
		} catch (AuthenticationException e) {
			logger.error("Cannot signout user from sessionID", e);
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		if (status) {
			return Response.ok().build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@CookieParam(SID) String sessionID) {
		User user = WebUtil.getUser(sessionID);
		return Response.ok(user).build();
	}

}