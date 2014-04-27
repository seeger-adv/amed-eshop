package de.adv_boeblingen.seegerj.amed.eshop.api;

import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;
import de.adv_boeblingen.seegerj.amed.eshop.model.enums.Availability;

public interface StockService {
	/**
	 * Returns the {@link Availability} for the given Product.
	 *
	 * @param product
	 *            Product reference
	 * @return The Products {@link Availability}
	 */
	Availability getAvailability(Product product);

	/**
	 * Decreases the count of Items the given Product has in Stock by a certain
	 * amount.
	 *
	 * @param product
	 *            Product to reduce the count of
	 * @param decreaseBy
	 *            Amount the reduce the count by
	 * @return Returns false if any error happens like the given amount would
	 *         result in a negative stock count.
	 * @throws IllegalArgumentException
	 *             if the amount to decrease by is negative
	 */
	boolean decreaseItemCount(Product product, int decreaseBy);

	/**
	 * Increases the count of Items the given Product has in Stock by a certain
	 * amount.
	 *
	 * @param product
	 *            Product to increase the count of
	 * @param increaseBy
	 *            Amount the increase the count by
	 * @throws IllegalArgumentException
	 *             if the amount to increase by is negative
	 */
	void increaseItemCount(Product product, int increaseBy);
}
