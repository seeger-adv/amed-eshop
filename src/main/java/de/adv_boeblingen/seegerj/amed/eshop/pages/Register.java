package de.adv_boeblingen.seegerj.amed.eshop.pages;

import javax.persistence.RollbackException;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.UserDao;

@Import(stylesheet = "context:css/login.css")
public class Register {

	@Persist
	@Property
	private String username;

	@Property
	private String password;

	@Property
	private String password2;

	@Inject
	private UserDao userDao;

	@Component
	private Form registerForm;

	void onValidateFromRegisterForm() {
		if (!this.password.equals(this.password2)) {
			this.registerForm.recordError("Passwords do not match!");
		}

		try {
			this.userDao.register(this.username, this.password);
		} catch (RollbackException e) {
			this.registerForm.recordError("User already exists!");
		}
	}

	Object onSuccess() {
		return Account.class;
	}
}
