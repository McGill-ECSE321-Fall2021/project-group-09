package ca.mcgill.ecse321.projectgroup09.controller;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailureMessage;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpSuccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.AccountDto;
import ca.mcgill.ecse321.projectgroup09.service.AccountService;

/**
 * Handles requests related to accounts.
 */
@CrossOrigin(origins = "*")
@RestController
public class AccountController {
	
	private static final String BASE_URL = "/account";
	@Autowired
	private AccountService accountService;
	
	@GetMapping(value = { BASE_URL + "/{id}", BASE_URL + "/{id}/"})
	public ResponseEntity<?> getAccountById(@PathVariable("id") Long accountId) {
		AccountDto a;
		try {
			a = AccountDto.convertToDto(accountService.getAccountById(accountId));
			return httpSuccess(a);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	
}
