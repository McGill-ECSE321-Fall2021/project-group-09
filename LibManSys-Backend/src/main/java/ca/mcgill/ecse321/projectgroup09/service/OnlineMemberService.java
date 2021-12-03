package ca.mcgill.ecse321.projectgroup09.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.BookingRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LoanRepository;
import ca.mcgill.ecse321.projectgroup09.dao.OnlineMemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.OnlineMember;

@Service
public class OnlineMemberService extends MemberService {
	
	@Autowired
	private OnlineMemberRepository onlineMemberRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	public static OnlineMember currentOMAccount = null;

	@Transactional
	public OnlineMember createOnlineMember(String fullName, String address, String phoneNumber, String memberEmail, String memberPassword, String memberUsername) {
		
		if (address == null || address == "" || address.equals("undefined")) {
            throw new IllegalArgumentException("Address cannot be null or empty");
		}
		
		if (phoneNumber == null || phoneNumber == ""|| phoneNumber.equals("undefined")) {
	            throw new IllegalArgumentException("Phone Number cannot be null or empty");
	    }
		
		if (phoneNumber.length() < 10 || phoneNumber.length() > 10) {
	            throw new IllegalArgumentException("Phone Number must be 10 characters long");
	        }
		
		if (fullName == null || fullName == ""|| fullName.equals("undefined")) {
            throw new IllegalArgumentException("Full Name cannot be null or empty");
        }
		
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
		

		long leftLimit = 000001;
	    long rightLimit = 999999;
	    long libCardNumber = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
		
	    
	    //OnlineMember onlineMember = (OnlineMember) super.createMember(fullName, address, phoneNumber);
		OnlineMember onlineMember = new OnlineMember();
		onlineMember.setFullName(fullName);
		onlineMember.setAddress(address);
		onlineMember.setPhoneNumber(phoneNumber);
        onlineMember.setLibCardNumber(libCardNumber);
        onlineMember.setIsVerifiedResident(false);
        onlineMember.setAmountOwed(0);
        onlineMember.setActiveLoans(0);
		onlineMember.setMemberEmail(memberEmail);
		onlineMember.setMemberPassword(memberPassword);
		onlineMember.setMemberUsername(memberUsername);
		currentOMAccount = onlineMember;
        onlineMemberRepository.save(onlineMember);
        return onlineMember;
	}
	
	@Transactional
	public OnlineMember getOnlineMemberByLibCardNumber(Long libCardNumber) {
        if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty");
        }
        
        OnlineMember member = onlineMemberRepository.findOnlineMemberByLibCardNumber(libCardNumber);
        
        if (member == null) {
            throw new IllegalArgumentException("No online member with the library card number exists");
        }

        return member;
    }
	
	@Transactional
	public OnlineMember getOnlineMemberByBookingID(Long bookingID) {
		if (bookingID == null) {
            throw new IllegalArgumentException("Booking cannot be null or empty");
        }
		
		OnlineMember onlineMember = (onlineMemberRepository.findOnlineMemberByBookings(bookingRepository.findBookingByBookingID(bookingID)));
		
		if (onlineMember == null) {
			 throw new IllegalArgumentException("No online member with the booking exists");
		}
		
		return onlineMember;
	}
	
	@Transactional
	public OnlineMember getOnlineMemberByLoanID(Long loanID) {
		if (loanID == null) {
            throw new IllegalArgumentException("Loan cannot be null or empty");
        }
		
		OnlineMember onlineMember = (onlineMemberRepository.findOnlineMemberByLoans(loanRepository.findLoanByLoanID(loanID)));
		
		if (onlineMember == null) {
			 throw new IllegalArgumentException("No online member with the loan exists");
		}
		
		return onlineMember;
	}
	
	@Transactional
	 public List<OnlineMember> getOnlineMemberByFullName(String fullName) {
	        
		List<OnlineMember> onlineMemberList = toList(onlineMemberRepository.findOnlineMemberByFullName(fullName));
	        if (onlineMemberList != null) {
	            return onlineMemberList;
	        } else {
	            throw new IllegalArgumentException("No online member with the full name exists!");
	        }
	    }
	
	@Transactional
	public List<OnlineMember> getOnlineMembersByVerificationStatus(boolean isVerifiedResident) {
		List<OnlineMember> onlineMemberList = toList(onlineMemberRepository.findOnlineMemberByisVerifiedResident(isVerifiedResident));
		
		if (onlineMemberList != null) {
            return onlineMemberList;
        } else {
            throw new IllegalArgumentException("No online member with this verification status exists!");
        }
	}
	
	@Transactional
	public OnlineMember updateOnlineMemberFullName(Long libCardNumber, String old_fullName, String new_fullName) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (old_fullName == null) {
            throw new IllegalArgumentException("The old full name cannot be null or empty.");
        }
		
		if (new_fullName == null) {
            throw new IllegalArgumentException("The new full name cannot be null or empty.");
        }
		
		OnlineMember onlineMember = onlineMemberRepository.findOnlineMemberByLibCardNumber(libCardNumber);
        
        if (onlineMember == null) {
            throw new IllegalArgumentException("No Online Member with the library card number exists.");
        }
        
       // if (onlineMember.getFullName() != old_fullName) {
       // 	throw new IllegalArgumentException("The library Card Number and the Online Member name does not match");
        //}
        
        onlineMember.setFullName(new_fullName);
        onlineMemberRepository.save(onlineMember);
		return onlineMember;
	}
	
	@Transactional
	public OnlineMember updateOnlineMemberAddress(Long libCardNumber, String old_address, String new_address) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (old_address == null) {
            throw new IllegalArgumentException("The old address cannot be null or empty.");
        }
		
		if (new_address == null) {
            throw new IllegalArgumentException("The new address cannot be null or empty.");
        }
		
		OnlineMember onlineMember = onlineMemberRepository.findOnlineMemberByLibCardNumber(libCardNumber);
        
        if (onlineMember == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
      //  if (onlineMember.getAddress() != old_address) {
        //	throw new IllegalArgumentException("The library Card Number and the member address does not match");
        //}
        
        onlineMember.setAddress(new_address);
        onlineMemberRepository.save(onlineMember);
		return onlineMember;
	}
	
	@Transactional
	public OnlineMember updateOnlineMemberPhoneNumber(Long libCardNumber, String old_PhoneNumber, String new_PhoneNumber) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (old_PhoneNumber == null) {
            throw new IllegalArgumentException("The old phone number cannot be null or empty.");
        }
		
		if (new_PhoneNumber == null) {
            throw new IllegalArgumentException("The new phone number cannot be null or empty.");
        }
		
		OnlineMember onlineMember = onlineMemberRepository.findOnlineMemberByLibCardNumber(libCardNumber);
        
        if (onlineMember == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
       // if (onlineMember.getPhoneNumber() != old_PhoneNumber) {
        //	throw new IllegalArgumentException("The library Card Number and the member phone number does not match");
        //}
        
        onlineMember.setPhoneNumber(new_PhoneNumber);
        onlineMemberRepository.save(onlineMember);
		return onlineMember;
	}
	
	@Transactional
	public OnlineMember updateOnlineMemberVerificationStatus(Long libCardNumber, String fullName, boolean isVerifiedResident) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (fullName == null) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }
		
		OnlineMember onlineMember = onlineMemberRepository.findOnlineMemberByLibCardNumber(libCardNumber);
        
        if (onlineMember == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
     //   if (onlineMember.getFullName() != fullName) {
       // 	throw new IllegalArgumentException("The library Card Number and the member name does not match");
        //}
        
        onlineMember.setIsVerifiedResident(isVerifiedResident);
        onlineMemberRepository.save(onlineMember);
		return onlineMember;
	}
	
	@Transactional
	public OnlineMember updateAmountOwedByOnlineMember(Long libCardNumber, String fullName, double changeInAmount) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (fullName == null) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }
		
		if (changeInAmount == 0) {
			throw new IllegalArgumentException("The change in amount owed cannot be 0.");
		}
		
		OnlineMember onlineMember = onlineMemberRepository.findOnlineMemberByLibCardNumber(libCardNumber);
        
        if (onlineMember == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
    //    if (onlineMember.getFullName() != fullName) {
      //  	throw new IllegalArgumentException("The library Card Number and the member name does not match");
        //}
        
        double oldAmountOwed = onlineMember.getAmountOwed();
        double newAmountOwed = (oldAmountOwed + changeInAmount);
        onlineMember.setAmountOwed(newAmountOwed);
        onlineMemberRepository.save(onlineMember);
		return onlineMember;
	}
	
	@Transactional
	public OnlineMember updateActiveLoansForOnlineMember(Long libCardNumber, String fullName, int changeInActiveLoans) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (fullName == null) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }
		
		if (changeInActiveLoans == 0) {
			throw new IllegalArgumentException("The change in active loans cannot be 0.");
		}
		
		OnlineMember onlineMember = onlineMemberRepository.findOnlineMemberByLibCardNumber(libCardNumber);
        
        if (onlineMember == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
    //    if (onlineMember.getFullName() != fullName) {
      //  	throw new IllegalArgumentException("The library Card Number and the member name does not match");
        //}
        
        int oldActiveLoans = onlineMember.getActiveLoans();
        int newActiveLoans = (oldActiveLoans + changeInActiveLoans);
        onlineMember.setActiveLoans(newActiveLoans);
        onlineMemberRepository.save(onlineMember);
		return onlineMember;
	}
	
	
	
	/**@Transactional
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
    public List<OnlineMember> getAllOnlineMembers() {
		return toList(onlineMemberRepository.findAll());
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
	@Override
	public OnlineMember updateMemberFullName(Long libCardNumber, String old_fullName, String new_fullName) {
		return (OnlineMember) super.updateMemberFullName(libCardNumber, old_fullName, new_fullName);
	}**/
	
	/**@Transactional
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
		 
	}**/
	
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
	
	@Transactional
	 public OnlineMember deleteOnlineMember(Long libCardNumber) {
			OnlineMember onlineMember = onlineMemberRepository.findOnlineMemberByLibCardNumber(libCardNumber);
			
			if (onlineMember == null) {
				throw new IllegalArgumentException("Member does not exist");
			}
			
			onlineMemberRepository.delete(onlineMember);
			return onlineMember;
			
		}
	
	@Transactional
    public List<OnlineMember> getAllOnlineMembers() {
	return toList(onlineMemberRepository.findAll());
    }
	

	<T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	@Transactional
	public OnlineMember loginOnlineMember(String username, String password) {
		if (username == null || username.isBlank()) {
			throw new IllegalArgumentException("Please provide a username.");
		}
		if (password == null || password.isBlank()) {
			throw new IllegalArgumentException("Please provide a password.");
		}
		OnlineMember om = onlineMemberRepository.findOnlineMemberByMemberUsername(username);
		if (om == null) {
			throw new IllegalStateException("Could not find online member with that username.");
		}
		if (!om.getMemberPassword().equals(password)) {
			throw new IllegalStateException("Password does not match username.");
		}
		return om;
	}


	@Transactional
	public OnlineMember loginAsOM(String username, String password) {
       if (username == null || username.trim().length() == 0 || username.isBlank()) {
           throw new IllegalArgumentException("Please enter a valid username or email");
       }
       if (password == null || password.trim().length() == 0 || password.isBlank()) {
           throw new IllegalArgumentException("Please enter a valid Password");
       }

       List<OnlineMember> onlinemembers = getAllOnlineMembers();
       
       OnlineMember foundOM = null;

       for (OnlineMember om : onlinemembers) {

           if ((om.getMemberUsername().equals(username) || om.getMemberEmail().equals(username)) && om.getMemberPassword().equals(password)) {
          
               currentOMAccount = om;
               foundOM = om;
               break;
           }
       }
       
       if (foundOM == null) {
           throw new IllegalArgumentException("Account does not exist, please register a new account or try again.");
       }

       return foundOM;

   }


@Transactional
   public OnlineMember getLoggedOMAccount() {
	
	if (currentOMAccount == null) {
		throw new IllegalArgumentException("No account is logged into at the moment. Please log in.");
	}
	else {
       return currentOMAccount;
   }
}

   @Transactional
   public void logout() {
       currentOMAccount = null;
   }

}
