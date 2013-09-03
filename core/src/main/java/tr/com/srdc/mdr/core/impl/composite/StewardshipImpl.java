package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * An Organization shall be identified as the steward responsible for
 * administering each Administered Item. This relationship identifies a
 * stewardship contact for the Administered Item.
 * 
 * @author anil
 * 
 */
public class StewardshipImpl extends AbstractMDRResource implements
		StewardshipResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createStewardship(ContactResource)} to avoid
	 * entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param stewardshipContact
	 *            Steward Responsible Contact Detail of Stewardship
	 * @param mdrDatabase
	 */
	public StewardshipImpl(Node n, EnhGraph g,
			ContactResource stewardshipContact, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Stewardship);
		setStewardshipContact(stewardshipContact);
	}

	public StewardshipImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setStewardshipContact(ContactResource stewardshipContact) {
		if (stewardshipContact == null) {
			throw new IllegalArgumentException(
					"Stewardship Contact must be specified for Stewardship");
		}

		setPropertyValue(mdrDatabase.getVocabulary().stewardshipContact,
				stewardshipContact);

	}

	@Override
	public ContactResource getStewardshipContact() {
		return new ContactImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().stewardshipContact),
				mdrDatabase);
	}

	@Override
	public void addAdministers(StewardshipRelationshipResource administers) {
		if (administers == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().administers, administers);
	}

	@Override
	public List<StewardshipRelationshipResource> getAdministers()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().administers);
		return mdrDatabase.getUtil().createList(it,
				StewardshipRelationshipResource.class);

	}

	@Override
	public void removeAdministers(StewardshipRelationshipResource administers) {
		if (administers == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().administers, administers);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public StewardshipResource asMDRResource() {
		return this;
	}

}
