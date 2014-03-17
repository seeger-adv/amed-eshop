package de.adv_boeblingen.seegerj.amed.eshop.model;

public class Session {
	private Customer customer;

	public Session() {
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isValid() {
		return this.customer != null;
	}

	public void invalidate() {
		this.customer = null;
	}
}
