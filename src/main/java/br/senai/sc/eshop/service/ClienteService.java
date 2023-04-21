package br.senai.sc.eshop.service;

import br.senai.sc.eshop.model.entity.Cliente;
import br.senai.sc.eshop.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public <S extends Cliente> S save(S entity) {
        return clienteRepository.save(entity);
    }

    public Optional<Cliente> findById(Long aLong) {
        return clienteRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return clienteRepository.existsById(aLong);
    }

    public void deleteById(Long aLong) {
        clienteRepository.deleteById(aLong);
    }
}
