package com.spring.petflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.petflow.entity.Atendimento;
import com.spring.petflow.entity.StatusAtendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    boolean existsByPetClienteIdAndStatusIn(
            Long clienteId,
            List<StatusAtendimento> status);
}
