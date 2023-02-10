package com.jag.test.similarProducts.controller;

import com.jag.test.similarProducts.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openapitools.model.ProductDetail;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductApiControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductApiController productApiController;

    @Test
    void shouldGetProductSimilar_returnEntity() {
        ProductDetail productDetail = new ProductDetail()
                .id("1")
                .availability(Boolean.TRUE)
                .name("ID1")
                .price(BigDecimal.valueOf(100));

        Set<ProductDetail> mockResponse = Set.of(productDetail);
        when(productService.getProductSimilar(anyString())).thenReturn(mockResponse);

        ResponseEntity<Set<ProductDetail>> response = productApiController.getProductSimilar("1");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().stream().anyMatch(hasAnyFieldNull()));
        Optional<ProductDetail> optionalProductDetail = response.getBody().stream().findAny();
        assertEquals(optionalProductDetail.get(), productDetail);
    }

    @Test
    void shouldGetProductSimilar_returnNotFound() {
        when(productService.getProductSimilar(anyString())).thenReturn(null);

        ResponseEntity<Set<ProductDetail>> response = productApiController.getProductSimilar("1");

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    static Predicate<ProductDetail> hasAnyFieldNull() {
        return p -> p.getAvailability() == null ||
                p.getId() == null ||
                p.getName() == null ||
                p.getPrice() == null;
    }
}