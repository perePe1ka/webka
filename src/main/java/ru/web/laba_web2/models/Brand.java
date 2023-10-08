package ru.web.laba_web2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand extends BaseEntity{
    private String name; //наименование бренда
    private java.sql.Date created; //дата и время
    private java.sql.Date modified; //дата и время
}
