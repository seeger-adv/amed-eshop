package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.Catalog;
import de.adv_boeblingen.seegerj.amed.eshop.model.Product;

public class Index {
	@Inject
	private Catalog catalog;

	public Set<Product> getItems() {
		return catalog.getItems(null);
	}
}
