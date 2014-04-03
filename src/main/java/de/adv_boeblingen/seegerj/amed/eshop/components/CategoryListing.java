package de.adv_boeblingen.seegerj.amed.eshop.components;

import java.util.Set;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.CategoryDao;
import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.api.FilterFactory;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Category;

public class CategoryListing {
	@Inject
	private CategoryDao categoryDao;

	@Inject
	private FilterFactory filterFactory;

	@Property
	private Category category;

	@Property
	@Parameter(defaultPrefix = "literal")
	private String title;

	@Parameter(allowNull = true)
	private String filter;

	public Set<Category> getCategories() {
		Filter<Category> categoryFilter = this.filterFactory.getCategoryFilter(this.filter);
		return this.categoryDao.getCategories(categoryFilter);
	}
}
