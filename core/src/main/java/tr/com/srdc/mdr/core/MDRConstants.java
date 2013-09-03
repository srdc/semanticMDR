package tr.com.srdc.mdr.core;

import java.util.ArrayList;
import java.util.List;

public class MDRConstants {
	// used during the creation of classification schemes
	public static final String CS_EXTRACTION_SPEC = "ExtractionSpecification";
	public static final String CS_MAPPING = "Mapping";

	// used to describe the type of the extraction spec.
	public static final String EXTRACTION_TYPE_XPATH = "XPATH";
	public static final String EXTRACTION_TYPE_BRIDG_PROPRIETARY = "PROPRIETARY(BRIDG)";

	// used to describe the content model types
	public static final String CONTENT_MODEL_EXTRACTION_HL7_CCD = "HL7 CCD";
	public static final String CONTENT_MODEL_EXTRACTION_BRIDG = "BRIDGModel";
	// used to describe the metadata registries
	public static final String CONTENT_MODEL_CDASH = "CDASH";
	public static final String CONTENT_MODEL_HITSP_C154 = "HITSP C154";
	public static final String CONTENT_MODEL_BRIDG = "BRIDG";
	public static final String CONTENT_MODEL_SDTM = "SDTM";;
	public static final String CONTENT_MODEL_OMOP = "OMOP";
	public static final String CONTENT_MODEL_SALUS_CIM = "SALUS CIM";

	// oids for content models and registries
	public static final String OID_BRIDG_EXTRACTION = "2.16.840.1.113883.1.8";
	public static final String OID_BRIDG_MAPPING = "2.16.840.1.113883.1.80";
	public static final String OID_HL7_CCD = "2.16.840.1.113883.10.20.1";
	public static final String OID_CDASH = "1.11.111.1.111111.1.0";
	public static final String OID_HITSP_C154 = "1.11.111.1.111111.1.1";
	public static final String OID_SDTM = "1.11.111.1.111111.1.2";
	public static final String OID_OMOP = "1.11.111.1.111111.1.3";

	public static final String SKOS_MAPPING_RELATION = "skos:mappingRelation";
	public static final String SKOS_CLOSE_MATCH = "skos:closeMatch";
	public static final String SKOS_EXACT_MATCH = "skos:exactMatch";
	public static final String SKOS_BROAD_MATCH = "skos:broadMatch";
	public static final String SKOS_NARROW_MATCH = "skos:narrowMatch";
	public static final String SKOS_RELATED_MATCH = "skos:relatedMatch";

	public static List<String> getMappingRelations() {
		List<String> relations = new ArrayList<String>();
		relations.add(SKOS_MAPPING_RELATION);
		relations.add(SKOS_CLOSE_MATCH);
		relations.add(SKOS_EXACT_MATCH);
		relations.add(SKOS_BROAD_MATCH);
		relations.add(SKOS_NARROW_MATCH);
		relations.add(SKOS_RELATED_MATCH);
		return relations;
	}

	public static String getContentModelFromOID(String oid) {
		if (oid.equals(OID_BRIDG_EXTRACTION)) {
			return CONTENT_MODEL_EXTRACTION_BRIDG;
		} else if (oid.equals(OID_BRIDG_MAPPING)) {
			return CONTENT_MODEL_BRIDG;
		} else if (oid.equals(OID_CDASH)) {
			return CONTENT_MODEL_CDASH;
		} else if (oid.equals(OID_HITSP_C154)) {
			return CONTENT_MODEL_HITSP_C154;
		} else if (oid.equals(OID_HL7_CCD)) {
			return CONTENT_MODEL_EXTRACTION_HL7_CCD;
		} else if (oid.equals(OID_OMOP)) {
			return CONTENT_MODEL_OMOP;
		} else if (oid.equals(OID_SDTM)) {
			return CONTENT_MODEL_SDTM;
		}
		return null;
	}

	public static String getOIDFromContentModel(String contentModel) {
		if (contentModel.equals(CONTENT_MODEL_EXTRACTION_BRIDG)) {
			return OID_BRIDG_EXTRACTION;
		} else if (contentModel.equals(CONTENT_MODEL_BRIDG)) {
			return OID_BRIDG_MAPPING;
		} else if (contentModel.equals(CONTENT_MODEL_CDASH)) {
			return OID_CDASH;
		} else if (contentModel.equals(CONTENT_MODEL_HITSP_C154)) {
			return OID_HITSP_C154;
		} else if (contentModel.equals(CONTENT_MODEL_EXTRACTION_HL7_CCD)) {
			return OID_HL7_CCD;
		} else if (contentModel.equals(CONTENT_MODEL_OMOP)) {
			return OID_OMOP;
		} else if (contentModel.equals(CONTENT_MODEL_SDTM)) {
			return OID_SDTM;
		}
		return null;
	}
}
