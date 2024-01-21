package ru.web.laba_web2.models;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class TimeClass extends BaseEntity {

    @Column(name = "modified", columnDefinition = "DATE")
    @LastModifiedDate
    public LocalDateTime modified;

    @Column(name = "created", columnDefinition = "DATE")
    @CreatedDate
    public LocalDateTime created;
}
