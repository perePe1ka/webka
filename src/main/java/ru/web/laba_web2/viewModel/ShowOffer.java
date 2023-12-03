package ru.web.laba_web2.viewModel;

import ru.web.laba_web2.constants.Engine;
import ru.web.laba_web2.constants.Transmission;

public class ShowOffer {
    private String description;
    private Engine engine;
    private int milleage;
    private int price;
    private int year;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getMilleage() {
        return milleage;
    }

    public void setMilleage(int milleage) {
        this.milleage = milleage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return description;
    }
}
