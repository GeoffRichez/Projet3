package fr.groupe1.goevent.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
	private Long id; 

	private String role;

	
//	@ManyToMany(mappedBy = "roles")
//	private List<User> users;

	
//	
//	public List<User> getUsers() {
//		return users;
//	}
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
	public Role() {
		super();
	
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + role + "]";
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role(Long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	
	
	

}
