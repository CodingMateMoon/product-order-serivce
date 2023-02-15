package com.codingmatemoon.productorderserivce.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
// 서비스->포트-> Adapter-> Repository 호출 플로우를 위해 POJO 클래스를 만들었는데 간단한 상품 조회는 처음부터 SpringBootTest로 해도 괜찮습니다
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품수정() {
        final Long productId = 1L;
        final UpdateProductRequest request = new UpdateProductRequest("상품 수정", 2000, DiscountPolicy.NONE);
        
        productService.updateProduct(productId, request);
    }

    private class UpdateProductRequest {
        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;

        public UpdateProductRequest(final String name, final int price, final DiscountPolicy discountPolicy) {

            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }
    }

    /*
    @Test
    @DisplayName("상품조회")
    public void 상품조회() throws Exception{
        // 상품등록
        productService.addProduct(ProductSteps.상품등록요청_생성());
        final long productId = 1L;

        // 상품을 조회
        final GetProductResponse response = productService.getProduct(productId);

        // 상품의 응답을 검증. 검증부 먼저 작성
        assertThat(response).isNotNull();
    }
    */
}
