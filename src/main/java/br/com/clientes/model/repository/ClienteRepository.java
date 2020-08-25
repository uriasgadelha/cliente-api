package br.com.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clientes.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
