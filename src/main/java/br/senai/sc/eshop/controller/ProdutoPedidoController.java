package br.senai.sc.eshop.controller;

import br.senai.sc.eshop.model.dto.ProdutoPedidoDTO;
import br.senai.sc.eshop.model.entity.ProdutoPedido;
import br.senai.sc.eshop.service.ProdutoPedidoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/prova/produtopedido")
public class ProdutoPedidoController {
    private ProdutoPedidoService produtoPedidoService;

    @GetMapping
    public ResponseEntity<List<ProdutoPedido>> findAll() {
        return ResponseEntity.ok(produtoPedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<ProdutoPedido> produtoPedidoOptional = produtoPedidoService.findById(id);

        if (produtoPedidoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(produtoPedidoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ProdutoPedidoDTO produtoPedidoDTO) {
        ProdutoPedido produtoPedido = new ProdutoPedido();

        BeanUtils.copyProperties(produtoPedidoDTO, produtoPedido, "id");

        return ResponseEntity.ok(produtoPedidoService.save(produtoPedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid ProdutoPedidoDTO produtoPedidoDTO, @PathVariable(value = "id") Long id) {
        if (!produtoPedidoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido não encontrado");
        }

        ProdutoPedido produtoPedido = new ProdutoPedido();
        BeanUtils.copyProperties(produtoPedidoDTO, produtoPedido, "id");

        produtoPedido.setId(id);

        return ResponseEntity.ok(produtoPedidoService.save(produtoPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        if (!produtoPedidoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido não encontrado");
        }

        produtoPedidoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("ProdutoPedido excluído com sucesso");
    }
}
