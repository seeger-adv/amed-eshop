package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;

@RequiresLogin
public class Account {
	@Inject
	private ApplicationStateManager stateManager;

	@Property
	private Purchase purchase;

	public Set<Purchase> getPurchases() {
		return new HashSet<Purchase>();
	}
}
