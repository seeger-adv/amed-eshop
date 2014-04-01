package de.adv_boeblingen.seegerj.amed.eshop.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.api.ProductDao;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider.DatabaseRunnable;

public class ProductDaoImpl implements ProductDao {
	@Override
	public Set<Product> getProducts(final Filter<Product> filter, final int max) {
		return DatabaseProvider.runQuery(new DatabaseRunnable<Set<Product>>() {
			@Override
			public Set<Product> run(EntityManager manager, EntityTransaction transaction) {
				CriteriaBuilder builder = manager.getCriteriaBuilder();
				CriteriaQuery<Product> query = builder.createQuery(Product.class);

				Root<Product> root = query.from(Product.class);
				query.select(root);

				if (filter != null) {
					filter.filter(manager, builder, query);
				}

				TypedQuery<Product> typedQuery = manager.createQuery(query);
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
