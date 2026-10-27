package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alimento")
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede pasar de 50 caracteres")
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @ManyToMany
    @JoinTable(name = "alimento_nutriente",
        joinColumns = @JoinColumn(name = "id_alimento"),
        inverseJoinColumns = @JoinColumn(name = "id_nutriente"))
    private Set<Nutriente> nutrientes = new HashSet<>();
}
