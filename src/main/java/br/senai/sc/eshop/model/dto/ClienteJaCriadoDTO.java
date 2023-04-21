package br.senai.sc.eshop.model.dto;

import br.senai.sc.eshop.model.entity.CartaoCredito;
import br.senai.sc.eshop.model.entity.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ClienteJaCriadoDTO {
    private Long id;
    private String nome;
    @NotNull
    @Email
    private String email;
    private String telefone;
    private List<Endereco> enderecos;
    private CartaoCredito cartao;
}
