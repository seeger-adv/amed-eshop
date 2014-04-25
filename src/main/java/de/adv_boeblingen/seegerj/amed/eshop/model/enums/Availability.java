package de.adv_boeblingen.seegerj.amed.eshop.model;

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
}
