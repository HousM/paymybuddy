package com.openclassrooms.PayMyBuddy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FeeCalculator {

	private static Logger LOGGER = LogManager.getLogger(FeeCalculator.class);
	private Float FEE_RATE = (float) 0.005;

	/**
	 * Calculates fee based on amount and rate.
	 */

	public float getFee(float amount) {
		LOGGER.debug("Inside FeeCalculator.getFee with amount : " + amount);
		float fee = (float) (amount * FEE_RATE);

		return fee;
	}

}
