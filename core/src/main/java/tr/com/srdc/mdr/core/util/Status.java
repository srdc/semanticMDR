package tr.com.srdc.mdr.core.util;

public enum Status {
	ALL("ALL"), Application("Application"), Candidate("Candidate"), Proposed(
			"Proposed"), Qualified("Qualified"), Standard("Standard"), StandardizedElsewhere(
			"StandardizedElsewhere"), Superceded("Superceded"), Suspended(
			"Suspended");

	private final String statusEnum;

	private Status(String abbreviation) {
		this.statusEnum = abbreviation;
	}

	@Override
	public final String toString() {
		return this.statusEnum;
	}

}
