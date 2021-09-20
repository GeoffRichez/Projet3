package fr.groupe1.goevent.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRESTATION")
public class Prestation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
		
	@Column(name = "TYPE")
	private String type;
		
	@Column(name = "PRICE")
	private float price;
	
	@ManyToMany(mappedBy = "prestations")
	private List<Event> event;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Prestation() {
		
	}
	
	public Prestation(Long id, String type, float price, List<Event> event, User user) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
		this.event = event;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {	
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Prestation [id=" + id + ", Type=" + type + ", price=" + price + "]";
	}
	
}
