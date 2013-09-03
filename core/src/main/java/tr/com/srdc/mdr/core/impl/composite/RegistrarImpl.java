package tr.com.srdc.mdr.core.impl.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.composite.Contact;
import tr.com.srdc.mdr.core.api.composite.RegistrationAuthority;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrarResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * A Registration Authority is represented by one or more Registrars. Registrars
 * are the persons who perform the administrative steps to register Administered
 * Items in a Metadata Registry.
 * 
 * @author anil
 * 
 */
public class RegistrarImpl extends AbstractMDRResource implements
		RegistrarResource {

	private static final Logger logger = LoggerFactory
			.getLogger(RegistrarImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createRegistrar(String,ContactResource)} to
	 * avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param registrarIdentifier
	 *            Identifier for the Registrar
	 * @param registrarContact
	 *            Contact Information for the Registrar
	 * @param mdrDatabase
	 */
	public RegistrarImpl(Node n, EnhGraph g, String registrarIdentifier,
			ContactResource registrarContact, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Registrar);
		setRegistrarIdentifier(registrarIdentifier);
		setRegistrarContact(registrarContact);
	}

	public RegistrarImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setRegistrarIdentifier(String registrarIdentifier) {
		if (Util.isNull(registrarIdentifier)) {
			throw new IllegalArgumentException(
					"Registrar Identifier must be specified for Registrar");
		}
		setPropertyValue(mdrDatabase.getVocabulary().registrarIdentifier,
				mdrDatabase.getUtil().createTypedLiteral(registrarIdentifier));
	}

	@Override
	public String getRegistrarIdentifier() {
		return getPropertyValue(mdrDatabase.getVocabulary().registrarIdentifier)
				.asLiteral().getString();
	}

	@Override
	public void setRegistrarContact(ContactResource registrarContact) {
		if (registrarContact == null) {
			throw new IllegalArgumentException(
					"Registrar Contact must be specified for Registrar");
		}

		setPropertyValue(mdrDatabase.getVocabulary().registrarContact,
				registrarContact);
	}

	@Override
	public ContactResource getRegistrarContact() {
		return new ContactImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().registrarContact),
				mdrDatabase);
	}

	@Override
	public void setRepresent(RegistrationAuthorityResource registrationAuthority) {
		setPropertyValue(mdrDatabase.getVocabulary().represents,
				registrationAuthority);
	}

	@Override
	public RegistrationAuthorityResource getRepresent() {
		RDFNode representedRegistrationAuthority = getPropertyResourceValue(mdrDatabase
				.getVocabulary().represents);
		if (representedRegistrationAuthority == null) {
			logger.debug("Registrar does not have Registration Authority");
			return null;
		}
		return new RegistrationAuthorityImpl(
				representedRegistrationAuthority.asResource(), mdrDatabase);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public RegistrarResource asMDRResource() {
		return this;
	}

	@Override
	public RegistrationAuthority getRegistrationAuthority() {
		return this.getRepresent();
	}

	@Override
	public String getIdentifier() {
		return this.getRegistrarIdentifier();
	}

	@Override
	public Contact getContact() {
		return this.getRegistrarContact();
	}

}
