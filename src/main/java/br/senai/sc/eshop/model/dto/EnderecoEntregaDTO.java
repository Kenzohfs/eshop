package br.senai.sc.eshop.model.dto;

import br.senai.sc.eshop.model.entity.Endereco;
import br.senai.sc.eshop.model.entity.Pedido;
import lombok.Data;

@Data
public class EnderecoEntregaDTO {
    private Long id;
    private Endereco endereco;
    private Pedido pedido;
}
