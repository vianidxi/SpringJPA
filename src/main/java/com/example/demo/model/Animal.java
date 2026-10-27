package com.example.demo.model;

import java.math.BigDecimal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoAsociado;

    @NotNull(message = "La edad es obligatoria")
    @Positive(message = "La edad debe ser mayor a 0")
    @Column(nullable = false)
    private Integer edad;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a 0")
    @Column(nullable = false, precision = 8, scale = 3)
    private BigDecimal peso;
}
