package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    @Mock
    GeoServiceImpl geoService;
    @Mock
    LocalizationServiceImpl service;

    MessageSenderImpl sender;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(LocalizationServiceImpl.class);
        geoService = Mockito.mock(GeoServiceImpl.class);
        sender = new MessageSenderImpl(geoService, service);

    }


    @Test
    void sendNull() {
        Mockito.when(service.locale(Country.USA)).thenReturn("Welcome");
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "");
        Assertions.assertEquals(sender.send(headers), "Welcome");
    }

    @Test
    void send() {
        Mockito.when(geoService.byIp("172.0.0.0")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp("96.0.0.0")).thenReturn(new Location("New York", Country.USA, null, 0));
        Mockito.when(service.locale(Country.USA)).thenReturn("Welcome");
        Mockito.when(service.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.0.0");
        Assertions.assertEquals(sender.send(headers), "Добро пожаловать");
        headers.clear();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.0.0.0");
        Assertions.assertEquals(sender.send(headers), "Welcome");
    }

}
