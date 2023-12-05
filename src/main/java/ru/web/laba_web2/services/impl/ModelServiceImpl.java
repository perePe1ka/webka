package ru.web.laba_web2.services.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.controllers.exceptions.ModelNotFoundException;
import ru.web.laba_web2.models.Brand;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.repositories.BrandRepository;
import ru.web.laba_web2.repositories.ModelRepository;
import ru.web.laba_web2.services.BrandService;
import ru.web.laba_web2.services.ModelService;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.services.dtos.ModelDto;
import ru.web.laba_web2.utils.ValidationUtil;
import ru.web.laba_web2.viewModel.AddModelViewModel;
import ru.web.laba_web2.viewModel.DetailModel;
import ru.web.laba_web2.viewModel.ShowModel;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService<String> {
    private final ModelMapper modelMapper;
    private BrandRepository brandRepository;
    private ModelRepository modelRepository;
    private final ValidationUtil validationUtil;
    private BrandService brandService;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public void register(AddModelViewModel newModel) {
        if (!this.validationUtil.isValid(newModel))
        {
            this.validationUtil
                    .violations(newModel)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Model model = modelMapper.map(newModel, Model.class);
                model.setBrand(brandRepository.findByName(newModel.getBrand()).orElse(null));
                this.modelRepository.saveAndFlush(model);
            } catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }


    @Override
    public void deleteByModelName(String modelName) {
        modelRepository.deleteByName(modelName);
    }

    @Override
    public void transfer(ModelDto modelDto, BrandDto brandDto) {
        Model model = modelRepository.findByUuid(modelDto.getUuid()).get();
        Brand brand = brandRepository.findByUuid(brandDto.getUuid()).get();
        model.setBrand(brand);
        modelRepository.saveAndFlush(model);
    }

    @Override
    public Optional<ModelDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findByUuid(uuid), ModelDto.class));
    }

    @Override
    public DetailModel getAll(String modelName) {
        Model model = modelRepository.findByName(modelName)
                .orElseThrow(() -> new ModelNotFoundException("Model with name " + modelName + " not found"));
        return modelMapper.map(model, DetailModel.class);
    }

    @Override
    public List<ShowModel> allModels() {
        return modelRepository.findAll().stream().map(model -> modelMapper.map(model, ShowModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void editModel(ModelDto modelDto) {
        if (!this.validationUtil.isValid(modelDto)) {
            this.validationUtil
                    .violations(modelDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Model model = modelMapper.map(modelDto, Model.class);
                modelRepository.saveAndFlush(model);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так");
            }
        }
    }


    @Override
    public List<ModelDto> getModelsSortedByYear() {
        List<Model> models = modelRepository.findAll();
        models.sort(Comparator.comparingInt(Model::getStartYear));

        return models.stream()
                .map(model -> modelMapper.map(model, ModelDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Model findByName(String name) {
        return this.modelRepository.findModelByName(name);
    }

}
