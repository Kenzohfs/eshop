package br.senai.sc.eshop.model.dto;

import br.senai.sc.eshop.model.entity.Cliente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class PedidoDTO {
    private Long id;
    @NotNull
    @Positive
    private Double valorTotal;
    @Valid
    private List<ProdutoPedidoDTO> produtos;
    private Cliente cliente;
    @Valid
    private EnderecoEntregaDTO endereco;
}
