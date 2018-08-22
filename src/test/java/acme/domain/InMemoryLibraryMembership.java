package acme.domain;

import acme.LibraryMembershipSystem;
import acme.Member;

import java.util.ArrayList;
import java.util.List;

public class InMemoryLibraryMembership implements LibraryMembershipSystem   {

    private List<Member> members = new ArrayList<>();

    public void add(Member member) {
        members.add(member);
    }

    @Override
    public boolean has(Member member) {
        return members.contains(member);
    }
}
