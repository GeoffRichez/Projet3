package fr.groupe1.goevent.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Picture {

	//Attributes
	@Id @Column 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "titre", nullable=false, length = 150, table = "picture")
	private String title;
	
	@Column(nullable=false, length = 500, table = "picture")
	private String url;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "event_id", nullable=false)
	private Event event;

	//Constructor
	public Picture() {
	}

	public Picture(Integer id, String title, String url) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
	}

	public Picture(Integer id, String title, String url, Event event) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.event = event;
	}

	//Getters - Setters
	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Picture [id=" + id + ", title=" + title + ", url=" + url + "]";
	}
	
}
