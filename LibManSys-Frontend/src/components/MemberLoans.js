function LoanDto(borrowedDate, returnDate, lateFees, loanStatus, loanId,
	libraryItem, member, librarian) {

	this.borrowedDate = borrowedDate;
	this.returnDate = returnDate;
	this.lateFees = lateFees;
	this.loanStatus = loanStatus;
	this.loanId = loanId;
	this.libraryItem = libraryItem;
	this.member = member;
	this.librarian = librarian;

}

export default {
	data() {

		return {
			loans: [],
			newLoan: '',
			response: []
		}
	},
	methods: {
	createLoan: function(borrowedDate, returnDate, lateFees, loanStatus, loanId,
	libraryItem, member, librarian){
		var p = new LoanDto(borrowedDate, returnDate, lateFees, loanStatus, loanId,
	libraryItem, member, librarian);
		this.loans.push(p);
		this.newLoan = '';
	}
	}
	,
	created: function() {
		const l1 = new LoanDto("2022 - 09 - 01", "2022 - 09 - 09", 0, Active, 6, 3, 667802, 29460);
		this.loans = [l1];
	}
}