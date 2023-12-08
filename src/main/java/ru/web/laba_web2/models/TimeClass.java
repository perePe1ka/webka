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
//    @Column(name = "modified", columnDefinition = "DATE")
//    @LastModifiedDate
//    public LocalDate getModified() {
//        return modified;
//    }
//
//    public void setModified(LocalDate modified) {
//        this.modified = modified;
//    }
//    @Column(name = "created", columnDefinition = "DATE")
//    @CreatedDate
//    public LocalDate getCreated() {
//        return created;
//    }
//
//    public void setCreated(LocalDate created) {
//        this.created = created;
//    }
//
//    public TimeClass(LocalDate modified, LocalDate created) {
//        this.modified = modified;
//        this.created = created;
//    }
}
