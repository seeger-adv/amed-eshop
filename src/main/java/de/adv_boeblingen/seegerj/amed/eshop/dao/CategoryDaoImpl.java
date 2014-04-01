package de.adv_boeblingen.seegerj.amed.eshop.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.adv_boeblingen.seegerj.amed.eshop.api.CategoryDao;
import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Category;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider.DatabaseRunnable;

public class CategoryDaoImpl implements CategoryDao {
	@Override
	public Set<Category> getCategories(final Filter<Category> filter) {
		return DatabaseProvider.runQuery(new DatabaseRunnable<Set<Category>>() {
			@Override
			public HashSet<Category> run(EntityManager manager, EntityTransaction transaction) {
				CriteriaBuilder builder = manager.getCriteriaBuilder();
				CriteriaQuery<Category> query = builder.createQuery(Category.class);

				Root<Category> root = query.from(Category.class);
				query.select(root);

				if (filter != null) {
					filter.filter(manager, builder, query);
				}

				TypedQuery<Category> typedQuery = manager.createQuery(query);
				return new HashSet<Category>(typedQuery.getResultList());
			}
		});
	}

	@Override
	public Category getCategory(final long categoryId) {
		return DatabaseProvider.runQuery(new DatabaseRunnable<Category>() {
			@Override
			public Category run(EntityManager manager, EntityTransaction transaction) {
				return manager.find(Category.class, categoryId);
			}
		});
	}
}
