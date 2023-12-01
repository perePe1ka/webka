package ru.web.laba_web2.viewModel;

import ru.web.laba_web2.constants.Category;

import java.time.LocalDate;

public class DetailModel {
    private String uuid;
    private String name; //имя модели

    private Category category;

    private String imageUrl; //ссылка на юрл

    private int startYear;

    private int endYear;
    private String brand;

    private LocalDate created;

    private LocalDate modified;
}
