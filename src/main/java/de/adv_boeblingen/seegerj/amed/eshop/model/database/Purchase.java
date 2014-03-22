package de.adv_boeblingen.seegerj.amed.eshop.model.database;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Purchase {
	@Id
	@Column(name = "purchaseid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToMany
	@JoinColumn(name = "purchase")
	@OrderBy(value = "id")
	private Set<Item> items;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer")
	private Customer customer;

	public int getId() {
		return this.id;
	}

	public Set<Item> getItems() {
		return this.items;
	}
}
