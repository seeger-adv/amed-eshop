package de.adv_boeblingen.seegerj.amed.eshop.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.adv_boeblingen.seegerj.amed.eshop.api.Catalog;
import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider.DatabaseRunnable;

public class CatalogDao implements Catalog {
	@Override
	public Set<Product> getProducts(final Filter filter, final int max) {
		return DatabaseProvider.runQuery(new DatabaseRunnable<Set<Product>>() {
			@Override
			public Set<Product> run(EntityManager manager, EntityTransaction transaction) {
				CriteriaBuilder builder = manager.getCriteriaBuilder();
				CriteriaQuery<Product> criteria = builder.createQuery(Product.class);

				@SuppressWarnings("unused")
				Root<Product> root = criteria.from(Product.class);

				if (filter != null) {
					filter.filter(criteria);
				}

				TypedQuery<Product> typedQuery = manager.createQuery(criteria);

				if (max != -1) {
					typedQuery.setMaxResults(max);
				}

				return new HashSet<Product>(typedQuery.getResultList());
			}
		});
	}

	@Override
	public Product getProduct(final long productId) {
		return DatabaseProvider.runQuery(new DatabaseRunnable<Product>() {
			@Override
			public Product run(EntityManager manager, EntityTransaction transaction) {
				return manager.find(Product.class, productId);
			}
		});
	}
}
