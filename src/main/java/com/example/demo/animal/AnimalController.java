package com.example.demo.animal;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;
import com.example.demo.model.Animal;
import com.example.demo.repo.AnimalRepository;
import com.example.demo.repo.EspecieRepository;

@RestController
public class AnimalController {
    private final AnimalRepository repo;
    private final EspecieRepository especieRepo;

    public AnimalController(AnimalRepository repo, EspecieRepository especieRepo) {
        this.repo = repo;
        this.especieRepo = especieRepo;
    }

    private Animal conEspecie(Animal a) {
        Long id = a.getEspecie().getId();
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debes indicar especie.id");
        }
        a.setEspecie(especieRepo.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "La especie " + id + " no existe")));
        return a;
    }

    @GetMapping("/animales")
    public List<Animal> findAll() {
        return repo.findAll();
    }

    @PostMapping("/animales")
    public Animal add(@Valid @RequestBody Animal animal) {
        return repo.save(conEspecie(animal));
    }

    @PutMapping("/animales/{id}")
    public Animal update(@PathVariable Long id, @Valid @RequestBody Animal a) {
        a.setCodigoAsociado(id);
        return repo.save(conEspecie(a));
    }

    @DeleteMapping("/animales/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
