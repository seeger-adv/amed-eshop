package de.adv_boeblingen.seegerj.amed.eshop.api;

import de.adv_boeblingen.seegerj.amed.eshop.model.Product;

public interface Filter {
	boolean filter(Product input);
}
