package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;

@Entity
public class OnlineMember extends Member{

	  //OnlineMember Attributes
	  private String memberEmail;
	  private String memberPassword;
	  private String memberUsername;

	  
	  @Override
		public void setFullName(String aFullName) {
			super.setFullName(aFullName); 
		}
		
		@Override
		public String getFullName() {
			return super.getFullName();
		}
		

		@Override
		public void setLibCardNumber(Long aLibCardNumber) {
			super.setLibCardNumber(aLibCardNumber);
		}
		
		@Override
		public Long getLibCardNumber() {
			return super.getLibCardNumber();
		}

		@Override
		public void setAddress(String anAddress) {
			super.setAddress(anAddress);
		}
		
		@Override
		public String getAddress() {
			return super.getAddress();
		}

		@Override
		public void setIsResident(boolean aIsResident) {
			super.setIsResident(aIsResident);
		}
		
		@Override
		public boolean getIsResident() {
			return super.getIsResident();
		}

		@Override
		public void setPhoneNumber(String aPhoneNumber) {
			super.setPhoneNumber(aPhoneNumber);
		}
		
		@Override
		public String getPhoneNumber() {
			return super.getPhoneNumber();
		}

		@Override
		public void setAmountOwed(double anAmountOwed) {
			super.setAmountOwed(anAmountOwed);
		}
		
		@Override
		public double getAmountOwed() {
			return super.getAmountOwed();
		}

		@Override
		public void setActiveLoans(int anActiveLoans) {
			super.setActiveLoans(anActiveLoans);
		}

		@Override
		public int getActiveLoans() {
			return super.getActiveLoans();
		}
		
		@Override
		public void setIsVerified(boolean aIsVerified) {
			super.setIsVerified(aIsVerified);
		}
		
		@Override
		public boolean getIsVerified() {
			return super.getIsVerified();
		}
		
	  
	  public void setMemberEmail(String aMemberEmail)
	  {
	    this.memberEmail = aMemberEmail; 
	  }

	  public void setMemberPassword(String aMemberPassword)
	  {
	    this.memberPassword = aMemberPassword;
	  }

	  public void setMemberUsername(String aMemberUsername)
	  {
		this.memberUsername = aMemberUsername;
	  }

	  public String getMemberEmail()
	  {
	    return this.memberEmail;
	  }

	  public String getMemberPassword()
	  {
	    return this.memberPassword;
	  }

	  public String getMemberUsername()
	  {
	    return this.memberUsername;
	  }

	  
	
}
