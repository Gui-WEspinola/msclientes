package io.github.guiwespinola.msclientes.infra.repository;

import io.github.guiwespinola.msclientes.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCpf(String cpf);
}
