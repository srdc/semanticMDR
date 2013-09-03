package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * Classificcation Scheme Itme Association represents the relation between
 * <ul>
 * <li>Classificication Sheme Item Relationship</li>
 * <li>Association Among Classification Scheme Items</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} backed by Jena TripleStore, such an holder class is
 * required for n-ary relations
 * 
 * @author anil
 */
public class ClassificationSchemeItemAssociationImpl extends
		AbstractMDRResource implements
		ClassificationSchemeItemAssociationResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createClassificationSchemeItemAssociation(ClassificationSchemeItemRelationshipResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param classificationSchemeItemRelationship
	 *            An instance of a Classification Scheme Item Relationship
	 * @param mdrDatabase
	 */
	public ClassificationSchemeItemAssociationImpl(
			Node n,
			EnhGraph g,
			ClassificationSchemeItemRelationshipResource classificationSchemeItemRelationship,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ClassificationSchemeItemAssociation);
		setClassificationSchemeItemRelationship(classificationSchemeItemRelationship);
	}

	public ClassificationSchemeItemAssociationImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setClassificationSchemeItemRelationship(
			ClassificationSchemeItemRelationshipResource classificationSchemeItemRelationship) {
		if (classificationSchemeItemRelationship == null) {
			throw new IllegalArgumentException(
					"Classification Scheme Item Relationship must be specified for ClassificationSchemeItemAssociation.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().csiAssociationRelationship,
				classificationSchemeItemRelationship);
	}

	@Override
	public ClassificationSchemeItemRelationshipResource getClassificationSchemeItemRelationship() {
		return new ClassificationSchemeItemRelationshipImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().csiAssociationRelationship),
				mdrDatabase);
	}

	@Override
	public void addCSIAssociates(
			ClassificationSchemeItemResource classificationSchemeItem) {
		addProperty(mdrDatabase.getVocabulary().csiAssociates,
				classificationSchemeItem);
	}

	@Override
	public void removeCSIAssociated(
			ClassificationSchemeItemResource classificationSchemeItem) {
		if (classificationSchemeItem == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().csiAssociates,
				classificationSchemeItem);
	}

	@Override
	public List<ClassificationSchemeItemResource> getCSIAssociates()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().csiAssociates);
		return mdrDatabase.getUtil().createList(it,
				ClassificationSchemeItemResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ClassificationSchemeItemAssociationResource asMDRResource() {
		return this;
	}

}
