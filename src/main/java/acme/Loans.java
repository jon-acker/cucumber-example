package acme;

import java.util.HashMap;

public interface Loans {

    public void add(Book book, Member member);

    public long findLoanCountFor(Member member);

    public Member findBorrowerOf(Book book);
}
