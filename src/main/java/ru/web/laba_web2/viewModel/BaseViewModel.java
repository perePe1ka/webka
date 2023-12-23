package ru.web.laba_web2.viewModel;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseViewModel {
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
