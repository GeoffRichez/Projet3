package fr.groupe1.goevent.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Adress {

	//Attributes
	@Id @Column(name = "adress_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "villes", nullable=false, length = 150, table = "adress")
	private String city;
	
	@Column(name="rue", nullable=false, length = 250, table = "adress")
	private String road;
	
	@Column(name="code_postal", nullable=false, table = "adress")
	private int zipCode;
	
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
	private List<Event> listeEvent;
	
	private Double longitude;
	
	private Double latitude;

	//Constructors
	public Adress() {

	}
	
	public Adress(Integer id, String city, String road, int zipCode, List<Event> listeEvent, Double longitude,
			Double latitude) {
		super();
		this.id = id;
		this.city = city;
		this.road = road;
		this.zipCode = zipCode;
		this.listeEvent = listeEvent;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Adress(Integer id, String city, String road, int zipCode, double latitude, double longitude) {
		super();
		this.id = id;
		this.city = city;
		this.road = road;
		this.zipCode = zipCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Adress(Integer id, String city, String road, int zipCode) {
		super();
		this.id = id;
		this.city = city;
		this.road = road;
		this.zipCode = zipCode;
	}

	public Adress(String city, String road, int zipCode) {
		super();
		this.city = city;
		this.road = road;
		this.zipCode = zipCode;
	}



	//Getters & Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public List<Event> getListeEvent() {
		return listeEvent;
	}

	public void setListeEvent(List<Event> listeEvent) {
		this.listeEvent = listeEvent;
	}

	@Override
	public String toString() {
		return "Adress [id=" + id + ", city=" + city + ", road=" + road + ", zipCode=" + zipCode + "]";
	}

	public Double getLongitude() {
		return longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
}
