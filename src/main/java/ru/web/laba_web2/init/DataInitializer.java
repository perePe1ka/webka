package ru.web.laba_web2.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.web.laba_web2.dtos.*;
import ru.web.laba_web2.services.ModelService;

import java.io.IOException;
import java.sql.Date;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ModelService modelService;

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() throws IOException {
        //Добавление в БД записей


        BrandDto brandDto1 = new BrandDto("Kia", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        BrandDto brandDto2 = new BrandDto("Hyndai", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));

        ModelDto modelDto1 = new ModelDto("Kia", Category.TRUCK, "https://vlad1.com/image.jpg", 2000, 2005, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), brandDto1);
        ModelDto modelDto2 = new ModelDto("Hyndai", Category.BUS, "https://vlad2.com/image.jpg", 2005, 2010, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), brandDto2);

        RolesDto rolesDto1 = new RolesDto(Role.USER);
        RolesDto rolesDto2 = new RolesDto(Role.ADMIN);

        UserDto userDto1 = new UserDto("Vladuss_1337", "123456", "Влад", "Михалков", true, rolesDto1, "https://vlad5.com/image.jpg", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        UserDto userDto2 = new UserDto("Vladuss", "123456", "Владислав", "Усков", true, rolesDto2, "https://vlad6.com/image.jpg", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));

        OfferDto offerDto1 = new OfferDto("some news", Engine.GASOLINE, "https://vlad3.com/image.jpg", 200000, 2000, Transmission.MANUAL, 2023, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), modelDto1, userDto1);
        OfferDto offerDto2 = new OfferDto("some news", Engine.DIESEL, "https://vlad4.com/image.jpg", 10000, 3000, Transmission.AUTOMATIC, 2022, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), modelDto2, userDto2);


        //brand -
        //roles -

        //model+
        //user+
        //offer+

        modelDto1 = modelService.register(modelDto1);
        modelDto2 = modelService.register(modelDto2);

        userDto1 = userService.register(userDto1);
        userDto2 = userService.register(userDto2);




//        s1 = studentService.register(s1);
//        s2 = studentService.register(s2);
//
//        printAllStudentsByGroupName("UVP-212");
//
//        studentService.transfer(s1, s2.getGroup());
//
//        printAllStudentsByGroupName("UVP-212");
    }
}
