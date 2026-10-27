package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
