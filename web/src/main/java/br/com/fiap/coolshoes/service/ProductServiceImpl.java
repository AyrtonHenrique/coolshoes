package br.com.fiap.coolshoes.service;

import br.com.fiap.coolshoes.dto.ProductCreateUpdateDTO;
import br.com.fiap.coolshoes.dto.ProductDTO;
import br.com.fiap.coolshoes.dto.ProductPriceUpdateDTO;
import br.com.fiap.coolshoes.entity.Product;
import br.com.fiap.coolshoes.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAll(Integer numero) {
        return productRepository.findAllByAtivoIsTrue()
                .stream()
                .map(product -> new ProductDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = getProduct(id);
        return new ProductDTO(product);
    }

    @Override
    public ProductDTO create(ProductCreateUpdateDTO productCreateUpdateDTO) {
        Product product = new Product();

        product.setCor(productCreateUpdateDTO.getCor());
        product.setPreco(productCreateUpdateDTO.getPreco());
        product.setModelo(productCreateUpdateDTO.getModelo());
        product.setNumero(productCreateUpdateDTO.getNumero());
        product.setAtivo(true);

        Product savedProduct = productRepository.save(product);

        return new ProductDTO(savedProduct);
    }

    @Override
    public ProductDTO update(Long id, ProductCreateUpdateDTO productCreateUpdateDTO) {
        Product product = getProduct(id);

        product.setCor(productCreateUpdateDTO.getCor());
        product.setPreco(productCreateUpdateDTO.getPreco());
        product.setModelo(productCreateUpdateDTO.getModelo());
        product.setNumero(productCreateUpdateDTO.getNumero());

        Product savedProduct = productRepository.save(product);

        return new ProductDTO(savedProduct);
    }

    @Override
    public ProductDTO updatePrice(Long id, ProductPriceUpdateDTO productPriceUpdateDTO) {
        Product product = getProduct(id);

        product.setPreco(productPriceUpdateDTO.getPreco());

        Product savedProduct = productRepository.save(product);

        return new ProductDTO(savedProduct);
    }

    @Override
    public void delete(Long id) {
        Product product = getProduct(id);
        product.setAtivo(false);
        productRepository.save(product);
    }

    private Product getProduct(Long id) {
        return productRepository.findFirstByIdAndAtivoIsTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
