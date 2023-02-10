package com.jag.test.similarProducts.client;

import org.junit.jupiter.api.Test;
import org.openapitools.model.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class SimilarProductsApiClientTest {

    @Autowired
    private SimilarProductsApiClient similarProductsApiClient;

    @Test
    void getProductId() {
        ProductDetail response = similarProductsApiClient.getProductId("1");

        assertNotNull(response);
        assertEquals(response.getId(), "1");
        assertEquals(response.getName(), "Shirt");
        assertEquals(response.getPrice(), BigDecimal.valueOf(9.99));
        assertTrue(response.getAvailability());
    }

    @Test
    void getProductSimilarIds() {
        Set<String> response = similarProductsApiClient.getProductSimilarIds("1");

        assertNotNull(response);
        Set<String> result = Set.of("2", "3", "4");
        assertEquals(response, result);
    }
}