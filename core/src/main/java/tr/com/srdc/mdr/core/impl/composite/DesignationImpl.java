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
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * The Designation class provides the naming entry of a Language Section in the
 * Terminological Entry for an Administered Item in a particular Context. In
 * other words, it is where the name for an Administered Item is specified in a
 * particular language for a particular Context. Where multiple Designations are
 * provided within the same Language Section, one of them may be specified as
 * the preferred designation.
 * 
 * @author anil
 * 
 */
public class DesignationImpl extends AbstractMDRResource implements
		DesignationResource {

	private static final Logger logger = LoggerFactory
			.getLogger(DesignationImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createDesignation(LanguageSectionResource,String,boolean)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param namingEntryOf
	 *            LanguageSection where Designation belongs to
	 * @param name
	 *            Name of the Designation
	 * @param preferredDesignation
	 *            Optional. If given <code>null</code>, default value is
	 *            <code>false</code>
	 * @param mdrDatabase
	 */
	public DesignationImpl(Node n, EnhGraph g,
			LanguageSectionResource namingEntryOf, String name,
			boolean preferredDesignation, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Designation);
		setNamingEntryOf(namingEntryOf);
		setName(name);
		setPreferredDesignation(preferredDesignation);
	}

	public DesignationImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setNamingEntryOf(LanguageSectionResource namingEntryOf) {
		if (namingEntryOf == null) {
			throw new IllegalArgumentException(
					"Language Section must be specified for Designation");
		}
		setPropertyValue(mdrDatabase.getVocabulary().namingEntryOf,
				namingEntryOf);
	}

	@Override
	public LanguageSectionResource getNamingEntryOf() {
		return new LanguageSectionImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().namingEntryOf),
				mdrDatabase);
	}

	@Override
	public void setName(String name) {
		if (Util.isNull(name)) {
			throw new IllegalArgumentException(
					"Name must be specified for Designation.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().name, mdrDatabase
				.getUtil().createTypedLiteral(name));
	}

	@Override
	public String getName() {
		return getPropertyValue(mdrDatabase.getVocabulary().name).asLiteral()
				.getString();
	}

	@Override
	public void setPreferredDesignation(boolean preferredDesignation) {
		setPropertyValue(
				mdrDatabase.getVocabulary().preferredDesignation,
				mdrDatabase.getOntModel().createLiteral(
						DatatypeConverter.printBoolean(preferredDesignation)));
	}

	@Override
	public boolean isPreferredDesignation() {
		RDFNode preferredDesignation = getPropertyValue(mdrDatabase
				.getVocabulary().preferredDesignation);
		if (preferredDesignation == null) {
			logger.debug("Designation does not have preferredDesignation");
			return false;
		}
		return preferredDesignation.asLiteral().getBoolean();
	}

	@Override
	public void setSpecificallyReferencing(
			DefinitionResource specificallyReferencing) {
		setPropertyValue(mdrDatabase.getVocabulary().specificallyReferencing,
				specificallyReferencing);
	}

	@Override
	public DefinitionResource getSpecificallyReferencing() {
		Resource specificallyReferencing = getPropertyResourceValue(mdrDatabase
				.getVocabulary().specificallyReferencing);
		if (specificallyReferencing == null) {
			logger.debug("Designation is not specifically referencing Definition");
			return null;
		}
		return new DefinitionImpl(specificallyReferencing, mdrDatabase);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public DesignationResource asMDRResource() {
		return this;
	}

}
