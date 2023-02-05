package com.codingmatemoon.productorderserivce.product;

import org.springframework.stereotype.Component;

@Component
class ProductService {
    private final ProductPort productPort;

    ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    public void addProduct(AddProductRequest request) {
//            throw new UnsupportedOperationException("unsupported addProduct");
        final Product product = new Product(request.name(), request.price(), request.discountPolicy());

        productPort.save(product);
    }
}
