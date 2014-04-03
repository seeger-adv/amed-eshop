package de.adv_boeblingen.seegerj.amed.eshop.api;

import de.adv_boeblingen.seegerj.amed.eshop.model.database.Category;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public interface FilterFactory {
	Filter<Category> getCategoryFilter(String parameters);

	Filter<Product> getProductFilter(String parameters);
}
