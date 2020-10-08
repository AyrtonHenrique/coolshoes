package br.com.fiap.coolshoes.repository;

import br.com.fiap.coolshoes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByAtivoIsTrue();

    Optional<Product> findFirstByIdAndAtivoIsTrue(Long id);

    List<Product> findAllByNumero(Integer numero);

    @Query("from Product " +
            "where numero = :numero")
    List<Product> buscaPorNumero(Integer numero);

}
