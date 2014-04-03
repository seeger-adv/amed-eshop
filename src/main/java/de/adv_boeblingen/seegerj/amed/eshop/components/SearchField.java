package de.adv_boeblingen.seegerj.amed.eshop.components;

import javax.inject.Inject;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.services.PageRenderLinkSource;

import de.adv_boeblingen.seegerj.amed.eshop.pages.Results;

public class SearchField {
	@Property
	private String query;

	@Component
	private Form searchForm;

	@Inject
	private PageRenderLinkSource linksource;

	@Property
	@Parameter(defaultPrefix = "literal")
	private String layout;

	Link onSuccess() {
		return this.linksource.createPageRenderLinkWithContext(Results.class, "query:" + this.query);
	}
}
