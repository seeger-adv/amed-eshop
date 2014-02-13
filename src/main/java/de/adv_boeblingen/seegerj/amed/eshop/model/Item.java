package de.adv_boeblingen.seegerj.amed.eshop.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Item {
	@Id
	@Column(name = "itemid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private Order order;
}
