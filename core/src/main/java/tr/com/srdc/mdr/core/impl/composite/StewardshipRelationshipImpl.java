package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.impl.ai.AdministeredItemImpl;
import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * {@link StewardshipRelationshipImpl} is used to represent the relation between
 * <ul>
 * <li>Stewardship</li>
 * <li>administering - administratedBy Relation between {@link OrganizationImpl}
 * and {@link AdministeredItemImpl}</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} is backed by Jena - Triple Store, such an holder class
 * is required to represent n-ary relations
 * 
 * @author anil
 * 
 */
public class StewardshipRelationshipImpl extends AbstractMDRResource implements
		StewardshipRelationshipResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createStewardshipRelationship(OrganizationResource,StewardshipResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param stewardshipOrganization
	 *            {@link OrganizationResource} which administrates the
	 *            AdministratedItem instance
	 * @param mdrDatabase
	 * @param administrator
	 *            Stewardship including Contact Details of Administrator
	 */
	public StewardshipRelationshipImpl(Node n, EnhGraph g,
			OrganizationResource stewardshipOrganization,
			StewardshipResource administer, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().StewardshipRelationship);
		setStewardshipOrganization(stewardshipOrganization);
		setAdminister(administer);

	}

	public StewardshipRelationshipImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setStewardshipOrganization(
			OrganizationResource stewardshipOrganization) {
		if (stewardshipOrganization == null) {
			throw new IllegalArgumentException(
					"An Organization must be specified for Stewardship Relationship");
		}

		setPropertyValue(mdrDatabase.getVocabulary().stewardshipOrganization,
				stewardshipOrganization);
	}

	@Override
	public OrganizationResource getStewardshipOrganization() {
		return new OrganizationImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().stewardshipOrganization),
				mdrDatabase);
	}

	@Override
	public void setAdminister(StewardshipResource administer) {
		if (administer == null) {
			throw new IllegalArgumentException(
					"Admininstrator must be specified for the StewardshipRelationship");
		}
		setPropertyValue(mdrDatabase.getVocabulary().administer, administer);
	}

	@Override
	public StewardshipResource getAdminister() {
		return new StewardshipImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().administer),
				mdrDatabase);
	}

	@Override
	public void addAdministratedItems(AdministeredItemResource administeredItem) {
		if (administeredItem == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().administeredItems,
				administeredItem);
	}

	@Override
	public List<AdministeredItemResource> getAdministratedItems()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().administeredItems);
		return mdrDatabase.getUtil().createList(it,
				AdministeredItemResource.class);
	}

	@Override
	public void removeAdministratedItem(
			AdministeredItemResource administratedItems) {
		if (administratedItems == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().administeredItems,
				administratedItems);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public StewardshipRelationshipResource asMDRResource() {
		return this;
	}

}
