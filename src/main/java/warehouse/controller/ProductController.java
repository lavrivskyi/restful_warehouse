package warehouse.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import warehouse.model.Product;
import warehouse.model.dto.request.ProductRequestDto;
import warehouse.model.dto.response.ProductResponseDto;
import warehouse.service.ProductService;
import warehouse.service.mapper.impl.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductMapper mapper;
    private final ProductService productService;

    public ProductController(ProductMapper mapper, ProductService productService) {
        this.mapper = mapper;
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto) {
        return mapper.mapToDto(productService.add(mapper.mapToModel(requestDto)));
    }

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.getAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/id")
    public ProductResponseDto getById(@RequestParam Long id) {
        return mapper.mapToDto(productService.get(id));
    }

    @GetMapping("/sku")
    public ProductResponseDto getBySku(@RequestParam String sku) {
        return mapper.mapToDto(productService.getByProductNumber(sku));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = mapper.mapToModel(requestDto);
        product.setId(id);
        return mapper.mapToDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.remove(id);
    }
}
