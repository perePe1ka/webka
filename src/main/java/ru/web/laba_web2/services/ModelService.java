package ru.web.laba_web2.services;

import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.services.dtos.ModelDto;
import ru.web.laba_web2.viewModel.AddModelViewModel;
import ru.web.laba_web2.viewModel.DetailModel;
import ru.web.laba_web2.viewModel.EditModel;
import ru.web.laba_web2.viewModel.ShowModel;

import java.util.List;
import java.util.Optional;

public interface ModelService<String> {
    void register(AddModelViewModel newModel);

    void deleteByModelName(java.lang.String modelName);

    void transfer(ModelDto modelDto, BrandDto brandDto);

    Optional<EditModel> findByUuid(String uuid);

    DetailModel getAll(String name);

    List<ShowModel> allModels();

    void editModel(EditModel editModel);

    Model findByName(String name);
}
