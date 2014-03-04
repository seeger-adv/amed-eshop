package de.adv_boeblingen.seegerj.amed.eshop.model;

public class CustomerSession {
	private Customer customer;
	private boolean isValid;

	public CustomerSession(Customer customer) {
		this();
		setCustomer(customer);
	}

	public CustomerSession() {
		this.isValid = true;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isValid() {
		return this.isValid;
	}

	public void invalidate() {
		this.isValid = false;
	}
}
