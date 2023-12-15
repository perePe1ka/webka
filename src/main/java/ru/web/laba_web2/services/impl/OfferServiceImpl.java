package ru.web.laba_web2.services.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.controllers.exceptions.ModelNotFoundException;
import ru.web.laba_web2.controllers.exceptions.OfferNotFoundException;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.models.Offer;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.ModelRepository;
import ru.web.laba_web2.repositories.OfferRepository;
import ru.web.laba_web2.repositories.UserRepository;
import ru.web.laba_web2.services.ModelService;
import ru.web.laba_web2.services.OfferService;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.ModelDto;
import ru.web.laba_web2.services.dtos.OfferDto;
import ru.web.laba_web2.services.dtos.UserDto;
import ru.web.laba_web2.utils.ValidationUtil;
import ru.web.laba_web2.viewModel.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class OfferServiceImpl implements OfferService<String> {
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private UserRepository userRepository;
    private ModelRepository modelRepository;
    private OfferRepository offerRepository;
    private ModelService modelService;

    private UserService userService;

    private StatisticsService statisticsService;

    @Autowired
    public void setStatisticsService(StatisticsService statisticsService){
        this.statisticsService = statisticsService;
    }

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void register(AddOfferViewModel newOffer) {
        if (!this.validationUtil.isValid(newOffer)) {

            this.validationUtil
                    .violations(newOffer)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            throw new IllegalArgumentException("Что то пошло не так");
        }

        Offer offer = this.modelMapper.map(newOffer, Offer.class);
        offer.setSeller(userService.findByUsername(newOffer.getSeller()));
        offer.setModel(modelService.findByName(newOffer.getModel()));

        this.offerRepository.saveAndFlush(offer);
    }

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void deleteByOfferDescription(String description) {
        offerRepository.deleteByDescription(description);
    }

    @Override
    public void transfer(OfferDto offerDto, ModelDto modelDto, UserDto userDto) {
        Offer offer = offerRepository.findByUuid(offerDto.getUuid()).get();
        Model model = modelRepository.findByUuid(modelDto.getUuid()).get();
        User user = userRepository.findByUuid(userDto.getUuid()).get();
        offer.setModel(model);
        offer.setSeller(user);
        offerRepository.save(offer);
    }

    @Override
    public Optional<EditOffer> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(offerRepository.findByUuid(uuid), EditOffer.class));
    }

    @Override
    @Cacheable("offers")
    public DetailOffer getAll(String offerDescription) {
        Offer offer = offerRepository.findByDescription(offerDescription)
                .orElseThrow(() -> new ModelNotFoundException("Offer with name " + offerDescription + " not found"));
        return modelMapper.map(offer, DetailOffer.class);
    }

    @Override
    @Cacheable("offers")
    public List<ShowOffer> allOffers() {
        return offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, ShowOffer.class)).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void editOffer(EditOffer editOffer) {
        if (!this.validationUtil.isValid(editOffer)) {
            this.validationUtil
                    .violations(editOffer)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Offer offer = modelMapper.map(editOffer, Offer.class);
                offer.setModel(modelRepository.findByName(editOffer.getModel()).orElse(null));
                offer.setSeller(userRepository.findByUsername(editOffer.getSeller()).orElse(null));
                this.offerRepository.saveAndFlush(offer);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так");
            }
        }
    }


}