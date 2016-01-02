package com.knihapaul.urlshortener.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "privileges")
public class Privilege extends NamedEntity{
	
	@ManyToMany
	@JoinTable(name = "privileges_roles", joinColumns = @JoinColumn(name = "privileges_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
	private Set<Role> roles;
	
	public Privilege() {}
	
	public Privilege(String name) {
		this.name = name;
	}
	
	public Set<Role> getRoles(){
		return getRolesInternal();
	}
	
	public void addRole(Role role){
		getRolesInternal().add(role);
	}

	public void removeRole(Role role) {
		getRolesInternal().remove(role);		
	}
	
	protected void setRolesInternal(Set<Role> roles){
		this.roles = roles;
	}
	
	protected Set<Role> getRolesInternal(){
		if(this.roles == null){
			this.roles = new HashSet<>();
		}
		return this.roles;
	}
}
