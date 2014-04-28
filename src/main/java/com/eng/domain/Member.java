package com.eng.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Member {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private ObjectId id;
	private String firstName;
	private String lastName;
	private String phone;
	
	public Member(){}

	public Member(String firstName, String lastName, String phone) {
		this.id = new ObjectId();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	 @Override
	 public String toString() {
		        return String.format(
		                "Member[id=%s, firstName='%s', lastName='%s', phone='%s']",
		                id, firstName, lastName, phone);
	 }


}
