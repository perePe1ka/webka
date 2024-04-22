package ru.web.laba_web2.services.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.models.Offer;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.IModelRepository;
import ru.web.laba_web2.repositories.IOfferRepository;
import ru.web.laba_web2.repositories.IUserRepository;
import ru.web.laba_web2.services.IModelService;
import ru.web.laba_web2.services.IOfferService;
import ru.web.laba_web2.services.IUserService;
import ru.web.laba_web2.utils.ValidationUtil;
import ru.web.laba_web2.viewModel.AddOfferViewModel;
import ru.web.laba_web2.viewModel.DetailOfferViewModel;
import ru.web.laba_web2.viewModel.EditOfferViewModel;
import ru.web.laba_web2.viewModel.ShowOfferViewModel;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//@EnableCaching
public class OfferServiceImpl implements IOfferService<String> {
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private IUserRepository userRepository;
    private IModelRepository modelRepository;
    private IOfferRepository offerRepository;
    private IModelService modelService;

    private IUserService userService;

    private StatisticsService statisticsService;

    @Autowired
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setModelService(IModelService modelService) {
        this.modelService = modelService;
    }

    @Autowired
    public void setModelRepository(IModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setOfferRepository(IOfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
//    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void register(AddOfferViewModel newOffer) {
        if (!this.validationUtil.isValid(newOffer)) {
            this.validationUtil
                    .violations(newOffer)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            throw new IllegalArgumentException("Что-то пошло не так");
        }

        User currentUser = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        Offer offer = this.modelMapper.map(newOffer, Offer.class);
        offer.setSeller(currentUser);
        offer.setModel(modelService.findByName(newOffer.getModel()));

        this.offerRepository.saveAndFlush(offer);
    }



    @Override
    public Optional<EditOfferViewModel> findByUuid(UUID uuid) {
        Optional<Offer> offer = offerRepository.findByUuid(uuid);
        return offer.map(o -> modelMapper.map(o, EditOfferViewModel.class));
    }

    @Override
//    @Cacheable("offers")
    public DetailOfferViewModel getAll(String offerDescription) {
        Optional<Offer> offer = offerRepository.findByDescription(offerDescription);
        return modelMapper.map(offer, DetailOfferViewModel.class);
    }

    @Override
//    @Cacheable("offers")
    public List<ShowOfferViewModel> allOffers() {
        return offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, ShowOfferViewModel.class)).collect(Collectors.toList());
    }

    @Override
//    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void editOffer(EditOfferViewModel editOffer) {
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
                offer.setSeller(userRepository.findByUsername(editOffer.getSeller().getUsername()).orElse(null));
                this.offerRepository.saveAndFlush(offer);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так");
            }
        }
    }

    @Override
//    @CacheEvict(cacheNames = "offers", allEntries = true)
    public void deleteByOfferDescription(String description) {

            offerRepository.deleteByDescription(description);


    }
}