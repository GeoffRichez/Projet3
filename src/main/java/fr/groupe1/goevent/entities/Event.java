package fr.groupe1.goevent.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;


@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_Id")
	private Integer IdEvent;

	@Column(unique = true)
	private String title;

	private String theme;
	
	private Double tarif;
	
	@Lob
	@Column(name="Info_Pratique", length=2048)
	private String infoPratique;
	
	@Lob 
	@Column(name="Descritpion_Details", length=2048)
	private String descritpionDetails;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	private int maxAttendees;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Prestation> prestations;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adress_Id")
    private Adress location;
	
	@Column(name="picture")
	private String picture;

//	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
//	private List<Picture> picture;
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "eventType_id")
	private EventType eventType;


	@ManyToOne
	@JoinColumn(name = "user_id")
    private User organizer;

	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 @JoinTable(name = "event_participant",
     joinColumns = { @JoinColumn(name = "event_id") },
     inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private List<User> participants = new ArrayList<User>();
	


	public Event(Integer idEvent, String title, String descritpion, Date startDate, Date endDate, int maxAttendees,
			List<Prestation> prestations, Adress location, String picture, EventType eventType, User organizer,
			List<User> participants, String theme) {
		super();
		IdEvent = idEvent;
		this.title = title;
		this.theme = theme;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxAttendees = maxAttendees;
		this.prestations = prestations;
		this.location = location;
		this.picture = picture;
		this.eventType = eventType;
		this.organizer = organizer;
		this.participants = participants;
		this.theme = theme;
	}

	public Event() {

	}

	public Event(Integer idEvent, String title, EventType eventType,  String theme, Date startDate, Date endDate, int maxAttendees, Adress location, String picture) {
		super();
		IdEvent = idEvent;
		this.title = title;
		this.eventType = eventType;
		this.theme = theme;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxAttendees = maxAttendees;
		this.location = location;
		this.picture = picture;
	}
	
	public Event(Integer idEvent, String title, EventType eventType,  String theme, Date startDate, Date endDate, int maxAttendees, Adress location, String picture, Double tarif) {
		super();
		IdEvent = idEvent;
		this.title = title;
		this.eventType = eventType;
		this.theme = theme;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxAttendees = maxAttendees;
		this.location = location;
		this.picture = picture;
		this.tarif = tarif;
	}
	
	public Event(Integer idEvent, String title, EventType eventType,  String theme, Date startDate, Date endDate, int maxAttendees, Adress location, String picture, String descritpionDetails) {
		super();
		IdEvent = idEvent;
		this.title = title;
		this.eventType = eventType;
		this.theme = theme;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxAttendees = maxAttendees;
		this.location = location;
		this.picture = picture;
		this.descritpionDetails = descritpionDetails;
	}
	
	public Event(Integer idEvent, String title, EventType eventType,  String theme, Date startDate, Date endDate, int maxAttendees, Adress location, String picture, String descritpionDetails, Double tarif) {
		super();
		IdEvent = idEvent;
		this.title = title;
		this.eventType = eventType;
		this.theme = theme;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxAttendees = maxAttendees;
		this.location = location;
		this.picture = picture;
		this.descritpionDetails = descritpionDetails;
		this.tarif = tarif;
	}

	public Event(Integer idEvent, String title, String theme, Date startDate, Date endDate, Adress location,
			String picture, int maxAttendees) {
		IdEvent = idEvent;
		this.title = title;
		this.theme = theme;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.picture = picture;
		this.maxAttendees = maxAttendees;
	}



	public Integer getIdEvent() {
		return IdEvent;
	}

	public void setIdEvent(Integer idEvent) {
		IdEvent = idEvent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public int getMaxAttendees() {
		return maxAttendees;
	}

	public void setMaxAttendees(int maxAttendees) {
		this.maxAttendees = maxAttendees;
	}


//	public User getUser() {
//		return organizer;
//	}
//
//	public void setUser(User user) {
//		this.organizer = user;
//	}

	
	public Adress getLocation() {
		return location;
	}

	public void setLocation(Adress location) {
		this.location = location;
	}



	public List<Prestation> getPrestations() {
		return prestations;
	}

	public void setPrestations(List<Prestation> prestations) {
		this.prestations = prestations;
	}

	public User getOrganizer() {
		return organizer;
	}

	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescritpionDetails() {
		return descritpionDetails;
	}

	public void setDescritpionDetails(String descritpionDetails) {
		this.descritpionDetails = descritpionDetails;
	}

	public String getInfoPratique() {
		return infoPratique;
	}

	public void setInfoPratique(String infoPratique) {
		this.infoPratique = infoPratique;
	}

	public Double getTarif() {
		return tarif;
	}

	public void setTarif(Double tarif) {
		this.tarif = tarif;
	}
	
	
	
	
//
//	@Override
//	public String toString() {
//		return "Event [IdEvent=" + IdEvent + ", title=" + title + ", descritpion=" + descritpion + ", startDate="
//				+ startDate + ", endDate=" + endDate + ", maxAttendees=" + maxAttendees + ", prestation=" + prestation
//				+ ", location=" + location + ", picture=" + picture + ", eventType=" + eventType + ", organizer="
//				+ organizer + ", participants=" + participants + "]";
//	}

	
	
}
