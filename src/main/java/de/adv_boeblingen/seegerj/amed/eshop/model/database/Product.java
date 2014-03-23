package de.adv_boeblingen.seegerj.amed.eshop.model.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Product {
	@Id
	@Column(name = "productid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NonVisual
	private long id;

	@Column
	private String description;

	@Column
	private String slug;

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public long getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
