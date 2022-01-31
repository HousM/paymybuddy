package com.openclassrooms.PayMyBuddy.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ConnectionDTO {
	/**
	 * Email of the buddy.
	 */

	@NotNull
	@NotEmpty(message = "Buddy email address is required")
	@Length(min = 14, message = "Please enter a valid email address")
	private String buddyEmail;
}
