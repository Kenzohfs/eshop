package br.senai.sc.eshop.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartaoCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String numero;
}
