package com.phoebus.service.serviceImpl;

import com.phoebus.entites.Client;
import com.phoebus.entites.Loja;
import com.phoebus.entites.Pedido;
import com.phoebus.exception.PedidoException;
import com.phoebus.repository.ClientRepository;
import com.phoebus.repository.LojaRepository;
import com.phoebus.repository.PedidoRepository;
import com.phoebus.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class PedidoServiceImpl implements PedidoService {

    @Inject
    private final PedidoRepository pedidoRepository;
    @Inject
    private final ClientRepository clientRepository;
    @Inject
    private final LojaRepository lojaRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClientRepository clientRepository, LojaRepository lojaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clientRepository = clientRepository;
        this.lojaRepository = lojaRepository;
    }

    public List<Pedido> listAll() {
        return  pedidoRepository.findAll();
    }


    public Pedido save(Pedido pedido) throws PedidoException {
        Optional<Client> client = clientRepository.findById(pedido.getClient().getId());
        if (client.isEmpty()) {
            throw new PedidoException("Client not found with id: " + pedido.getClient().getId());
        }
        Optional<Loja> loja = lojaRepository.findById(pedido.getLoja().getId());
        if (loja.isEmpty()) {
            throw new PedidoException("Loja not found with id: " + pedido.getLoja().getId());
        }
        pedido.setClient(client.get());
        pedido.setLoja(loja.get());
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> findById(Long id) throws PedidoException {
        Optional<Pedido> existingPedido = pedidoRepository.findById(id);
        if (existingPedido.isEmpty()) {
            throw new PedidoException("Pedido not found with id: " + id);
        }
        return existingPedido;
    }

    public void deleteById(Long id) throws PedidoException {
        Optional<Pedido> existingPedido = pedidoRepository.findById(id);
        if (existingPedido.isEmpty()) {
            throw new PedidoException("Pedido not found with id: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    public Pedido updatePedido(Long id , Pedido pedido)throws PedidoException{
        Optional<Pedido> existingPedido = pedidoRepository.findById(id);
        if (existingPedido.isEmpty()) {
            throw new PedidoException("Pedido not found with id: " + id);
        }
        Pedido pedidoToUpdate = existingPedido.get();
        pedidoToUpdate.setItensPedidos(pedido.getItensPedidos());
        return pedidoRepository.save(pedidoToUpdate);
    }
}
