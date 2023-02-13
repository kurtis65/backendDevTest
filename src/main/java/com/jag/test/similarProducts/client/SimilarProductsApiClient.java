package com.jag.test.similarProducts.client;

import com.jag.test.similarProducts.exception.FeignExceptionHandler;
import org.openapitools.model.ProductDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "SimilarProductsApiClient",
        url = "${similar.product.url}",
        fallback = FeignExceptionHandler.class)
public interface SimilarProductsApiClient {

    @GetMapping(value = "/product/{productId}")
    ProductDetail getProductId(@PathVariable("productId") String productId);

    @GetMapping(value = "/product/{productId}/similarids")
    Set<String> getProductSimilarIds(@PathVariable("productId") String productId);

}
