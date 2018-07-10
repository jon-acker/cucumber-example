package acme;

import java.util.Objects;

public class Sandwich {
    private String sandwichType;

    public Sandwich(String sandwichType) {

        this.sandwichType = sandwichType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sandwich sandwich = (Sandwich) o;
        return Objects.equals(sandwichType, sandwich.sandwichType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sandwichType);
    }

    public String getName() {
        return sandwichType;
    }
}
