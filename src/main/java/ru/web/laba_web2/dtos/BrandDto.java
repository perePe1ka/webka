package ru.web.laba_web2.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Data
public class BrandDto {
    private Long uuid;
    private String name; //наименование бренда

    private LocalDate created; //дата и время
    private LocalDate modified; //дата и время

    public BrandDto() {

    }

    public BrandDto(String name, LocalDate created, LocalDate modified) {
        this.name = name;
        this.created = created;
        this.modified = modified;
    }

}
