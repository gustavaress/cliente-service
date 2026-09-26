package br.com.fiap.microservices.clienteservice.repository;

import br.com.fiap.microservices.clienteservice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente>findByEmail(String email);
}
