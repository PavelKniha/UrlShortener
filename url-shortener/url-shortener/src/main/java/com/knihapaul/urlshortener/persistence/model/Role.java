package com.knihapaul.urlshortener.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends NamedEntity{
	
	@ManyToMany(mappedBy = "roles")
	private Set<Privilege> privileges;
	
	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "roles_id"), inverseJoinColumns = @JoinColumn(name = "users_id"))
	private Set<User> users;
	
	public Role() {}
	
	public Role(String name) {
		this.name = name;
	}
	
	protected void setUsersInternal(Set<User> users){
		this.users = users;
	}
	
	protected Set<User> getUsersInternal(){
		if (this.users == null){
			this.users = new HashSet<>();
		}
		return this.users;
	}
	
	public Set<User> getUsers(){
		return getUsersInternal();
	}
	
	public void addUser(User user){
		getUsersInternal().add(user);
	}
	
	public void removeUser(User user) {
		getUsersInternal().remove(user);	
	}
	
	protected void setPrivilegesInternal(Set<Privilege> privileges){
		this.privileges = privileges;
	}
	
	protected Set<Privilege> getPrivilegesInternal(){
		if(this.privileges == null){
			this.privileges = new HashSet<>();
		}
		return this.privileges;
	}
	
	public Set<Privilege> getPrivileges(){
		return getPrivilegesInternal();
	}
	
	public void addPrivilege(Privilege privilege){
		getPrivilegesInternal().add(privilege);
		privilege.addRole(this);
	}

	public void removePrivilege(Privilege privilege) {
		getPrivilegesInternal().remove(privilege);
		privilege.removeRole(this);	
	}

	public void removeAllPrivileges() {
		Set<Privilege> privileges = getPrivilegesInternal();
		for(Privilege privilege : privileges){
			privilege.removeRole(this);
		}
		privileges.clear();
	}



}
