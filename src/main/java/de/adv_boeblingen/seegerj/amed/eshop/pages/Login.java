package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.AuthenticatorService;

@Import(stylesheet = "context:css/login.css")
public class Login {

	@Persist
	@Property
	private String username;

	@Property
	private String password;

	@Inject
	private AuthenticatorService authenticator;

	@InjectComponent
	private PasswordField passwordField;

	@Component
	private Form loginForm;

	void onValidateFromLoginForm() {
		if (!authenticator.login(username, password)) {
			loginForm.recordError(passwordField, "Invalid user name or password.");
		}
	}

	Object onSuccess() {
		return Index.class;
	}
}
