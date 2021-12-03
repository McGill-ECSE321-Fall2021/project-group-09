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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.MemberDto;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.service.MemberService;

@CrossOrigin(origins = "*")
@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value = {"/members", "/members/"})
	public List<MemberDto> getAllMembers() {
		List<MemberDto> memberDtos = new ArrayList<>();
		for (Member member : memberService.getAllMembers()) {
			memberDtos.add(MemberDto.convertToDto(member));
		}
		return memberDtos;
	}

	@GetMapping(value = { "/member/{libCardNumber}", "/member/{libCardNumber}/" })
	public MemberDto getMemberById(@PathVariable("libCardNumber") Long libCardNumber) throws IllegalArgumentException {
		MemberDto mdto = MemberDto.convertToDto(memberService.getMemberByLibCardNumber(libCardNumber));
		return mdto;
	}
	
	@GetMapping(value = {"/members/{fullName}", "/members/{fullName}/"})
	public List<MemberDto> getMemberByFullName(@PathVariable("fullName") String fullName) {
		return memberService.getMemberByFullName(fullName).stream().map(member -> MemberDto.convertToDto(member)).collect(Collectors.toList());
	}
	
	@GetMapping(value = {"/members/{isVerifiedResident}", "/members/{isVerifiedResident}/"})
	public List<MemberDto> getMembersByVerificationStatus(@PathVariable("isVerifiedResident") boolean isVerifiedResident) {
		return memberService.getMembersByVerificationStatus(isVerifiedResident).stream().map(member -> MemberDto.convertToDto(member)).collect(Collectors.toList());
	}

	@GetMapping(value = {"/member/bookings/{bookingID}", "/member/bookings/{bookingID}/"})
	public MemberDto getMemberByBookingId(@PathVariable("bookingID") Long bookingID) throws IllegalArgumentException {
		return MemberDto.convertToDto(memberService.getMemberByBookingID(bookingID));
	}

	@GetMapping(value = {"/member/loans/{loanID}", "/member/loans/{loanID}/"})
	public MemberDto getMemberByLoanId(@PathVariable("loanID") Long loanID) throws IllegalArgumentException {
		return MemberDto.convertToDto(memberService.getMemberByLoanID(loanID));
	}
	
	 
	@PostMapping(value = { "/members/create/{fullName}", "/members/create/{fullName}/" })
	public ResponseEntity<?> createMember(@PathVariable("fullName") String fullName,
				@RequestParam(name = "Address") String memberAddress,
				@RequestParam(name = "Phone Number") String memberPhoneNumber) {
		try {
			Member member = memberService.createMember(fullName, memberAddress, memberPhoneNumber);
			return httpSuccess(MemberDto.convertToDto(member));
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	@PostMapping(value = { "/member/fullName/update/{libCardNumber}", "/member/fullName/update/{libCardNumber}/" })
	public MemberDto updateMemberFullName(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Previous Full Name") String old_fullName,
				@RequestParam(name = "New Full Name") String new_fullName)
				throws IllegalArgumentException {
			Member member = memberService.updateMemberFullName(libCardNumber, old_fullName, new_fullName);
			return MemberDto.convertToDto(member);
		}
	
	@PostMapping(value = { "/member/address/update/{libCardNumber}", "/member/address/update/{libCardNumber}/" })
	public MemberDto updateMemberAddress(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Previous Address") String old_address,
				@RequestParam(name = "New Address") String new_address)
				throws IllegalArgumentException {
			Member member = memberService.updateMemberAddress(libCardNumber, old_address, new_address);
			return MemberDto.convertToDto(member);
		}
	
	@PatchMapping(value = { "/member/phoneNumber/update/{libCardNumber}", "/member/phoneNumber/update/{libCardNumber}/" })
	public MemberDto updateMemberPhoneNumber(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Previous Phone Number") String old_phoneNumber,
				@RequestParam(name = "New Phone Number") String new_phoneNumber)
				throws IllegalArgumentException {
			Member member = memberService.updateMemberPhoneNumber(libCardNumber, old_phoneNumber, new_phoneNumber);
			return MemberDto.convertToDto(member);
		}
	
	@PatchMapping(value = { "/member/verificationstatus/update/{libCardNumber}", "/member/verificationstatus/update/{libCardNumber}/" })
	public MemberDto updateMemberVerificationStatus(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Full Name") String fullName,
				@RequestParam(name = "New Verification Status") boolean isVerifiedResident)
				throws IllegalArgumentException {
			Member member = memberService.updateMemberVerificationStatus(libCardNumber, fullName, isVerifiedResident);
			return MemberDto.convertToDto(member);
		}
	
	@PatchMapping(value = { "/member/activeLoans/update/{libCardNumber}", "/member/activeLoans/update/{libCardNumber}/" })
	public MemberDto updateActiveLoans(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Full Name") String fullName,
				@RequestParam(name = "Change in number of active loans") int changeInActiveLoans)
				throws IllegalArgumentException {
			Member member = memberService.updateActiveLoans(libCardNumber, fullName, changeInActiveLoans);
			return MemberDto.convertToDto(member);
		}
	
	@PatchMapping(value = { "/member/amountOwed/update/{libCardNumber}", "/member/amountOwed/update/{libCardNumber}/" })
	public MemberDto updateAmountOwed(@PathVariable("libCardNumber") Long libCardNumber,
				@RequestParam(name = "Full Name") String fullName,
				@RequestParam(name = "Change in amount owed") double changeInAmountOwed)
				throws IllegalArgumentException {
			Member member = memberService.updateAmountOwed(libCardNumber, fullName, changeInAmountOwed);
			return MemberDto.convertToDto(member);
		}
	
	@DeleteMapping(value = {"/member/delete/{libCardNumber}", "/member/delete/{libCardNumber}/"})
	public MemberDto DeleteMember(@PathVariable("libCardNumber") Long libCardNumber) {
		Member member = memberService.deleteMember(libCardNumber);
		return MemberDto.convertToDto(member);
	}
	
}



