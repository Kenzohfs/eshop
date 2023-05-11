package br.senai.sc.eshop.controller;

import br.senai.sc.eshop.model.dto.CartaoCreditoDTO;
import br.senai.sc.eshop.model.dto.ClienteDTO;
import br.senai.sc.eshop.model.dto.ClienteJaCriadoDTO;
import br.senai.sc.eshop.model.dto.EnderecoDTO;
import br.senai.sc.eshop.model.entity.CartaoCredito;
import br.senai.sc.eshop.model.entity.Cliente;
import br.senai.sc.eshop.model.entity.Endereco;
import br.senai.sc.eshop.service.CartaoCreditoService;
import br.senai.sc.eshop.service.ClienteService;
import br.senai.sc.eshop.service.EnderecoService;
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
@RequestMapping("/prova/cliente")
public class ClienteController {
    private ClienteService clienteService;
    private EnderecoService enderecoService;
    private CartaoCreditoService cartaoCreditoService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Cliente> clienteOptional = clienteService.findById(id);

        if (clienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        List<Endereco> enderecos = new ArrayList<>();
        CartaoCredito cartaoCredito = new CartaoCredito();

        BeanUtils.copyProperties(clienteDTO, cliente, "id");

        for (EnderecoDTO enderecoDTO : clienteDTO.getEnderecos()) {
            Endereco endereco = new Endereco();
            BeanUtils.copyProperties(enderecoDTO, endereco);
            enderecos.add(endereco);
        }

        BeanUtils.copyProperties(clienteDTO.getCartao(), cartaoCredito);

        cliente.setEnderecos(enderecos);
        cliente.setCartao(cartaoCredito);

        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid ClienteJaCriadoDTO clienteDTO, @PathVariable(value = "id") Long id) {
        if (!clienteService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        Cliente cliente = new Cliente();

        BeanUtils.copyProperties(clienteDTO, cliente, "id");

        if (clienteDTO.getEnderecos() != null) {
            List<Endereco> enderecos = new ArrayList<>();
            for (Endereco endereco : clienteDTO.getEnderecos()) {
                enderecos.add(enderecoService.findById(endereco.getId()).get());
            }
            cliente.setEnderecos(enderecos);
        }

        if (clienteDTO.getCartao() != null) {
            cliente.setCartao(cartaoCreditoService.findById(clienteDTO.getCartao().getId()).get());
        }

        cliente.setId(id);

        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        if (!clienteService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        clienteService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso");
    }
}
