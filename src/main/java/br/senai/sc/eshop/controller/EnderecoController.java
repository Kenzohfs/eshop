package br.senai.sc.eshop.controller;

import br.senai.sc.eshop.model.dto.EnderecoDTO;
import br.senai.sc.eshop.model.entity.Endereco;
import br.senai.sc.eshop.service.EnderecoService;
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
@RequestMapping("/prova/endereco")
public class EnderecoController {
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> findAll() {
        return ResponseEntity.ok(enderecoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Endereco> enderecoOptional = enderecoService.findById(id);

        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(enderecoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();

        BeanUtils.copyProperties(enderecoDTO, endereco, "id");

        return ResponseEntity.ok(enderecoService.save(endereco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid EnderecoDTO enderecoDTO, @PathVariable(value = "id") Long id) {
        if (!enderecoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado");
        }

        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco, "id");

        endereco.setId(id);

        return ResponseEntity.ok(enderecoService.save(endereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        if (!enderecoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado");
        }

        enderecoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Endereço excluído com sucesso");
    }
}
