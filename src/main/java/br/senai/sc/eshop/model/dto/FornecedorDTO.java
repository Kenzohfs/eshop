package br.senai.sc.eshop.model.dto;

import br.senai.sc.eshop.model.entity.Cliente;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class FornecedorDTO {
    private Long id;
    private String nome;
    private String cnpj;
    @Valid
    private List<ProdutoDTO> produtos;
    private List<Cliente> clientes;
}
