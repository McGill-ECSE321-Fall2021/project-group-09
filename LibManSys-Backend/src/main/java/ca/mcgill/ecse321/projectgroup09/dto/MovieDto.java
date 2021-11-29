package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;

import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Movie;

public class MovieDto extends LibraryItemDto {
	
	 //Movie Attributes
	  private String director;
	  private int runtime;
	  private String genre;
	
	  public MovieDto() {
	  
	  }
	
	  public MovieDto(Long aLibraryItemId, String aTitle, Integer aPublishedYear,
				Integer aLoanablePeriod, Double aDailyOverdueFee, ItemStatus aItemStatus, 
				Member aMember, List<Loan> aLoans,
				String aDirector, Integer aRuntime, String aGenre) {
		  super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod,aDailyOverdueFee, aItemStatus, aMember, aLoans, LibraryItemType.MOVIE);
			this.director = aDirector;
			this.runtime = aRuntime;
			this.genre = aGenre;
			
	  }
	
		public static MovieDto convertToDto(Movie movie) {
			if (movie == null) {
				throw new IllegalArgumentException("movie parameter cannot be null.");
			}
			//MemberDto memberDto = MemberDto.convertToDto(movie.getMember());
			//List<LoanDto> loansDto = new ArrayList<LoanDto>();
			MovieDto movieDto = new MovieDto(
					movie.getlibraryItemID(),
					movie.getTitle(),
					movie.getPublishedYear(),
					movie.getLoanablePeriod(),
					movie.getDailyOverdueFee(),
					movie.getItemStatus(),
					movie.getMember(),
					movie.getLoans(),
					movie.getDirector(),
					movie.getRuntime(),
					movie.getGenre()
			);
			return movieDto;
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
}
