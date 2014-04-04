package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Collection;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.EventLink;

import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Item;

public class ShoppingCart {
	@Property
	@SessionState
	private Cart shoppingCart;

	@Component(parameters = { "event=add", "context=item.id" })
	private EventLink add;

	@Component(parameters = { "event=sub", "context=item.id" })
	private EventLink sub;

	@Property
	private Item item;

	public Collection<Item> getItems() {
		return this.shoppingCart.getItems();
	}

	public float getTotal() {
		return this.item.getAmount() * this.item.getProduct().getPrice();
	}

	public void onAdd(String columnId) {
		int amount = this.item.getAmount();
		this.item.setAmount(amount + 1);
	}

	public void onSub(String columnId) {
		int amount = this.item.getAmount();
	}
}
