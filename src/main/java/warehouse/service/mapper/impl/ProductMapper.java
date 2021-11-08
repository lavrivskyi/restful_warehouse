package warehouse.service.mapper.impl;

import org.springframework.stereotype.Component;
import warehouse.model.Product;
import warehouse.model.dto.request.ProductRequestDto;
import warehouse.model.dto.response.ProductResponseDto;
import warehouse.service.mapper.RequestDtoMapper;
import warehouse.service.mapper.ResponseDtoMapper;

@Component
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<Product, ProductResponseDto> {
    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setWeight(dto.getWeight());
        product.setSku(dto.getSku());
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product model) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(model.getId());
        responseDto.setTitle(model.getTitle());
        responseDto.setPrice(model.getPrice());
        responseDto.setQuantity(model.getQuantity());
        responseDto.setWeight(model.getWeight());
        responseDto.setSku(model.getSku());
        return responseDto;
    }
}
