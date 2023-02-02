package com.codingmatemoon.productorderserivce.product;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class ProductServiceTest {

    @Test
    void 상품등록() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
        productService.addProduct(request);
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
}
