package acme.domain;

import acme.Book;
import acme.Loans;
import acme.Member;

import java.util.HashMap;
import java.util.List;

public class InMemoryLoans implements Loans {

    private HashMap<Book, Member> booksAndMembers = new HashMap<Book, Member>();

    public void add(Book book, Member member) {
        booksAndMembers.put(book, member);
    }

    public long findLoanCountFor(Member member) {
        return booksAndMembers.entrySet().stream().filter(x -> x.getValue().equals(member)).count();
    }

    @Override
    public Member findBorrowerOf(Book book) {
        return booksAndMembers.get(book);
    }
}
