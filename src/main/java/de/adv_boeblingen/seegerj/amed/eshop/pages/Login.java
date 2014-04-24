package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ComponentSource;

import de.adv_boeblingen.seegerj.amed.eshop.api.AuthenticatorService;

@Import(stylesheet = "context:css/login.css")
public class Login {

	@ActivationRequestParameter("next")
	private String nextPage;

	@Persist
	@Property
	private String username;

	@Property
	private String password;

	@Inject
	private ComponentSource componentSource;

	@Inject
	private AuthenticatorService authenticator;

	@Component
	private Form loginForm;

	void onValidateFromLoginForm() {
		if (!authenticator.login(username, password)) {
			loginForm.recordError("Invalid user name or password.");
		}
	}

	Object onSuccess() {
		if (nextPage == null) {
			return Index.class;
		}

		return this.componentSource.getPage(nextPage);
	}
}
