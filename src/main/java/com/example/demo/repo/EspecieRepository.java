package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Especie;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
}
