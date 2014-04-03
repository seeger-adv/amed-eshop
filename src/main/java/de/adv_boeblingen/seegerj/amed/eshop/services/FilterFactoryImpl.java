package de.adv_boeblingen.seegerj.amed.eshop.services;

import java.util.StringTokenizer;

import de.adv_boeblingen.seegerj.amed.eshop.api.Filter;
import de.adv_boeblingen.seegerj.amed.eshop.api.FilterFactory;
import de.adv_boeblingen.seegerj.amed.eshop.filters.BlockingFilter;
import de.adv_boeblingen.seegerj.amed.eshop.filters.CategoryFilter;
import de.adv_boeblingen.seegerj.amed.eshop.filters.NullCategoryFilter;
import de.adv_boeblingen.seegerj.amed.eshop.filters.SearchFilter;
import de.adv_boeblingen.seegerj.amed.eshop.filters.SubcategoryFilter;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Category;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;

public class FilterFactoryImpl implements FilterFactory {
	@Override
	public Filter<Category> getCategoryFilter(String parameters) {
		System.out.println("Category: " + parameters);
		StringTokenizer tokenizer = new StringTokenizer(parameters, ":");

		if (!tokenizer.hasMoreElements()) {
			return null;
		}

		String command = tokenizer.nextToken();
		if ("cat".equals(command)) {
			String cat = tokenizer.nextToken();
			long categoryId = Long.parseLong(cat);
			return new SubcategoryFilter(categoryId);
		} else if ("null".equals(command)) {
			return new NullCategoryFilter();
		}

		return new BlockingFilter<Category>();
	}

	@Override
	public Filter<Product> getProductFilter(String parameters) {
		System.out.println("Product: " + parameters);
		StringTokenizer tokenizer = new StringTokenizer(parameters, ":");

		if (!tokenizer.hasMoreElements()) {
			return null;
		}

		String command = tokenizer.nextToken();
		if ("cat".equals(command)) {
			String cat = tokenizer.nextToken();
			long categoryId = Long.parseLong(cat);
			return new CategoryFilter(categoryId);
		} else if ("query".equals(command)) {
			return new SearchFilter(tokenizer.nextToken());
		} else if ("fav".equals(command)) {
			return null; // new FavFilter();
		}

		return new BlockingFilter<Product>();
	}
}
