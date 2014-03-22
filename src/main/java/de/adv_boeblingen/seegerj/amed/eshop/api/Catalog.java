package de.adv_boeblingen.seegerj.amed.eshop.api;

import java.util.Set;

import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public interface Catalog {
	Set<Product> getProducts(Filter filter);
}
