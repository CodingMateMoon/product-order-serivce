package com.codingmatemoon.productorderserivce.product;

import com.codingmatemoon.productorderserivce.ApiTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductApiTest extends ApiTest {
    private final ProductSteps productSteps = new ProductSteps();

//    @Autowired
//    private ProductService productService;
    /*
    private ProductPort productPort;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        productPort = new ProductAdapter(productRepository);
        productService = new ProductSerive(productPort);
    }

     */

    @Test
    void 상품등록() {
        final var request = productSteps.상품등록요청_생성();

        //productService.addProduct(request);
        //API 요청
        // 요청을 보내는 로그를 남깁니다
        final var response = productSteps.상품등록요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public ExtractableResponse<Response> 상품등록요청(final AddProductRequest request) {
        return productSteps.상품등록요청(request);
    }

    public AddProductRequest 상품등록요청_생성() {
        return productSteps.상품등록요청_생성();
    }

    @Test
    @DisplayName("상품조회")
    public void 상품조회() throws Exception{
        productSteps.상품등록요청(productSteps.상품등록요청_생성());
        Long productId = 1L;

        final var response = productSteps.상품수정요청(productId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
    }


}
