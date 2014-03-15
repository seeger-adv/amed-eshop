package de.adv_boeblingen.seegerj.amed.eshop.model;

public class Session {
	private Customer customer;
	private boolean isValid;

	public Session() {
		this.isValid = false;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		this.isValid = true;
	}

	public boolean isValid() {
		return this.isValid;
	}

	public void invalidate() {
		this.isValid = false;
	}
}
