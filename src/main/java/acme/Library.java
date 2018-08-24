package acme;

import java.util.HashMap;

public class Library {

    private final LibraryMembershipSystem membership;
    private final Loans loans;
    private final FineManager fineManager;

    public Library(LibraryMembershipSystem membership, Loans loans, FineManager fineManager) {
        this.membership = membership;
        this.loans = loans;
        this.fineManager = fineManager;
    }

    public void lend(Book book, Member member) throws LoanException {

        if (!hasMembership(member)) {
            throw new LoanException("You must be a registered member to borrow books");
        }

        if (exceedBookAllowance(member)) {
            throw new LoanException("You cannot borrow more than 2 books");
        }

        if (fineManager.hasFinesFor(member)) {
            throw new LoanException("you cannot borrow any books because you have unpaid fines");
        }

        loanBook(book, member);
    }

    private void loanBook(Book book, Member member) {
        loans.add(book, member);
    }

    private boolean hasMembership(Member member) {
        return membership.has(member);
    }

    private boolean exceedBookAllowance(Member member) {
        return loans.findLoanCountFor(member) >= 2;
    }

    public Member whoHas(Book book) {
        return loans.findBorrowerOf(book);
    }
}
