package com.knihapaul.urlshortener.persistence.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "target_links")
public class TargetLink extends BaseEntity{
	
	@NotEmpty
	private String key;
	
	@Column(name = "source_link")
	@NotEmpty
	private String sourceLink;
	
	@NotEmpty
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "targetLink")
	private List<ClickOnLink> clicksOnLink;
	
	@ManyToMany
	@JoinTable(name = "tags_target_links", joinColumns = @JoinColumn(name = "target_links_id"), inverseJoinColumns = @JoinColumn(name = "tags_id"))
	private Set<Tag> tags;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSourceLink() {
		return sourceLink;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	protected List<ClickOnLink> getClicksOnLinkInternal(){
		if (this.clicksOnLink == null){
			this.clicksOnLink = new ArrayList<>();
		}
		return this.clicksOnLink;
	}

	public List<ClickOnLink> getClicksOnLink() {
		return Collections.unmodifiableList(getClicksOnLinkInternal());
	}

	public void addClickOnLink(ClickOnLink clickOnLink){
		getClicksOnLinkInternal().add(clickOnLink);
		clickOnLink.setTargetLink(this);
	}
	
	protected void setTagsInternal(Set<Tag> tags){
		this.tags = tags;
	}
	
	protected Set<Tag> getTagsInternal(){
		if (this.tags == null){
			this.tags = new HashSet<>();
		}
		return this.tags;
	}
	
	public void addTag(Tag tag){
		getTagsInternal().add(tag);
	}
	
	public void removeTag(Tag tag){
		getTagsInternal().remove(tag);
	}
	
	public Set<Tag> getTags() {
		return Collections.unmodifiableSet(getTagsInternal());
	}

	public void setTags(Set<Tag> tags) {
		Set<Tag> tagsInternal = getTagsInternal();
		tagsInternal.clear();
		tagsInternal.addAll(tags);
	}
	
	
	
	
	
	

}
