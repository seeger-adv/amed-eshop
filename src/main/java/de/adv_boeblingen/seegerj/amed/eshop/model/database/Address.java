package de.adv_boeblingen.seegerj.amed.eshop.model.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import de.adv_boeblingen.seegerj.amed.eshop.api.Identifiable;

@Entity
@DynamicUpdate
@DynamicInsert
public class Address implements Identifiable {
	@Id
	@Column(name = "addressid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer")
	private Customer customer;

	@Column
	private String name;

	@Column
	private String street;

	@Column
	private int zip;

	@Column
	private String city;

	public String getCity() {
		return city;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public int getZip() {
		return zip;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
}
