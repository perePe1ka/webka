package ru.web.laba_web2.services.impl;


import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.models.Brand;
import ru.web.laba_web2.repositories.IBrandRepository;
import ru.web.laba_web2.services.IBrandService;
import ru.web.laba_web2.utils.ValidationUtil;
import ru.web.laba_web2.viewModel.AddBrandViewModel;
import ru.web.laba_web2.viewModel.DetailBrand;
import ru.web.laba_web2.viewModel.EditBrand;
import ru.web.laba_web2.viewModel.ShowBrand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class BrandServiceImpl implements IBrandService<String> {

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;
    private IBrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setBrandRepository(IBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public void register(AddBrandViewModel addBrandViewModel) {

        if (!this.validationUtil.isValid(addBrandViewModel)) {
            this.validationUtil
                    .violations(addBrandViewModel)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                this.brandRepository
                        .saveAndFlush(this.modelMapper.map(addBrandViewModel, Brand.class));
            } catch (Exception e) {
                System.out.println("Что то пошло не так");
            }
        }
    }


    @Override
    @CacheEvict(cacheNames = {"brands", "models", "offers"}, allEntries = true)
    public void deleteByName(String brandName) {
        brandRepository.deleteByName(brandName);
    }


    @Override
    public Optional<EditBrand> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findByUuid(uuid), EditBrand.class));
    }

    @Override
    @Cacheable("brands")
    public List<ShowBrand> allBrands() {
        return brandRepository.findAll().stream().map(brand -> modelMapper.map(brand, ShowBrand.class))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable("brands")
    public DetailBrand getAll(String brandName) {
        Optional<Brand> brand = brandRepository.findByName(brandName);
        return modelMapper.map(brand, DetailBrand.class);
    }

    @Override
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public void editBrand(EditBrand editBrand) {
        if (!this.validationUtil.isValid(editBrand)) {
            this.validationUtil
                    .violations(editBrand)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Brand brand = modelMapper.map(editBrand, Brand.class);
                brandRepository.saveAndFlush(brand);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так");
            }
        }
    }
}
