package com.openclassrooms.PayMyBuddy.util;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.openclassrooms.PayMyBuddy.constant.Fee;

@Component
public class FeeCalculator {

	private static Logger LOGGER = LogManager.getLogger(FeeCalculator.class);

	/**
	 * Calculates fee based on amount and rate.
	 */

	public BigDecimal getFee(BigDecimal amount) {
		LOGGER.debug("Inside FeeCalculator.getFee with amount : " + amount);
		BigDecimal fee = amount.multiply(Fee.FEE_RATE);

		return fee;
	}

}
