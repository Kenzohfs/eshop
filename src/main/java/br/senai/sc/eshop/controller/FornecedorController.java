package br.senai.sc.eshop.controller;

import br.senai.sc.eshop.model.dto.FornecedorDTO;
import br.senai.sc.eshop.model.dto.FornecedorJaCriadoDTO;
import br.senai.sc.eshop.model.dto.ProdutoDTO;
import br.senai.sc.eshop.model.entity.Cliente;
import br.senai.sc.eshop.model.entity.Fornecedor;
import br.senai.sc.eshop.model.entity.Produto;
import br.senai.sc.eshop.service.ClienteService;
import br.senai.sc.eshop.service.FornecedorService;
import br.senai.sc.eshop.service.ProdutoService;
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
@RequestMapping("/prova/fornecedor")
public class FornecedorController {
    private FornecedorService fornecedorService;
    private ProdutoService produtoService;
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> findAll() {
        return ResponseEntity.ok(fornecedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorService.findById(id);

        if (fornecedorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(fornecedorOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        List<Produto> listaProdutos = new ArrayList<>();

        BeanUtils.copyProperties(fornecedorDTO, fornecedor, "id");

        for (ProdutoDTO produtoDTO : fornecedorDTO.getProdutos()) {
            Produto produto = new Produto();
            BeanUtils.copyProperties(produtoDTO, produto);
            listaProdutos.add(produto);
        }

        fornecedor.setProdutos(listaProdutos);

        return ResponseEntity.ok(fornecedorService.save(fornecedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid FornecedorJaCriadoDTO fornecedorDTO, @PathVariable(value = "id") Long id) {
        if (!fornecedorService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado");
        }

        Fornecedor fornecedor = new Fornecedor();

        BeanUtils.copyProperties(fornecedorDTO, fornecedor, "id");

        if (fornecedorDTO.getProdutos() != null) {
            List<Produto> listaProdutos = new ArrayList<>();
            for (Produto produto : fornecedorDTO.getProdutos()) {
                listaProdutos.add(produtoService.findById(produto.getId()).get());
            }
            fornecedor.setProdutos(listaProdutos);
        }

        if (fornecedorDTO.getClientes() != null) {
            List<Cliente> listaClientes = new ArrayList<>();
            for (Cliente cliente : fornecedorDTO.getClientes()) {
                listaClientes.add(clienteService.findById(cliente.getId()).get());
            }
            fornecedor.setClientes(listaClientes);
        }

        fornecedor.setId(id);

        return ResponseEntity.ok(fornecedorService.save(fornecedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        if (!fornecedorService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado");
        }

        fornecedorService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Fornecedor excluído com sucesso");
    }
}
