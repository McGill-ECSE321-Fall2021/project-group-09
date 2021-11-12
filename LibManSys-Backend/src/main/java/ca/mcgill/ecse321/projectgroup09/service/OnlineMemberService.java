package ca.mcgill.ecse321.projectgroup09.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.projectgroup09.dao.OnlineMemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.OnlineMember;

public class OnlineMemberService extends MemberService {
	
	@Autowired
	private OnlineMemberRepository onlineMemberRepository;

	@Transactional
	public OnlineMember createMember(String fullName, String address, String phoneNumber, String memberEmail, String memberPassword, String memberUsername) {
		
		if (memberEmail == null || memberEmail == ""|| memberEmail.equals("undefined")) {
	        throw new IllegalArgumentException("Email Address cannot be null or empty");
	        }
	    if (!memberEmail.contains("@")) {
	        throw new IllegalArgumentException("Email Address must contain @ character");
	        }
	    
	    if (memberPassword == null || memberPassword == "") {
	        throw new IllegalArgumentException("Member Password cannot be null or empty");
	        }
		if (memberPassword.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
		}
		
		if (memberUsername == null || memberUsername== ""|| memberUsername.equals("undefined")) {
	        throw new IllegalArgumentException("Username cannot be null or empty");
	        }
		
		if (onlineMemberRepository.findOnlineMemberByMemberUsername(memberUsername) != null) {
			throw new IllegalArgumentException("Username already exists. Please pick a different username");
		}
		
		
		OnlineMember onlineMember = (OnlineMember) super.createMember(fullName, address, phoneNumber);
		onlineMember.setMemberEmail(memberEmail);
		onlineMember.setMemberPassword(memberPassword);
		onlineMember.setMemberUsername(memberUsername);
        onlineMemberRepository.save(onlineMember);
        return onlineMember;
	}
	
	@Transactional
	@Override
	 public OnlineMember getMemberByLibCardNumber(Long libCardNumber) {
		return (OnlineMember)super.getMemberByLibCardNumber(libCardNumber);
	    }

	@Transactional
	@Override
	public OnlineMember getMemberByBookingID(Long bookingID) {
		return (OnlineMember)super.getMemberByBookingID(bookingID);
	}
	
	@Transactional
	@Override
	public OnlineMember getMemberByLoanID(Long loanID) {
		return (OnlineMember)super.getMemberByLoanID(loanID);
	}
	
	@Transactional
	@Override
	public List<Member> getMemberByFullName(String fullName) {
		return super.getMemberByFullName(fullName);
	}
	
	@Transactional
	@Override
	public List<Member> getMembersByVerificationStatus(boolean isVerifiedResident) {
		return super.getMembersByVerificationStatus(isVerifiedResident);
	}
	
	@Transactional
    public List<OnlineMember> getAllOnlineMembers() {
		return toList(onlineMemberRepository.findAll());
    }
	
	@Transactional
	@Override
	public OnlineMember updateMemberFullName(Long libCardNumber, String old_fullName, String new_fullName) {
		return (OnlineMember) super.updateMemberFullName(libCardNumber, old_fullName, new_fullName);
	}
	
	@Transactional
	@Override
	public OnlineMember updateMemberAddress(Long libCardNumber, String old_address, String new_address) {
		return (OnlineMember) super.updateMemberAddress(libCardNumber, old_address, new_address);
	}
	
	@Transactional
	@Override
	public OnlineMember updateMemberPhoneNumber(Long libCardNumber, String old_PhoneNumber, String new_PhoneNumber) {
		return (OnlineMember) super.updateMemberPhoneNumber(libCardNumber, old_PhoneNumber, new_PhoneNumber);
}
	@Transactional
	@Override
	public OnlineMember updateMemberVerificationStatus(Long libCardNumber, String fullName, boolean isVerifiedResident) {
		return (OnlineMember) super.updateMemberVerificationStatus(libCardNumber, fullName, isVerifiedResident);
	}
	
	@Transactional
	@Override
	public OnlineMember updateAmountOwed(Long libCardNumber, String fullName, double changeInAmount) {
		return (OnlineMember) super.updateAmountOwed(libCardNumber, fullName, changeInAmount);
	}
	
	@Transactional
	@Override
	public OnlineMember updateActiveLoans(Long libCardNumber, String fullName, int changeInActiveLoans) {
		return (OnlineMember) super.updateActiveLoans(libCardNumber, fullName, changeInActiveLoans);
	}
	
	@Transactional
	@Override
	 public OnlineMember deleteMember(Long libCardNumber) {
		 return (OnlineMember) super.deleteMember(libCardNumber);
		 
	}
	
	public OnlineMember updateOnlineMemberPassword(Long libCardNumber, String memberUsername, String old_memberPassword, String new_memberPassword) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (memberUsername == null) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }
		
		if (old_memberPassword == null || new_memberPassword == null || old_memberPassword == "" || new_memberPassword == "") {
            throw new IllegalArgumentException("None of the password fields can be null or empty.");
        }
		
		if (old_memberPassword == new_memberPassword) {
			throw new IllegalArgumentException("The old and the new password cannot be the same");
        }
		
		if (new_memberPassword.length() < 6) {
			throw new IllegalArgumentException("The new password cannot be less than 6 characters long");
		}
		
		OnlineMember onlineMember = onlineMemberRepository.findOnlineMemberByLibCardNumber(libCardNumber);
        
        if (onlineMember == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
        if (onlineMember.getMemberUsername() != memberUsername) {
        	throw new IllegalArgumentException("The library Card Number and the member username does not match");
        }
        
        if (onlineMember.getMemberPassword() != old_memberPassword) {
        	throw new IllegalArgumentException("The old password does not match with the password on the database.");
        }
        
        onlineMember.setMemberPassword(new_memberPassword);
        onlineMemberRepository.save(onlineMember);
		return onlineMember;
	}
	
	public OnlineMember updateOnlineMemberEmail(Long libCardNumber, String memberUsername, String old_memberEmail, String new_memberEmail) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (memberUsername == null) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }
		
		if (old_memberEmail == null || new_memberEmail == null) {
            throw new IllegalArgumentException("None of the email fields can be null or empty.");
        }
		
		if (old_memberEmail == new_memberEmail) {
			throw new IllegalArgumentException("The old and the new email cannot be the same");
        }
		
		if (new_memberEmail == null || new_memberEmail == "") {
	        throw new IllegalArgumentException("Email Address cannot be null or empty");
	        }
	    if (!new_memberEmail.contains("@")) {
	        throw new IllegalArgumentException("Email Address must contain @ character");
	        }
		
		OnlineMember onlineMember = onlineMemberRepository.findOnlineMemberByLibCardNumber(libCardNumber);
        
        if (onlineMember == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
        if (onlineMember.getMemberUsername() != memberUsername) {
        	throw new IllegalArgumentException("The library Card Number and the member username does not match");
        }
        
        if (onlineMember.getMemberEmail() != old_memberEmail) {
        	throw new IllegalArgumentException("The old password does not match with the password on the database.");
        }
        
        onlineMember.setMemberEmail(new_memberEmail);
        onlineMemberRepository.save(onlineMember);
		return onlineMember;
	}
}
