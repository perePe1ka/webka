package ru.web.laba_web2.models;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
@EntityListeners(AuditingEntityListener.class)
public class TimeClass extends BaseEntity{
    public LocalDate modified;

    public LocalDate created;
    @Column(name = "modified", columnDefinition = "DATE")
    @LastModifiedDate
    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }
    @Column(name = "created", columnDefinition = "DATE")
    @CreatedDate
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
