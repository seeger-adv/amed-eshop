package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.ProductDao;

public class Product {
	@Inject
	private ProductDao catalog;

	@Persist
	private de.adv_boeblingen.seegerj.amed.eshop.model.database.Product product;

	public de.adv_boeblingen.seegerj.amed.eshop.model.database.Product getProduct() {
		return product;
	}

	void onActivate(long productId) {
		product = catalog.getProduct(productId);
	}
}
