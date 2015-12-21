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
	Set<Role> roles;
	
	public Privilege() {}
	
	public Privilege(String name) {
		this.name = name;
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
	
	public Set<Role> getRoles(){
		return getRolesInternal();
	}
	
	public void addRole(Role role){
		getRolesInternal().add(role);
	}
	
}
