package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.ComponentSource;

import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;

@Import(stylesheet = "context:css/contact.css")
public class Contact {
	@Property
	private String username;

	@Property
	private String message;

	@Inject
	private ComponentSource componentSource;

	@Inject
	private ApplicationStateManager stateManager;

	@Component
	private Form contactForm;

	public void setupRender() {
		this.username = getUserInfo();
	}

	void onValidateFromContactForm() {
		System.out.println("Message processed.");
	}

	Object onSuccess() {
		return Contact.class;
	}

	public String getUserInfo() {
		Session session = this.stateManager.get(Session.class);
		Customer user = session.getCustomer();
		if (user == null) {
			return null;
		}
		return user.getMail();
	}
}
