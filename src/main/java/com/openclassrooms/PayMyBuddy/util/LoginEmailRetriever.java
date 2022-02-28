//package com.openclassrooms.PayMyBuddy.util;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LoginEmailRetriever {
//
//	/**
//	 * LoginEmailRetriever logger.
//	 */
//	private static Logger LOGGER = LogManager.getLogger(LoginEmailRetriever.class);
//
//	/**
//	 * Calculates fee based on amount and rate.
//	 */
//	public String getUsername(HttpServletRequest request) {
//		LOGGER.debug("LoginEmailRetriever.getUsername with username : " + request.getUserPrincipal().getName());
//
//		String username = request.getUserPrincipal().getName();
//
//		return username;
//	}
//}