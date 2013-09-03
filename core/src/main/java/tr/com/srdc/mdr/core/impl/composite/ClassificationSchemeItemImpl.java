package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.ai.ClassificationScheme;
import tr.com.srdc.mdr.core.impl.ai.ClassificationSchemeImpl;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ClassificationSchemeResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * A Classification Scheme Item represents an individual item within a
 * Classification Scheme.<br>
 * 
 * @author anil
 * @author mert
 */
public class ClassificationSchemeItemImpl extends AbstractMDRResource implements
		ClassificationSchemeItemResource {

	private static final Logger logger = LoggerFactory
			.getLogger(ClassificationSchemeItemImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createClassificationSchemeItem(String,String)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param classificationScheme
	 *            Classification Scheme which contains the Item
	 * @param classificationSchemeItemTypeName
	 *            Optional. The name of the type of the Classification Scheme
	 *            Item.
	 * @param classificationSchemeItemValue
	 *            Optional. An instance of a Classification Scheme Item.
	 * @param mdrDatabase
	 */
	public ClassificationSchemeItemImpl(Node n, EnhGraph g,
			ClassificationSchemeResource classificationScheme,
			String classificationSchemeItemTypeName,
			String classificationSchemeItemValue, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ClassificationSchemeItem);
		setClassificationSchemeItemTypeName(classificationSchemeItemTypeName);
		setClassificationSchemeItemValue(classificationSchemeItemValue);
		setContainedIn(classificationScheme);
	}

	public ClassificationSchemeItemImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setClassificationSchemeItemTypeName(
			String classificationSchemeItemTypeName) {
		setPropertyValue(
				mdrDatabase.getVocabulary().classificationSchemeItemTypeName,
				mdrDatabase.getUtil().createTypedLiteral(
						classificationSchemeItemTypeName));
	}

	@Override
	public String getClassificationSchemeItemTypeName() {
		RDFNode classificationSchemeItemTypeName = getPropertyValue(mdrDatabase
				.getVocabulary().classificationSchemeItemTypeName);
		if (classificationSchemeItemTypeName == null) {
			logger.debug("ClassificationSchemeItem does not have classificationSchemeItemTypeName");
			return null;
		}
		return classificationSchemeItemTypeName.asLiteral().getString();
	}

	@Override
	public void setClassificationSchemeItemValue(
			String classificationSchemeItemValue) {
		setPropertyValue(
				mdrDatabase.getVocabulary().classificationSchemeItemValue,
				mdrDatabase.getUtil().createTypedLiteral(
						classificationSchemeItemValue));
	}

	@Override
	public String getClassificationSchemeItemValue() {
		RDFNode classificationSchemeItemValue = getPropertyValue(mdrDatabase
				.getVocabulary().classificationSchemeItemValue);
		if (classificationSchemeItemValue == null) {
			logger.debug("ClassificationSchemeItem does not have classificationSchemeItemValue");
			return null;
		}
		return classificationSchemeItemValue.asLiteral().getString();
	}

	@Override
	public void addClassifying(AdministeredItemResource administeredItem) {
		if (administeredItem == null) {
			throw new IllegalArgumentException(
					"Null is not a valid value for adding classifying Administered Item to Classification Scheme Item.");
		}
		addProperty(mdrDatabase.getVocabulary().classifying, administeredItem);
	}

	@Override
	public void removeClassifying(AdministeredItemResource administeredItem) {
		if (administeredItem == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the Administered Item to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().classifying,
				administeredItem);
	}

	@Override
	public List<AdministeredItemResource> getClassifyings() throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().classifying);
		return mdrDatabase.getUtil().createList(it,
				AdministeredItemResource.class);
	}

	@Override
	public void setContainedIn(ClassificationSchemeResource classificationScheme) {
		if (classificationScheme == null) {
			throw new IllegalArgumentException(
					"Classification Scheme must be specified for ClassificationSchemeItem.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().containedIn,
				classificationScheme);
	}

	@Override
	public ClassificationSchemeResource getContainedIn() {
		return new ClassificationSchemeImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().containedIn),
				mdrDatabase);
	}

	@Override
	public void addCSIAssociatedIn(
			ClassificationSchemeItemAssociationResource csiRelationshipAssociation) {
		if (csiRelationshipAssociation == null) {
			throw new IllegalArgumentException(
					"Null is not a valid value for adding Classification Scheme Item Association to Classification Scheme Item.");
		}
		addProperty(mdrDatabase.getVocabulary().csiAssociatedIn,
				csiRelationshipAssociation);
	}

	@Override
	public void removeCSIAssociatedIn(
			ClassificationSchemeItemAssociationResource csiRelationshipAssociation) {
		if (csiRelationshipAssociation == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().csiAssociatedIn,
				csiRelationshipAssociation);
	}

	@Override
	public List<ClassificationSchemeItemAssociationResource> getCSIAssociatedIns()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().csiAssociatedIn);
		return mdrDatabase.getUtil().createList(it,
				ClassificationSchemeItemAssociationResource.class);

	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ClassificationSchemeItemResource asMDRResource() {
		return this;
	}

	@Override
	public ClassificationScheme getClassificationScheme() {
		return this.getContainedIn();
	}

	@Override
	public String getTypeName() {
		return this.getClassificationSchemeItemTypeName();
	}

	@Override
	public String getItemValue() {
		return this.getClassificationSchemeItemValue();
	}

}
