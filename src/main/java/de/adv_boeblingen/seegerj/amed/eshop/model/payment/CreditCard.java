package de.adv_boeblingen.seegerj.amed.eshop.model.payment;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CreditCard
extends PaymentInfo {

	@Column
	private String owner;

	@Column
	private String number;

	@Column
	private String cvv;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return String.format("%s%n%s%n", owner, number);
	}
}
