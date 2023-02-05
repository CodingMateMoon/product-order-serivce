package com.codingmatemoon.productorderserivce.product;

class ProductSerive {
    private final ProductPort productPort;

    ProductSerive(ProductPort productPort) {
        this.productPort = productPort;
    }

    public void addProduct(AddProductRequest request) {
//            throw new UnsupportedOperationException("unsupported addProduct");
        final Product product = new Product(request.name(), request.price(), request.discountPolicy());

        productPort.save(product);
    }
}
