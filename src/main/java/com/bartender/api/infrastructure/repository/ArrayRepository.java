package com.bartender.api.infrastructure.repository;


import com.bartender.api.infrastructure.entity.ArraysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrayRepository extends JpaRepository<ArraysEntity, Integer> {}
