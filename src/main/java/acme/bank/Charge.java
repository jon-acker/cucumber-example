package acme.bank;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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