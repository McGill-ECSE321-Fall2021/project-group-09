package ca.mcgill.ecse321.projectgroup09.dto;

import ca.mcgill.ecse321.projectgroup09.models.Movie;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;

public class MovieDto {

	 //Movie Attributes
	  private String director;
	  private int runtime;
	  private String genre;
	  private String title;
	  private Integer loanablePeriod;
	  private Long libraryItemId;
	  private Integer publishedYear;
	  private ItemStatus itemStatus;
	  private Double dailyOverdueFee;
	  
	  public MovieDto() {
	}
	  
	  public MovieDto(Long libraryItemId, String title, Integer publishedYear,
				Integer loanablePeriod, Double dailyOverdueFee, ItemStatus itemStatus, String director,
				Integer runtime, String genre) {  
	        
	        this.title = title;
			this.dailyOverdueFee = dailyOverdueFee;
			this.publishedYear = publishedYear;
			this.genre = genre;
			this.itemStatus = itemStatus;
			this.libraryItemId = libraryItemId;
			this.loanablePeriod = loanablePeriod;
			this.publishedYear = publishedYear;
			this.runtime = runtime;
			
					}
	
		public void setTitle(String aTitle) {
			this.title= aTitle;
		}

	
		public void setPublishedYear(int aPublishedYear) {
			this.publishedYear= aPublishedYear;
		}
	

		public String getTitle() {
			return title;
		}

	  public void setDirector(String aDirector)
	  {
	    this.director = aDirector;
	  }

	  public void setRuntime(int aRuntime)
	  {
		 this.runtime = aRuntime;
	  }

	  public void setGenre(String aGenre)
	  {
	    this.genre = aGenre;
	  }

	  public String getDirector()
	  {
	    return this.director;
	  }

	  public int getRuntime()
	  {
	    return this.runtime;
	  }

	  public String getGenre()
	  {
	    return genre;
	  }

	public void setLoanablePeriod(Integer loanablePeriod) {
		this.loanablePeriod = loanablePeriod;
	}

	public Long getLibraryItemId() {
		return libraryItemId;
	}

	public void setLibraryItemId(Long libraryItemId) {
		this.libraryItemId = libraryItemId;
	}

	public Integer getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(Integer publishedYear) {
		this.publishedYear = publishedYear;
	}

	public ItemStatus getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(ItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Double getDailyOverdueFee() {
		return dailyOverdueFee;
	}

	public void setDailyOverdueFee(Double dailyOverdueFee) {
		this.dailyOverdueFee = dailyOverdueFee;
	}

	 

	  
}
