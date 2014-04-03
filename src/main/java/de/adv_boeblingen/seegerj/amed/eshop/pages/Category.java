package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.CategoryDao;

public class Category {
	@Inject
	private CategoryDao categoryDao;

	private String query;

	@Property
	private de.adv_boeblingen.seegerj.amed.eshop.model.database.Category category;

	void onActivate(long categoryId) {
		this.category = this.categoryDao.getCategory(categoryId);
	}

	void onActivate(String query) {
		this.query = query;
	}

	public String getCategoryFilter() {
		if (this.query != null) {
			return "query:" + this.query;
		}

		if (this.category == null) {
			return "null";
		} else {
			return "cat:" + this.category.getId();
		}
	}

	public String getProductFilter() {
		if (this.query != null) {
			return "query:" + this.query;
		}

		if (this.category == null) {
			return "null";
		} else {
			return "cat:" + this.category.getId();
		}
	}
}
