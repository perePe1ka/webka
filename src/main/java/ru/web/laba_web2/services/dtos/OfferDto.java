package ru.web.laba_web2.services.dtos;

import jakarta.validation.constraints.DecimalMin;
import ru.web.laba_web2.constants.Engine;
import ru.web.laba_web2.constants.Transmission;
import ru.web.laba_web2.viewModel.BaseViewModel;


public class OfferDto extends BaseViewModel {
    private String description;
    private Engine engine;

    private String imageUrl;

    private int milleage;

    private int price;
    private Transmission transmission;


    private int year;

    private String seller;

    private String modelDto;


    public OfferDto() {

    }

    public OfferDto(String description, Engine engine, String imageUrl, int milleage, int price, Transmission transmission, int year, String seller, String modelDto) {
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.milleage = milleage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.modelDto = modelDto;
        this.seller = seller;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMilleage() {
        return milleage;
    }

    public void setMilleage(int milleage) {
        this.milleage = milleage;
    }
    @DecimalMin(value = "0")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModelDto() {
        return modelDto;
    }

    public void setModelDto(String modelDto) {
        this.modelDto = modelDto;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
