package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import de.adv_boeblingen.seegerj.amed.eshop.api.Identifiable;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;

public abstract class AbstractCheckoutSelection<T extends Identifiable> {
	@Inject
	private ApplicationStateManager stateManager;

	protected Session session;

	protected Purchase purchase;

	@Property
	private T item;

	protected abstract void add(T a);

	public abstract Set<T> getItems();

	public Object onActionFromSelect(long id) {
		for (T a : getItems()) {
			if (a.getId() == id) {
				add(a);
			}
		}
		return Checkout.class;
	}

	public void onActivate() {
		session = stateManager.get(Session.class);
		purchase = stateManager.get(Purchase.class);
	}
}
