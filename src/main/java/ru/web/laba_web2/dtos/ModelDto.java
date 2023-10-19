package ru.web.laba_web2.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ModelDto {
    private Long uuid;
    private String name; //имя модели

    private Category category;

    private String imageUrl; //ссылка на юрл

    private int startYear;

    private int endYear;

    private LocalDate created;

    private LocalDate modified;
    private BrandDto brand;

    private OfferDto offer;

    public ModelDto() {

    }

    public ModelDto(String name, Category category, String imageUrl, int startYear, int endYear, LocalDate created, LocalDate modified, BrandDto brand, OfferDto offer) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.created = created;
        this.modified = modified;
        this.brand = brand;
        this.offer = offer;
    }
}
