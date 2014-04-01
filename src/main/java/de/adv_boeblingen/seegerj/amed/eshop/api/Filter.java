package de.adv_boeblingen.seegerj.amed.eshop.api;

import javax.persistence.criteria.CriteriaQuery;

public interface Filter {
	boolean filter(CriteriaQuery<?> input);
}
