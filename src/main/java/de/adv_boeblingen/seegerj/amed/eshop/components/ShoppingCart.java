package de.adv_boeblingen.seegerj.amed.eshop.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;

public class ShoppingCart {
	@Property
	@SessionState
	private Cart shoppingCart;

	@Property
	@Parameter(defaultPrefix = "literal")
	private String layout;
}
