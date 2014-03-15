package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;

@RequiresLogin
public class Account {
	@Inject
	private ApplicationStateManager stateManager;
}
