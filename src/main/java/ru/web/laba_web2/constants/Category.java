package ru.web.laba_web2.constants;

public enum Category{
    CAR(0),
    BUS(1),
    TRUCK(2),
    MOTORCYCLE(3);

    private int name;

        Category(int name) {
            this.name = name;
        }
    }