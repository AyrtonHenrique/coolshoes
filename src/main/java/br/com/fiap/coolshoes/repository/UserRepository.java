package br.com.fiap.coolshoes.repository;

import br.com.fiap.coolshoes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);

}
