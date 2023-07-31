package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @Test
    void testSendRussianMessageWhenRussianLocation() {
        GeoService geoServiceMock = Mockito.mock(GeoService.class);
        Location location = new Location("Moscow", Country.RUSSIA, "Kremlin", 1);
        Mockito.when(geoServiceMock.byIp(Mockito.any())).thenReturn(location);

        LocalizationService localizationService = new LocalizationServiceImpl();

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "8.8.8.8");

        String actual = messageSender.send(headers);

        String expected = "Добро пожаловать";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSendEnglishMessageWhenUSALocation() {
        GeoService geoServiceMock = Mockito.mock(GeoService.class);
        Location location = new Location("Boston", Country.USA, "Trump", 1);
        Mockito.when(geoServiceMock.byIp(Mockito.any())).thenReturn(location);

        LocalizationService localizationService = new LocalizationServiceImpl();

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "8.8.8.8");

        String actual = messageSender.send(headers);

        String expected = "Welcome";

        Assertions.assertEquals(expected, actual);
    }
}
