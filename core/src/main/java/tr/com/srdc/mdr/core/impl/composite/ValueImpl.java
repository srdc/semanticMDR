package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.PermissibleValueResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * This is the actual value associated with a Permissible Value in an Enumerated
 * Value Domain.
 * 
 * @author anil
 * 
 */
public class ValueImpl extends AbstractMDRResource implements ValueResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createValue(String)} to avoid entering illegal
	 * states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param valueItem
	 * @param mdrDatabase
	 */
	public ValueImpl(Node n, EnhGraph g, String valueItem,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Value);
		setValueItem(valueItem);
	}

	public ValueImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setValueItem(String valueItem) {
		if (Util.isNull(valueItem)) {
			throw new IllegalArgumentException(
					"Value Item must be specified for Value.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().valueItem, mdrDatabase
				.getUtil().createTypedLiteral(valueItem));
	}

	@Override
	public String getValueItem() {
		RDFNode valueItem = getPropertyValue(mdrDatabase.getVocabulary().valueItem);
		return valueItem.asLiteral().getString();
	}

	@Override
	public void addUsedInPermittedValue(
			PermissibleValueResource usedInPermittedValue) {
		if (usedInPermittedValue == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value for the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().usedInPermittedValue,
				usedInPermittedValue);
	}

	@Override
	public void removeUsedInPermittedValue(
			PermissibleValueResource usedInPermittedValue) {
		if (usedInPermittedValue == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value for the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().usedInPermittedValue,
				usedInPermittedValue);
	}

	@Override
	public List<PermissibleValueResource> getUsedInPermittedValues()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().usedInPermittedValue);
		return mdrDatabase.getUtil().createList(it,
				PermissibleValueResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ValueResource asMDRResource() {
		return this;
	}

}
