package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.EventLink;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import de.adv_boeblingen.seegerj.amed.eshop.api.ProductDao;
import de.adv_boeblingen.seegerj.amed.eshop.model.Availability;
import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;

public class Product {
	@Inject
	private ProductDao catalog;

	@Component(parameters = { "event=add", "context=product.id" })
	private EventLink addToBasket;

	@Persist
	private de.adv_boeblingen.seegerj.amed.eshop.model.database.Product product;

	@Inject
	private PageRenderLinkSource linkSource;

	@Property
	@SessionState
	private Cart shoppingCart;

	public de.adv_boeblingen.seegerj.amed.eshop.model.database.Product getProduct() {
		return this.product;
	}

	public Availability getAvailability() {
		return Availability.AVAILABLE;
	}

	void onActivate(long productId) {
		this.product = this.catalog.getProduct(productId);
	}

	Object onAdd(String productId) {
		this.shoppingCart.add(product);
		return this.linkSource.createPageRenderLinkWithContext(Product.class, productId);
	}
}
