package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.Catalog;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public class Index {
	@Inject
	private Catalog catalog;

	@Property
	private Product product;

	public Set<Product> getProducts() {
		return this.catalog.getProducts(null);
	}
}
