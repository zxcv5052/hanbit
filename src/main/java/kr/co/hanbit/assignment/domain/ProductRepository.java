package kr.co.hanbit.assignment.domain;

import java.util.List;

public interface ProductRepository {
    Product add(Product product);
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByName(String name);
    Product update(Product product);
    void delete(Long id);
}
