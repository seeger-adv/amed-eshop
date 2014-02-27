package de.adv_boeblingen.seegerj.amed.eshop.controller;

import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;
import de.adv_boeblingen.seegerj.amed.eshop.model.Item;
import de.adv_boeblingen.seegerj.amed.eshop.model.Order;

public class CartController {
	public static Order convertToOrder(Cart cart) {
		Order newOrder = new Order();
		newOrder.getItems().addAll(cart.getItems());
		return newOrder;
	}

	public static void addToOrder(Cart cart, Item item) {

	}
}
