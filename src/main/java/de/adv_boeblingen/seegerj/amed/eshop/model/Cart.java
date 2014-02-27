package de.adv_boeblingen.seegerj.amed.eshop.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Cart {
	private final Set<Item> items = new HashSet<Item>();

	public Collection<? extends Item> getItems() {
		return this.items;
	}
}
