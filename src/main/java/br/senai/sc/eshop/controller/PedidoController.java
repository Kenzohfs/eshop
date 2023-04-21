package br.senai.sc.eshop.controller;

import br.senai.sc.eshop.model.dto.PedidoDTO;
import br.senai.sc.eshop.model.dto.PedidoJaCriadoDTO;
import br.senai.sc.eshop.model.dto.ProdutoPedidoDTO;
import br.senai.sc.eshop.model.entity.Endereco;
import br.senai.sc.eshop.model.entity.EnderecoEntrega;
import br.senai.sc.eshop.model.entity.Pedido;
import br.senai.sc.eshop.model.entity.ProdutoPedido;
import br.senai.sc.eshop.service.EnderecoEntregaService;
import br.senai.sc.eshop.service.EnderecoService;
import br.senai.sc.eshop.service.PedidoService;
import br.senai.sc.eshop.service.ProdutoPedidoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/prova/pedido")
public class PedidoController {
    private PedidoService pedidoService;
    private ProdutoPedidoService produtoPedidoService;
    private EnderecoEntregaService enderecoEntregaService;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Pedido> pedidoOptional = pedidoService.findById(id);

        if (pedidoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pedidoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        List<ProdutoPedido> listaProdutoPedido = new ArrayList<>();
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();

        BeanUtils.copyProperties(pedidoDTO, pedido, "id");

        for (ProdutoPedidoDTO produtoPedidoDTO : pedidoDTO.getProdutos()) {
            ProdutoPedido produtoPedido = new ProdutoPedido();
            BeanUtils.copyProperties(produtoPedidoDTO, produtoPedido);
            listaProdutoPedido.add(produtoPedido);
        }

        BeanUtils.copyProperties(pedidoDTO.getEndereco(), enderecoEntrega);

        pedido.setProdutos(listaProdutoPedido);
        pedido.setEndereco(enderecoEntrega);

        Pedido pedidoSalvo = pedidoService.save(pedido);

        pedidoSalvo.getProdutos().forEach(produtoPedido -> {
            produtoPedido.setPedido(pedidoSalvo);
        });

        return ResponseEntity.ok(pedidoService.save(pedidoSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid PedidoJaCriadoDTO pedidoDTO, @PathVariable(value = "id") Long id) {
        if (!pedidoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        Pedido pedido = new Pedido();

        BeanUtils.copyProperties(pedidoDTO, pedido, "id");

        pedido.setId(id);

        if (pedidoDTO.getEndereco() != null) {
            EnderecoEntrega enderecoEntrega = enderecoEntregaService.findById(pedidoDTO.getEndereco().getId()).get();
            pedido.setEndereco(enderecoEntrega);
        }

        if (pedidoDTO.getProdutos() != null) {
            List<ProdutoPedido> listaProdutoPedido = new ArrayList<>();
            for (ProdutoPedido produtoPedido : pedidoDTO.getProdutos()) {
                listaProdutoPedido.add(produtoPedidoService.findById(produtoPedido.getId()).get());
            }
            pedido.setProdutos(listaProdutoPedido);
        }

        Pedido pedidoSalvo = pedidoService.save(pedido);

        if (pedidoSalvo.getProdutos() != null) {
            pedidoSalvo.getProdutos().forEach(produtoPedido -> {
                produtoPedido.setPedido(pedidoSalvo);
            });
        }

        return ResponseEntity.ok(pedidoService.save(pedidoSalvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        if (!pedidoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        pedidoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Pedido excluído com sucesso");
    }
}
