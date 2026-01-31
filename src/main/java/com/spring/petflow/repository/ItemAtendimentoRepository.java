package com.spring.petflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.petflow.entity.ItemAtendimento;

@Repository
public interface ItemAtendimentoRepository extends JpaRepository<ItemAtendimento, Long> {
    
}
