package br.senai.sc.eshop.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private String cnpj;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @ManyToMany
    @JoinTable(name = "fornecedor_cliente")
    private List<Cliente> clientes;
}
