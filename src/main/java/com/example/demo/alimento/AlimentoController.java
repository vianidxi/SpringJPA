package com.example.demo.alimento;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;
import com.example.demo.model.Alimento;
import com.example.demo.model.Nutriente;
import com.example.demo.repo.AlimentoRepository;
import com.example.demo.repo.NutrienteRepository;

@RestController
public class AlimentoController {
    private final AlimentoRepository repo;
    private final NutrienteRepository nutrienteRepo;

    public AlimentoController(AlimentoRepository repo, NutrienteRepository nutrienteRepo) {
        this.repo = repo;
        this.nutrienteRepo = nutrienteRepo;
    }

    private Nutriente buscarNutriente(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cada nutriente necesita su id");
        }
        return nutrienteRepo.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "El nutriente " + id + " no existe"));
    }

    private Alimento resolver(Alimento a) {
        Set<Nutriente> completos = new HashSet<>();
        for (Nutriente n : a.getNutrientes()) {
            completos.add(buscarNutriente(n.getId()));
        }
        a.setNutrientes(completos);
        return a;
    }

    private Alimento buscar(Long id) {
        return repo.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "El alimento " + id + " no existe"));
    }

    @GetMapping("/alimentos")
    public List<Alimento> findAll() {
        return repo.findAll();
    }

    @PostMapping("/alimentos")
    public Alimento add(@Valid @RequestBody Alimento alimento) {
        return repo.save(resolver(alimento));
    }

    @PutMapping("/alimentos/{id}")
    public Alimento update(@PathVariable Long id, @Valid @RequestBody Alimento a) {
        a.setId(id);
        return repo.save(resolver(a));
    }

    @DeleteMapping("/alimentos/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // RF3: asociar, desasociar y consultar nutrientes de un alimento
    @GetMapping("/alimentos/{id}/nutrientes")
    @Transactional(readOnly = true)
    public Set<Nutriente> nutrientesDe(@PathVariable Long id) {
        return new HashSet<>(buscar(id).getNutrientes());
    }

    @PostMapping("/alimentos/{id}/nutrientes/{nutrienteId}")
    @Transactional
    public Alimento asociar(@PathVariable Long id, @PathVariable Long nutrienteId) {
        Alimento a = buscar(id);
        a.getNutrientes().add(buscarNutriente(nutrienteId));
        return repo.save(a);
    }

    @DeleteMapping("/alimentos/{id}/nutrientes/{nutrienteId}")
    @Transactional
    public Alimento desasociar(@PathVariable Long id, @PathVariable Long nutrienteId) {
        Alimento a = buscar(id);
        a.getNutrientes().removeIf(n -> n.getId().equals(nutrienteId));
        return repo.save(a);
    }
}
