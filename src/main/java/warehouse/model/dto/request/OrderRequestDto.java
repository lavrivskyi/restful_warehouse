package warehouse.model.dto.request;

import java.util.Map;

public class OrderRequestDto {
    private Map<String, Integer> skuAndQuantity;

    public Map<String, Integer> getSkuAndQuantity() {
        return skuAndQuantity;
    }

    public void setSkuAndQuantity(Map<String, Integer> skuAndQuantity) {
        this.skuAndQuantity = skuAndQuantity;
    }
}
