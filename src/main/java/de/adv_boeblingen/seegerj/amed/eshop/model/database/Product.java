package de.adv_boeblingen.seegerj.amed.eshop.model.database;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private float price;

	@Column
	private String description;

	@Column
	private String slug;

	@Column
	private int itemsLeft = 0;

	public int getItemsLeft() {
		return itemsLeft;
	}

	public void setItemsLeft(int itemsLeft) {
		this.itemsLeft = itemsLeft;
	}

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "category")
	private Category category;

	@Column
	private String image;

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

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Category getCategory() {
		return this.category;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = (int) (hash * 17 + this.id);
		hash = hash * 31 + this.slug.hashCode();
		hash = hash * 13 + this.description.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product other = (Product) obj;
			return other.id == this.id;
		}
		return false;
	}
}
