package de.adv_boeblingen.seegerj.amed.eshop.filters;

import javax.persistence.criteria.CriteriaQuery;

import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;

public class CategoryFilter implements Filter {

	@Override
	public boolean filter(CriteriaQuery<?> input) {
		return true;
	}

}
