package com.jag.test.similarProducts.service.impl;

import com.jag.test.similarProducts.client.SimilarProductsApiClient;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openapitools.model.ProductDetail;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private SimilarProductsApiClient similarProductsApiClient;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void shouldGetProductSimilar_returnEntity() {
        ProductDetail productDetail = new ProductDetail()
                .id("1")
                .availability(Boolean.TRUE)
                .name("ID1")
                .price(BigDecimal.valueOf(100));

        Set<String> result = Set.of("1", "2", "3");
        when(similarProductsApiClient.getProductSimilarIds(anyString())).thenReturn(result);
        when(similarProductsApiClient.getProductId(anyString())).thenReturn(productDetail);

        Set<ProductDetail> response = productService.getProductSimilar("1");

        Optional<ProductDetail> optionalProductDetail = response.stream().findAny();
        assertTrue(optionalProductDetail.isPresent());
        assertEquals(optionalProductDetail.get(), productDetail);
    }

    @Test
    void shouldGetProductSimilar_returnNotFound() {
        Set<String> result = Set.of("1", "2", "3");
        when(similarProductsApiClient.getProductSimilarIds(anyString())).thenReturn(result);
        when(similarProductsApiClient.getProductId(anyString())).thenThrow(FeignException.NotFound.class);

        try {
            productService.getProductSimilar("1");
        } catch (Exception e) {
            assertEquals(FeignException.NotFound.class, e.getClass());
        }

    }
}
