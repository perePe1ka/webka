package ru.web.laba_web2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "model")
public class Model extends BaseEntity{
    private String name; //имя модели
    private enum category{
        CAR, BUS, TRUCK, MOTORCYCLE
    }

    private String imageUrl; //ссылка на юрл

    private int startYear;

    private int endYear;

    private java.sql.Date created;

    private java.sql.Date modified;

    private Brand brand;
}
