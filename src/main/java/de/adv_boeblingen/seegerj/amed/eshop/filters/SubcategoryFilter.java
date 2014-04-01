package de.adv_boeblingen.seegerj.amed.eshop.filters;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Category;

public class SubcategoryFilter implements Filter<Category> {

	private final Category category;

	public SubcategoryFilter(Category category) {
		this.category = category;
	}

	@Override
	public void filter(EntityManager em, CriteriaBuilder cb, CriteriaQuery<Category> query) {
		Root<Category> root = query.from(Category.class);

		Path<Category> field = root.<Category> get("parent");
		if (this.category != null) {
			query.where(cb.equal(field, this.category));
		} else {
			query.where(cb.isNull(field));
		}
	}
}
