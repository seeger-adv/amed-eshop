package de.adv_boeblingen.seegerj.amed.eshop.api;

import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;

public interface UserDao {
	boolean register(final String username, final String password);

	void updateUser(Customer customer);
}
