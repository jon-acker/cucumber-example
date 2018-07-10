package acme;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Basket {
    private ArrayList<Sandwich> sandwiches = new ArrayList<Sandwich>();

    public static Basket empty() {
        return new Basket();
    }

    public void add(Sandwich sandwich) {
        this.sandwiches.add(sandwich);

        Stream<Sandwich> withBacon = this.sandwiches.stream().filter(s -> {
            return s.getName().equals("bacon");
        });

        if (withBacon.count() > 1 ) {
            this.sandwiches.add(new Sandwich("coke"));
        }
    }

    public ArrayList<Sandwich> getContents() {
        return this.sandwiches;
    }
}
