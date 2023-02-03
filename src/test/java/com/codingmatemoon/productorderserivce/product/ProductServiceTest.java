package com.codingmatemoon.productorderserivce.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class ProductServiceTest {

    private ProductSerive productService;
    private ProductPort productPort;

    @BeforeEach
    void setUp() {
        productPort = new ProductAdapter(productRepository)
        productService = new ProductSerive(productPort);
    }

    @Test
    void 상품등록() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
        productService.addProduct(request);
    }

    private class ProductSerive {
        private final ProductPort productPort;

        private ProductSerive(ProductPort productPort) {
            this.productPort = productPort;
        }

        public void addProduct(AddProductRequest request) {
//            throw new UnsupportedOperationException("unsupported addProduct");
            final Product product = new Product(request.name(), request.price(), request.discountPolicy());

            productPort.save(product);
        }
    }

    private record AddProductRequest(String name, int price,
                                     DiscountPolicy discountPolicy) {
        private AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
            Assert.hasText(name, "상품명은 필수입니다");
            Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다");
            Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");

        }
    }

    private enum DiscountPolicy {
        NONE

    }


    private class Product {
        private Long id;
        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;

        public Product(String name, int price, DiscountPolicy discountPolicy) {
            Assert.hasText(name, "상품명은 필수입니다");
            Assert.isTrue(price >0, "상품 가격은 0보다 커야 합니다.");
            Assert.notNull(discountPolicy, "할인 정책은 필수입니다");
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
            throw new UnsupportedOperationException("unsupported Exception");
        }

        public void assignId(Long aLong) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    private interface ProductPort {
         void save(Product product);
      }

    private class ProductAdapter implements ProductPort {
        private final ProductRepository productRepository;

        private ProductAdapter(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        @Override
        public void save(final Product product) {
            productRepository.save(product);
        }
    }

    private class ProductRepository {
        private Long sequence = 0L;
        private Map<Long, Product> persistence = new HashMap<>();

        public void save(final Product product) {
            product.assignId(++sequence);
            persistence.put(product.getId(),product);
        }
    }
}
