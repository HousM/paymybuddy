package com.openclassrooms.PayMyBuddy.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "ContactsDTO")
public class ContactsDTO {

	/**
	 * List of contact.
	 */
	private Collection<String> contacts = new ArrayList<>();
}
