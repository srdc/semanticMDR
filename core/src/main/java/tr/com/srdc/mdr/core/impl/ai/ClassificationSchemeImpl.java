package tr.com.srdc.mdr.core.impl.ai;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.composite.ClassificationSchemeItem;
import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ClassificationSchemeResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * A Classification Scheme may be a taxonomy, a network, an ontology, or any
 * other terminological system. The classification may also be just a list of
 * controlled jenaDatabase of property words (or terms). The list might be taken
 * from the "leaf level" of a taxonomy.<br>
 * <br>
 * 
 * @author anil
 * 
 */

public class ClassificationSchemeImpl extends AdministeredItemImpl implements
		ClassificationSchemeResource {

	private static final Logger logger = LoggerFactory
			.getLogger(ClassificationSchemeImpl.class);
	
	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createClassificationScheme(AdministrationRecordResource,String)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param classificationSchemeAdministrationRecord
	 *            The Administration Record for a Classification Scheme
	 * @param classificationSchemeTypeName
	 *            The name of the type of Classification Scheme
	 * @param administeredBy
	 *            An Administered Item is administered by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link StewardshipRelationshipResource}.
	 * @param submittedBy
	 *            An Administered Item is submitted by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link SubmissionRelationshipResource}.
	 * @param registeredBy
	 *            An {@link AdministeredItemResource} is registered by a
	 *            {@link RegistrationAuthorityResource}.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @param mdrDatabase
	 */
	public ClassificationSchemeImpl(
			Node n,
			EnhGraph g,
			AdministrationRecordResource classificationSchemeAdministrationRecord,
			String classificationSchemeTypeName,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having, MDRDatabase mdrDatabase) {
		super(n, g, administeredBy, submittedBy, registeredBy, having,
				mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ClassificationScheme);
		setAdministrationRecord(classificationSchemeAdministrationRecord);
		setClassificationSchemeTypeName(classificationSchemeTypeName);
	}

	/**
	 * Constructor for {@link ClassificationSchemeImpl} which constructs the
	 * class from a given {@link Resource}.
	 * 
	 * @param resource
	 * @param mdrDatabase
	 */
	public ClassificationSchemeImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setAdministrationRecord(
			AdministrationRecordResource classificationSchemeAdministrationRecord) {
		if (classificationSchemeAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be specified for ClassificationScheme.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().classificationSchemeAdministrationRecord,
				classificationSchemeAdministrationRecord);
	}

	@Override
	public AdministrationRecordResource getAdministrationRecord() {
		return new AdministrationRecordImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().classificationSchemeAdministrationRecord),
				mdrDatabase);
	}

	@Override
	public void setClassificationSchemeTypeName(
			String classificationSchemeTypeName) {
		if (Util.isNull(classificationSchemeTypeName)) {
			throw new IllegalArgumentException(
					"Classification Scheme Type Name must be specified for ClassificationScheme.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().classificationSchemeTypeName,
				mdrDatabase.getUtil().createTypedLiteral(
						classificationSchemeTypeName));
	}

	@Override
	public String getClassificationSchemeTypeName() {
		return getPropertyValue(
				mdrDatabase.getVocabulary().classificationSchemeTypeName)
				.asLiteral().getString();
	}

	@Override
	public void addContaining(
			ClassificationSchemeItemResource classificationSchemeItem) {
		addProperty(mdrDatabase.getVocabulary().containing,
				classificationSchemeItem);
	}

	@Override
	public void removeContaining(
			ClassificationSchemeItemResource classificationSchemeItem) {
		if (classificationSchemeItem == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().containing,
				classificationSchemeItem);
	}

	@Override
	public List<ClassificationSchemeItemResource> getContanining()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().containing);
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
	public ClassificationSchemeResource asMDRResource() {
		return this;
	}

//	@Override
//	public String getSchemeName() {
//		return this.getClassificationSchemeTypeName();
//	}
//
//	@Override
//	public void setSchemeName(String name) {
//		this.setClassificationSchemeTypeName(name);
//	}

	@Override
	public ClassificationSchemeItem createClassificationSchemeItem(
			String typeName, String itemValue) {
		ClassificationSchemeItemResource item = this.mdrDatabase.getResourceFactory()
				.createClassificationSchemeItem(this, typeName, itemValue);
		logger.debug("ClassificationSchemeItem created: {} ", typeName);
		this.addClassifiedBy(item);
		return item;
	}

//	@Override
//	public List<ClassificationSchemeItem> getContainedSchemeItems()
//			throws MDRException {
//		List<ClassificationSchemeItem> itemList;
//		try {
//			itemList = Collections
//					.<ClassificationSchemeItem> unmodifiableList(this
//							.getContanining());
//		} catch (MDRException e) {
//			logger.error(
//					"ClassificationSchemeItems of {} could not be obtained",
//					this.getClassificationSchemeTypeName());
//			throw e;
//		}
//		return itemList;
//	}
}
