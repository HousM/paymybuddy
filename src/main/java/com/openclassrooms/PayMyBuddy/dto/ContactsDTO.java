package com.openclassrooms.PayMyBuddy.dto;

import java.util.ArrayList;
import java.util.Collection;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ContactsDTO {

	/**
	 * List of contact.
	 */
	private Collection<String> contacts = new ArrayList<>();
}
