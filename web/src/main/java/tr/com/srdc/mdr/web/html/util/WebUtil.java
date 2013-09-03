package tr.com.srdc.mdr.web.html.util;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.salusproject.securityprivacy.authentication.AuthenticationException;
import eu.salusproject.securityprivacy.authentication.AuthenticationManager;
import eu.salusproject.securityprivacy.authentication.User;


/**
 * @author anil
 * 
 */
public class WebUtil {

	private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

	/**
	 * This function is used by some Resource classes to check the sessionID by
	 * retrieving the associated user.
	 * 
	 * @param sessionID
	 * @return
	 */
	public static User getUser(String sessionID) throws WebApplicationException {
		User user = null;
		try {
			user = AuthenticationManager.getInstance().getUserFromSession(
					sessionID);
		} catch (AuthenticationException e) {
			logger.error("Cannot retrieve user from sessionID", e);
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		if (user == null) {
			throw new WebApplicationException(Response
					.status(Status.UNAUTHORIZED).entity("Session Not valid.")
					.build());
		}
		return user;
	}

	/**
	 * This function is used by some Resource classes to check the validity of
	 * the user session ID.
	 * 
	 * @param sessionID
	 * @return
	 */
	public static void checkUserSession(String sessionID)
			throws WebApplicationException {
		getUser(sessionID);
	}

	/**
	 * This method is to determine serialization language of the model according
	 * to the media type given
	 * 
	 * @param mediaType
	 *            media type given in HTTP request
	 * @return serialization type supported by Jena Writers
	 */
	public static String getSerializationLanguage(String mediaType) {
		if (mediaType == null)
			return "RDF/XML";
		else if (mediaType.toLowerCase().contains("RDF+XML-ABBREV".toLowerCase()))
			return "RDF/XML-ABBREV";
		else if (mediaType.toLowerCase().contains("RDF+XML".toLowerCase()))
			return "RDF/XML";
		else if (mediaType.toLowerCase().contains("N-TRIPLE".toLowerCase()))
			return "N-TRIPLE";
		else if (mediaType.toLowerCase().contains("N3".toLowerCase()))
			return "N3";
		else if (mediaType.toLowerCase().contains("TURTLE".toLowerCase()))
			return "TURTLE";
		else if (mediaType.toLowerCase().contains("TTL".toLowerCase()))
			return "TTL";
		else if (mediaType.toLowerCase().contains("RDF+JSON".toLowerCase()))
			return "RDF/JSON";
		else
			return "RDF/XML";
	}
	
	public static final String MEDIA_TYPE_APPLICATION_NTRIPLE = "application/n-triple";
	public static final String MEDIA_TYPE_APPLICATION_RDFJSON = "application/rdf+json";
	public static final String MEDIA_TYPE_APPLICATION_RDFXML = "application/rdf+xml";
	public static final String MEDIA_TYPE_TEXT_N3 = "text/n3";
	public static final String MEDIA_TYPE_TEXT_TURTLE = "text/turtle";
	public static final String MEDIA_TYPE_APPLICATION_RDFXML_ABBREV = "application/rdf+xml-abbrev";

	public static final MediaType MEDIA_TYPE_APPLICATION_NTRIPLE_TYPE = new MediaType(
			"application", "n-triple");
	public static final MediaType MEDIA_TYPE_APPLICATION_RDFJSON_TYPE = new MediaType(
			"application", "rdf+json");
	public static final MediaType MEDIA_TYPE_APPLICATION_RDFXML_TYPE = new MediaType(
			"application", "rdf+xml");
	public static final MediaType MEDIA_TYPE_TEXT_N3_TYPE = new MediaType(
			"text", "n3");
	public static final MediaType MEDIA_TYPE_TEXT_TURTLE_TYPE = new MediaType(
			"text", "turtle");
	public static final MediaType MEDIA_TYPE_APPLICATION_RDFXML_ABBREV_TYPE = new MediaType(
			"application", "rdf+xml-abbrev");

	private static final MediaType[] supportedTypes = new MediaType[] {
			MEDIA_TYPE_APPLICATION_NTRIPLE_TYPE,
			MEDIA_TYPE_APPLICATION_RDFJSON_TYPE,
			MEDIA_TYPE_APPLICATION_RDFXML_TYPE, MEDIA_TYPE_TEXT_N3_TYPE,
			MEDIA_TYPE_TEXT_TURTLE_TYPE,
			MEDIA_TYPE_APPLICATION_RDFXML_ABBREV_TYPE,
			MediaType.TEXT_PLAIN_TYPE };

	public static final List<Variant> VARIANTS = Variant.VariantListBuilder.newInstance()
			.mediaTypes(WebUtil.supportedTypes).add().build();
	
}
