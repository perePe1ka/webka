package ru.web.laba_web2.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@MappedSuperclass
public abstract class BaseEntity {

    protected UUID uuid;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
