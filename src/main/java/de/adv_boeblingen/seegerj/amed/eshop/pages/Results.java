package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.Property;

public class Results {
	@Property
	private String query;

	void onActivate(String query) {
		this.query = query;
	}

	public String getProductFilter() {
		return this.query;
	}
}
