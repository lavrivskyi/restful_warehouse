package warehouse.model.dto.response;

import java.util.Map;

public class OrderResponseDto {
    private Map<String, Double> skuAndPrice;

    public Map<String, Double> getSkuAndPrice() {
        return skuAndPrice;
    }

    public void setSkuAndPrice(Map<String, Double> skuAndPrice) {
        this.skuAndPrice = skuAndPrice;
    }
}
