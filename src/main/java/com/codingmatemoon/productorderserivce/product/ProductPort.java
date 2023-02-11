package com.codingmatemoon.productorderserivce.product;

interface ProductPort {
    void save(Product product);

    public Product getProduct(final Long productId);
}
