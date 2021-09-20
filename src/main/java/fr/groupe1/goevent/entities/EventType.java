package fr.groupe1.goevent.entities;

import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import fr.groupe1.goevent.repository.IEventTypeRepository;

@Entity
public class EventType  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	
	@OneToMany(mappedBy="eventType")
	private List<Event> events;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
	
	public EventType(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public EventType(String name, String description) {	
		this.name = name;
		this.description = description;
	}

	public EventType() {
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}	