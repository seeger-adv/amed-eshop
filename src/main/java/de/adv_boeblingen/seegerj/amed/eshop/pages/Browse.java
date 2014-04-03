package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.CategoryDao;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Category;

public class Browse {
	@Inject
	private CategoryDao categoryDao;

	@Property
	private Category category;

	void onActivate(long categoryId) {
		this.category = this.categoryDao.getCategory(categoryId);
	}

	public String getCategoryFilter() {
		if (this.category == null) {
			return "null:";
		} else {
			return "cat:" + this.category.getId();
		}
	}

	public String getProductFilter() {
		if (this.category == null) {
			return "null:";
		} else {
			return "cat:" + this.category.getId();
		}
	}
}
