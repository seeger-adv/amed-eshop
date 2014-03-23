package de.adv_boeblingen.seegerj.amed.eshop.model.database;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

import de.adv_boeblingen.seegerj.amed.eshop.model.ShippingState;

@Entity
@DynamicInsert
public class Purchase {
	@Id
	@Column(name = "purchaseid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToMany
	@JoinColumn(name = "purchase")
	@OrderBy(value = "id")
	private final Set<Item> items = new HashSet<Item>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer")
	private Customer customer;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Enumerated(EnumType.ORDINAL)
	private ShippingState shippingState;

	public int getId() {
		return this.id;
	}

	public Set<Item> getItems() {
		return this.items;
	}

	@PrePersist
	protected void onCreate() {
		this.date = new Date();
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ShippingState getShippingState() {
		return this.shippingState;
	}

	public void setShippingState(ShippingState shippingState) {
		this.shippingState = shippingState;
	}
}
