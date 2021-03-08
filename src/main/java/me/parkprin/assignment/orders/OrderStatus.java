package me.parkprin.assignment.orders;

public enum OrderStatus {

    REQUESTED("REQUESTED"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    SHIPPING("SHIPPING"),
    COMPLETED("COMPLETED");


    public final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
