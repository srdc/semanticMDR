package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageSectionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.TerminologicalEntryResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * A Terminological Entry applies to an Administered Item in a particular
 * Context. It provides a grouping of Designations and Definitions partitioned
 * into Language Sections, allowing the Administered Item to be named and
 * defined within the Context in multiple languages.
 * 
 * @author anil
 * 
 */
public class TerminologicalEntryImpl extends AbstractMDRResource implements
		TerminologicalEntryResource {

	// TODO terminological entry should NOT have more than one
	// AdministeredItemContext
	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createTerminologicalEntry(LanguageSectionResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param containingTerminologicalEntryLanguage
	 *            Language Section which defines one of the Languages that
	 *            Administered Item is named within a Context
	 * @param mdrDatabase
	 */
	public TerminologicalEntryImpl(Node n, EnhGraph g,
			LanguageSectionResource containingTerminologicalEntryLanguage,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().TerminologicalEntry);
		addContainingTerminologicalEntryLanguage(containingTerminologicalEntryLanguage);
	}

	public TerminologicalEntryImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void addContainingTerminologicalEntryLanguage(
			LanguageSectionResource containingTerminologicalEntryLanguage) {
		if (containingTerminologicalEntryLanguage == null) {
			throw new IllegalArgumentException(
					"Null is not permitted as a value for the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().containingTerminologicalEntryLanguage,
				containingTerminologicalEntryLanguage);
	}

	@Override
	public void removeContainingTerminologicalEntryLanguage(
			LanguageSectionResource containingTerminologicalEntryLanguage) {
		if (containingTerminologicalEntryLanguage == null) {
			throw new IllegalArgumentException(
					"Null is not permitted as a value for the property to be removed.");
		}
		if (listPropertyValues(
				mdrDatabase.getVocabulary().containingTerminologicalEntryLanguage)
				.toSet().size() <= 1) {
			throw new IllegalStateException(
					"At least 1 Language section must be specified for TerminologicalEntry");
		}
		removeProperty(
				mdrDatabase.getVocabulary().containingTerminologicalEntryLanguage,
				containingTerminologicalEntryLanguage);
	}

	@Override
	public List<LanguageSectionResource> getContaninigTerminologicalEntryLanguage()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().containingTerminologicalEntryLanguage);
		return mdrDatabase.getUtil().createList(it,
				LanguageSectionResource.class);
	}

	@Override
	public void addTerminologicalEntryAdministeredItemContext(
			AdministeredItemContextResource terminologicalEntryAdministeredItemContext) {
		if (terminologicalEntryAdministeredItemContext == null) {
			throw new IllegalArgumentException(
					"Null is not permitted as a value for the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().terminologicalEntryAdministeredItemContext,
				terminologicalEntryAdministeredItemContext);
	}

	@Override
	public void removeTerminologicalEntryAdministeredItemContext(
			AdministeredItemContextResource terminologicalEntryAdministeredItemContext) {
		if (terminologicalEntryAdministeredItemContext == null) {
			throw new IllegalArgumentException(
					"Null is not permitted as a value for the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().terminologicalEntryAdministeredItemContext,
				terminologicalEntryAdministeredItemContext);
	}

	@Override
	public List<AdministeredItemContextResource> getTerminologicalEntryAdministeredItemContext()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().terminologicalEntryAdministeredItemContext);
		return mdrDatabase.getUtil().createList(it,
				AdministeredItemContextResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public TerminologicalEntryResource asMDRResource() {
		return this;
	}

}
