package com.knihapaul.urlshortener.persistence.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity(name = "tags")
public class Tag extends NamedEntity{
	
	@ManyToMany(mappedBy = "tags")
	private Set<TargetLink> targetLinks;
	
	public Tag() {}
	
	public Tag(String name){
		this.name = name;
	}
	
	protected void setTargetLinks(Set<TargetLink> targetLinks){
		this.targetLinks = targetLinks;
	}
	
	protected Set<TargetLink> getTargetLinksInternal(){
		if (this.targetLinks == null){
			this.targetLinks = new HashSet<>();
		}
		return this.targetLinks;
	}
	
	public Set<TargetLink> getTargetLinks(){
		return Collections.unmodifiableSet(getTargetLinksInternal());
	}
	

}
