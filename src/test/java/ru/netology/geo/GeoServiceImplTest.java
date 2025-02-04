package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    private final GeoService geoService = new GeoServiceImpl();

    @Test
    void testLHIp() {
        String localHostIp = "127.0.0.1";

        Location location = geoService.byIp(localHostIp);

        assertNotNull(location);
        assertNull(location.getCountry());
    }

    @Test
    void testValidMoscowIp() {
        String russianIp = "172.0.32.11";

        Location location = geoService.byIp(russianIp);

        assertNotNull(location);
        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Lenina", location.getStreet());
        assertEquals(15, location.getBuiling());
    }

    @Test
    void testValidRussianIp() {
        String russianIp = "172.168.2.129";

        Location location = geoService.byIp(russianIp);

        assertNotNull(location);
        assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    void testValidNewYorkIp() {
        String usaIp = "96.44.183.149";

        Location location = geoService.byIp(usaIp);

        assertNotNull(location);
        assertEquals("New York", location.getCity());
        assertEquals(Country.USA, location.getCountry());
        assertEquals(" 10th Avenue", location.getStreet());
        assertEquals(32, location.getBuiling());
    }

    @Test
    void testValidUSAIp() {
        String usaIp = "96.74.12.12";

        Location location = geoService.byIp(usaIp);

        assertNotNull(location);
        assertEquals(Country.USA, location.getCountry());
    }

    @Test
    void testInvalidIp() {
        String invalidIp = "8.8.8.8";

        Location location = geoService.byIp(invalidIp);

        assertNull(location);
    }
}