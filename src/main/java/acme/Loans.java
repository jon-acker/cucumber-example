package acme;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

public interface Loans {

    public void add(Book book, Member member, LocalDateTime dateTime);

    public long findLoanCountFor(Member member);
}
