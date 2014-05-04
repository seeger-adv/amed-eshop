package de.adv_boeblingen.seegerj.amed.eshop.model.payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;

@Entity
public class PaymentInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer")
	private Customer customer;

	@Column
	private String owner;

	@Column
	private String number;

	@Column
	private String cvv;

	public Customer getCustomer() {
		return customer;
	}

	public String getCvv() {
		return cvv;
	}

	public long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public String getOwner() {
		return owner;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return String.format("%s%n%s%n", owner, number);
	}
}
