package acme;

import java.util.Objects;

public class Member {

    private final String memberName;
    private Book book;

    public Member(String memberName) {
        this.memberName = memberName;
    }

    public Member borrow(Book book) {
        this.book = book;
        return this;
    }

    public void from(Library library) throws LoanException {
        library.borrow(this.book, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(memberName, member.memberName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberName, book);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberName='" + memberName + '\'' +
                ", book=" + book +
                '}';
    }
}

