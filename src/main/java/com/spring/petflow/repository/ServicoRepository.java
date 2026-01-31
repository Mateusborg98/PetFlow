package com.spring.petflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.petflow.entity.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    
}
