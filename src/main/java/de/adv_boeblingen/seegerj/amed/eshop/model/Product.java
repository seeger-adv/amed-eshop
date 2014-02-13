package de.adv_boeblingen.seegerj.amed.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@Column(name = "productid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String description;

	public int getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
