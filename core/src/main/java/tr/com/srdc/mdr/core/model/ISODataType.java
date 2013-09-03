package tr.com.srdc.mdr.core.model;

public enum ISODataType {

	INTEGER("integer"), REAL("real"), BOOLEAN("boolean"), CHARACTER("character"), STRING(
			"characterstring"), TIME("time"), CD("CD"), CD_CV("CD.CV"), TS_DATE(
			"TS.DATE"), TS_DATETIME("TS.DATETIME"), II("II"), INT_NONNEG(
			"INT.NONNEG"), PQ("PQ"), PQ_TIME("PQ.TIME"), IVL_TS("IVL.TS"), IVL_PQ(
			"IVL.PQ"), PIVL_TS("PIVL.TS");

	private final String identifier;

	private ISODataType(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public final String toString() {
		return this.identifier;
	}

}
