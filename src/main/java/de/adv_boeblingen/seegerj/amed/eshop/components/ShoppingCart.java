package de.adv_boeblingen.seegerj.amed.eshop.components;

import java.util.Collection;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Item;

@Import(library = { "context:js/dropdown.js" })
public class ShoppingCart {
	@Property
	@SessionState
	private Cart shoppingCart;

	@Property
	private Item item;

	@Parameter
	@Property
	private String layout;

	public Collection<Item> getItems() {
		return shoppingCart.getItems();
	}
}
