package me.parkprin.assignment.products;


import me.parkprin.assignment.errors.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;
import static me.parkprin.assignment.utils.ApiUtils.success;
import static me.parkprin.assignment.utils.ApiUtils.ApiResult;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService){
        this.productService = productService;
    }

    @GetMapping
    public ApiResult findAll(Pageable pageable){
        return success(productService.findAll(pageable).stream()
                .map(ProductDto::new)
                .collect(toList()));
    }

    @GetMapping(path = "{id}")
    public ApiResult findById(@PathVariable Long id) {
        return success(productService.findById(id)
                .map(ProductDto::new)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id)));
    }
}
