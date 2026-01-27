package com.spring.petflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.petflow.entity.Atendimento;

public interface AtendimentoRepository
        extends JpaRepository<Atendimento, Long> {

}
