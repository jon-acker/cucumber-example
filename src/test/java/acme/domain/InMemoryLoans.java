package acme.domain;

import acme.Book;
import acme.Loan;
import acme.Loans;
import acme.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryLoans implements Loans {

    private List<Loan> loans = new ArrayList<Loan>();

    public void add(Book book, Member member, LocalDateTime loanDate) {
        loans.add(new Loan(book, member, loanDate));
    }

    public long findLoanCountFor(Member member) {
        return loans.stream().filter(loan -> loan.belongsTo(member)).count();
    }

    public boolean bookIsLoanedToMember(Book book, Member member) {
        return loans.stream()
                .filter(loan -> loan.of(book) && loan.belongsTo(member)).count() > 0;
    }
}
