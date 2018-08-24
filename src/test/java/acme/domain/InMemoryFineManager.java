package acme.domain;

import acme.FineManager;
import acme.Member;

import java.util.HashMap;

public class InMemoryFineManager implements FineManager {

    private HashMap<Member, Integer> fines = new HashMap<>();

    @Override
    public void add(Member member, int fineAmount) {
        fines.put(member, fineAmount);
    }

    @Override
    public boolean hasFinesFor(Member member) {
        return fines.containsKey(member);
    }
}
