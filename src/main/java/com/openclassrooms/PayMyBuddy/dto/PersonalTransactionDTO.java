//package com.openclassrooms.PayMyBuddy.dto;
//
//import java.math.BigDecimal;
//
//import javax.validation.constraints.DecimalMax;
//import javax.validation.constraints.DecimalMin;
//import javax.validation.constraints.Digits;
//import javax.validation.constraints.NotEmpty;
//
//import org.hibernate.validator.constraints.Length;
//
//import com.sun.istack.NotNull;
//
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@AllArgsConstructor(access = AccessLevel.PUBLIC)
//@Getter(AccessLevel.PUBLIC)
//@Setter(AccessLevel.PUBLIC)
//public class PersonalTransactionDTO {
//	/**
//	 * The personal transaction description.
//	 */
//
//	@NotNull
//	@NotEmpty(message = "Description is required")
//	@Length(max = 100, message = "Description size must have less than 100 characters")
//	private String description;
//
//	/**
//	 * Amount of the personal transaction.
//	 */
//	@NotNull
//	@Digits(integer = 5, fraction = 2)
//	@DecimalMax(value = "999.99", message = "maximum amount authorized is 999.99")
//	@DecimalMin(value = "1.00", message = "minimum amount authorized is 1")
//	private BigDecimal amount;
//}
