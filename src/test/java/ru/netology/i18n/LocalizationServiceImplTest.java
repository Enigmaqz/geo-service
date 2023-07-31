package ru.netology.i18n;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;

import javax.management.ConstructorParameters;

public class LocalizationServiceImplTest {

    @ParameterizedTest
    @EnumSource(Country.class)
    void localizationTest(Country country) {

        LocalizationService localizationService = new LocalizationServiceImpl();
        String expected = null;

        if (country == Country.RUSSIA) {
            expected = "Добро пожаловать";
        } else {
            expected = "Welcome";
        }
        String actual = localizationService.locale(country);
        Assertions.assertEquals(expected, actual);
    }
}
