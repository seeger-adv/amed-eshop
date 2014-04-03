package de.adv_boeblingen.seegerj.amed.eshop.api;

import java.util.Set;

import de.adv_boeblingen.seegerj.amed.eshop.model.database.Category;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public interface CategoryDao {
	/**
	 * Returns a single Category as specified by its id.
	 * 
	 * @param categoryId
	 *            The categorys id.
	 * @return found Category or null.
	 */
	Category getCategory(long categoryId);

	/**
	 * Returns a {@link Set} of {@link Product}s matching the criteria of the
	 * {@link ProductFilter}.
	 * 
	 * @param categoryFilter
	 *            Filter or null for all Products.
	 * @return Set of Products.
	 */
	Set<Category> getCategories(Filter<Category> categoryFilter);
}
