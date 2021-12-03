package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;

import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;

/**
 * Data Transfer Object for Book model class.
 * 
 * Need to store all information of Book class in this class,
 * including superclass information (LibraryItem).
 */
public class BookDto extends LibraryItemDto {
	
	// Book attributes
	private String author;
	private String publisher;
	private String ISBN;
	private int numPages;
	
	/**
	 * Default constructor.
	 */
	public BookDto() {
		
	}
	
	/**
	 * Initialize a new BookDto object with the specified attributes. This constructor creates a BookDto object with all fields filled.
	 * @param aLibraryItemId
	 * @param aTitle
	 * @param aPublishedYear
	 * @param aLoanablePeriod
	 * @param aDailyOverdueFee
	 * @param aItemStatus
	 * @param aMember
	 * @param aLoans
	 * @param aAuthor
	 * @param aPublisher
	 * @param aISBN
	 * @param aNumPages
	 */
	public BookDto(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee,
			ItemStatus aItemStatus, Member aMember, List<Loan> aLoans, String aAuthor, String aPublisher, String aISBN, int aNumPages) {
		super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod,aDailyOverdueFee, aItemStatus, aMember, aLoans, LibraryItemType.BOOK);
		
		this.author = aAuthor;
		this.publisher = aPublisher;
		this.ISBN = aISBN;
		this.numPages = aNumPages;
	}
	
	/**
	 * Isn't this just the same as above constructor? But takes entire object as input.
	 * Simply takes information of passed object and calls BookDto constructor with it.
	 * 
	 * Construct a BookDto object containing the information of {@code book} and return it.
	 * @param book Returned BookDto object will contain the same information as this input.
	 * @return The BookDto object.
	 */
	public static BookDto convertToDto(Book book) {
		if (book == null) {
			throw new IllegalArgumentException("book parameter cannot be null.");
		}
		//MemberDto memberDto = MemberDto.convertToDto(book.getMember());
		//List<LoanDto> loansDto = new ArrayList<LoanDto>();
		BookDto bookDto = new BookDto(
				book.getlibraryItemID(),
				book.getTitle(),
				book.getPublishedYear(),
				book.getLoanablePeriod(),
				book.getDailyOverdueFee(),
				book.getItemStatus(),
				book.getMember(),
				book.getLoans(),
				book.getAuthor(),
				book.getPublisher(),
				book.getISBN(),
				book.getNumPages()
		);
		return bookDto;
	}
	
	/**
	 * Convert list of books to list of book dtos.
	 * @param books
	 * @return
	 */
	//public static List<BookDto> convertToDtos(List<Book> books) {
	//	return books.stream().map(book -> BookDto.convertToDto(book)).collect(Collectors.toList());
	//}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * @return the numPages
	 */
	public int getNumPages() {
		return numPages;
	}

	/**
	 * @param numPages the numPages to set
	 */
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

}
