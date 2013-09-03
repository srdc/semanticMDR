package tr.com.srdc.mdr.core.impl.composite;

import java.util.Collections;
import java.util.List;

import tr.com.srdc.mdr.core.api.composite.LanguageIdentification;
import tr.com.srdc.mdr.core.api.composite.RegistrationAuthorityIdentifier;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageIdentificationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrarResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityIdentifierResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * A Registration Authority is any Organization authorized to register metadata.
 * A Registration Authority is a subtype of Organization and inherits all of its
 * attributes and relationships. An Administered Item has a Registration
 * Authority that is its owner. A Registration Authority may register many
 * Administered Items.
 * 
 * @author anil
 * 
 */
public class RegistrationAuthorityImpl extends OrganizationImpl implements
		RegistrationAuthorityResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createRegistrationAuthority(RegistrationAuthorityIdentifierResource, LanguageIdentificationResource, String, String)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param registrationAuthorityIdentifier
	 *            Identifier for the owning Registration Authority
	 * @param documentationLanguageIdentifier
	 *            Language Identification for Documentation
	 * @param organizationName
	 *            Name of the Organization
	 * @param organizationMailAddress
	 *            Optional. Mail Address of the Organization
	 * @param representedBy
	 *            Person who perform the administrative steps to register
	 *            Administered Items
	 * @param mdrDatabase
	 */
	public RegistrationAuthorityImpl(
			Node n,
			EnhGraph g,
			RegistrationAuthorityIdentifierResource registrationAuthorityIdentifier,
			LanguageIdentificationResource documentationLanguageIdentifier,
			String organizationName, String organizationMailAddress,
			RegistrarResource representedBy, MDRDatabase mdrDatabase) {
		super(n, g, organizationName, organizationMailAddress, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().RegistrationAuthority);
		setRegistrationAuthorityIdentifier(registrationAuthorityIdentifier);
		addDocumentationLanguageIdentifier(documentationLanguageIdentifier);
		setOrganizationMailAddress(organizationMailAddress);
		setOrganizationName(organizationName);
		addRepresentedBy(representedBy);
	}

	/**
	 * @param resource
	 * @param mdrDatabase
	 */
	public RegistrationAuthorityImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setRegistrationAuthorityIdentifier(
			RegistrationAuthorityIdentifierResource registrationAuthorityIdentifier) {
		if (registrationAuthorityIdentifier == null) {
			throw new IllegalArgumentException(
					"Registration Authority Identifier must be specified for RegistrationAuthority");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().registrationAuthorityIdentifier,
				registrationAuthorityIdentifier);
	}

	@Override
	public RegistrationAuthorityIdentifierResource getRegistrationAuthorityIdentifier() {
		return new RegistrationAuthorityIdentifierImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().registrationAuthorityIdentifier),
				mdrDatabase);
	}

	@Override
	public void addDocumentationLanguageIdentifier(
			LanguageIdentificationResource documentationLanguageIdentifier) {
		if (documentationLanguageIdentifier == null) {
			throw new IllegalArgumentException(
					"At least 1 Documentation Language Identifier must be specified for Registration Authority");
		}
		addProperty(
				mdrDatabase.getVocabulary().documentationLanguageIdentifier,
				documentationLanguageIdentifier);
	}

	@Override
	public void removeDocumentationLanguageIdentifier(
			LanguageIdentificationResource documentationLanguageIdentifier) {
		if (documentationLanguageIdentifier == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		if (listProperties(
				mdrDatabase.getVocabulary().documentationLanguageIdentifier)
				.toSet().size() <= 1) {
			throw new IllegalArgumentException(
					"At least 1 Documentation Language Identifier must be specified for Registration Authority");
		}
		removeProperty(
				mdrDatabase.getVocabulary().documentationLanguageIdentifier,
				documentationLanguageIdentifier);
	}

	@Override
	public List<LanguageIdentificationResource> getDocumentationLanguageIdentifiers()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().documentationLanguageIdentifier);
		return mdrDatabase.getUtil().createList(it,
				LanguageIdentificationResource.class);
	}

	@Override
	public void addRegistering(AdministeredItemResource registering) {
		if (registering == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().registering, registering);
	}

	@Override
	public void removeRegistering(AdministeredItemResource registering) {
		if (registering == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}

		removeProperty(mdrDatabase.getVocabulary().registering, registering);
	}

	@Override
	public List<AdministeredItemResource> getRegistering() throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().registering);
		return mdrDatabase.getUtil().createList(it,
				AdministeredItemResource.class);
	}

	@Override
	public void addRepresentedBy(RegistrarResource representedBy) {
		if (representedBy == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().representedBy, representedBy);
	}

	@Override
	public void removeRepresentedBy(RegistrarResource representedBy) {
		if (representedBy == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		if (listProperties(mdrDatabase.getVocabulary().representedBy).toSet()
				.size() <= 1) {
			throw new IllegalArgumentException(
					"At least 1 representedBy must be specified for Registration Authority");
		}
		removeProperty(mdrDatabase.getVocabulary().representedBy, representedBy);
	}

	@Override
	public List<RegistrarResource> getRepresentedBy() throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().representedBy);
		return mdrDatabase.getUtil().createList(it, RegistrarResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public RegistrationAuthorityResource asMDRResource() {
		return this;
	}

	@Override
	public RegistrationAuthorityIdentifier getIdentifier() {
		return this.getRegistrationAuthorityIdentifier();
	}

	@Override
	public List<LanguageIdentification> getLanguageIdentifications()
			throws MDRException {
		return Collections.<LanguageIdentification> unmodifiableList(this
				.getDocumentationLanguageIdentifiers());
	}

}
