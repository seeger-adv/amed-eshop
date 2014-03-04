package de.adv_boeblingen.seegerj.amed.eshop.filters;

import java.io.IOException;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Response;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.services.AuthenticatorService;

public class RequiresLoginFilter
implements ComponentRequestFilter {

	private final PageRenderLinkSource renderLinkSource;

	private final ComponentSource componentSource;

	private final Response response;

	private final AuthenticatorService authService;

	public RequiresLoginFilter(PageRenderLinkSource renderLinkSource, ComponentSource componentSource,
			Response response, AuthenticatorService authService) {
		this.renderLinkSource = renderLinkSource;
		this.componentSource = componentSource;
		this.response = response;
		this.authService = authService;
	}

	@Override
	public void handleComponentEvent(ComponentEventRequestParameters parameters, ComponentRequestHandler handler)
			throws IOException {

		if (redirectToLoginPageIfNecessary(parameters.getActivePageName())) {
			return;
		}

		handler.handleComponentEvent(parameters);
	}

	@Override
	public void handlePageRender(PageRenderRequestParameters parameters, ComponentRequestHandler handler)
			throws IOException {

		if (redirectToLoginPageIfNecessary(parameters.getLogicalPageName())) {
			return;
		}

		handler.handlePageRender(parameters);
	}

	private boolean redirectToLoginPageIfNecessary(String pageName) throws IOException {

		if (this.authService.isValidSession()) {
			return false;
		}

		Component page = this.componentSource.getPage(pageName);

		if (!page.getClass().isAnnotationPresent(RequiresLogin.class)) {
			return false;
		}

		Link link = this.renderLinkSource.createPageRenderLink("Login");

		this.response.sendRedirect(link);

		return true;
	}
}