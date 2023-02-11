package com.codingmatemoon.productorderserivce.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
// 서비스->포트-> Adapter-> Repository 호출 플로우를 위해 POJO 클래스를 만들었는데 간단한 상품 조회는 처음부터 SpringBootTest로 해도 괜찮습니다
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

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

}
