package com.spring.petflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.petflow.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    void setAtivoFalseById(Long id);

    boolean existsByNomeAndTelefone(String nome, String telefone);

}
