package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Alimento;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
}
