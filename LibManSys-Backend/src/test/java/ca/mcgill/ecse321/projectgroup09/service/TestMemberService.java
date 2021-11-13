package ca.mcgill.ecse321.projectgroup09.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.context.annotation.Profile;

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
	public void TestGetProfile2() {
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
}
