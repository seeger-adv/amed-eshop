package de.adv_boeblingen.seegerj.amed.eshop.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import de.adv_boeblingen.seegerj.amed.eshop.api.Catalog;
import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.model.Product;

public class CatalogDao
		implements Catalog {

	@Inject
	private Session session;

	@Override
	public Set<Product> getItems(Filter filter) {
		List<Product> allItems = getAllProducts();
		Set<Product> retval = new HashSet<Product>();

		for (Product product : allItems) {
			if (filter == null || filter.filter(product)) {
				retval.add(product);
			}
		}

		return retval;
	}

	@SuppressWarnings("unchecked")
	private List<Product> getAllProducts() {
		return session.createCriteria(Product.class).list();
	}
}
