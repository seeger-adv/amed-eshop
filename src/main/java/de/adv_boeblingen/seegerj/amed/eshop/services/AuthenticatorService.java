package de.adv_boeblingen.seegerj.amed.eshop.services;

public interface AuthenticatorService {
	boolean isValidSession();

	boolean login(String username, String password);
}
