package de.adv_boeblingen.seegerj.amed.eshop.model;

import java.util.HashMap;
import java.util.Map;

import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public class Cart {
	private final Map<Product, Integer> items = new HashMap<Product, Integer>();

	public void add(Product product) {
		if (!items.containsKey(product)) {
			items.put(product, 0);
		}

		getNewAmount(product, 1);
	}

	public void clear(Product product) {
		items.remove(product);
	}

	public void clearAll() {
		items.clear();
	}

	public Map<Product, Integer> getItems() {
		return this.items;
	}

	private int getNewAmount(Product item, int direction) {
		int oldAmount = items.get(item);
		int newAmount = oldAmount + direction;
		items.put(item, newAmount);
		return newAmount;
	}

	public void remove(Product product) {
		int amount = getNewAmount(product, -1);

		if (amount == 0) {
			clear(product);
		}
	}
}
