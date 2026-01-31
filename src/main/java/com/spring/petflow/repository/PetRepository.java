package com.spring.petflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.petflow.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    
}
