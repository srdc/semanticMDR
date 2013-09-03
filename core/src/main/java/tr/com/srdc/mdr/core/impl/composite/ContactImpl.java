package tr.com.srdc.mdr.core.impl.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * The composite data type Contact is used to specify the contact information
 * for registrar contact, stewardship contact and submission contact.
 * 
 * @author anil
 * 
 */
public class ContactImpl extends AbstractMDRResource implements ContactResource {

	private static final Logger logger = LoggerFactory
			.getLogger(ContactImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createContact(String, String, String)} to avoid
	 * entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param contactInformation
	 *            Information to enable a {@link ContactImpl} to be located or
	 *            communicated with. This can be an e-mail address.
	 * @param contactName
	 *            The name of the {@link ContactImpl}.
	 * @param contactTitle
	 *            Optional. The name of the position held by the
	 *            {@link ContactImpl} .
	 * @param mdrDatabase
	 */
	public ContactImpl(Node n, EnhGraph g, String contactInformation,
			String contactName, String contactTitle, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Contact);
		setContactInformation(contactInformation);
		setContactName(contactName);
		setContactTitle(contactTitle);
	}

	public ContactImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setContactInformation(String contactInformation) {
		if (Util.isNull(contactInformation)) {
			throw new IllegalArgumentException(
					"Contact Information must be specified  for Contact.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().contactInformation,
				mdrDatabase.getUtil().createTypedLiteral(contactInformation));
	}

	@Override
	public String getContactInformation() {
		return getPropertyValue(mdrDatabase.getVocabulary().contactInformation)
				.asLiteral().getString();
	}

	@Override
	public void setContactName(String contactName) {
		if (Util.isNull(contactName)) {
			throw new IllegalArgumentException(
					"Contact Name must be specified  for Contact.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().contactName, mdrDatabase
				.getUtil().createTypedLiteral(contactName));
	}

	@Override
	public String getContactName() {
		return getPropertyValue(mdrDatabase.getVocabulary().contactName)
				.asLiteral().getString();
	}

	@Override
	public void setContactTitle(String contactTitle) {
		setPropertyValue(mdrDatabase.getVocabulary().contactTitle, mdrDatabase
				.getUtil().createTypedLiteral(contactTitle));
	}

	@Override
	public String getContactTitle() {
		RDFNode contactTitle = getPropertyValue(mdrDatabase.getVocabulary().contactTitle);
		if (contactTitle == null) {
			logger.debug("Contact does not have contactTitle");
			return null;
		}
		return contactTitle.asLiteral().getString();
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ContactResource asMDRResource() {
		return this;
	}

	@Override
	public String getName() {
		return this.getContactName();
	}

	@Override
	public String getTitle() {
		return this.getContactTitle();
	}

}
