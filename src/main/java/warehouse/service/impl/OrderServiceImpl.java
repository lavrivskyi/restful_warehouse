package warehouse.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.dao.ProductDao;
import warehouse.model.Product;
import warehouse.model.dto.request.OrderRequestDto;
import warehouse.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductDao productDao;

    @Autowired
    public OrderServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Map<String, Double> placeOrder(OrderRequestDto orderRequestDto) {
        Map<String, Double> skuAndPrice = new HashMap<>();
        Map<String, Integer> skuAndQuantity = orderRequestDto.getSkuAndQuantity();
        for (Map.Entry<String, Integer> entry : skuAndQuantity.entrySet()) {
            Optional<Product> byProductNumber = productDao.getByProductNumber(entry.getKey());
            if (byProductNumber.isPresent()
                    && byProductNumber.get().getQuantity() >= entry.getValue()) {
                Product product = byProductNumber.get();
                String productSku = product.getSku();
                Double price = product.getPrice();
                Integer productQuantity = product.getQuantity();
                Integer orderAmount = entry.getValue();
                skuAndPrice.put(productSku, price * orderAmount);
                Product updatedProduct = byProductNumber.get();
                updatedProduct.setQuantity(productQuantity - orderAmount);
                productDao.update(updatedProduct);
            }
        }
        return skuAndPrice;
    }
}
