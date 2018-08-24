package acme;

import java.util.Objects;

public class Member {
    private final String memberName;

    public Member(String memberName) {
        this.memberName = memberName;
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

