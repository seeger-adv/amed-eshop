package de.adv_boeblingen.seegerj.amed.eshop.filters;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public class SearchFilter
		implements Filter<Product> {

	private final String query;

	public SearchFilter(String query) {
		this.query = query;
	}

	@Override
	public void filter(EntityManager em, CriteriaBuilder cb, Root<Product> root, CriteriaQuery<Product> query) {
		String searchTerm = "%" + this.query + "%";
		Path<String> description = root.<String> get("description");
		Path<String> slug = root.<String> get("slug");
		Predicate descPredicate = cb.like(description, searchTerm);
		Predicate slugPredicate = cb.like(slug, searchTerm);
		query.where(cb.or(descPredicate, slugPredicate));
	}
}
