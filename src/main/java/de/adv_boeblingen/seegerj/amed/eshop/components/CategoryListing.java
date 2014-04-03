package de.adv_boeblingen.seegerj.amed.eshop.components;

import java.util.Set;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.CategoryDao;
import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.filters.SubcategoryFilter;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Category;

public class CategoryListing {
	@Inject
	private CategoryDao categoryDao;

	@Property
	private Category category;

	@Parameter(allowNull = true)
	private String filter;

	public Set<Category> getCategories() {
		Filter<Category> categoryFilter = getFilter(this.filter);
		return this.categoryDao.getCategories(categoryFilter);
	}

	private Filter<Category> getFilter(String input) {
		System.out.println("asdf: " + input);

		String categoryString = input.substring(input.lastIndexOf(':') + 1);
		if (categoryString.equals("null")) {
			this.category = null;
		} else {
			Long categoryId = Long.valueOf(categoryString);
			this.category = this.categoryDao.getCategory(categoryId);
		}

		return new SubcategoryFilter(this.category);
	}
}
