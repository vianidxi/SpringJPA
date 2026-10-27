package com.example.demo.nutriente;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Nutriente;
import com.example.demo.repo.NutrienteRepository;

@RestController
public class NutrienteController {
    private final NutrienteRepository repo;

    public NutrienteController(NutrienteRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/nutrientes")
    public List<Nutriente> findAll() {
        return repo.findAll();
    }

    @PostMapping("/nutrientes")
    public Nutriente add(@RequestBody Nutriente nutriente) {
        return repo.save(nutriente);
    }

    @PutMapping("/nutrientes/{id}")
    public Nutriente update(@PathVariable Long id, @RequestBody Nutriente n) {
        n.setId(id);
        return repo.save(n);
    }

    @DeleteMapping("/nutrientes/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
