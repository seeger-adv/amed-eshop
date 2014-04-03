package de.adv_boeblingen.seegerj.amed.eshop.filters;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Category;

public class NullCategoryFilter implements Filter<Category> {

	@Override
	public void filter(EntityManager em, CriteriaBuilder cb, Root<Category> root, CriteriaQuery<Category> query) {
		Path<Long> field = root.<Category> get("parent").<Long> get("id");
		Predicate condition = cb.isNull(field);
		query.where(condition);
	}
}
