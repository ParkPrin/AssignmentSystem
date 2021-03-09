package me.parkprin.assignment.role;

public enum RoleStatus {

    USER("USER"),
    ADMIN("ADMIN"),
    MANAGER("MANAGER");


    public final String value;

    RoleStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
