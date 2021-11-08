package warehouse.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.dao.ProductDao;
import warehouse.exception.DataProcessingException;
import warehouse.model.Product;
import warehouse.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product add(Product product) {
        return productDao.add(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id).orElseThrow(() ->
                new DataProcessingException("Product by id: " + id + " not found in the DB"));
    }

    @Override
    public Product getByProductNumber(String sku) {
        return productDao.getByProductNumber(sku).orElseThrow(() ->
                new DataProcessingException("Product by SKU number: " + sku
                        + " not found in the DB"));
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public void remove(Long id) {
        productDao.remove(id);
    }
}
