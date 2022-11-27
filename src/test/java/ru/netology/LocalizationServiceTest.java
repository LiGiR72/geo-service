package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceTest {
    static LocalizationServiceImpl service;

    @BeforeAll
    static void setup() {
        service = new LocalizationServiceImpl();
    }


    @Test
    void localeTest() {
        Assertions.assertEquals(service.locale(Country.RUSSIA), "Добро пожаловать");
    }

    @Test
    void localeNotRussiaTest() {
        Assertions.assertEquals(service.locale(Country.USA), "Welcome");
    }
}
