package ru.web.laba_web2.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class OfferDto {
    private Long uuid;
    private String description;
    private Engine engine;

    private String imageUrl;

    private int milleage;

    private int price;
    private Transmission transmission;


    private int year;

    private LocalDate created;

    private LocalDate modified;


    public OfferDto() {

    }

    public OfferDto(String description, Engine engine, String imageUrl, int milleage, int price, Transmission transmission, int year, LocalDate created, LocalDate modified) {
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.milleage = milleage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.created = created;
        this.modified = modified;
    }

}
