package br.senai.sc.eshop.service;

import br.senai.sc.eshop.model.entity.Endereco;
import br.senai.sc.eshop.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnderecoService {
    private EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public <S extends Endereco> S save(S entity) {
        return enderecoRepository.save(entity);
    }

    public Optional<Endereco> findById(Long aLong) {
        return enderecoRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return enderecoRepository.existsById(aLong);
    }

    public void deleteById(Long aLong) {
        enderecoRepository.deleteById(aLong);
    }
}
