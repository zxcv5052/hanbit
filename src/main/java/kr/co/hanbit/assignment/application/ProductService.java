package kr.co.hanbit.assignment.application;

import kr.co.hanbit.assignment.domain.Product;
import kr.co.hanbit.assignment.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ValidationService validationService;

    @Autowired
    ProductService(ProductRepository productRepository, ValidationService validationService) {
        this.productRepository = productRepository;
        this.validationService = validationService;
    }

    public ProductDto add(ProductDto productDto) {
        Product product = ProductDto.toEntity(productDto);
        validationService.checkValid(product);
        /* 잘못된 검증이다.
        1. 도메인 지식이 도메인 객체 외부로 새어나가고 있음.
        2. 애플리케이션 서비스 코드의 흐름을 확인하기 어려움
        3. null을 반환하여 정상적인 상황인 것처럼 반환 값을 주고 있음.
        if(product.getName().length() > 100 && product.getName().length() < 1) { return null; }
        */
        Product savedProduct = productRepository.add(product);

        return ProductDto.toDto(savedProduct);
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);

        return ProductDto.toDto(product);
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductDto::toDto)
                .toList();
    }

    public List<ProductDto> findByName(String name) {
        List<Product> products = productRepository.findByName(name);

        return products.stream()
                .map(ProductDto::toDto)
                .toList();
    }

    public ProductDto update(ProductDto productDto) {
        Product product = ProductDto.toEntity(productDto);
        Product updateProduct = productRepository.update(product);

        return ProductDto.toDto(product);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
