package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.sql.*;

@Entity
public class Schedule {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------
	public enum DayofWeek {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}

	// Schedule Attributes

	private int scheduledId;
	private Time openingTime;
	private Time closingTime;
	private DayofWeek dayofWeek;

	// Schedule Associations
	private Librarian librarian;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Schedule(int aScheduledId, Time aOpeningTime, Time aClosingTime, DayofWeek aDayofWeek,
			Librarian aLibrarian) {
		scheduledId = aScheduledId;
		openingTime = aOpeningTime;
		closingTime = aClosingTime;
		dayofWeek = aDayofWeek;
		boolean didAddLibrarian = setLibrarian(aLibrarian);
		if (!didAddLibrarian) {
			throw new RuntimeException(
					"Unable to create schedule due to librarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setScheduledId(int aScheduledId) {
		boolean wasSet = false;
		scheduledId = aScheduledId;
		wasSet = true;
		return wasSet;
	}

	public boolean setOpeningTime(Time aOpeningTime) {
		boolean wasSet = false;
		openingTime = aOpeningTime;
		wasSet = true;
		return wasSet;
	}

	public boolean setClosingTime(Time aClosingTime) {
		boolean wasSet = false;
		closingTime = aClosingTime;
		wasSet = true;
		return wasSet;
	}

	public boolean setDayofWeek(DayofWeek aDayofWeek) {
		boolean wasSet = false;
		dayofWeek = aDayofWeek;
		wasSet = true;
		return wasSet;
	}
	
	@Id
	public int getScheduledId() {
		return scheduledId;
	}

	public Time getOpeningTime() {
		return openingTime;
	}

	public Time getClosingTime() {
		return closingTime;
	}

	public DayofWeek getDayofWeek() {
		return dayofWeek;
	}

	/* Code from template association_GetOne */
	@ManyToOne(optional=false)
	public Librarian getLibrarian() {
		return librarian;
	}

	/* Code from template association_SetOneToAtMostN */
	public boolean setLibrarian(Librarian aLibrarian) {
		boolean wasSet = false;
		// Must provide librarian to schedule
		if (aLibrarian == null) {
			return wasSet;
		}

		// librarian already at maximum (7)
		if (aLibrarian.numberOfSchedules() >= Librarian.maximumNumberOfSchedules()) {
			return wasSet;
		}

		Librarian existingLibrarian = librarian;
		librarian = aLibrarian;
		if (existingLibrarian != null && !existingLibrarian.equals(aLibrarian)) {
			boolean didRemove = existingLibrarian.removeSchedule(this);
			if (!didRemove) {
				librarian = existingLibrarian;
				return wasSet;
			}
		}
		librarian.addSchedule(this);
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		Librarian placeholderLibrarian = librarian;
		this.librarian = null;
		if (placeholderLibrarian != null) {
			placeholderLibrarian.removeSchedule(this);
		}
	}

	public String toString() {
		return super.toString() + "[" + "scheduledId" + ":" + getScheduledId() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "openingTime" + "="
				+ (getOpeningTime() != null
						? !getOpeningTime().equals(this) ? getOpeningTime().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "closingTime" + "="
				+ (getClosingTime() != null
						? !getClosingTime().equals(this) ? getClosingTime().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "dayofWeek" + "="
				+ (getDayofWeek() != null
						? !getDayofWeek().equals(this) ? getDayofWeek().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "librarian = "
				+ (getLibrarian() != null ? Integer.toHexString(System.identityHashCode(getLibrarian())) : "null");
	}
}
