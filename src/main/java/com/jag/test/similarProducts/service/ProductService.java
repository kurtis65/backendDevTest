package com.jag.test.similarProducts.service;


import org.openapitools.model.ProductDetail;

import java.util.Set;

public interface ProductService {

    Set<ProductDetail> getProductSimilar(String productId);
}
