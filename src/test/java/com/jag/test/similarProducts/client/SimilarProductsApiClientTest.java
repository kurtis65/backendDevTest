package com.jag.test.similarProducts.client;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.openapitools.model.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest()
class SimilarProductsApiClientTest {

    @Mock
    private SimilarProductsApiClient similarProductsApiClient;

    @Test
    void getProductId() {
        ProductDetail productDetail = new ProductDetail()
                .id("1")
                .availability(Boolean.TRUE)
                .name("Shirt")
                .price(BigDecimal.valueOf(9.99));

        when(similarProductsApiClient.getProductId(anyString())).thenReturn(productDetail);

        ProductDetail response = similarProductsApiClient.getProductId("1");

        assertNotNull(response);
        assertEquals(response.getId(), "1");
        assertEquals(response.getName(), "Shirt");
        assertEquals(response.getPrice(), BigDecimal.valueOf(9.99));
        assertTrue(response.getAvailability());
    }

    @Test
    void getProductSimilarIds() {
        Set<String> result = Set.of("1", "2", "3");
        when(similarProductsApiClient.getProductSimilarIds(anyString())).thenReturn(result);

        Set<String> response = similarProductsApiClient.getProductSimilarIds("1");

        assertNotNull(response);
        assertEquals(response, result);
    }
}