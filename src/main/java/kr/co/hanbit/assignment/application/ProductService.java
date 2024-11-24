package kr.co.hanbit.assignment.application;

import kr.co.hanbit.assignment.domain.Product;
import kr.co.hanbit.assignment.infrastructure.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Autowired
    ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public ProductDto add(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.add(product);

        return modelMapper.map(savedProduct, ProductDto.class);
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);

        return modelMapper.map(product, ProductDto.class);
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    public List<ProductDto> findByName(String name) {
        List<Product> products = productRepository.findByName(name);

        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    public ProductDto update(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product updateProduct = productRepository.update(product);

        return modelMapper.map(updateProduct, ProductDto.class);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
