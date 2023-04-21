package br.senai.sc.eshop.controller;

import br.senai.sc.eshop.model.dto.EnderecoEntregaDTO;
import br.senai.sc.eshop.model.entity.EnderecoEntrega;
import br.senai.sc.eshop.service.EnderecoEntregaService;
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
@RequestMapping("/prova/enderecoentrega")
public class EnderecoEntregaController {
    private EnderecoEntregaService enderecoEntregaService;

    @GetMapping
    public ResponseEntity<List<EnderecoEntrega>> findAll() {
        return ResponseEntity.ok(enderecoEntregaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<EnderecoEntrega> enderecoEntregaOptional = enderecoEntregaService.findById(id);

        if (enderecoEntregaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço de entrega não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(enderecoEntregaOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid EnderecoEntregaDTO enderecoEntregaDTO) {
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();

        BeanUtils.copyProperties(enderecoEntregaDTO, enderecoEntrega, "id");

        return ResponseEntity.ok(enderecoEntregaService.save(enderecoEntrega));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid EnderecoEntregaDTO enderecoEntregaDTO, @PathVariable(value = "id") Long id) {
        if (!enderecoEntregaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço de entrega não encontrado");
        }

        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
        BeanUtils.copyProperties(enderecoEntregaDTO, enderecoEntrega, "id");

        enderecoEntrega.setId(id);

        return ResponseEntity.ok(enderecoEntregaService.save(enderecoEntrega));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        if (!enderecoEntregaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço de entrega não encontrado");
        }

        enderecoEntregaService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Endereço de entrega excluído com sucesso");
    }
}
