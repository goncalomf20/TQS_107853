package com.homework1.homework1;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class APITest {

    Logger logger = mock(Logger.class);

    @Test
    void testCall() throws IOException {
        APICaller apiCaller = new APICaller();
        apiCaller.call();

        Map<String, Object> currencies = apiCaller.getCurrencies();

        assertNotNull(currencies);
        assertFalse(currencies.isEmpty());

    }

}
