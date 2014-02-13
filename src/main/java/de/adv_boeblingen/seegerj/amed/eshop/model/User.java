package de.adv_boeblingen.seegerj.amed.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@Column
	private String mail;

	@Column
	private long created;

	@Column
	private long lastLogin;

	@Column
	private String password;

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public long getCreated() {
		return this.created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public long getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}