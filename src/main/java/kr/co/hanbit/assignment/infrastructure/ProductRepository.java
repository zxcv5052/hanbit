package kr.co.hanbit.assignment.infrastructure;

import kr.co.hanbit.assignment.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    // CopyOnWriteArrayList를 사용하는 이유는 멀티스레드 환경에서 Thread Safety 컬렉션을 사용해야 하기 때문
    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L));

        products.add(product);
        return product;
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Product> findAll() {
        return products;
    }

    public List<Product> findByName(String name) {
        return products.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    public Product update(Product product) {
        return products.set(products.indexOf(product), product);
    }

    public void delete(Long id) {
        Product product = this.findById(id);
        products.remove(product);
    }
}
