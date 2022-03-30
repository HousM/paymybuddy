package com.openclassrooms.PayMyBuddy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.PayMyBuddy.constant.TransactionType;
import com.openclassrooms.PayMyBuddy.model.BuddyAccount;
import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.TransactionService;
import com.openclassrooms.PayMyBuddy.service.UserService;
import com.openclassrooms.PayMyBuddy.util.LoginEmailRetriever;

@Controller
public class TransactionController {
	/**
	 * TransactionController logger.
	 */
	private static Logger LOGGER = LogManager.getLogger(TransactionController.class);

	/**
	 * ITransactionService's implement class reference.
	 */
	private TransactionService transactionService;
	 private UserService userService;

	/**
	 * LoginEmailRetriever instance.
	 */
	private LoginEmailRetriever loginEmailRetriever;

	/**
	 * Constructor of class TransactionController. Initialize transactionService,
	 * loginEmailRetriever.
	 */
	@Autowired
	public TransactionController(TransactionService transactionService,
			LoginEmailRetriever loginEmailRetriever) {
		this.transactionService = transactionService;
		this.loginEmailRetriever = loginEmailRetriever;
	}


	@PostMapping("/transfer")
	public ResponseEntity<Transaction> transferToBankAccount(@RequestBody Transaction transfer,
			HttpServletRequest request) throws Exception {
		LOGGER.debug("Transfer request with amount {}", transfer.getAmount());

		String ownerEmail = loginEmailRetriever.getUserName(request);

		if (transfer.getAmount() == 0 ) {
			throw new Exception("Amount is required");
		}

		Transaction transferSaved = transactionService.saveTransaction(transfer.getUser(), transfer.getType(), transfer.getBuddyReciver(),
				transfer.getBuddyOwner(),  transfer.getAmount());

		LOGGER.info("Money transfer request - SUCCESS");
		return new ResponseEntity<>(transferSaved, HttpStatus.OK);
	}

    @PostMapping(value = "/transaction")
    public String saveTransaction (@AuthenticationPrincipal User user,
                                   @RequestParam("type") TransactionType type,
                                   @RequestParam("receiver") BuddyAccount buddyAccountreciver,
                                   @RequestParam("owner") BuddyAccount buddyAccountowner,
                                   @RequestParam("amount") float amount,
                                   Model model) {
        if (transactionService.saveTransaction(user, type, buddyAccountreciver, buddyAccountowner, amount) == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                UserDetails userDetails = (UserDetails) auth.getPrincipal();
                String email = userDetails.getUsername();
                User user2 = userService.findByUserEmail(email);
//                List<User> userFriends = user2.getUsersList();
//                model.addAttribute("userfriends",userFriends);
                List<Transaction> transactionList = transactionService.findByUser(user);
                model.addAttribute("transaction",transactionList);
            }
            model.addAttribute("error", "Recharge balance ");
            return "transactions";
        } else {
            return "redirect:transactions";
        }
    }



}
