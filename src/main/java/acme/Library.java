package acme;

import java.util.HashMap;

public class Library {

    private final LibraryMembershipSystem membership;
    private HashMap<Book, Member> booksAndMembers;

    public Library(LibraryMembershipSystem membership) {
        this.membership = membership;
        booksAndMembers = new HashMap<>();
    }

    public void borrow(Book book, Member member) throws LoanException {

        if (!hasMembership(member)) {
            throw new LoanException("You must be a registered member to borrow books");
        }

        if (exceedBookAllowance(member)) {
            throw new LoanException("You cannot borrow more than 2 books");
        }

        loanBook(book, member);
    }

    private Member loanBook(Book book, Member member) {
        return booksAndMembers.put(book, member);
    }

    private boolean hasMembership(Member member) {
        return membership.has(member);
    }

    private boolean exceedBookAllowance(Member member) {
        return booksAndMembers.entrySet().stream().filter(x -> x.getValue().equals(member)).count() >= 2;
    }

    public Member whoHas(Book book) {
        return booksAndMembers.get(book);
    }
}
