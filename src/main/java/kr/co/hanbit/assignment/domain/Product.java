package kr.co.hanbit.assignment.domain;

import lombok.Setter;

import java.util.Objects;

public class Product {
    @Setter
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public boolean containsName(String name) {
        return this.name.contains(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }
}
