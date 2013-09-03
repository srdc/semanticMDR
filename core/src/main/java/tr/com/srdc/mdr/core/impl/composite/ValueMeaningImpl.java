package tr.com.srdc.mdr.core.impl.composite;

import java.util.Calendar;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.composite.PermissibleValue;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.PermissibleValueResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueMeaningResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * Each member of an Enumerated Conceptual Domain has a Value Meaning that
 * provides its distinction from other members. In the example of ISO 3166, the
 * notion of each country as specified would be the Value Meanings. The
 * representation of Value Meanings in a registry shall be independent of (and
 * shall not constrain) their representation in any corresponding Value Domain.
 * A particular Value Meaning may have more than one means of representation by
 * Permissible Values — each from a distinct Enumerated Value Domain.
 * 
 * @author anil
 * 
 */
public class ValueMeaningImpl extends AbstractMDRResource implements
		ValueMeaningResource {

	private static final Logger logger = LoggerFactory
			.getLogger(ValueMeaningImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createValue(String, String, Calendar, Calendar, EnumeratedConceptualDomainResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param valueMeaningIdentifier
	 * @param valueMeaningDescription
	 *            Optional.
	 * @param valueMeaningBeginDate
	 * @param valueMeaningEndDate
	 *            Optional.
	 * @param containedInValueMeaningSet
	 * @param mdrDatabase
	 */
	public ValueMeaningImpl(Node n, EnhGraph g, String valueMeaningIdentifier,
			String valueMeaningDescription, Calendar valueMeaningBeginDate,
			Calendar valueMeaningEndDate,
			EnumeratedConceptualDomainResource containedInValueMeaningSet,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ValueMeaning);
		setValueMeaningIdentifier(valueMeaningIdentifier);
		setValueMeaningDescription(valueMeaningDescription);
		setValueMeaningBeginDate(valueMeaningBeginDate);
		setValueMeaningEndDate(valueMeaningEndDate);
		addContainedInValueMeaningSet(containedInValueMeaningSet);
	}

	public ValueMeaningImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setValueMeaningIdentifier(String valueMeaningIdentifier) {
		if (Util.isNull(valueMeaningIdentifier)) {
			throw new IllegalArgumentException(
					"Identifier should be specified for ValueMeaning.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().valueMeaningIdentifier,
				mdrDatabase.getUtil()
						.createTypedLiteral(valueMeaningIdentifier));
	}

	@Override
	public String getValueMeaningIdentifier() {
		RDFNode valueMeaningIdentifier = getPropertyValue(mdrDatabase
				.getVocabulary().valueMeaningIdentifier);
		return valueMeaningIdentifier.asLiteral().getString();
	}

	@Override
	public void setValueMeaningDescription(String valueMeaningDescription) {
		setPropertyValue(
				mdrDatabase.getVocabulary().valueMeaningDescription,
				mdrDatabase.getUtil().createTypedLiteral(
						valueMeaningDescription));
	}

	@Override
	public String getValueMeaningDescription() {
		RDFNode valueMeaningDescription = getPropertyValue(mdrDatabase
				.getVocabulary().valueMeaningDescription);
		if (valueMeaningDescription == null) {
			logger.debug("ValueMeaning does not have description");
			return null;
		}
		return valueMeaningDescription.asLiteral().getString();
	}

	@Override
	public void setValueMeaningBeginDate(Calendar valueMeaningBeginDate) {
		if (valueMeaningBeginDate == null) {
			throw new IllegalArgumentException(
					"Begin Date should be specified for ValueMeaning");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().valueMeaningBeginDate,
				mdrDatabase.getOntModel().createTypedLiteral(
						DatatypeConverter.printDateTime(valueMeaningBeginDate)));
	}

	@Override
	public Calendar getValueMeaningBeginDate() {
		RDFNode valueMeaningBeginDate = getPropertyValue(mdrDatabase
				.getVocabulary().valueMeaningBeginDate);
		return DatatypeConverter.parseDateTime(valueMeaningBeginDate
				.asLiteral().getLexicalForm());
	}

	@Override
	public void setValueMeaningEndDate(Calendar valueMeaningEndDate) {
		if (valueMeaningEndDate == null) {
			setPropertyValue(mdrDatabase.getVocabulary().valueMeaningEndDate,
					mdrDatabase.getUtil().createTypedLiteral(null));
		} else {
			setPropertyValue(
					mdrDatabase.getVocabulary().valueMeaningEndDate,
					mdrDatabase.getOntModel().createTypedLiteral(
							DatatypeConverter
									.printDateTime(valueMeaningEndDate)));
		}
	}

	@Override
	public Calendar getValueMeaningEndDate() {
		RDFNode valueMeaningEndDate = getPropertyValue(mdrDatabase
				.getVocabulary().valueMeaningEndDate);
		if (valueMeaningEndDate == null) {
			logger.debug("ValueMeaning does not have endDate");
			return null;
		}
		return DatatypeConverter.parseDateTime(valueMeaningEndDate.asLiteral()
				.getLexicalForm());
	}

	@Override
	public void addContainedInValueMeaningSet(
			EnumeratedConceptualDomainResource containedInValueMeaningSet) {
		if (containedInValueMeaningSet == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value for the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().containedInValueMeaningSet,
				containedInValueMeaningSet);
	}

	@Override
	public void removeContainedInValueMeaningSet(
			EnumeratedConceptualDomainResource containedInValueMeaningSet) {
		if (containedInValueMeaningSet == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value for the property to be removed.");
		}
		if (listPropertyValues(
				mdrDatabase.getVocabulary().containedInValueMeaningSet)
				.toList().size() <= 1) {
			throw new IllegalStateException(
					"There should be at least 1 value for the property");
		}
		removeProperty(mdrDatabase.getVocabulary().containedInValueMeaningSet,
				containedInValueMeaningSet);
	}

	@Override
	public List<EnumeratedConceptualDomainResource> getContainedInValueMeaningSets()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().containedInValueMeaningSet);
		return mdrDatabase.getUtil().createList(it,
				EnumeratedConceptualDomainResource.class);
	}

	@Override
	public void addUsedInPermissibleValueMeaning(
			PermissibleValueResource usedInPermissibleValueMeaning) {
		if (usedInPermissibleValueMeaning == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value for the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().usedInPermissibleValueMeaning,
				usedInPermissibleValueMeaning);
	}

	@Override
	public void removeUsedInPermissibleValueMeaning(
			PermissibleValueResource usedInPermissibleValueMeaning) {
		if (usedInPermissibleValueMeaning == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value for the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().usedInPermissibleValueMeaning,
				usedInPermissibleValueMeaning);
	}

	@Override
	public List<PermissibleValueResource> getUsedInPermissibleValueMeanings()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().usedInPermissibleValueMeaning);
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
	public ValueMeaningResource asMDRResource() {
		return this;
	}

	@Override
	public String getDescription() {
		return this.getValueMeaningDescription();
	}

	@Override
	public String getIdentifier() {
		return this.getValueMeaningIdentifier();
	}

	@Override
	public PermissibleValue createPermissibleValue(String valueItem) {
		ValueResource vr = mdrDatabase.getResourceFactory().createValue(
				valueItem);
		PermissibleValueResource pvr = mdrDatabase.getResourceFactory()
				.createPermissibleValue(Calendar.getInstance(), null, vr, this);
		vr.addUsedInPermittedValue(pvr);
		this.addUsedInPermissibleValueMeaning(pvr);
		return pvr;
	}

}
