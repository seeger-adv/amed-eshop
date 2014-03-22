package de.adv_boeblingen.seegerj.amed.eshop.api;

import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public interface Filter {
	boolean filter(Product input);
}
