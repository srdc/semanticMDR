package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.composite.DefinitionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DesignationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageIdentificationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageSectionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.TerminologicalEntryResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * If a registry supports multiple languages, the language(s) associated with
 * particular names and definitions need to be identified. A Language Section
 * partitions a Terminological Entry by Language. A language section language
 * identifier identifies the Language associated with a particular Language
 * Section. A Language Section contains zero or more Designations. A Language
 * Section contains zero or more Definitions.
 * 
 * @author anil
 * 
 */
public class LanguageSectionImpl extends AbstractMDRResource implements
		LanguageSectionResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createLanguageSection(LanguageIdentificationResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param languageSectionLanguageIdentifier
	 *            {@link LanguageIdentificationResource} identifying the
	 *            LanguageSection.
	 * @param mdrDatabase
	 */
	public LanguageSectionImpl(Node n, EnhGraph g,
			LanguageIdentificationResource languageSectionLanguageIdentifier,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().LanguageSection);
		setLanguageSectionLanguageIdentifier(languageSectionLanguageIdentifier);
	}

	public LanguageSectionImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setLanguageSectionLanguageIdentifier(
			LanguageIdentificationResource languageSectionLanguageIdentifier) {
		if (languageSectionLanguageIdentifier == null) {
			throw new IllegalArgumentException(
					"LanguageIdentification must be specified for LanguageSection");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().languageSectionLanguageIdentifier,
				languageSectionLanguageIdentifier);
	}

	@Override
	public LanguageIdentificationResource getLanguageSectionLanguageIdentifier() {
		return new LanguageIdentificationImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().languageSectionLanguageIdentifier),
				mdrDatabase);
	}

	@Override
	public void addPartitioning(TerminologicalEntryResource partitioning) {
		if (partitioning == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().partitioning, partitioning);
	}

	@Override
	public void removePartitioning(TerminologicalEntryResource partitioning) {
		if (partitioning == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed");
		}
		removeProperty(mdrDatabase.getVocabulary().partitioning, partitioning);
	}

	@Override
	public List<TerminologicalEntryResource> getPartitionings()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().partitioning);
		return mdrDatabase.getUtil().createList(it,
				TerminologicalEntryResource.class);
	}

	@Override
	public void addContainingNameEntry(DesignationResource containingNameEntry) {
		if (containingNameEntry == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().containingNameEntry,
				containingNameEntry);
	}

	@Override
	public void removeContainingNameEntry(
			DesignationResource containingNameEntry) {
		if (containingNameEntry == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed");
		}
		removeProperty(mdrDatabase.getVocabulary().containingNameEntry,
				containingNameEntry);
	}

	@Override
	public List<DesignationResource> getContainingNameEntry()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().containingNameEntry);
		return mdrDatabase.getUtil().createList(it, DesignationResource.class);
	}

	@Override
	public void addContainigDefinitionEntry(
			DefinitionResource containingDefinitionEntry) {
		if (containingDefinitionEntry == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().containingDefinitionEntry,
				containingDefinitionEntry);
	}

	@Override
	public void removeContainigDefinitionEntry(
			DefinitionResource containingDefinitionEntry) {
		if (containingDefinitionEntry == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed");
		}
		removeProperty(mdrDatabase.getVocabulary().containingDefinitionEntry,
				containingDefinitionEntry);
	}

	@Override
	public List<DefinitionResource> getContainigDefinitionEntry()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().containingDefinitionEntry);
		return mdrDatabase.getUtil().createList(it, DefinitionResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public LanguageSectionResource asMDRResource() {
		return this;
	}

}
