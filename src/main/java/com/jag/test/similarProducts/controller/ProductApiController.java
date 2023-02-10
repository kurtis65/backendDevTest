package com.jag.test.similarProducts.controller;

import com.jag.test.similarProducts.service.ProductService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.ProductApi;
import org.openapitools.model.ProductDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductApiController implements ProductApi {

    private final ProductService productService;

    @GetMapping(value = "/product/{productId}/similar", produces = { MediaType.APPLICATION_JSON_VALUE } )
    public ResponseEntity<Set<ProductDetail>> getProductSimilar(@PathVariable("productId") String productId) {
        Set<ProductDetail> result = productService.getProductSimilar(productId);
        return result == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(result);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<String> notFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Not Found");
    }
}
