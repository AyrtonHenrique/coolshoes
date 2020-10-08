package br.com.fiap.coolshoes.dto;

import br.com.fiap.coolshoes.entity.Product;

import java.math.BigDecimal;

public class ProductDTO {

    private Long id;
    private String modelo;
    private Integer numero;
    private String cor;
    private BigDecimal preco;

    public ProductDTO(){}

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.modelo = product.getModelo();
        this.numero = product.getNumero();
        this.cor = product.getCor();
        this.preco = product.getPreco();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
