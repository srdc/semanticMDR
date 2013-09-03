package tr.com.srdc.mdr.core.impl.composite;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.DefinitionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DesignationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageSectionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ReferenceDocumentResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * The Definition class provides the defining entry of a Language Section in the
 * Terminological Entry for an Administered Item in a particular Context. In
 * other words, it is where the definition for an Administered Item is specified
 * in a particular language for a particular Context. Where multiple Definitions
 * are provided within the same Language Section, one of them may be specified
 * as the preferred definition.
 * 
 * @author anil
 * 
 */
public class DefinitionImpl extends AbstractMDRResource implements
		DefinitionResource {

	private static final Logger logger = LoggerFactory
			.getLogger(DefinitionImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createDefinition(LanguageSectionResource,String,boolean)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param definingEntryOf
	 *            Language section of which Definiton belongs to
	 * @param definitionText
	 *            Definition Test
	 * @param preferredDefinition
	 *            Optional. default value <code>false</code> if not given
	 * @param definitionSourceReference
	 *            Optional.
	 * @param mdrDatabase
	 */
	public DefinitionImpl(Node n, EnhGraph g,
			LanguageSectionResource definingEntryOf, String definitionText,
			boolean preferredDefinition,
			ReferenceDocumentResource definitionSourceReference,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Definition);
		setDefiningEntryOf(definingEntryOf);
		setDefinitionText(definitionText);
		setPreferredDefinition(preferredDefinition);
		setDefinitionSourceReference(definitionSourceReference);
	}

	public DefinitionImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setDefiningEntryOf(LanguageSectionResource definingEntryOf) {
		if (definingEntryOf == null) {
			throw new IllegalArgumentException(
					"Language Section must be specified for Definition");
		}
		setPropertyValue(mdrDatabase.getVocabulary().definingEntryOf,
				definingEntryOf);
	}

	@Override
	public LanguageSectionResource getDefiningEntryOf() {
		return new LanguageSectionImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().definingEntryOf),
				mdrDatabase);
	}

	@Override
	public void setDefinitionText(String definitionText) {
		if (Util.isNull(definitionText)) {
			throw new IllegalArgumentException(
					"Definition Text must be specified for Definition.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().definitionText,
				mdrDatabase.getUtil().createTypedLiteral(definitionText));
	}

	@Override
	public String getDefinitionText() {
		return getPropertyValue(mdrDatabase.getVocabulary().definitionText)
				.asLiteral().getString();
	}

	@Override
	public void setPreferredDefinition(boolean preferredDefinition) {
		setPropertyValue(
				mdrDatabase.getVocabulary().preferredDefinition,
				mdrDatabase.getOntModel().createLiteral(
						DatatypeConverter.printBoolean(preferredDefinition)));
	}

	@Override
	public boolean isPreferredDefinition() {
		RDFNode preferredDefinition = getPropertyValue(mdrDatabase
				.getVocabulary().preferredDefinition);
		if (preferredDefinition == null) {
			logger.debug("Definition does not have preferredDefinition");
			return false;
		}
		return preferredDefinition.asLiteral().getBoolean();
	}

	@Override
	public void setDefinitionSourceReference(
			ReferenceDocumentResource definitionSourceReference) {
		setPropertyValue(mdrDatabase.getVocabulary().definitionSourceReference,
				definitionSourceReference);
	}

	@Override
	public ReferenceDocumentResource getDefinitionSourceReference() {
		Resource definitionSourceReference = getPropertyResourceValue(mdrDatabase
				.getVocabulary().definitionSourceReference);
		if (definitionSourceReference == null) {
			logger.debug("Definition does not have definitionSourceReference");
			return null;
		}
		return new ReferenceDocumentImpl(definitionSourceReference, mdrDatabase);
	}

	@Override
	public void setSpecificallyUsing(DesignationResource specificallyUsing) {
		setPropertyValue(mdrDatabase.getVocabulary().specificallyUsing,
				specificallyUsing);
	}

	@Override
	public DesignationResource getSpecificallyUsing() {
		Resource specificallyUsing = getPropertyResourceValue(mdrDatabase
				.getVocabulary().specificallyUsing);
		if (specificallyUsing == null) {
			logger.debug("Definition is not specifically using Designation");
			return null;
		}
		return new DesignationImpl(specificallyUsing, mdrDatabase);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public DefinitionResource asMDRResource() {
		return this;
	}

}
