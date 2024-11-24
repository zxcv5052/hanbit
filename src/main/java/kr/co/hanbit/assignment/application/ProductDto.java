package kr.co.hanbit.assignment.application;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductDto {
    @Setter
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;
}
