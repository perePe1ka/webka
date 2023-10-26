package ru.web.laba_web2.dtos;

import lombok.Data;
import ru.web.laba_web2.constants.Engine;
import ru.web.laba_web2.constants.Transmission;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class OfferDto {
    private UUID uuid;
    private String description;
    private Engine engine;

    private String imageUrl;

    private int milleage;

    private int price;
    private Transmission transmission;


    private int year;

    private LocalDate created;

    private LocalDate modified;

    private ModelDto modelDto;

    private UserDto seller;


    public OfferDto() {

    }

    public OfferDto(String description, Engine engine, String imageUrl, int milleage, int price, Transmission transmission, int year, LocalDate created, LocalDate modified, ModelDto modelDto, UserDto seller) {
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.milleage = milleage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.created = created;
        this.modified = modified;
        this.modelDto = modelDto;
        this.seller = seller;
    }

}
