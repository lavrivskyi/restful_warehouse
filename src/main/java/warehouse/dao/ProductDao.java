package warehouse.dao;

import java.util.List;
import java.util.Optional;
import warehouse.model.Product;

public interface ProductDao {
    Product add(Product entity);

    Optional<Product> get(Long id);

    Product update(Product entity);

    void remove(Long id);

    List<Product> getAll();

    Optional<Product> getByProductNumber(String productNumber);
}
