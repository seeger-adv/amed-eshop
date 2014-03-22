package de.adv_boeblingen.seegerj.amed.eshop.filters;

import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public class CategoryFilter implements Filter {

	@Override
	public boolean filter(Product input) {
		return true;
	}

}
