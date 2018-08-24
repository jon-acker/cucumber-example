package acme;

public interface FineManager {
    void add(Member member, int fineAmount);

    boolean hasFinesFor(Member member);
}
