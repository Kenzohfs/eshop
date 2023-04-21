package br.senai.sc.eshop.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EnderecoEntrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    private Endereco endereco;

    @OneToOne(mappedBy = "endereco")
    @JsonIgnore
    private Pedido pedido;
}
