package de.adv_boeblingen.seegerj.amed.eshop.api;

public interface AuthenticatorService {
	boolean isValidSession();

	boolean login(String username, String password);
}
