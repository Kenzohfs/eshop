package br.senai.sc.eshop.service;

import br.senai.sc.eshop.model.entity.EnderecoEntrega;
import br.senai.sc.eshop.repository.EnderecoEntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnderecoEntregaService {
    private EnderecoEntregaRepository enderecoEntregaRepository;

    public List<EnderecoEntrega> findAll() {
        return enderecoEntregaRepository.findAll();
    }

    public <S extends EnderecoEntrega> S save(S entity) {
        return enderecoEntregaRepository.save(entity);
    }

    public Optional<EnderecoEntrega> findById(Long aLong) {
        return enderecoEntregaRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return enderecoEntregaRepository.existsById(aLong);
    }

    public void deleteById(Long aLong) {
        enderecoEntregaRepository.deleteById(aLong);
    }
}
