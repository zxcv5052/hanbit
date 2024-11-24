package kr.co.hanbit.assignment.presentation;

import kr.co.hanbit.assignment.application.ProductDto;
import kr.co.hanbit.assignment.application.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.add(productDto);
    }

    @GetMapping("/products/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/products")
    public List<ProductDto> findAllProduct() {
        return productService.findAll();
    }

    @GetMapping("/products")
    public List<ProductDto> findProductByName(@RequestParam(required = false) String name) {
        if (name == null) {
            return productService.findAll();
        }

        return productService.findByName(name);
    }

    @PutMapping("/products")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productDto.setId(id);
        return productService.update(productDto);
    }

    @DeleteMapping("/products")
    public void deleteProduct(@RequestBody Long id) {
        productService.delete(id);
    }
}
