package ru.web.laba_web2.constants;

public enum Engine{
        GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid");

    private String name;
         Engine(String name) {
             this.name = name;
         }
    }