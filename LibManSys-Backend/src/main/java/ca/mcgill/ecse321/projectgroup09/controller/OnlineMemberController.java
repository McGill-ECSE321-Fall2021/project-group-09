/**
 * @author Zarif Ashraf
 */
package ca.mcgill.ecse321.projectgroup09.controller;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailureMessage;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpSuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.OnlineMemberDto;
import ca.mcgill.ecse321.projectgroup09.models.OnlineMember;
import ca.mcgill.ecse321.projectgroup09.service.OnlineMemberService;

@CrossOrigin(origins = "*")
@RestController
public class OnlineMemberController {

	private static final String BASE_URL = "/OnlineMember";
	
	@Autowired
	private OnlineMemberService onlineMemberService;
	
	
	@GetMapping(value = { BASE_URL, BASE_URL + "/"})
	public List<OnlineMemberDto> getAllOnlineMembers() {
		List<OnlineMemberDto> OnlineMemberDtos = new ArrayList<>();
		for (OnlineMember onlineMember : onlineMemberService.getAllOnlineMembers()) {
			OnlineMemberDtos.add(OnlineMemberDto.convertToDto(onlineMember));
		}
		return OnlineMemberDtos;
	}

	@GetMapping(value = { BASE_URL + "/get-by-libcardnumber/{libCardNumber}", BASE_URL + "/get-by-libcardnumber/{libCardNumber}/" })
	public OnlineMemberDto getOnlineMemberById(@PathVariable("libCardNumber") Long libCardNumber) throws IllegalArgumentException {
		return OnlineMemberDto.convertToDto(onlineMemberService.getOnlineMemberByLibCardNumber(libCardNumber));
	}
	
	@GetMapping(value = {"/OnlineMember/get-by-fullname/{fullName}", "/OnlineMember/get-by-fullname/{fullName}/"})
	public List<OnlineMemberDto> getOnlineMemberByFullName(@PathVariable("fullName") String fullName) {
		return onlineMemberService.getOnlineMemberByFullName(fullName).stream().map(onlineMember -> OnlineMemberDto.convertToDto(onlineMember)).collect(Collectors.toList());
	}
	
	@GetMapping(value = {"/OnlineMember/get-by-verification-status/{isVerifiedResident}", "/OnlineMember/{isVerifiedResident}/"})
	public List<OnlineMemberDto> getOnlineMembersByVerificationStatus(@PathVariable("isVerifiedResident") boolean isVerifiedResident) {
		return onlineMemberService.getOnlineMembersByVerificationStatus(isVerifiedResident).stream().map(onlineMember -> OnlineMemberDto.convertToDto(onlineMember)).collect(Collectors.toList());
	}

	@GetMapping(value = {"/OnlineMember/bookings/{bookingID}", "/OnlineMember/bookings/{bookingID}/"})
	public OnlineMemberDto getOnlineMemberByBookingId(@PathVariable("bookingID") Long bookingID) throws IllegalArgumentException {
		return OnlineMemberDto.convertToDto(onlineMemberService.getOnlineMemberByBookingID(bookingID));
	}

	@GetMapping(value = {"/OnlineMember/loans/{loanID}", "/OnlineMember/loans/{loanID}/"})
	public OnlineMemberDto getOnlineMemberByLoanId(@PathVariable("loanID") Long loanID) throws IllegalArgumentException {
		return OnlineMemberDto.convertToDto(onlineMemberService.getOnlineMemberByLoanID(loanID));
	}
	
	 
	/*@PostMapping(value = { "/OnlineMember/create/{fullName}", "/OnlineMember/create/{fullName}/" })
	public ResponseEntity<?> createOnlineMember(@PathVariable("fullName") String fullName,
				@RequestParam(name = "address") String OnlineMemberAddress,
				@RequestParam(name = "phoneNumber") String OnlineMemberPhoneNumber,
				@RequestParam(name = "emailAddress") String memberEmail,
				@RequestParam(name = "password") String memberPassword,
				@RequestParam(name = "username") String memberUsername
				) throws IllegalArgumentException {
			try {
				OnlineMember OnlineMember = onlineMemberService.createOnlineMember(fullName, OnlineMemberAddress, OnlineMemberPhoneNumber, memberEmail, memberPassword, memberUsername);
				return httpSuccess(OnlineMemberDto.convertToDto(OnlineMember));
			} catch (Exception e) {
				return httpFailureMessage(e.getMessage());
			}
		}*/
	
	@PostMapping(value = { "/OnlineMember/create/{fullName}", "/OnlineMember/create/{fullName}/" })
	public ResponseEntity<?> createOnlineMember(@PathVariable("fullName") String fullName,
				@RequestParam(name = "address") String OnlineMemberAddress,
				@RequestParam(name = "phoneNumber") String OnlineMemberPhoneNumber,
				@RequestParam(name = "emailAddress") String memberEmail,
				@RequestParam(name = "password") String memberPassword,
				@RequestParam(name = "username") String memberUsername
				) {
		try {
			OnlineMember OnlineMember = onlineMemberService.createOnlineMember(fullName, OnlineMemberAddress, OnlineMemberPhoneNumber, memberEmail, memberPassword, memberUsername);
			return httpSuccess(OnlineMemberDto.convertToDto(OnlineMember));
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	@PostMapping(value = { "/OnlineMember/fullName/update/{libCardNumber}", "/OnlineMember/fullName/update/{libCardNumber}/" })
	public OnlineMemberDto updateOnlineMemberFullName(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Previous Full Name") String old_fullName,
				@RequestParam(name = "New Full Name") String new_fullName)
				throws IllegalArgumentException {
			OnlineMember onlineMember = onlineMemberService.updateOnlineMemberFullName(libCardNumber, old_fullName, new_fullName);
			return OnlineMemberDto.convertToDto(onlineMember);
		}
	
	@PostMapping(value = { "/OnlineMember/address/update/{libCardNumber}", "/OnlineMember/address/update/{libCardNumber}/" })
	public OnlineMemberDto updateOnlineMemberAddress(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Previous Address") String old_address,
				@RequestParam(name = "New Address") String new_address)
				throws IllegalArgumentException {
			OnlineMember onlineMember = onlineMemberService.updateOnlineMemberAddress(libCardNumber, old_address, new_address);
			return OnlineMemberDto.convertToDto(onlineMember);
		}
	
	@PatchMapping(value = { "/OnlineMember/phoneNumber/update/{libCardNumber}", "/OnlineMember/phoneNumber/update/{libCardNumber}/" })
	public OnlineMemberDto updateOnlineMemberPhoneNumber(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Previous Phone Number") String old_phoneNumber,
				@RequestParam(name = "New Phone Number") String new_phoneNumber)
				throws IllegalArgumentException {
			OnlineMember onlineMember = onlineMemberService.updateOnlineMemberPhoneNumber(libCardNumber, old_phoneNumber, new_phoneNumber);
			return OnlineMemberDto.convertToDto(onlineMember);
		}
	
	@PatchMapping(value = { "/OnlineMember/verificationstatus/update/{libCardNumber}", "/OnlineMember/verificationstatus/update/{libCardNumber}/" })
	public OnlineMemberDto updateOnlineMemberVerificationStatus(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Full Name") String fullName,
				@RequestParam(name = "New Verification Status") boolean isVerifiedResident)
				throws IllegalArgumentException {
			OnlineMember onlineMember = onlineMemberService.updateOnlineMemberVerificationStatus(libCardNumber, fullName, isVerifiedResident);
			return OnlineMemberDto.convertToDto(onlineMember);
		}
	
	@PatchMapping(value = { "/OnlineMember/activeLoans/update/{libCardNumber}", "/OnlineMember/activeLoans/update/{libCardNumber}/" })
	public OnlineMemberDto updateActiveLoans(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Full Name") String fullName,
				@RequestParam(name = "Change in number of active loans") int changeInActiveLoans)
				throws IllegalArgumentException {
			OnlineMember onlineMember = onlineMemberService.updateActiveLoansForOnlineMember(libCardNumber, fullName, changeInActiveLoans);
			return OnlineMemberDto.convertToDto(onlineMember);
		}
	
	@PatchMapping(value = { "/OnlineMember/amountOwed/update/{libCardNumber}", "/OnlineMember/amountOwed/update/{libCardNumber}/" })
	public OnlineMemberDto updateAmountOwed(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Full Name") String fullName,
				@RequestParam(name = "Change in amount owed") double changeInAmountOwed)
				throws IllegalArgumentException {
			OnlineMember onlineMember = onlineMemberService.updateAmountOwedByOnlineMember(libCardNumber, fullName, changeInAmountOwed);
			return OnlineMemberDto.convertToDto(onlineMember);
		}
	
	@PatchMapping(value = { "/OnlineMember/password/update/{libCardNumber}", "/OnlineMember/password/update/{libCardNumber}/" })
	public OnlineMemberDto updateOnlineMemberPassword(@PathVariable("libCardNumber") Long libCardNumber,
			@RequestParam(name = "Full Name") String fullName,
			@RequestParam(name = "Old Password") String old_memberPassword,
			@RequestParam(name = "New Password") String new_memberPassword
			)
			throws IllegalArgumentException {
		OnlineMember onlineMember = onlineMemberService.updateOnlineMemberPassword(libCardNumber, fullName, old_memberPassword, new_memberPassword);
		return OnlineMemberDto.convertToDto(onlineMember);
	}
	
	@PatchMapping(value = { "/OnlineMember/email/update/{libCardNumber}", "/OnlineMember/email/update/{libCardNumber}/" })
	public OnlineMemberDto updateOnlineMemberEmail(@PathVariable("libCardNumber") Long libCardNumber,
			@RequestParam(name = "Full Name") String fullName,
			@RequestParam(name = "Old Email") String old_memberEmail,
			@RequestParam(name = "New Email") String new_memberEmail
			)
			throws IllegalArgumentException {
		OnlineMember onlineMember = onlineMemberService.updateOnlineMemberEmail(libCardNumber, fullName, old_memberEmail, new_memberEmail);
		return OnlineMemberDto.convertToDto(onlineMember);
	}
	
	@DeleteMapping(value = {"/OnlineMember/delete/{libCardNumber}", "/OnlineMember/delete/{libCardNumber}/"})
	public OnlineMemberDto DeleteOnlineMember(@PathVariable("libCardNumber") Long libCardNumber) {
		OnlineMember onlineMember = onlineMemberService.deleteOnlineMember(libCardNumber);
		return OnlineMemberDto.convertToDto(onlineMember);
	}
	
	/**
	 * Login in an online member, given a username and password, if they match,
	 * return corresponding online member.
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/login", BASE_URL + "/login/"})
	public ResponseEntity<?> loginOnlineMember(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		try {
			OnlineMemberDto omdto = OnlineMemberDto.convertToDto(onlineMemberService.loginAsOM(username, password));
			return httpSuccess(omdto);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		
	}
	
	@PostMapping(value = {BASE_URL + "/login2", BASE_URL + "/login2/"})
	public ResponseEntity<?> loginOnlineMember2(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		try {
			OnlineMemberDto omdto = OnlineMemberDto.convertToDto(onlineMemberService.loginOnlineMember(username, password));
			return httpSuccess(omdto);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		
	}
	
	@PostMapping(value = { "/logout", "/logout/" })
	public ResponseEntity<?> logout() {
		try {
		onlineMemberService.logout();
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = {"/login/currentMember", "/login/currentMember/" })
	public OnlineMemberDto getCurrentAccount() {
		
			OnlineMember currentOMAccount = onlineMemberService.getLoggedOMAccount();
			return OnlineMemberDto.convertToDto(currentOMAccount);
		} 

	}
	
	
