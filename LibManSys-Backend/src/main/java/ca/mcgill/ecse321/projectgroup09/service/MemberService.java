

/**
 * @author Zarif Ashraf
 */

package ca.mcgill.ecse321.projectgroup09.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.BookingRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LoanRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired 
	private LoanRepository loanRepository;
	
	@Autowired 
	private BookingRepository bookingRepository;

	@Transactional
	public Member createMember(String fullName, String address, String phoneNumber) {
		
		if (fullName == null || fullName == ""|| fullName.equals("undefined")) {
            throw new IllegalArgumentException("Full Name cannot be null or empty");
        }
		
		if (address == null || address == "" || address.equals("undefined")) {
            throw new IllegalArgumentException("Address cannot be null or empty");
		}
		
		if (phoneNumber == null || phoneNumber == ""|| phoneNumber.equals("undefined")) {
	            throw new IllegalArgumentException("Phone Number cannot be null or empty");
	    }
		
		if (phoneNumber.length() < 10 || phoneNumber.length() > 10) {
	            throw new IllegalArgumentException("Phone Number must be 10 characters long");
	        }
		
		long leftLimit = 000001;
	    long rightLimit = 999999;
	    long libCardNumber = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
		
	        
	        //final Long libCardNumber = UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE;
	
		Member member = new Member();
		member.setFullName(fullName);
		member.setAddress(address);
		member.setPhoneNumber(phoneNumber);
        member.setLibCardNumber(libCardNumber);
        
        memberRepository.save(member);
        return member;
	}

	@Transactional
	 public Member getMemberByLibCardNumber(Long libCardNumber) {
	        if (libCardNumber == null) {
	            throw new IllegalArgumentException("Library Card Number cannot be null or empty");
	        }
	        
	        Member member = memberRepository.findMemberByLibCardNumber(libCardNumber);
	        
	        if (member == null) {
	            throw new IllegalArgumentException("No member with the library card number exists");
	        }

	        return member;
	    }
	
	@Transactional
	public Member getMemberByBookingID(Long bookingID) {
		if (bookingID == null) {
            throw new IllegalArgumentException("Booking cannot be null or empty");
        }
		
		Member member = (memberRepository.findMemberByBookings(bookingRepository.findBookingByBookingID(bookingID)));
		
		if (member == null) {
			 throw new IllegalArgumentException("No member with the booking exists");
		}
		
		return member;
	}
	
	@Transactional
	public Member getMemberByLoanID(Long loanID) {
		if (loanID == null) {
            throw new IllegalArgumentException("Loan cannot be null or empty");
        }
		
		Member member = (memberRepository.findMemberByLoans(loanRepository.findLoanByLoanID(loanID)));
		
		if (member == null) {
			 throw new IllegalArgumentException("No member with the loan exists");
		}
		
		return member;
	}
	
	@Transactional
	public List<Member> getMemberByFullName(String fullName) {
		
		if (fullName == null || fullName == "") {
            throw new IllegalArgumentException("Full name cannot be null or empty");
        }
		        
		List<Member> memberList = toList(memberRepository.findMemberByFullName(fullName));
		
		//List<Member> memberList = memberRepository.findMemberByFullName(fullName);
		
		if ((memberList == null)) {
			throw new IllegalArgumentException("No members with the full name exists!");
		}
		
		else if (memberList.isEmpty() == true) {
			throw new IllegalArgumentException("No members with the full name exists!");
		        } 
		else {
		       return memberList;     
		        }
		    }
	
	@Transactional
	public List<Member> getMembersByVerificationStatus(boolean isVerifiedResident) {
		List<Member> memberList = toList(memberRepository.findMemberByisVerifiedResident(isVerifiedResident));
		
		if (memberList != null) {
            return memberList;
        } else {
            throw new IllegalArgumentException("No members with this verification status exists!");
        }
	}
	
	@Transactional
	    public List<Member> getAllMembers() {
		return toList(memberRepository.findAll());
	    }
	
	@Transactional
	public Member updateMemberFullName(Long libCardNumber, String old_fullName, String new_fullName) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (old_fullName == null || old_fullName == "") {
            throw new IllegalArgumentException("The old full name cannot be null or empty.");
        }
		
		if (new_fullName == null || new_fullName == "") {
            throw new IllegalArgumentException("The new full name cannot be null or empty.");
        }
		
		Member member = memberRepository.findMemberByLibCardNumber(libCardNumber);
        
        if (member == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
        if (member.getFullName() != old_fullName) {
        	throw new IllegalArgumentException("The library Card Number and the member name does not match");
        }
        
        member.setFullName(new_fullName);
        memberRepository.save(member);
		return member;
	}
	
	@Transactional
	public Member updateMemberAddress(Long libCardNumber, String old_address, String new_address) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (old_address == null) {
            throw new IllegalArgumentException("The old address cannot be null or empty.");
        }
		
		if (new_address == null) {
            throw new IllegalArgumentException("The new address cannot be null or empty.");
        }
		
		Member member = memberRepository.findMemberByLibCardNumber(libCardNumber);
        
        if (member == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
        if (member.getAddress() != old_address) {
        	throw new IllegalArgumentException("The library Card Number and the member address does not match");
        }
        
        member.setAddress(new_address);
        memberRepository.save(member);
		return member;
	}
	
	@Transactional
	public Member updateMemberPhoneNumber(Long libCardNumber, String old_PhoneNumber, String new_PhoneNumber) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (old_PhoneNumber == null) {
            throw new IllegalArgumentException("The old phone number cannot be null or empty.");
        }
		
		if (new_PhoneNumber == null) {
            throw new IllegalArgumentException("The new phone number cannot be null or empty.");
        }
		
		Member member = memberRepository.findMemberByLibCardNumber(libCardNumber);
        
        if (member == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
        if (member.getPhoneNumber() != old_PhoneNumber) {
        	throw new IllegalArgumentException("The library Card Number and the member phone number does not match");
        }
        
        member.setAddress(new_PhoneNumber);
        memberRepository.save(member);
		return member;
	}
	
	@Transactional
	public Member updateMemberVerificationStatus(Long libCardNumber, String fullName, boolean isVerifiedResident) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (fullName == null) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }
		
		Member member = memberRepository.findMemberByLibCardNumber(libCardNumber);
        
        if (member == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
        if (member.getFullName() != fullName) {
        	throw new IllegalArgumentException("The library Card Number and the member name does not match");
        }
        
        member.setIsVerifiedResident(isVerifiedResident);
        memberRepository.save(member);
		return member;
	}
	
	@Transactional
	public Member updateAmountOwed(Long libCardNumber, String fullName, double changeInAmount) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (fullName == null) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }
		
		if (changeInAmount == 0) {
			throw new IllegalArgumentException("The change in amount owed cannot be 0.");
		}
		
		Member member = memberRepository.findMemberByLibCardNumber(libCardNumber);
        
        if (member == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
        if (member.getFullName() != fullName) {
        	throw new IllegalArgumentException("The library Card Number and the member name does not match");
        }
        
        double oldAmountOwed = member.getAmountOwed();
        double newAmountOwed = (oldAmountOwed + changeInAmount);
        member.setAmountOwed(newAmountOwed);
        memberRepository.save(member);
		return member;
	}
	
	@Transactional
	public Member updateActiveLoans(Long libCardNumber, String fullName, int changeInActiveLoans) {
		if (libCardNumber == null) {
            throw new IllegalArgumentException("Library Card Number cannot be null or empty.");
        }
        
		if (fullName == null) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }
		
		if (changeInActiveLoans == 0) {
			throw new IllegalArgumentException("The change in active loans cannot be 0.");
		}
		
		Member member = memberRepository.findMemberByLibCardNumber(libCardNumber);
        
        if (member == null) {
            throw new IllegalArgumentException("No member with the library card number exists.");
        }
        
        if (member.getFullName() != fullName) {
        	throw new IllegalArgumentException("The library Card Number and the member name does not match");
        }
        
        int oldActiveLoans = member.getActiveLoans();
        int newActiveLoans = (oldActiveLoans + changeInActiveLoans);
        member.setActiveLoans(newActiveLoans);
        memberRepository.save(member);
		return member;
	}
	
	@Transactional
	 public Member deleteMember(Long libCardNumber) {
			Member member = memberRepository.findMemberByLibCardNumber(libCardNumber);
			
			if (member == null) {
				throw new IllegalArgumentException("Member does not exist");
			}
			
			memberRepository.delete(member);
			return member;
			
		}
	
	@Transactional
	public List<LibraryItem> getReservedItems(Long memberId) {
		if (memberId == null) {
			throw new IllegalArgumentException("memberID argument must not be null");
		}
		
		Member m = memberRepository.findMemberByLibCardNumber(memberId);
		if (m == null) {
			throw new IllegalStateException("Member with library card number " + memberId + " , does not exists in Member repository.");
		}
		
		List<LibraryItem> reservedItems = m.getReserved();// = new ArrayList<LibraryItem>();
		/*
		for (LibraryItem li : m.getReserved()) {
			reservedItems.add(li.clone());
		}
		*/
		return reservedItems;
	}
	
	<T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}
