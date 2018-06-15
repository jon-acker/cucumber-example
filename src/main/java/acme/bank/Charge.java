package acme.bank;

public class Charge {
    private double amount;

    public Charge(double amount) {
        this.amount = amount;
    }

    @Override 
    public boolean equals(Object charge) {
        return this.amount == ((Charge)charge).amount;
    }
}