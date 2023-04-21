package br.senai.sc.eshop.controller;

import br.senai.sc.eshop.model.dto.CartaoCreditoDTO;
import br.senai.sc.eshop.model.entity.CartaoCredito;
import br.senai.sc.eshop.service.CartaoCreditoService;
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
@RequestMapping("/prova/cartaocredito")
public class CartaoCreditoController {
    private CartaoCreditoService cartaoCreditoService;

    @GetMapping
    public ResponseEntity<List<CartaoCredito>> findAll() {
        return ResponseEntity.ok(cartaoCreditoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<CartaoCredito> cartaoCreditoOptional = cartaoCreditoService.findById(id);

        if (cartaoCreditoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão de Crédito não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(cartaoCreditoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid CartaoCreditoDTO cartaoCreditoDTO) {
        CartaoCredito cartaoCredito = new CartaoCredito();

        BeanUtils.copyProperties(cartaoCreditoDTO, cartaoCredito, "id");

        return ResponseEntity.ok(cartaoCreditoService.save(cartaoCredito));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid CartaoCreditoDTO cartaoCreditoDTO, @PathVariable(value = "id") Long id) {
        if (!cartaoCreditoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão de Crédito não encontrado");
        }

        CartaoCredito cartaoCredito = new CartaoCredito();
        BeanUtils.copyProperties(cartaoCreditoDTO, cartaoCredito, "id");

        cartaoCredito.setId(id);

        return ResponseEntity.ok(cartaoCreditoService.save(cartaoCredito));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        if (!cartaoCreditoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão de Crédito não encontrado");
        }

        cartaoCreditoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Cartão de Crédito excluído com sucesso");
    }
}
