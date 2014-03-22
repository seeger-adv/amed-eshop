package de.adv_boeblingen.seegerj.amed.eshop.model.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Product {
	@Id
	@Column(name = "productid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String description;

	@Lob
	@Column
	private byte[] image;

	@Column
	private String slug;

	@Column
	private String title;

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public int getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
