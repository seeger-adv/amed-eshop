package de.adv_boeblingen.seegerj.amed.eshop.filters;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;

public class BlockingFilter<T> implements Filter<T> {
	@Override
	public void filter(EntityManager em, CriteriaBuilder builder, Root<T> root, CriteriaQuery<T> query) {
		Path<Long> field = root.<Long> get("id");
		Predicate expr = builder.notEqual(field, field);
		query.where(expr);
	}
}
