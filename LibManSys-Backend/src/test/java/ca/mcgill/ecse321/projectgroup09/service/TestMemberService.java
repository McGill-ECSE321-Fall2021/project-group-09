package ca.mcgill.ecse321.projectgroup09.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.Member;

@ExtendWith(MockitoExtension.class)
public class TestMemberService {
	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberService memberService;

	private static final String FULLNAME1 = "Harry";
	private static final String ADDRESS_LINE1 = "Assguard, Universe";
	private static final String PHONE_NUMBER1 = "8889995555";
	private static final Long LIB_CARD_NUM1 = 5653l;
	private static final double AMOUNT_OWED1 = 0;
	private static final int ACTIVE_LOANS1 = 0; 
	private static final boolean IS_VERIFIED_RESIDENT1 = true;

	private static final String FULLNAME2 = "Ron";
	private static final String ADDRESS_LINE2 = "Mars, Solar System";
	private static final String PHONE_NUMBER2 = "5554442222";
	private static final Long LIB_CARD_NUM2 = 7623l;
	private static final double AMOUNT_OWED2 = 10;
	private static final int ACTIVE_LOANS2 = 1;
	private static final boolean IS_VERIFIED_RESIDENT2 = false;

	
	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(memberRepository.save(any(Member.class))).thenAnswer(returnParameterAsAnswer);
		
		lenient().when(memberRepository.findMemberByLibCardNumber(anyLong()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(LIB_CARD_NUM1)) {
						Member member1 = new Member();
						member1.setLibCardNumber(LIB_CARD_NUM1);
						member1.setAddress(ADDRESS_LINE1);
						member1.setFullName(FULLNAME1);
						member1.setPhoneNumber(PHONE_NUMBER1);
						member1.setAmountOwed(AMOUNT_OWED1);
						member1.setActiveLoans(ACTIVE_LOANS1);
						member1.setIsVerifiedResident(IS_VERIFIED_RESIDENT1);

						return member1;
					}

					if (invocation.getArgument(0).equals(LIB_CARD_NUM2)) {
						Member member2 = new Member();
						member2.setLibCardNumber(LIB_CARD_NUM2);
						member2.setAddress(ADDRESS_LINE2);
						member2.setFullName(FULLNAME2);
						member2.setPhoneNumber(PHONE_NUMBER2);
						member2.setAmountOwed(AMOUNT_OWED2);
						member2.setActiveLoans(ACTIVE_LOANS2);
						member2.setIsVerifiedResident(IS_VERIFIED_RESIDENT2);
				

						return member2;
					}
					return null;
				});
		
		/*lenient().when(memberRepository.findMemberByFullName(anyString()))
		.thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(FULLNAME1)) {
				Member member1 = new Member();
				member1.setLibCardNumber(LIB_CARD_NUM1);
				member1.setAddress(ADDRESS_LINE1);
				member1.setFullName(FULLNAME1);
				member1.setPhoneNumber(PHONE_NUMBER1);
				member1.setAmountOwed(AMOUNT_OWED1);
				member1.setActiveLoans(ACTIVE_LOANS1);
				member1.setIsVerifiedResident(IS_VERIFIED_RESIDENT1);

				return member1;
			}

			if (invocation.getArgument(0).equals(FULLNAME2)) {
				Member member2 = new Member();
				member2.setLibCardNumber(LIB_CARD_NUM2);
				member2.setAddress(ADDRESS_LINE2);
				member2.setFullName(FULLNAME2);
				member2.setPhoneNumber(PHONE_NUMBER2);
				member2.setAmountOwed(AMOUNT_OWED2);
				member2.setActiveLoans(ACTIVE_LOANS2);
				member2.setIsVerifiedResident(IS_VERIFIED_RESIDENT2);
		

				return member2;
			}
			return null;
		});*/

		lenient().when(memberRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Member> members = new ArrayList<>();

			Member member1 = new Member();
			member1.setLibCardNumber(LIB_CARD_NUM1);
			member1.setAddress(ADDRESS_LINE1);
			member1.setFullName(FULLNAME1);
			member1.setPhoneNumber(PHONE_NUMBER1);
			member1.setAmountOwed(AMOUNT_OWED1);
			member1.setActiveLoans(ACTIVE_LOANS1);
			member1.setIsVerifiedResident(IS_VERIFIED_RESIDENT1);
			
			Member member2 = new Member();
			member2.setLibCardNumber(LIB_CARD_NUM2);
			member2.setAddress(ADDRESS_LINE2);
			member2.setFullName(FULLNAME2);
			member2.setPhoneNumber(PHONE_NUMBER2);
			member2.setAmountOwed(AMOUNT_OWED2);
			member2.setActiveLoans(ACTIVE_LOANS2);
			member2.setIsVerifiedResident(IS_VERIFIED_RESIDENT2);

			members.add(member1);
			members.add(member2);

			return members;

		});

	}
	
	@Test
	public void TestGetMember1() {
		Long LIB_CARD_NUM = 5653l;
		Member member1 = null;
		try {
			member1 = memberService.getMemberByLibCardNumber(LIB_CARD_NUM);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(member1);
		assertEquals(LIB_CARD_NUM1, member1.getLibCardNumber());
		assertEquals(FULLNAME1, member1.getFullName());
		assertEquals(ADDRESS_LINE1, member1.getAddress());
		assertEquals(PHONE_NUMBER1, member1.getPhoneNumber());
		assertEquals(AMOUNT_OWED1, member1.getAmountOwed());
		assertEquals(ACTIVE_LOANS1, member1.getActiveLoans());
		assertEquals(IS_VERIFIED_RESIDENT1, member1.getIsVerifiedResident());
	
	}

	@Test
	public void TestGetMember2() {
		Long lib_Card_Num = 7623l;
		Member member2 = null;
		try {
			member2 = memberService.getMemberByLibCardNumber(lib_Card_Num);
		} catch (IllegalArgumentException e) {
			fail();
		}


		assertNotNull(member2);
		assertEquals(LIB_CARD_NUM2, member2.getLibCardNumber());
		assertEquals(FULLNAME2, member2.getFullName());
		assertEquals(ADDRESS_LINE2, member2.getAddress());
		assertEquals(PHONE_NUMBER2, member2.getPhoneNumber());
		assertEquals(AMOUNT_OWED2, member2.getAmountOwed());
		assertEquals(ACTIVE_LOANS2, member2.getActiveLoans());
		assertEquals(IS_VERIFIED_RESIDENT2, member2.getIsVerifiedResident());

	}
	
	@Test
	public void TestGetMemberByInvalidId() {
		Long invalidId = 4312l;
		Member member = null;

		String error = null;
		try {
			member = memberService.getMemberByLibCardNumber(invalidId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(member);
		assertEquals(error, "No member with the library card number exists");

	}
	
	/*@Test
	public void TestGetMember1ByFullName() {
		String fullName = "Harry";
		List<Member> members = null;
		
		try {
			members = memberService.getMemberByFullName(fullName);
		} 
		catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(members);
		
	}
	
	@Test
	public void TestGetMember2ByFullName() {
		String fullName = "Ron";
		List<Member> members = null;
		
		try {
			members = memberService.getMemberByFullName(fullName);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(members);
		
	}
	
	
	
	@Test
	public void TestGetMemberByInvalidFullName() {
		String fullName = "Hermione";
		List<Member> members = null;

		String error = null;

		try {
			members = memberService.getMemberByFullName(fullName);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(members);
		assertEquals("No members with the full name exists!", error);

	}*/
	
	@Test
	public void TestCreateMember() {
		String fullName = "Hermoine";
		String addressLine = "MidGuard";
		String phoneNumber = "4440007777";
		
		Member member= null;
		try {
			member = memberService.createMember(fullName, addressLine, phoneNumber);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(member);
		assertNotNull(member.getLibCardNumber());
		assertEquals(fullName, member.getFullName());
		assertEquals(addressLine, member.getAddress());
		assertEquals(phoneNumber, member.getPhoneNumber());
        assertEquals(false, member.getIsVerifiedResident());
        assertEquals(0, member.getAmountOwed());
        assertEquals(0, member.getActiveLoans());
	}
	
	@Test
	public void TestCreateMemberWithEmptyFullName() {
		String fullName = "";
		String addressLine = "MidGuard";
		String phoneNumber = "4440007777";
		
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.createMember(fullName, addressLine, phoneNumber);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "Full Name cannot be null or empty");
	}
	
	
	@Test
	public void TestCreateMemberWithNullFullName() {
		String fullName = "";
		String addressLine = "MidGuard";
		String phoneNumber = "4440007777";
		
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.createMember(fullName, addressLine, phoneNumber);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "Full Name cannot be null or empty");
	}
	
	
	@Test
	public void TestCreateMemberWithEmptyAddress() {
		String fullName = "Hermoine";
		String addressLine = "";
		String phoneNumber = "4440007777";
		
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.createMember(fullName, addressLine, phoneNumber);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "Address cannot be null or empty");
		
	}
	
	@Test
	public void TestCreateMemberWithNullAddress() {
		String fullName = "Hermoine";
		String addressLine = null;
		String phoneNumber = "4440007777";
		
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.createMember(fullName, addressLine, phoneNumber);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "Address cannot be null or empty");
		
	}
	
	@Test
	public void TestCreateMemberWithNullPhoneNumber() {
		String fullName = "Hermoine";
		String addressLine = "Midguard";
		String phoneNumber = null;
		
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.createMember(fullName, addressLine, phoneNumber);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "Phone Number cannot be null or empty");
		
	}
	
	@Test
	public void TestCreateMemberWithEmptyPhoneNumber() {
		String fullName = "Hermoine";
		String addressLine = "Midguard";
		String phoneNumber = "";
		
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.createMember(fullName, addressLine, phoneNumber);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "Phone Number cannot be null or empty");
		
	}
	
	@Test
	public void TestCreateProfilePhoneNumberLongerThan10() {
		String fullName = "Hermoine";
		String addressLine = "Central London";
		String phoneNumber = "44488855550";
		
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.createMember(fullName, addressLine, phoneNumber);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "Phone Number must be 10 characters long");
		
	}
	
	@Test
	public void TestCreateProfilePhoneNumberLessThan10() {
		String fullName = "Hermoine";
		String addressLine = "Central London";
		String phoneNumber = "12";
		
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.createMember(fullName, addressLine, phoneNumber);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "Phone Number must be 10 characters long");
		
	}
	
	@Test
	public void TestUpdateMemberFullName () {
		Long libCardNum = 5653l;
		String old_Name = "Harry";
		String new_Name = "Berry";
		
		Member member= null;
		
		try {
			member = memberService.updateMemberFullName(libCardNum, old_Name, new_Name);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(member);
		assertEquals(new_Name, member.getFullName());
	
	}
	
	@Test
	public void TestUpdateMemberFullNameWithEmptyOldFullName () {
		Long libCardNum = 5653l;
		String old_Name = "";
		String new_Name = "Berry";
			
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.updateMemberFullName(libCardNum, old_Name, new_Name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "The old full name cannot be null or empty.");
	
	}
	
	@Test
	public void TestUpdateMemberFullNameWithNullOldFullName () {
		Long libCardNum = 5653l;
		String old_Name = null;
		String new_Name = "Berry";
			
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.updateMemberFullName(libCardNum, old_Name, new_Name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "The old full name cannot be null or empty.");
	
	}
	
	@Test
	public void TestUpdateMemberFullNameWithEmptyNewFullName () {
		Long libCardNum = 5653l;
		String old_Name = "Harry";
		String new_Name = "";
			
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.updateMemberFullName(libCardNum, old_Name, new_Name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "The new full name cannot be null or empty.");
	
	}
	
	@Test
	public void TestUpdateMemberFullNameWithNullNewFullName () {
		Long libCardNum = 5653l;
		String old_Name = "Harry";
		String new_Name = null;
			
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.updateMemberFullName(libCardNum, old_Name, new_Name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "The new full name cannot be null or empty.");
	
	}
	
	@Test
	public void TestUpdateMemberFullNameWithNullLibCardNum () {
		Long libCardNum = null;
		String old_Name = "Harry";
		String new_Name = "Berry";
			
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.updateMemberFullName(libCardNum, old_Name, new_Name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "Library Card Number cannot be null or empty.");
	
	}
	
	@Test
	public void TestUpdateMemberdFullNameWithInvalidLibCardNum () {
		Long libCardNum = 1L;
		String old_Name = "Harry";
		String new_Name = "Berry";
			
		String error = null;
		
		Member member= null;
		
		try {
			member = memberService.updateMemberFullName(libCardNum, old_Name, new_Name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(member);
		assertEquals(error, "No member with the library card number exists.");
	
	}
	
	
	
	
	
}
