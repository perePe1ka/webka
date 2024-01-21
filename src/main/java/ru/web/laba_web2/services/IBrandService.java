package ru.web.laba_web2.services;

import ru.web.laba_web2.viewModel.AddBrandViewModel;
import ru.web.laba_web2.viewModel.DetailBrandViewModel;
import ru.web.laba_web2.viewModel.EditBrandViewModel;
import ru.web.laba_web2.viewModel.ShowBrandViewModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBrandService<String> {
    void register(AddBrandViewModel addBrandViewModel);

    void deleteByName(String brandName);

    Optional<EditBrandViewModel> findByUuid(UUID uuid);

    List<ShowBrandViewModel> allBrands();

    DetailBrandViewModel getAll(String brandName);

    void editBrand(EditBrandViewModel editBrand);
}
