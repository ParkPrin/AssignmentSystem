package me.parkprin.assignment.role;

public enum RoleStatus {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER");


    public final String value;

    RoleStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
