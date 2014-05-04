package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.EventLink;

import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;
import de.adv_boeblingen.seegerj.amed.eshop.model.enums.Availability;

public class ShoppingCart {
	@Property
	@SessionState
	private Cart shoppingCart;

	@Component(parameters = { "event=add", "context=item.id" })
	private EventLink add;

	@Component(parameters = { "event=del", "context=item.id" })
	private EventLink del;

	@Component(parameters = { "event=sub", "context=item.id" })
	private EventLink sub;

	@Component(parameters = { "event=checkout" })
	private EventLink checkout;

	@Property
	private Product item;

	public Set<Product> getItems() {
		return this.shoppingCart.getItems().keySet();
	}

	public float getTotal() {
		return getAmount() * this.item.getPrice();
	}

	public int getAmount() {
		return getAmount(this.item);
	}

	private int getAmount(Product product) {
		return this.shoppingCart.getItems().get(product);
	}

	public void onAdd(String columnId) {
		Product item = getItem(columnId);
		shoppingCart.add(item);
	}

	public void onSub(String columnId) {
		Product item = getItem(columnId);
		shoppingCart.remove(item);
	}

	public void onDel(String columnId) {
		Product item = getItem(columnId);
		shoppingCart.clear(item);
	}

	public Object onCheckout() {
		Object redirectTo = Checkout.class;
		for (Product product : getItems()) {
			if (product.getItemsLeft() < getAmount(product)) {
				redirectTo = null;
			}
		}
		return redirectTo;
	}

	private Product getItem(String columnId) {
		int itemId = Integer.parseInt(columnId);
		for (Product item : getItems()) {
			if (itemId == item.getId()) {
				return item;
			}
		}
		return null;
	}
}
