package kr.co.hanbit.assignment.application;

import kr.co.hanbit.assignment.domain.Product;
import kr.co.hanbit.assignment.domain.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUnitTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("상품 추가 후에는 추가된 상품이 반환되어야한다.")
    void productAddTest() {
        ProductDto productDto = new ProductDto("연필", 300, 20);
        Long PRODUCT_ID = 1L;

        Product product = ProductDto.toEntity(productDto);
        product.setId(PRODUCT_ID);
        when(productRepository.add(any())).thenReturn(product);

        ProductDto savedProductDto = productService.add(productDto);

        assertEquals(PRODUCT_ID, savedProductDto.getId());
        assertEquals(savedProductDto.getName(), productDto.getName());
        assertEquals(savedProductDto.getPrice(), productDto.getPrice());
        assertEquals(savedProductDto.getAmount(), productDto.getAmount());
    }
}