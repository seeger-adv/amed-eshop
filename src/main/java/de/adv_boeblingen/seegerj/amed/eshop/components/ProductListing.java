package de.adv_boeblingen.seegerj.amed.eshop.components;

import java.util.Set;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.api.FilterFactory;
import de.adv_boeblingen.seegerj.amed.eshop.api.ProductDao;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

@Import(stylesheet = { "context:css/productlisting.css" })
public class ProductListing {
	@Inject
	private ProductDao catalog;

	@Inject
	private FilterFactory filterFactory;

	@Property
	private Product product;

	@Property
	@Parameter(defaultPrefix = "literal")
	private String title;

	@Parameter(allowNull = true)
	private String filter;

	@Property
	@Parameter(value = "-1")
	private String max;

	@Parameter(value = "-1", required = false)
	private int characterlimit;

	public Set<Product> getProducts() {
		Filter<Product> productFilter = this.filterFactory.getProductFilter(this.filter);
		int maxProducts = Integer.parseInt(this.max);
		return this.catalog.getProducts(productFilter, maxProducts);
	}

	public String getDescription() {
		String retval = this.product.getDescription();
		if (this.characterlimit != -1 && retval.length() > this.characterlimit) {
			return retval.substring(0, this.characterlimit) + '\u2026';
		}

		return retval;
	}
}
