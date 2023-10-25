package ru.web.laba_web2.constants;

public enum Role{
        USER("User"),
    ADMIN("Admin");

    private String name;

        Role(String name) {
            this.name = name;
        }
    }