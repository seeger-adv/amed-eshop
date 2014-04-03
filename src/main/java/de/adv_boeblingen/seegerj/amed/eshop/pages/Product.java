package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.corelib.components.EventLink;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.ProductDao;

public class Product {
	@Inject
	private ProductDao catalog;

	@Component(parameters = { "event=add", "context=product.id" })
	private EventLink addToBasket;

	@Persist
	private de.adv_boeblingen.seegerj.amed.eshop.model.database.Product product;

	public de.adv_boeblingen.seegerj.amed.eshop.model.database.Product getProduct() {
		return this.product;
	}

	public String getAvailability() {
		return "Available";
	}

	void onActivate(long productId) {
		this.product = this.catalog.getProduct(productId);
	}

	void onAdd(String productId) {
		System.out.println("added Product(Id:" + productId + ") to Shopping Cart");
	}
}
