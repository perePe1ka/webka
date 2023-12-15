package ru.web.laba_web2.viewModel;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DetailBrand {
    private String uuid;
    private String name;
    private String  modified;
    private String  created;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return name;
    }
}
