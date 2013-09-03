package tr.com.srdc.mdr.core.impl.composite;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.UnitOfMeasureResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * If meaningful, a Value Domain may be associated with a Unit of Measure — the
 * unit in which any associated Data Element values are specified. The unit is
 * designated by a unit of measure name. When specified, the unit must be
 * consistent with the dimensionality specified in the corresponding Conceptual
 * Domain. Optionally, a unit of measure precision may be specified, as the
 * number of decimal places to be supported in the associated Data Element
 * values. This precision shall be considered a default that may be overridden
 * for any particular Data Element.
 * 
 * @author anil
 * 
 */
public class UnitOfMeasureImpl extends AbstractMDRResource implements
		UnitOfMeasureResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createDatatype(String, Integer)} to avoid
	 * entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param unitOfMeasureName
	 * @param unitOfMeasurePrecision
	 * @param mdrDatabase
	 */
	public UnitOfMeasureImpl(Node n, EnhGraph g, String unitOfMeasureName,
			Integer unitOfMeasurePrecision, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().UnitOfMeasure);
		setUnitOfMeasureName(unitOfMeasureName);
		setUnitOfMeasurePrecision(unitOfMeasurePrecision);
	}

	public UnitOfMeasureImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setUnitOfMeasureName(String unitOfMeasureName) {
		if (Util.isNull(unitOfMeasureName)) {
			throw new IllegalArgumentException(
					"Name must be speicifed for UnitOfMeasure");
		}
		setPropertyValue(mdrDatabase.getVocabulary().unitOfMeasureName,
				mdrDatabase.getUtil().createTypedLiteral(unitOfMeasureName));
	}

	@Override
	public String getUnitOfMeasureName() {
		RDFNode unitOfMeasureName = getPropertyValue(mdrDatabase
				.getVocabulary().unitOfMeasureName);
		return unitOfMeasureName.asLiteral().getString();
	}

	@Override
	public void setUnitOfMeasurePrecision(Integer unitOfMeasurePrecision) {
		if (unitOfMeasurePrecision == null) {
			throw new IllegalArgumentException(
					"Precision must be specified for UnitOfMeasure");
		}
		setPropertyValue(mdrDatabase.getVocabulary().unitOfMeasurePrecision,
				mdrDatabase.getUtil()
						.createTypedLiteral(unitOfMeasurePrecision));
	}

	@Override
	public Integer getUnitOfMeasurePrecision() {
		RDFNode unitOfMeasurePrecision = getPropertyValue(mdrDatabase
				.getVocabulary().unitOfMeasurePrecision);
		return unitOfMeasurePrecision.asLiteral().getInt();
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public UnitOfMeasureResource asMDRResource() {
		return this;
	}

	@Override
	public String getName() {
		return this.getUnitOfMeasureName();
	}

	@Override
	public int getPrecision() {
		return this.getUnitOfMeasurePrecision();
	}

}
