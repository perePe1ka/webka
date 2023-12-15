package ru.web.laba_web2.services.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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
import ru.web.laba_web2.viewModel.EditModel;
import ru.web.laba_web2.viewModel.ShowModel;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableCaching
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
    @CacheEvict(cacheNames = "models", allEntries = true)
    public void register(AddModelViewModel newModel) {
        if (!this.validationUtil.isValid(newModel)) {
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
    @CacheEvict(cacheNames = {"models","offers"}, allEntries = true)
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
    public Optional<EditModel> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findByUuid(uuid), EditModel.class));
    }

    @Override
    @Cacheable("models")
    public DetailModel getAll(String modelName) {
        Model model = modelRepository.findByName(modelName)
                .orElseThrow(() -> new ModelNotFoundException("Model with name " + modelName + " not found"));
        return modelMapper.map(model, DetailModel.class);
    }

    @Override
    @Cacheable("models")
    public List<ShowModel> allModels() {
        return modelRepository.findAll().stream().map(model -> modelMapper.map(model, ShowModel.class))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "models", allEntries = true)
    public void editModel(EditModel editModel) {
        if (!this.validationUtil.isValid(editModel)) {
            this.validationUtil
                    .violations(editModel)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Model model = modelMapper.map(editModel, Model.class);
                model.setBrand(brandRepository.findByName(editModel.getBrand()).orElse(null));
                this.modelRepository.saveAndFlush(model);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так");
            }
        }
    }

    @Override
    public Model findByName(String name) {
        return this.modelRepository.findModelByName(name);
    }

}
