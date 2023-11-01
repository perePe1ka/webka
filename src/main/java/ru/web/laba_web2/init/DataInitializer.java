package ru.web.laba_web2.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.web.laba_web2.constants.Category;
import ru.web.laba_web2.constants.Engine;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.constants.Transmission;
import ru.web.laba_web2.services.OfferService;
import ru.web.laba_web2.services.dtos.*;

import java.io.IOException;


@Component
public class DataInitializer implements CommandLineRunner {

    private final OfferService offerService;
    @Autowired
    public DataInitializer(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() throws IOException {

        BrandDto brandDto1 = new BrandDto(
                "KiaBrand"
        );

        BrandDto brandDto2 = new BrandDto(
                "HyndaiBrand"
        );

        ModelDto modelDto1 = new ModelDto(
                "KiaModel",
                Category.TRUCK,
                "https://vlad1.com/image.jpg",
                2000,
                2005,
                brandDto1
        );

        ModelDto modelDto2 = new ModelDto(
                "HyndaiModel",
                Category.BUS,
                "https://vlad2.com/image.jpg",
                2005,
                2010,
                brandDto2
        );

        RolesDto rolesDto1 = new RolesDto(
                Role.USER
        );

        RolesDto rolesDto2 = new RolesDto(
                Role.ADMIN
        );

        UserDto userDto1 = new UserDto(
                "Vladuss_1337",
                "123456",
                "Влад",
                "Михалков",
                true,
                rolesDto1,
                "https://vlad5.com/image.jpg"
        );

        UserDto userDto2 = new UserDto(
                "Vladuss",
                "123456",
                "Владислав",
                "Усков",
                true,
                rolesDto2,
                "https://vlad6.com/image.jpg"
        );

        OfferDto offerDto1 = new OfferDto(
                "some news",
                Engine.GASOLINE,
                "https://vlad3.com/image.jpg",
                200000,
                2000,
                Transmission.MANUAL,
                2023,
                modelDto1,
                userDto1
                );

        OfferDto offerDto2 = new OfferDto(
                "some news",
                Engine.DIESEL,
                "https://vlad4.com/image.jpg",
                10000,
                3000,
                Transmission.AUTOMATIC,
                2022,
                modelDto2,
                userDto2
                );


        offerDto1 = offerService.register(offerDto1);
        offerDto2 = offerService.register(offerDto2);


        System.out.println(offerService.getAllAdmins());
    }
}


