package ca.mcgill.ecse321.projectgroup09.models;

public class OnlineMember extends Member{

	  //------------------------
	  // MEMBER VARIABLES
	  //------------------------

	  //OnlineMember Attributes
	  private String memberEmail;
	  private String memberPassword;
	  private String memberUsername;

	  //------------------------
	  // CONSTRUCTOR
	  //------------------------

	  public OnlineMember(String aFullName, LibraryManagement aLibraryManagement, int aLibCardNumber, String aAddress, boolean aIsResident, String aPhoneNumber, double aAmountOwed, int aActiveLoans, boolean aIsVerified, String aMemberEmail, String aMemberPassword, String aMemberUsername)
	  {
	    super(aFullName, aLibraryManagement, aLibCardNumber, aAddress, aIsResident, aPhoneNumber, aAmountOwed, aActiveLoans, aIsVerified);
	    memberEmail = aMemberEmail;
	    memberPassword = aMemberPassword;
	    memberUsername = aMemberUsername;
	  }

	  //------------------------
	  // INTERFACE
	  //------------------------

	  public boolean setMemberEmail(String aMemberEmail)
	  {
	    boolean wasSet = false;
	    memberEmail = aMemberEmail;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setMemberPassword(String aMemberPassword)
	  {
	    boolean wasSet = false;
	    memberPassword = aMemberPassword;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setMemberUsername(String aMemberUsername)
	  {
	    boolean wasSet = false;
	    memberUsername = aMemberUsername;
	    wasSet = true;
	    return wasSet;
	  }

	  public String getMemberEmail()
	  {
	    return memberEmail;
	  }

	  public String getMemberPassword()
	  {
	    return memberPassword;
	  }

	  public String getMemberUsername()
	  {
	    return memberUsername;
	  }

	  public void delete()
	  {
	    super.delete();
	  }


	  public String toString()
	  {
	    return super.toString() + "["+
	            "memberEmail" + ":" + getMemberEmail()+ "," +
	            "memberPassword" + ":" + getMemberPassword()+ "," +
	            "memberUsername" + ":" + getMemberUsername()+ "]";
	  }
}
