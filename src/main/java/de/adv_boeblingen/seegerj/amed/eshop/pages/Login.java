package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.services.AuthenticatorService;

@Import(stylesheet = "context:css/login.css")
public class Login {

	@Persist
	@Property
	private String userName;

	@Property
	private String password;

	@Inject
	private AuthenticatorService authenticator;

	@InjectComponent
	private PasswordField passwordField;

	@Component
	private Form form;

	void onValidateFromLoginForm() {
		if (!authenticator.login(userName, password)) {
			// record an error, and thereby prevent Tapestry from emitting a
			// "success" event
			form.recordError(passwordField, "Invalid user name or password.");
		}
	}

	Object onSuccess() {
		return Index.class;
	}

}
