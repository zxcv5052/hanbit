package kr.co.hanbit.assignment.infrastructure;

import kr.co.hanbit.assignment.domain.Product;
import kr.co.hanbit.assignment.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("prod")
public class DatabaseProductRepository implements ProductRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseProductRepository(NamedParameterJdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Product add(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);

        jdbcTemplate
                .update("INSERT INTO products (name, price, amount) VALUES (:name, :price, :amount)",
                        namedParameter,
                        keyHolder
                );
        Long generatedId = keyHolder.getKey().longValue();
        product.setId(generatedId);

        return product;
    }
    public Product findById(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);

        return jdbcTemplate.queryForObject(
                "SELECT id, name, price, amount FROM products WHERE id=:id",
                namedParameter,
                new BeanPropertyRowMapper<>(Product.class)
        );
    }

    public List<Product> findAll() {
        return namedParameterJdbcTemplate.query(
                "SELECT * FROM products",
                new BeanPropertyRowMapper<>(Product.class)
        );
    }

    public List<Product> findByName(String name) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("name", "%" + name + "%");
        return namedParameterJdbcTemplate.query(
                "SELECT * FROM products WHERE name LIKE :name",
                namedParameter,
                new BeanPropertyRowMapper<>(Product.class)
        );
    }

    public Product update(Product product) {
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);

        namedParameterJdbcTemplate.update(
                "UPDATE products SET name=:name, price=:price, amount=:amount WHERE id=:id",
                namedParameter
        );

        return product;
    }

    public void delete(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);

        namedParameterJdbcTemplate.update(
                "DELETE FROM products WHERE id=:id",
                namedParameter
        );
    }
}
