package ru.web.laba_web2.services.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.models.Brand;
import ru.web.laba_web2.repositories.BrandRepository;
import ru.web.laba_web2.services.BrandService;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.utils.ValidationUtil;

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
    public void register(BrandDto brandDto) {

        if (!this.validationUtil.isValid(brandDto)) {
            this.validationUtil
                    .violations(brandDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                this.brandRepository
                        .saveAndFlush(this.modelMapper.map(brandDto, Brand.class));
            } catch (Exception e) {
                System.out.println("Что то пошло не так");
            }
        }
    }

    public void addBrand(String brandName) {
        BrandDto brandDto = new BrandDto();
        brandDto.setName(brandName);

        if (!this.validationUtil.isValid(brandDto)) {

            this.validationUtil
                    .violations(brandDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            this.brandRepository
                    .saveAndFlush(this.modelMapper
                            .map(brandDto, Brand.class));

        }
    }


    @Override
    public void deleteByUuid(String uuid) {
        brandRepository.deleteByUuid(uuid);
    }


    @Override
    public Optional<BrandDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findByUuid(uuid), BrandDto.class));
    }

    @Override
    public List<BrandDto> getAll() {
        return brandRepository.findAll().stream().map((brand) -> modelMapper.map(brand, BrandDto.class)).collect(Collectors.toList());
    }

    @Override
    public void editBrand(BrandDto brandDto) {
        Brand brand = modelMapper.map(brandDto, Brand.class);
        brandRepository.saveAndFlush(brand);
    }

    @Override
    public Brand findByName(String name) {
        return this.brandRepository.findByName(name);
    }


}
