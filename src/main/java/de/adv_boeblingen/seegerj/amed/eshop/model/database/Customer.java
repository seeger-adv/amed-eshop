package de.adv_boeblingen.seegerj.amed.eshop.model.database;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@DynamicInsert
public class Customer {
	@Id
	@Column
	private String mail;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	@Column
	private String password;

	@PrePersist
	protected void onCreate() {
		this.created = new Date();
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer")
	@OrderBy(value = "id")
	private final Set<Address> address = new HashSet<Address>();

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer")
	@OrderBy(value = "id")
	private final Set<Purchase> purchase = new HashSet<Purchase>();

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getCreated() {
		return this.created;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public Set<Purchase> getPurchase() {
		return purchase;
	}
}
