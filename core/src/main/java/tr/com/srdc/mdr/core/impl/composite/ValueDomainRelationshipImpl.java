package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.impl.ai.ValueDomainImpl;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueDomainRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueDomainRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * A Value Domain may be associated with other Value Domains, via the Value
 * Domain Relationship. The nature of the relationship is described using the
 * Value Domain relationship type description.
 * 
 * @author anil
 * 
 */
public class ValueDomainRelationshipImpl extends AbstractMDRResource implements
		ValueDomainRelationshipResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createValueDomainRelationship(String)} to avoid
	 * entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param valueDomainRelationshipTypeDescription
	 *            Type Description of the Relationship Between
	 *            {@link ValueDomainImpl}s
	 * @param valueDomainRelationshipTypeDescription
	 * @param mdrDatabase
	 */
	public ValueDomainRelationshipImpl(Node n, EnhGraph g,
			String valueDomainRelationshipTypeDescription,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ValueDomainRelationship);
		setValueDomainRelationshipTypeDescription(valueDomainRelationshipTypeDescription);
	}

	public ValueDomainRelationshipImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setValueDomainRelationshipTypeDescription(
			String valueDomainRelationshipTypeDescription) {
		if (Util.isNull(valueDomainRelationshipTypeDescription)) {
			throw new IllegalArgumentException(
					"Type Description must be specified for ValueDomainRelationship.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().valueDomainRelationshipTypeDescription,
				mdrDatabase.getUtil().createTypedLiteral(
						valueDomainRelationshipTypeDescription));
	}

	@Override
	public String getValueDomainRelationshipTypeDescription() {
		RDFNode valueDomainRelationshipTypeDescription = getPropertyValue(mdrDatabase
				.getVocabulary().valueDomainRelationshipTypeDescription);
		return valueDomainRelationshipTypeDescription.asLiteral().getString();
	}

	@Override
	public void addDescribingValueDomainRelationship(
			ValueDomainRelationshipAssociationResource describingValueDomainRelationship) {
		if (describingValueDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().describingValueDomainRelationship,
				describingValueDomainRelationship);
	}

	@Override
	public void removeDescribingValueDomainRelationship(
			ValueDomainRelationshipAssociationResource describingValueDomainRelationship) {
		if (describingValueDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().describingValueDomainRelationship,
				describingValueDomainRelationship);
	}

	@Override
	public List<ValueDomainRelationshipAssociationResource> getDescribingValueDomainRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().describingValueDomainRelationship);
		return mdrDatabase.getUtil().createList(it,
				ValueDomainRelationshipAssociationResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ValueDomainRelationshipResource asMDRResource() {
		return this;
	}
}
