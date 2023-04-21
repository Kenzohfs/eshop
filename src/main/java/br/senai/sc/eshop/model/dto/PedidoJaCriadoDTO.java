package br.senai.sc.eshop.model.dto;

import br.senai.sc.eshop.model.entity.Cliente;
import br.senai.sc.eshop.model.entity.EnderecoEntrega;
import br.senai.sc.eshop.model.entity.ProdutoPedido;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class PedidoJaCriadoDTO {
    private Long id;
    @NotNull
    @Positive
    private Double valorTotal;
    private List<ProdutoPedido> produtos;
    private Cliente cliente;
    private EnderecoEntrega endereco;
}
