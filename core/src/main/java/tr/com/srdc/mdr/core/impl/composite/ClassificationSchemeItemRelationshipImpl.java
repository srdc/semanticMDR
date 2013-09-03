package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * A Classification Scheme Item Relationship associates two or more
 * Classification Scheme Items within a Classification Scheme. Such
 * relationships serve to assist navigation through a large number of
 * Classification Scheme Items.
 * <p>
 * Since {@link Abbreviation} is backed by Jena Triple Store, Classification Scheme
 * Item Relationship and Classification Scheme Items are associated by use of
 * {@link ClassificationSchemeItemAssociationImpl}
 * 
 * @author anil
 */
public class ClassificationSchemeItemRelationshipImpl extends
		AbstractMDRResource implements
		ClassificationSchemeItemRelationshipResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createClassificationSchemeItemRelationship(String)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param classificationSchemeItemRelationshipTypeDescription
	 *            Description for the Classification Scheme Item Relationship
	 * @param mdrDatabase
	 */
	public ClassificationSchemeItemRelationshipImpl(Node n, EnhGraph g,
			String classificationSchemeItemRelationshipTypeDescription,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ClassificationSchemeItemRelationship);
		setClassificationSchemeItemRelationshipTypeDescription(classificationSchemeItemRelationshipTypeDescription);
	}

	public ClassificationSchemeItemRelationshipImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tr.com.srdc.salus.mdr.core.impl.composite.
	 * ClassificationSchemeItemRelationshipIND
	 * #setClassificationSchemeItemRelationshipTypeDescription(java.lang.String)
	 */
	@Override
	public void setClassificationSchemeItemRelationshipTypeDescription(
			String classificationSchemeItemRelationshipTypeDescription) {
		if (classificationSchemeItemRelationshipTypeDescription == null) {
			throw new IllegalArgumentException(
					"Classification Scheme Item Type Description must be specified for ClassificationSchemeItemRelationship.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().classificationSchemeItemRelationshipTypeDescription,
				mdrDatabase.getUtil().createTypedLiteral(
						classificationSchemeItemRelationshipTypeDescription));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tr.com.srdc.salus.mdr.core.impl.composite.
	 * ClassificationSchemeItemRelationshipIND
	 * #getClassificationSchemeItemRelationshipTypeDescription()
	 */
	@Override
	public String getClassificationSchemeItemRelationshipTypeDescription() {
		return getPropertyValue(
				mdrDatabase.getVocabulary().classificationSchemeItemRelationshipTypeDescription)
				.asLiteral().getString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tr.com.srdc.salus.mdr.core.impl.composite.
	 * ClassificationSchemeItemRelationshipIND
	 * #addCSIRelationshipAssocation(tr.com
	 * .srdc.salus.mdr.core.impl.composite.ClassificationSchemeItemAssociationImpl
	 * )
	 */
	@Override
	public void addCSIRelationshipAssocation(
			ClassificationSchemeItemAssociationImpl csiRelationshipAssociation) {
		addProperty(mdrDatabase.getVocabulary().csiRelationshipAssociation,
				csiRelationshipAssociation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tr.com.srdc.salus.mdr.core.impl.composite.
	 * ClassificationSchemeItemRelationshipIND
	 * #removeCSIRelationshipAssocation(tr
	 * .com.srdc.salus.mdr.core.impl.composite
	 * .ClassificationSchemeItemAssociationImpl)
	 */
	@Override
	public void removeCSIRelationshipAssocation(
			ClassificationSchemeItemAssociationImpl csiRelationshipAssociation) {
		if (csiRelationshipAssociation == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().csiRelationshipAssociation,
				csiRelationshipAssociation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tr.com.srdc.salus.mdr.core.impl.composite.
	 * ClassificationSchemeItemRelationshipIND#getCSIRelationshipAssocations()
	 */
	@Override
	public List<ClassificationSchemeItemAssociationImpl> getCSIRelationshipAssocations()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().csiRelationshipAssociation);
		return mdrDatabase.getUtil().createList(it,
				ClassificationSchemeItemAssociationImpl.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ClassificationSchemeItemRelationshipResource asMDRResource() {
		return this;
	}

}
