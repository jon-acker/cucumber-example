package acme;

import java.util.Objects;

public class Book {
    private final String name;

    public Book(String name) {
        this.name = name;
    }

    public void addTo(StockManager stockManager) {
        stockManager.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
