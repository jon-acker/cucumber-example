package acme.domain;

import acme.LibraryMembershipSystem;
import acme.Member;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("cucumber-glue")
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
