package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.LanguageSection;
import tr.com.srdc.mdr.core.impl.ai.AdministeredItemImpl;
import tr.com.srdc.mdr.core.impl.ai.ContextImpl;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.TerminologicalEntryResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * This class is additionally created to handle the n-ary relation between
 * {@link AdministeredItemImpl}, {@link TerminologicalEntryImpl} and
 * {@link ContextImpl}. Each {@link AdministeredItemContextImpl} must have one
 * {@link ContextImpl} and one {@link TerminologicalEntryImpl}. Each
 * {@link AdministeredItemImpl} must have at least one
 * {@link AdministeredItemContextImpl}.
 * 
 * @author anil
 * 
 */
public class AdministeredItemContextImpl extends AbstractMDRResource implements
		AdministeredItemContextResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createTerminologicalEntry(LanguageSection)} to
	 * avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param context
	 * @param terminologicalEntry
	 * @param mdrDatabase
	 */
	public AdministeredItemContextImpl(Node n, EnhGraph g,
			ContextResource context,
			TerminologicalEntryResource terminologicalEntry,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().AdministeredItemContext);
		setContext(context);
		setTerminologicalEntry(terminologicalEntry);
	}

	public AdministeredItemContextImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setContext(ContextResource context) {
		if (context == null) {
			throw new IllegalArgumentException(
					"Context cannot be null for AdministeredItemContext");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().administeredItemContextContext,
				context);
	}

	@Override
	public ContextResource getContext() {
		return new ContextImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().administeredItemContextContext),
				mdrDatabase);
	}

	@Override
	public void setTerminologicalEntry(
			TerminologicalEntryResource terminologicalEntry) {
		if (terminologicalEntry == null) {
			throw new IllegalArgumentException(
					"TerminologicalEntry cannot be null for AdministeredItemContext");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().administeredItemContextTerminologicalEntry,
				terminologicalEntry);
	}

	@Override
	public TerminologicalEntryResource getTerminologicalEntry() {
		return new TerminologicalEntryImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().administeredItemContextTerminologicalEntry),
				mdrDatabase);
	}

	@Override
	public void addGrouping(AdministeredItemResource administeredItem) {
		addProperty(mdrDatabase.getVocabulary().grouping, administeredItem);
	}

	@Override
	public void removeGrouping(AdministeredItemResource administeredItem) {
		if (administeredItem == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().grouping, administeredItem);
	}

	@Override
	public List<AdministeredItemResource> getGroupings() throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().grouping);
		return mdrDatabase.getUtil().createList(it,
				AdministeredItemResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public AdministeredItemContextResource asMDRResource() {
		return this;
	}

}
