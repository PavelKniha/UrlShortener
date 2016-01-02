package com.knihapaul.urlshortener.persistence.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "clicks_on_link")
public class ClickOnLink extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "target_links_id")
	private TargetLink targetLink;

	public TargetLink getTargetLink() {
		return targetLink;
	}

	public void setTargetLink(TargetLink targetLink) {
		this.targetLink = targetLink;
	}
	
	

}
