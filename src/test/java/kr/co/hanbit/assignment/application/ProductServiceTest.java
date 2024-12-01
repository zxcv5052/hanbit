package kr.co.hanbit.assignment.application;

import kr.co.hanbit.assignment.domain.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Transactional
    @Test
    @DisplayName("상품을 추가한 후 id로 조회하면 해당 상품이 조회되어야 한다.")
    void productAddAndFindByIdTest() {
        ProductDto productDto = new ProductDto("연필", 300, 20);

        ProductDto savedProductDto = productService.add(productDto);
        Long savedProductId = savedProductDto.getId();

        ProductDto foundProductDto = productService.findById(savedProductId);

        assertEquals(savedProductDto.getId(), foundProductDto.getId());
        assertEquals(savedProductDto.getName(), foundProductDto.getName());
        assertEquals(savedProductDto.getPrice(), foundProductDto.getPrice());
        assertEquals(savedProductDto.getAmount(), foundProductDto.getAmount());
    }

    @Test
    @DisplayName("존재하지 않는 상품 id로 조회하면 EntityNotFoundException이 발생해야한다.")
    void findProductNotExistIdTest() {
        Long notExistId = -1L;

        assertThrows(EntityNotFoundException.class, () -> {
            productService.findById(notExistId);
        });
    }
}
