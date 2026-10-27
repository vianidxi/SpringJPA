package com.example.demo.model;

import java.math.BigDecimal;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false, precision = 8, scale = 3)
    private BigDecimal peso;
}
