package ru.web.laba_web2.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.web.laba_web2.dtos.*;
import ru.web.laba_web2.services.ModelService;
import ru.web.laba_web2.services.UserService;
import java.io.IOException;
import java.time.LocalDate;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ModelService modelService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() throws IOException {

        OfferDto offerDto1 = new OfferDto(
                "some news",
                Engine.GASOLINE,
                "https://vlad3.com/image.jpg",
                200000,
                2000,
                Transmission.MANUAL,
                2023,
                LocalDate.now(),
                LocalDate.now()
        );

        OfferDto offerDto2 = new OfferDto(
                "some news",
                Engine.DIESEL,
                "https://vlad4.com/image.jpg",
                10000,
                3000,
                Transmission.AUTOMATIC,
                2022,
                LocalDate.now(),
                LocalDate.now()
        );

        BrandDto brandDto1 = new BrandDto(
                "Kia",
                LocalDate.now(),
                LocalDate.now()
        );

        BrandDto brandDto2 = new BrandDto(
                "Hyndai",
                LocalDate.now(),
                LocalDate.now()
        );

        ModelDto modelDto1 = new ModelDto(
                "Kia",
                Category.TRUCK,
                "https://vlad1.com/image.jpg",
                2000,
                2005,
                LocalDate.now(),
                LocalDate.now(),
                brandDto1,
                offerDto1
        );

        ModelDto modelDto2 = new ModelDto(
                "Hyndai",
                Category.BUS,
                "https://vlad2.com/image.jpg",
                2005,
                2010,
                LocalDate.now(),
                LocalDate.now(),
                brandDto2,
                offerDto2
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
                "https://vlad5.com/image.jpg",
                LocalDate.now(),
                LocalDate.now(),
                offerDto1
        );

        UserDto userDto2 = new UserDto(
                "Vladuss",
                "123456",
                "Владислав",
                "Усков",
                true,
                rolesDto2,
                "https://vlad6.com/image.jpg",
                LocalDate.now(),
                LocalDate.now(),
                offerDto2
        );

        modelDto1 = modelService.register(modelDto1);
        modelDto2 = modelService.register(modelDto2);

        userDto1 = userService.register(userDto1);
        userDto2 = userService.register(userDto2);
    }
}
