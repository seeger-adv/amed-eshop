package de.adv_boeblingen.seegerj.amed.eshop.pages;

import javax.persistence.RollbackException;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.UserDao;

@Import(stylesheet = "context:css/login.css")
public class Register {

	@Persist
	@Property
	private String username;

	@Property
	private String password;

	@Inject
	private UserDao userDao;

	@InjectComponent
	private PasswordField passwordField;

	@Component
	private Form registerForm;

	void onValidateFromRegisterForm() {
		try {
			userDao.register(username, password);
		} catch (RollbackException e) {
			registerForm.recordError(passwordField, "User already exists!");
		}
	}

	Object onSuccess() {
		return Account.class;
	}
}
