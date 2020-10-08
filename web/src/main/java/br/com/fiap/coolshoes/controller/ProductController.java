package br.com.fiap.coolshoes.controller;

import br.com.fiap.coolshoes.dto.ProductCreateUpdateDTO;
import br.com.fiap.coolshoes.dto.ProductDTO;
import br.com.fiap.coolshoes.dto.ProductPriceUpdateDTO;
import br.com.fiap.coolshoes.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> listAll(@RequestParam(required = false) Integer numero){
        logger.info("Hello");
        return productService.findAll(numero);
    }

    @GetMapping("{id}")
    public ProductDTO getById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateUpdateDTO productCreateUpdateDTO){
        return productService.create(productCreateUpdateDTO);
    }

    @PutMapping("{id}")
    public ProductDTO update(@PathVariable Long id,
                             @RequestBody ProductCreateUpdateDTO productCreateUpdateDTO){
        return productService.update(id, productCreateUpdateDTO);
    }

    @PatchMapping("{id}/price")
    public ProductDTO updatePrice(@PathVariable Long id,
                             @RequestBody ProductPriceUpdateDTO productPriceUpdateDTO){
        return productService.updatePrice(id, productPriceUpdateDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

}
