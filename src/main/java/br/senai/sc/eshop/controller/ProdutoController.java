package br.senai.sc.eshop.controller;

import br.senai.sc.eshop.model.dto.ProdutoDTO;
import br.senai.sc.eshop.model.entity.Produto;
import br.senai.sc.eshop.service.ProdutoService;
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
@RequestMapping("/prova/produto")
public class ProdutoController {
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Produto> produtoOptional = produtoService.findById(id);

        if (produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ProdutoDTO produtoDTO) {
        Produto produto = new Produto();

        BeanUtils.copyProperties(produtoDTO, produto, "id");

        return ResponseEntity.ok(produtoService.save(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid ProdutoDTO produtoDTO, @PathVariable(value = "id") Long id) {
        if (!produtoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto, "id");

        produto.setId(id);

        return ResponseEntity.ok(produtoService.save(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        if (!produtoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        produtoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Produto excluído com sucesso");
    }
}
