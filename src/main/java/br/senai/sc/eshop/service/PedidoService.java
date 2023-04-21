package br.senai.sc.eshop.service;

import br.senai.sc.eshop.model.entity.Pedido;
import br.senai.sc.eshop.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoService {
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public <S extends Pedido> S save(S entity) {
        return pedidoRepository.save(entity);
    }

    public Optional<Pedido> findById(Long aLong) {
        return pedidoRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return pedidoRepository.existsById(aLong);
    }

    public void deleteById(Long aLong) {
        pedidoRepository.deleteById(aLong);
    }
}
