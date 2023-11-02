package ru.web.laba_web2.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@MappedSuperclass
public abstract class BaseEntity {

    protected String uuid;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
