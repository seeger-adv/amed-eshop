package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.EventLink;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.ProductDao;
import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Item;

public class Product {
	@Inject
	private ProductDao catalog;

	@Component(parameters = { "event=add", "context=product.id" })
	private EventLink addToBasket;

	@Persist
	private de.adv_boeblingen.seegerj.amed.eshop.model.database.Product product;

	@Property
	@SessionState
	private Cart shoppingCart;

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
		Item item = new Item();
		item.setProduct(this.product);
		item.setAmount(1);
		this.shoppingCart.getItems().add(item);
	}
}
