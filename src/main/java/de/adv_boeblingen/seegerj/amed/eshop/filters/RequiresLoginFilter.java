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

	private final AuthenticatorService authService;
	private final PageRenderLinkSource renderLinkSource;
	private final ComponentSource componentSource;
	private final Response response;

	public RequiresLoginFilter(PageRenderLinkSource renderLinkSource, ComponentSource componentSource,
			Response response, AuthenticatorService authService) {
		this.renderLinkSource = renderLinkSource;
		this.componentSource = componentSource;
		this.response = response;
		this.authService = authService;
	}

	@Override
	public void handleComponentEvent(ComponentEventRequestParameters params, ComponentRequestHandler handler)
			throws IOException {

		String activePage = params.getActivePageName();
		if (redirectToLoginPageIfNecessary(activePage)) {
			return;
		}

		handler.handleComponentEvent(params);
	}

	@Override
	public void handlePageRender(PageRenderRequestParameters parameters, ComponentRequestHandler handler)
			throws IOException {

		String logicalName = parameters.getLogicalPageName();
		if (redirectToLoginPageIfNecessary(logicalName)) {
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