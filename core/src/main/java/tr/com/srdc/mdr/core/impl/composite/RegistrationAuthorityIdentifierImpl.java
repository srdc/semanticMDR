package tr.com.srdc.mdr.core.impl.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityIdentifierResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * The composite data type Registration Authority Identifier is used to uniquely
 * identify a Registration Authority.
 * 
 * @author anil
 * 
 */
public class RegistrationAuthorityIdentifierImpl extends AbstractMDRResource
		implements RegistrationAuthorityIdentifierResource {

	private static final Logger logger = LoggerFactory
			.getLogger(RegistrationAuthorityIdentifierImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createRegistrationAuthorityIdentifier(String, String, String, String)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param internationalCodeDesignator
	 *            The identifier of an organization identification scheme
	 * @param organizationIdentifier
	 *            The identifier assigned to an Organization within an
	 *            organization identification scheme, and unique within that
	 *            scheme.The identifier are specified in ISO/IEC 11179-3.
	 * @param organizationPartIdentifier
	 *            Optional. An identifier allocated to a particular organization
	 *            part.The identifier are specified in ISO/IEC 11179-3.
	 * @param opiSource
	 *            Optional. However, must exist if the
	 *            <code>organizationPartIdentifier</code> is specified. The
	 *            source for the organization part identifier. The identifier
	 *            are specified in ISO/IEC 11179-3.
	 * @param mdrDatabase
	 */
	public RegistrationAuthorityIdentifierImpl(Node n, EnhGraph g,
			String internationalCodeDesignator, String organizationIdentifier,
			String organizationPartIdentifier, String opiSource,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().RegistrationAuthorityIdentifier);
		setInternationalCodeDesignator(internationalCodeDesignator);
		setOrganizationIdentifier(organizationIdentifier);
		setOrganizationPartIdentifier(organizationPartIdentifier, opiSource);
	}

	/**
	 * Creates a {@link RegistrationAuthorityIdentifierImpl} with a
	 * {@link Resource}
	 * 
	 * @param resource
	 * @param mdrDatabase
	 */
	public RegistrationAuthorityIdentifierImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setInternationalCodeDesignator(
			String internationalCodeDesignator) {
		if (Util.isNull(internationalCodeDesignator)) {
			throw new IllegalArgumentException(
					"International Code Designator must be specified for RegistrationAuthorityIdentifier.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().internationalCodeDesignator,
				mdrDatabase.getUtil().createTypedLiteral(
						internationalCodeDesignator));
	}

	@Override
	public String getInternationalCodeDesignator() {
		return getPropertyValue(
				mdrDatabase.getVocabulary().internationalCodeDesignator)
				.asLiteral().getString();
	}

	@Override
	public void setOrganizationIdentifier(String organizationIdentifier) {
		if (Util.isNull(organizationIdentifier)) {
			throw new IllegalArgumentException(
					"Organization Identifier must be specified for RegistrationAuthorityIdentifier.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().organizationIdentifier,
				mdrDatabase.getUtil()
						.createTypedLiteral(organizationIdentifier));
	}

	@Override
	public String getOrganizationIdentifier() {
		return getPropertyValue(
				mdrDatabase.getVocabulary().organizationIdentifier).asLiteral()
				.getString();
	}

	@Override
	public void setOrganizationPartIdentifier(
			String organizationPartIdentifier, String opiSource) {
		if (!Util.isNull(organizationPartIdentifier) && Util.isNull(opiSource)) {
			throw new IllegalArgumentException(
					"Opi Source must be specified while setting Organization Part Identifier for RegistrationAuthorityIdentifier.");
		}
		if (Util.isNull(organizationPartIdentifier) && !Util.isNull(opiSource)) {
			throw new IllegalArgumentException(
					"Opi Source cannot be set without an Organization Part Identifier for RegistrationAuthorityIdentifier.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().organizationPartIdentifier,
				mdrDatabase.getUtil().createTypedLiteral(
						organizationPartIdentifier));
		setPropertyValue(mdrDatabase.getVocabulary().opiSource, mdrDatabase
				.getUtil().createTypedLiteral(opiSource));
	}

	@Override
	public String getOrganizationPartIdentifier() {
		RDFNode organizationPartIdentifier = getPropertyValue(mdrDatabase
				.getVocabulary().organizationPartIdentifier);
		if (organizationPartIdentifier == null) {
			logger.debug("RegistrationAuthorityIdentifier does not have organizationPartIdentifier");
			return null;
		}
		return organizationPartIdentifier.asLiteral().getString();
	}

	@Override
	public String getOpiSource() {
		RDFNode opiSource = getPropertyValue(mdrDatabase.getVocabulary().opiSource);
		if (opiSource == null) {
			logger.debug("RegistrationAuthorityIdentifier does not have opiSource");
			return null;
		}
		return opiSource.asLiteral().getString();
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public RegistrationAuthorityIdentifierResource asMDRResource() {
		return this;
	}

}
