package de.adv_boeblingen.seegerj.amed.eshop.filters;

import java.io.IOException;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
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

	@Inject
	private AuthenticatorService authService;

	@Inject
	private PageRenderLinkSource renderLinkSource;

	@Inject
	private ComponentSource componentSource;

	@Inject
	private Response response;

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