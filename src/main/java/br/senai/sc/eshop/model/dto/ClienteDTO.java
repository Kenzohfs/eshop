package br.senai.sc.eshop.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {
    private Long id;
    private String nome;
    @NotNull
    @Email
    private String email;
    private String telefone;
    @Valid
    private List<EnderecoDTO> enderecos;
    @Valid
    private CartaoCreditoDTO cartao;
}
