package tr.com.srdc.mdr.core.impl.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.DatatypeResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * A Datatype is designated by a data type name, and described by a datatype
 * description. The datatype name is usually drawn from some external source,
 * which is designated by a datatype scheme reference. Additional information
 * may optionally be provided using the datatype annotation.
 * 
 * @author anil
 * @author mert
 * 
 */
public class DatatypeImpl extends AbstractMDRResource implements
		DatatypeResource {

	private static final Logger logger = LoggerFactory
			.getLogger(DatatypeImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createDatatype(String, String, String, String)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param datatypeName
	 * @param datatypeDescription
	 *            Optional.
	 * @param datatypeSchemeReference
	 * @param datatypeAnnotation
	 *            Optional.
	 * @param mdrDatabase
	 */
	public DatatypeImpl(Node n, EnhGraph g, String datatypeName,
			String datatypeDescription, String datatypeSchemeReference,
			String datatypeAnnotation, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Datatype);
		setDatatypeName(datatypeName);
		setDatatypeDescription(datatypeDescription);
		setDatatypeSchemeReference(datatypeSchemeReference);
		setDatatypeAnnotation(datatypeAnnotation);
	}

	public DatatypeImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setDatatypeName(String datatypeName) {
		if (Util.isNull(datatypeName)) {
			throw new IllegalArgumentException(
					"Datatype Name must be specified for Datatype");
		}
		setPropertyValue(mdrDatabase.getVocabulary().datatypeName, mdrDatabase
				.getUtil().createTypedLiteral(datatypeName));
	}

	@Override
	public String getDatatypeName() {
		RDFNode datatypeName = getPropertyValue(mdrDatabase.getVocabulary().datatypeName);
		return datatypeName.asLiteral().getString();
	}

	@Override
	public void setDatatypeDescription(String datatypeDescription) {
		setPropertyValue(mdrDatabase.getVocabulary().datatypeDescription,
				mdrDatabase.getUtil().createTypedLiteral(datatypeDescription));
	}

	@Override
	public String getDatatypeDescription() {
		RDFNode datatypeDescription = getPropertyValue(mdrDatabase
				.getVocabulary().datatypeDescription);
		if (datatypeDescription == null) {
			logger.debug("DataType does not have datatypeDescription");
			return null;
		}
		return datatypeDescription.asLiteral().getString();
	}

	@Override
	public void setDatatypeSchemeReference(String datatypeSchemeReference) {
		if (Util.isNull(datatypeSchemeReference)) {
			throw new IllegalArgumentException(
					"Datatype Scheme Reference must be specified for Datatype");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().datatypeSchemeReference,
				mdrDatabase.getUtil().createTypedLiteral(
						datatypeSchemeReference));
	}

	@Override
	public String getDatatypeSchemeReference() {
		RDFNode datatypeSchemeReference = getPropertyValue(mdrDatabase
				.getVocabulary().datatypeSchemeReference);
		return datatypeSchemeReference.asLiteral().getString();
	}

	@Override
	public void setDatatypeAnnotation(String datatypeAnnotation) {
		setPropertyValue(mdrDatabase.getVocabulary().datatypeAnnotation,
				mdrDatabase.getUtil().createTypedLiteral(datatypeAnnotation));
	}

	@Override
	public String getDatatypeAnnotation() {
		RDFNode datatypeAnnotation = getPropertyValue(mdrDatabase
				.getVocabulary().datatypeAnnotation);
		if (datatypeAnnotation == null) {
			logger.debug("DataType does not have datatypeAnnotation");
			return null;
		}
		return datatypeAnnotation.asLiteral().getString();
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public DatatypeResource asMDRResource() {
		return this;
	}

	@Override
	public String getName() {
		return this.getDatatypeName();
	}

	@Override
	public String getSchemeReference() {
		return this.getDatatypeSchemeReference();
	}
}
