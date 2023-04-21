package br.senai.sc.eshop.model.dto;

import br.senai.sc.eshop.model.entity.Pedido;
import br.senai.sc.eshop.model.entity.Produto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProdutoPedidoDTO {
    private Long id;
    @NotNull
    @Positive
    private Integer quantidade;
    private Produto produto;
    private Pedido pedido;
}
