package com.knihapaul.urlshortener.persistence.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Column(name = "first_name")
	@NotEmpty
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty
	private String lastName;

	@Column(length = 60)
	@NotEmpty
	private String password;

	@NotEmpty
	private String email;

	private boolean enabled;

	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<TargetLink> targetLinks;

	public User() {
		this.enabled = false;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	protected void setRolesInternal(Set<Role> roles) {
		this.roles = roles;
	}

	protected Set<Role> getRolesInternal() {
		if (this.roles == null) {
			this.roles = new HashSet<>();
		}
		return this.roles;
	}

	public Set<Role> getRoles() {
		return Collections.unmodifiableSet(getRolesInternal());
	}

	public void addRole(Role role) {
		getRolesInternal().add(role);
		role.addUser(this);
	}
	
	public void removeRole(Role role){
		getRolesInternal().remove(role);
		role.removeUser(this);
	}
	
	@PreRemove
	public void removeAllUserRoles(){
		Set<Role> roles = getRolesInternal();
		for(Role role : roles){
			role.removeUser(this);
		}
	}
	
	protected void setTargetLinksInternal(Set<TargetLink> targetLinks){
		this.targetLinks = targetLinks;
	}
	
	protected Set<TargetLink> getTargetLinksInternal(){
		if (this.targetLinks == null){
			this.targetLinks = new HashSet<>();
		}
		return targetLinks;
	}
	
	public void addTargetLink(TargetLink targetLink){
		getTargetLinksInternal().add(targetLink);
		targetLink.setUser(this);
	}
	
	public void removeTargetLink(TargetLink targetLink){
		getTargetLinksInternal().remove(targetLink);
		targetLink.setUser(null);
	}
	
	public Set<TargetLink> getTargetLinks(){
		return Collections.unmodifiableSet(getTargetLinksInternal());
	}

	
	
	

}
