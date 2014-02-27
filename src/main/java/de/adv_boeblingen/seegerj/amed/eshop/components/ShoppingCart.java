package de.adv_boeblingen.seegerj.amed.eshop.components;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;
import de.adv_boeblingen.seegerj.amed.eshop.model.Item;

public class ShoppingCart {
	@SessionState
	private Cart shoppingCart;

	@Property
	private Item item;

	public Item[] getItems() {
		return new Item[0];
	}
}
