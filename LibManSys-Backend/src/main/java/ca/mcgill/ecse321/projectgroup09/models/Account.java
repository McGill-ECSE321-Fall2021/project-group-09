package ca.mcgill.ecse321.projectgroup09.models;

import java.util.List;

//JPA tags added

import javax.persistence.*;

@Entity

public abstract class Account {
	
	
	private String fullName;
	
	private LibraryManagement libraryManagement;
	
	@ElementCollection
	private List<Booking> bookings;

	public void setFullName(String aFullName) {
		this.fullName = aFullName; 
	}
	
	
	@Id
	public String getFullName() {
		return fullName;
	}

	@ManyToOne
	public LibraryManagement getLibraryManagement() {
		return libraryManagement;
	}

	@OneToMany
	public List<Booking> getBookings() {
		return this.bookings;
	}
	
	@OneToMany
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public void setLibraryManagement(LibraryManagement aLibraryManagement) {
		this.libraryManagement = aLibraryManagement;
	}

	

}

