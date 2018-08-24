package acme;

import java.time.LocalDateTime;

public class Loan {

    private final Book book;
    private final Member member;
    private final LocalDateTime loanDate;

    public Loan(Book book, Member member, LocalDateTime loanDate) {
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
    }

    public boolean belongsTo(Member member) {
        return this.member.equals(member);
    }

    public boolean of(Book book) {
        return this.book.equals(book);
    }
}
