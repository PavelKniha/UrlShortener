package com.knihapaul.urlshortener.persistence.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.knihapaul.urlshortener.validation.ValidEmail;
import com.knihapaul.urlshortener.validation.ValidPassword;

public class UserDto {
	
	 @NotNull
	    @Size(min = 1)
	    private String firstName;

	    @NotNull
	    @Size(min = 1)
	    private String lastName;

	    @ValidPassword
	    private String password;

	    @ValidEmail
	    @NotNull
	    @Size(min = 1)
	    private String email;

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
	
	

}
