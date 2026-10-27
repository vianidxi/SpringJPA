package com.example.demo.alimento;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Alimento;
import com.example.demo.repo.AlimentoRepository;

@RestController
public class AlimentoController {
    private final AlimentoRepository repo;

    public AlimentoController(AlimentoRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/alimentos")
    public List<Alimento> findAll() {
        return repo.findAll();
    }

    @PostMapping("/alimentos")
    public Alimento add(@Valid @RequestBody Alimento alimento) {
        return repo.save(alimento);
    }

    @PutMapping("/alimentos/{id}")
    public Alimento update(@PathVariable Long id, @Valid @RequestBody Alimento a) {
        a.setId(id);
        return repo.save(a);
    }

    @DeleteMapping("/alimentos/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
