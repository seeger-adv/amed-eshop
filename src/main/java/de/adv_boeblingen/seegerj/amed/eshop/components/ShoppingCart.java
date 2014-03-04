package de.adv_boeblingen.seegerj.amed.eshop.components;

import java.util.Collection;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;
import de.adv_boeblingen.seegerj.amed.eshop.model.Item;

public class ShoppingCart {
	@SessionState
	private Cart shoppingCart;

	@Property
	private Item item;

	public Collection<Item> getItems() {
		return shoppingCart.getItems();
	}

	public int getItemCount() {
		return shoppingCart.getItems().size();
	}
}
