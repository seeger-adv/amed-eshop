package de.adv_boeblingen.seegerj.amed.eshop.filters;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Category;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public class CategoryFilter implements Filter<Product> {

	private final Category category;

	public CategoryFilter(Category category) {
		this.category = category;
	}

	@Override
	public void filter(EntityManager em, CriteriaBuilder cb, Root<Product> root, CriteriaQuery<Product> query) {
		Path<Category> field = root.<Category> get("category");
		query.where(cb.equal(field, this.category));
	}
}
