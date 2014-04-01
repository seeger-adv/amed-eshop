package de.adv_boeblingen.seegerj.amed.eshop.model.database;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@DynamicInsert
public class Category {
	@Id
	@Column(name = "categoryid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String slug;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private final Set<Category> subcategories = new HashSet<Category>();

	@OneToMany(mappedBy = "category")
	private final Set<Product> products = new HashSet<Product>();

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public int getId() {
		return this.id;
	}

	public Set<Category> getSubcategories() {
		return this.subcategories;
	}

	public Set<Product> getProducts() {
		return this.products;
	}
}
