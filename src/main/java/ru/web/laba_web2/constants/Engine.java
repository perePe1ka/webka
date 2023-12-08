package ru.web.laba_web2.constants;

public enum Engine {
    GASOLINE(0),
    DIESEL(1),
    ELECTRIC(2),
    HYBRID(3);

    private int name;

    Engine(int name) {
        this.name = name;
    }
}