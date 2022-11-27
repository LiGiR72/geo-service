package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceTest {
    static GeoServiceImpl service;
    String russianIp = "172.0.0.0";
    String usaIp = "96.0.0.0";
    String unknownLocation = "0.0.0.0";

    @BeforeAll
    static void setService() {
        service = new GeoServiceImpl();
    }

    @Test
    void byIpTest() {
        Assertions.assertEquals(service.byIp(russianIp).getCountry(), Country.RUSSIA);
        Assertions.assertEquals(service.byIp(usaIp).getCountry(), Country.USA);
    }

    @Test
    void wrongLocationByIpTest() {
        Assertions.assertNull(service.byIp(unknownLocation));
    }

    @Test
    void byLocationExceptionThrowTest() {
        Assertions.assertThrows(RuntimeException.class, () -> service.byCoordinates(0, 0));
    }
}
