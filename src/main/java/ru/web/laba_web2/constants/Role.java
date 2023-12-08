package ru.web.laba_web2.constants;

public enum Role {
    USER(0),
    ADMIN(1);

    private int name;

    Role(int name) {
        this.name = name;
    }
}