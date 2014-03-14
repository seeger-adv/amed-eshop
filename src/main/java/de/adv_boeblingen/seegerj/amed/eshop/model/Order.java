package de.adv_boeblingen.seegerj.amed.eshop.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
public class Order {
	@Id
	@Column(name = "orderid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToMany
	@JoinColumn(name = "order")
	@OrderBy(value = "itemid")
	private Set<Item> items;

	@ManyToOne
	private Customer user;

	public int getId() {
		return this.id;
	}

	public Set<Item> getItems() {
		return this.items;
	}
}
