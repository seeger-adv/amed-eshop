package de.adv_boeblingen.seegerj.amed.eshop.components;

import java.util.Set;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.Catalog;
import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public class GridListing {
	@Inject
	private Catalog catalog;

	@Property
	private Product product;

	@Parameter(allowNull = true)
	private String filter;

	public Set<Product> getProducts() {
		Filter productFilter = getFilter(this.filter);
		return this.catalog.getProducts(productFilter);
	}

	private Filter getFilter(String input) {
		return null;
	}
}
