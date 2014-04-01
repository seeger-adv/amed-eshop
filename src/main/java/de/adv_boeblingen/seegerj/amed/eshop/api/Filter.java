package de.adv_boeblingen.seegerj.amed.eshop.api;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public interface Filter<T> {
	void filter(EntityManager em, CriteriaBuilder builder, CriteriaQuery<T> query);
}
