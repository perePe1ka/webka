package ru.web.laba_web2.constants;

public enum Category{
        CAR("Car"),
    BUS("Bus"),
    TRUCK("Truck"),
    MOTORCYCLE("Motorcycle");

    private String name;

        Category(String name) {
            this.name = name;
        }
    }