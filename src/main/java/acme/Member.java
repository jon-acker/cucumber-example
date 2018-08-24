package acme;

import java.util.Objects;

public class Member {

    public class Borrowing {

        private final Book book;
        private final Member member;

        private Borrowing(Book book, Member member) {
            this.book = book;
            this.member = member;
        }

        public void from(Library library) throws LoanException {
            library.borrow(this.book, this.member);
        }
    }

    private final String memberName;

    public Member(String memberName) {
        this.memberName = memberName;
    }

    public Borrowing borrow(Book book) {
        return new Borrowing(book, this);
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
        return Objects.hash(memberName);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberName='" + memberName + '}';
    }
}

