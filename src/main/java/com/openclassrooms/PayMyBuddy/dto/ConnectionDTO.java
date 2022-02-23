package com.openclassrooms.PayMyBuddy.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.sun.istack.NotNull;

@Entity
@DynamicUpdate
@Table(name = "ConnectionDTO")
public class ConnectionDTO {
	/**
	 * Email of the buddy.
	 */

	@NotNull
	private String buddyEmail;
}
