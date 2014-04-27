package de.adv_boeblingen.seegerj.amed.eshop.model.enums;

public enum Availability {
	AVAILABLE, FEW, OUT_OF_STOCK, REORDERED, UNKNOWN;

	public String getBadge() {
		switch (this) {
		case AVAILABLE:
			return "success";
		case FEW:
			return "warning";
		case OUT_OF_STOCK:
			return "danger";
		case REORDERED:
			return "primary";
		default:
			return "default";
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case AVAILABLE:
			return "Available";
		case FEW:
			return "Few";
		case OUT_OF_STOCK:
			return "Out of Stock";
		case REORDERED:
			return "Reordered";
		default:
			return "Unknown";
		}
	}
}
