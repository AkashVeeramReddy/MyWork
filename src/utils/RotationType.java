package utils;

public enum RotationType {
	
	CLOCK_WISE,
	ANTI_CLOCKWISE
	;
	
	public static RotationType getInverseRotationType(RotationType rotationType) {
		switch (rotationType) {
		case CLOCK_WISE:
			return ANTI_CLOCKWISE;
		case ANTI_CLOCKWISE:
			return CLOCK_WISE;
		default:
			return null;
		}
	}
}
