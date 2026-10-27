package com.example.demo.especie;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.demo.model.Especie;
import com.example.demo.repo.EspecieRepository;

@RestController
public class EspecieController {
    private final EspecieRepository repo;

    public EspecieController(EspecieRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/especies")
    public List<Especie> findAll() {
        return repo.findAll();
    }

    @PostMapping("/especies")
    public Especie add(@Valid @RequestBody Especie e) {
        return repo.save(e);
    }

    @PutMapping("/especies/{id}")
    public Especie update(@PathVariable Long id, @Valid @RequestBody Especie e) {
        e.setId(id);
        return repo.save(e);
    }

    @DeleteMapping("/especies/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
