package tr.com.srdc.mdr.core.impl.composite;

import java.util.Calendar;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedValueDomainResource;
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
 * A Permissible Value is an expression of a Value Meaning within an Enumerated
 * Value Domain. It is one of a set of such values that comprises an Enumerated
 * Value Domain. Each Permissible Value is associated with a Value Meaning.
 * 
 * @author anil
 * 
 */
public class PermissibleValueImpl extends AbstractMDRResource implements
		PermissibleValueResource {

	private static final Logger logger = LoggerFactory
			.getLogger(PermissibleValueImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createPermissibleValue(Calendar, Calendar, ValueResource, ValueMeaningResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param permissibleValueBeginDate
	 * @param permissibleValueEndDate
	 *            Optional.
	 * @param hasPermittedValue
	 *            Value of the Enumeration
	 * @param hasPermissibleValueMeaning
	 *            meaning of the Enumeration Value
	 * @param mdrDatabase
	 * 
	 */
	public PermissibleValueImpl(Node n, EnhGraph g,
			Calendar permissibleValueBeginDate,
			Calendar permissibleValueEndDate, ValueResource hasPermittedValue,
			ValueMeaningResource hasPermissibleValueMeaning,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().PermissibleValue);
		setPermissibleValueBeginDate(permissibleValueBeginDate);
		setPermissibleValueEndDate(permissibleValueEndDate);
		setHasPermissibleValueMeaning(hasPermissibleValueMeaning);
		setHasPermittedValue(hasPermittedValue);
	}

	public PermissibleValueImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setPermissibleValueBeginDate(Calendar permissibleValueBeginDate) {
		if (permissibleValueBeginDate == null) {
			throw new IllegalArgumentException(
					"Permissible Value Begin Date must be specified for PermissibleValue");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().permissibleValueBeginDate,
				mdrDatabase.getOntModel().createTypedLiteral(
						DatatypeConverter
								.printDateTime(permissibleValueBeginDate)));
	}

	@Override
	public Calendar getPermissibleValueBeginDate() {
		RDFNode permissibleValueBeginDate = getPropertyValue(mdrDatabase
				.getVocabulary().permissibleValueBeginDate);
		return DatatypeConverter.parseDateTime(permissibleValueBeginDate
				.asLiteral().getLexicalForm());
	}

	@Override
	public void setPermissibleValueEndDate(Calendar permissibleValueEndDate) {
		if (permissibleValueEndDate == null) {
			setPropertyValue(
					mdrDatabase.getVocabulary().permissibleValueEndDate,
					mdrDatabase.getUtil().createTypedLiteral(null));
		} else {
			setPropertyValue(
					mdrDatabase.getVocabulary().permissibleValueEndDate,
					mdrDatabase.getOntModel().createTypedLiteral(
							DatatypeConverter
									.printDateTime(permissibleValueEndDate)));
		}
	}

	@Override
	public Calendar getPermissibleValueEndDate() {
		RDFNode permissibleValueEndDate = getPropertyValue(mdrDatabase
				.getVocabulary().permissibleValueEndDate);
		if (permissibleValueEndDate == null) {
			logger.debug("PermissibleValue does not have permissibleValueEndDate");
			return null;
		}
		return DatatypeConverter.parseDateTime(permissibleValueEndDate
				.asLiteral().getLexicalForm());
	}

	@Override
	public void setHasPermittedValue(ValueResource hasPermittedValue) {
		if (hasPermittedValue == null) {
			throw new IllegalArgumentException(
					"Value must be specified for PermissibleValue.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().hasPermittedValue,
				hasPermittedValue);
	}

	@Override
	public ValueResource getHasPermittedValue() {
		Resource hasPermittedValue = getPropertyResourceValue(mdrDatabase
				.getVocabulary().hasPermittedValue);
		return new ValueImpl(hasPermittedValue, mdrDatabase);
	}

	@Override
	public void setHasPermissibleValueMeaning(
			ValueMeaningResource hasPermissibleValueMeaning) {
		if (hasPermissibleValueMeaning == null) {
			throw new IllegalArgumentException(
					"Value Meaning must be specified for PermissibleValue.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().hasPermissibleValueMeaning,
				hasPermissibleValueMeaning);
	}

	@Override
	public ValueMeaningResource getHasPermissibleValueMeaning() {
		Resource hasPermissibleValueMeaning = getPropertyResourceValue(mdrDatabase
				.getVocabulary().hasPermissibleValueMeaning);
		return new ValueMeaningImpl(hasPermissibleValueMeaning, mdrDatabase);
	}

	@Override
	public void addContainedInPermissibleValueSet(
			EnumeratedValueDomainResource containedInPermissibleValueSet) {
		if (containedInPermissibleValueSet == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().containedInPermissibleValueSet,
				containedInPermissibleValueSet);
	}

	@Override
	public void removeContainedInPermissibleValueSet(
			EnumeratedValueDomainResource containedInPermissibleValueSet) {
		if (containedInPermissibleValueSet == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().containedInPermissibleValueSet,
				containedInPermissibleValueSet);
	}

	@Override
	public List<EnumeratedValueDomainResource> getContainedInPermissibleValueSets()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().containedInPermissibleValueSet);
		return mdrDatabase.getUtil().createList(it,
				EnumeratedValueDomainResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public void delete() {
		this.getHasPermittedValue().delete();
		this.remove();
	}

	@Override
	public PermissibleValueResource asMDRResource() {
		return this;
	}

	@Override
	public Calendar getBeginDate() {
		return this.getPermissibleValueBeginDate();
	}

	@Override
	public Calendar getEndDate() {
		return this.getPermissibleValueEndDate();
	}

	@Override
	public String getValueMeaningIdentifier() {
		return this.getHasPermissibleValueMeaning().getValueMeaningIdentifier();
	}

	@Override
	public String getValueItem() {
		return this.getHasPermittedValue().getValueItem();
	}

	@Override
	public String getValueMeaningDescription() {
		return this.getHasPermissibleValueMeaning()
				.getValueMeaningDescription();
	}
}
