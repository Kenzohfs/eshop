package br.senai.sc.eshop.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class ProdutoDTO {
    private Long id;
    private String nome;
    @NotNull
    @Positive
    private Double preco;
    @NotNull
    @Positive
    private Integer quantidade;
}
