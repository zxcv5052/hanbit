package kr.co.hanbit.assignment.application;

import jakarta.validation.constraints.NotNull;
import kr.co.hanbit.assignment.domain.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductDto {
    @Setter
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer amount;

    public ProductDto() {

    }

    public ProductDto(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public ProductDto(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public static Product toEntity(ProductDto productDto) {
        return new Product(
            productDto.getId(),
            productDto.getName(),
            productDto.getPrice(),
            productDto.getAmount()
        );
    }

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getAmount()
        );
    }
}
