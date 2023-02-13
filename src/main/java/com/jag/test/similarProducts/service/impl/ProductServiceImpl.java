package com.jag.test.similarProducts.service.impl;

import com.jag.test.similarProducts.client.SimilarProductsApiClient;
import com.jag.test.similarProducts.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.ProductDetail;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final SimilarProductsApiClient similarProductsApiClient;

    public ProductServiceImpl(SimilarProductsApiClient similarProductsApiClient) {
        this.similarProductsApiClient = similarProductsApiClient;
    }

    public Set<ProductDetail> getProductSimilar(String productId) {
        log.debug("getProductSimilar: " + productId);

        Set<String> productSimilarIds = similarProductsApiClient.getProductSimilarIds(productId);

        if (Objects.isNull(productSimilarIds) ||
                CollectionUtils.isEmpty(productSimilarIds)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return productSimilarIds
                .stream().map(similarProductsApiClient::getProductId)
                .collect(Collectors.toSet());
    }
}
