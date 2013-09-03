package tr.com.srdc.mdr.core.util;

import tr.com.srdc.mdr.core.MDRConstants;

/**
 * Used to describe mapping relation.
 * 
 * SubjectOID, Relation, ObjectOID
 * 
 */
public class MappingRelation {
	private String subjectOID;
	private String relationType;
	private String objectOID;

	public String getObjectOID() {
		return objectOID;
	}

	public void setObjectOID(String objectOID) {
		this.objectOID = objectOID;
	}

	public String getSubjectOID() {
		return subjectOID;
	}

	public void setSubjectOID(String subjectOID) {
		this.subjectOID = subjectOID;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	/**
	 * @return whether this relation is symmetric or not.
	 */
	public boolean isSymmetric() {
		if (relationType.equals(MDRConstants.SKOS_MAPPING_RELATION)
				|| relationType.equals(MDRConstants.SKOS_CLOSE_MATCH)
				|| relationType.equals(MDRConstants.SKOS_EXACT_MATCH)
				|| relationType.equals(MDRConstants.SKOS_RELATED_MATCH)) {
			return true;
		}
		return false;
	}
}
