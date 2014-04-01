package de.adv_boeblingen.seegerj.amed.eshop.api;

import java.util.Set;

import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

/**
 * Provides access to the Articles.
 * 
 * @author jan
 */
public interface Catalog {
	/**
	 * Returns a {@link Set} of {@link Product}s matching the criteria of the
	 * {@link Filter}.
	 * 
	 * @param filter
	 *            Filter or null for all Products.
	 * @return Set of Products.
	 */
	Set<Product> getProducts(Filter filter, int max);

	/**
	 * Returns a single Product as specified by its id.
	 * 
	 * @param productId
	 *            The product's id.
	 * @return found Product or null.
	 */
	Product getProduct(long productId);
}
