package warehouse.service;

import java.util.List;
import warehouse.model.Product;

public interface ProductService {
    Product add(Product product);

    Product get(Long id);

    Product getByProductNumber(String productNumber);

    List<Product> getAll();

    Product update(Product product);

    void remove(Long id);
}
