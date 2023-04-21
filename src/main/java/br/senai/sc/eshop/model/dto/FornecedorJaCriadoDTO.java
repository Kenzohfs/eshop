package br.senai.sc.eshop.model.dto;

import br.senai.sc.eshop.model.entity.Cliente;
import br.senai.sc.eshop.model.entity.Produto;
import lombok.Data;

import java.util.List;

@Data
public class FornecedorJaCriadoDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private List<Produto> produtos;
    private List<Cliente> clientes;
}
