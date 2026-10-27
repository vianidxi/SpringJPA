package com.example.demo.animal;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Animal;
import com.example.demo.repo.AnimalRepository;

@RestController
public class AnimalController {
    private final AnimalRepository repo;

    public AnimalController(AnimalRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/animales")
    public List<Animal> findAll() {
        return repo.findAll();
    }

    @PostMapping("/animales")
    public Animal add(@Valid @RequestBody Animal animal) {
        return repo.save(animal);
    }

    @PutMapping("/animales/{id}")
    public Animal update(@PathVariable Long id, @Valid @RequestBody Animal a) {
        a.setCodigoAsociado(id);
        return repo.save(a);
    }

    @DeleteMapping("/animales/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
