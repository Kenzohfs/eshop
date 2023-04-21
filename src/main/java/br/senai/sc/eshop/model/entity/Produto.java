package br.senai.sc.eshop.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private Double preco;

    @Column
    private Integer quantidade;

    @ManyToMany(mappedBy = "produtos")
    @JsonIgnore
    private List<Fornecedor> fornecedores;
}
