package br.senai.sc.eshop.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private  String telefone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<Endereco> enderecos;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartao_id")
    private CartaoCredito cartao;
}
