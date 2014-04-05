package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.EventLink;

import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public class ShoppingCart {
	@Property
	@SessionState
	private Cart shoppingCart;

	@Component(parameters = { "event=add", "context=item.id" })
	private EventLink add;

	@Component(parameters = { "event=sub", "context=item.id" })
	private EventLink sub;

	@Property
	private Product item;

	public Set<Product> getItems() {
		return this.shoppingCart.getItems().keySet();
	}

	public float getTotal() {
		return getAmount() * this.item.getPrice();
	}

	public int getAmount() {
		return this.shoppingCart.getItems().get(this.item);
	}

	public void onAdd(String columnId) {
		Product item = getItem(columnId);
		shoppingCart.add(item);
	}

	public void onSub(String columnId) {
		Product item = getItem(columnId);
		shoppingCart.remove(item);
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
