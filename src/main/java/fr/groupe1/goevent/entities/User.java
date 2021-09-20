package fr.groupe1.goevent.entities;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class User {

	private static final String ROLES_DELIMITER = ", ";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;

	private String firstName;
	private String lastName;

//	@NotEmpty(message = "Le champs email ne doit pas être vide!")
	private String email;
	private String password;
	private String phoneNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private String picture;
	private int active;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<EventType> eventTypes;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Prestation> prestation;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles ;

	

	@OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
	private List<Event> organizerEvents;

	@ManyToMany(mappedBy = "participants", fetch = FetchType.EAGER)
	private List<Event> participantEvents = new ArrayList<Event>();
	
	@Column(name = "reset_password_token")
    private String resetPasswordToken;
	

	public User() {

	}
	

	public User(Integer id, String firstName) {
		this.id = id;
		this.firstName = firstName;
	}
	
	

	public User(Integer id, String firstName, String lastName, String email, String password, String phoneNumber,
			Date birthday, String picture, List<Role> roles ) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.picture = picture;
		this.roles = roles;
	}


	
	public User(Integer id, String firstName, String lastName, String email, String password, String phoneNumber,
			Date birthday, String picture, int active, List<EventType> eventTypes, List<Prestation> prestation,
			List<Role> roles, List<Event> organizerEvents, List<Event> participantEvents, String resetPasswordToken) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.picture = picture;
		this.active = active;
		this.eventTypes = eventTypes;
		this.prestation = prestation;
		this.roles = roles;
		this.organizerEvents = organizerEvents;
		this.participantEvents = participantEvents;
		this.resetPasswordToken = resetPasswordToken;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", birthday=" + birthday + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}



	public List<EventType> getEventTypes() {
		return eventTypes;
	}

	public void setEventTypes(List<EventType> eventTypes) {
		this.eventTypes = eventTypes;
	}

	public List<Prestation> getPrestation() {
		return prestation;
	}

	public void setPrestation(List<Prestation> prestation) {
		this.prestation = prestation;
	}

	public List<Event> getListeEvents() {
		return organizerEvents;
	}

	public void setListeEvents(List<Event> listeEvents) {
		this.organizerEvents = listeEvents;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<Event> getOrganizerEvents() {
		return organizerEvents;
	}

	public void setOrganizerEvents(List<Event> organizerEvents) {
		this.organizerEvents = organizerEvents;
	}

	public List<Event> getParticipantEvents() {
		return participantEvents;
	}

	public void setParticipantEvents(List<Event> participantEvents) {
		this.participantEvents = participantEvents;
	}
	
	public String getUserRoles() {
		if(roles.isEmpty()) 
			return ROLES_DELIMITER;
		
		List<String> rolesByNames = roles.stream()
				.map(Role::getRole)
				.collect(Collectors.toList());
		
		return String.join(ROLES_DELIMITER, rolesByNames);
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public String getResetPasswordToken() {
		return resetPasswordToken;
	}


	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

}
