package de.adv_boeblingen.seegerj.amed.eshop.model.payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PaymentInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column
	private long id;
}
