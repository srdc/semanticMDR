package tr.com.srdc.mdr.core.impl.composite;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.ItemIdentifierResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityIdentifierResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * The composite data type Item Identifier is used to specify the unique
 * identifier for an Administered Item.
 * 
 * @author anil
 * 
 */
public class ItemIdentifierImpl extends AbstractMDRResource implements
		ItemIdentifierResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createItemIdentifier(RegistrationAuthorityIdentifierImpl)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param itemRegistrationAuthorityIdentifier
	 *            The identifier for the owning Registration Authority.
	 * @param dataIdentifier
	 *            The unique identifier for an Administered Item within a
	 *            Registration Authority
	 * @param version
	 *            The unique version identifier of the Administered Item
	 * @param mdrDatabase
	 */
	public ItemIdentifierImpl(
			Node n,
			EnhGraph g,
			RegistrationAuthorityIdentifierResource itemRegistrationAuthorityIdentifier,
			String dataIdentifier, String version, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ItemIdentifier);
		setItemRegistrationAuthorityIdentifier(itemRegistrationAuthorityIdentifier);
		setDataIdentifier(dataIdentifier);
		setVersion(version);
	}

	/**
	 * Constructor for {@link ItemIdentifierImpl} which constructs the class
	 * from a given {@link Resource}.
	 * 
	 * @param resource
	 * @param mdrDatabase
	 */
	public ItemIdentifierImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setItemRegistrationAuthorityIdentifier(
			RegistrationAuthorityIdentifierResource itemRegistrationAuthorityIdentifier) {
		if (itemRegistrationAuthorityIdentifier == null) {
			throw new IllegalArgumentException(
					"Registration Authority Identifier must be specified for ItemIdentifier.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().itemRegistrationAuthorityIdentifier,
				itemRegistrationAuthorityIdentifier);
	}

	@Override
	public RegistrationAuthorityIdentifierImpl getItemRegistrationAuthorityIdentifier() {
		return new RegistrationAuthorityIdentifierImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().itemRegistrationAuthorityIdentifier),
				mdrDatabase);
	}

	@Override
	public void setDataIdentifier(String dataIdentifier) {
		if (Util.isNull(dataIdentifier)) {
			throw new IllegalArgumentException(
					"Data Identifier must be specified for ItemIdentifier.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().dataIdentifier,
				mdrDatabase.getUtil().createTypedLiteral(dataIdentifier));
	}

	@Override
	public String getDataIdentifier() {
		return getPropertyValue(mdrDatabase.getVocabulary().dataIdentifier)
				.asLiteral().getString();
	}

	@Override
	public void setVersion(String version) {
		if (Util.isNull(version)) {
			throw new IllegalArgumentException(
					"Version must be specified for ItemIdentifier.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().version, mdrDatabase
				.getUtil().createTypedLiteral(version));
	}

	@Override
	public String getVersion() {
		return getPropertyValue(mdrDatabase.getVocabulary().version)
				.asLiteral().getString();
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ItemIdentifierResource asMDRResource() {
		return this;
	}

}
