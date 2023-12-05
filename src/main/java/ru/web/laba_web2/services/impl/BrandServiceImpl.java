package ru.web.laba_web2.services.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.controllers.exceptions.BrandNotFoundException;
import ru.web.laba_web2.models.Brand;
import ru.web.laba_web2.repositories.BrandRepository;
import ru.web.laba_web2.services.BrandService;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.utils.ValidationUtil;
import ru.web.laba_web2.viewModel.AddBrandViewModel;
import ru.web.laba_web2.viewModel.DetailBrand;
import ru.web.laba_web2.viewModel.ShowBrand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService<String> {

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;
    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
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
    public void deleteByName(String brandName) {
        brandRepository.deleteByName(brandName);
    }


    @Override
    public Optional<BrandDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findByUuid(uuid), BrandDto.class));
    }

    @Override
    public List<ShowBrand> allBrands() {
        return brandRepository.findAll().stream().map(brand -> modelMapper.map(brand, ShowBrand.class))
                .collect(Collectors.toList());
    }

    @Override
    public DetailBrand getAll(String brandName) {
        Brand brand = brandRepository.findByName(brandName)
                .orElseThrow(() -> new BrandNotFoundException("Brand with name " + brandName + " not found"));
        return modelMapper.map(brand, DetailBrand.class);
    }

    @Override
    public void editBrand(BrandDto brandDto) {
        if (!this.validationUtil.isValid(brandDto)) {
            this.validationUtil
                    .violations(brandDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Brand brand = modelMapper.map(brandDto, Brand.class);
                brandRepository.saveAndFlush(brand);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так");
            }
        }
    }


    @Override
    public Brand findByName(String name) {
        return this.brandRepository.findBrandByName(name);
    }
}
