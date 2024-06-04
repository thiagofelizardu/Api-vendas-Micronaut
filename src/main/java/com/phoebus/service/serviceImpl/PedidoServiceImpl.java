package com.phoebus.service.serviceImpl;

import com.phoebus.entites.Cliente;
import com.phoebus.entites.DTO.ItemDoPedidoDTO;
import com.phoebus.entites.DTO.PedidoDTO;
import com.phoebus.entites.ItemPedido;
import com.phoebus.entites.Pedido;
import com.phoebus.entites.Produto;
import com.phoebus.exception.ClientException;
import com.phoebus.exception.PedidoException;
import com.phoebus.exception.ProdutoException;
import com.phoebus.repository.ClientRepository;
import com.phoebus.repository.PedidoRepository;
import com.phoebus.repository.ProdutoRepository;
import com.phoebus.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    @Inject
    private final PedidoRepository pedidoRepository;
    @Inject
    private final ClientRepository clientRepository;

    private final ProdutoRepository produtoRepository;

    public List<PedidoDTO> listAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(PedidoDTO::convertPedidoDTO)
                .collect(Collectors.toList());
    }

    public PedidoDTO save(Long idCliente, PedidoDTO pedidoDTO) throws ClientException, ProdutoException {
        Cliente existingCliente = clientRepository.findById(idCliente)
                .orElseThrow(() -> new ClientException(idCliente));

        Pedido pedido = new Pedido();
        pedido.setClient(existingCliente);

        List<ItemPedido> itensPedido = new ArrayList<>();
        for (ItemDoPedidoDTO itemDoPedidoDTO : pedidoDTO.getItensPedido()) {
            Produto produto = produtoRepository.findById(itemDoPedidoDTO.getProdutoId())
                    .orElseThrow(() -> new ProdutoException(itemDoPedidoDTO.getProdutoId()));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDoPedidoDTO.getQuantidade());

            itensPedido.add(itemPedido);
        }
        pedido.setItensPedido(itensPedido);

        try {
            Pedido savedPedido = pedidoRepository.save(pedido);
            return PedidoDTO.convertPedidoDTO(savedPedido);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o pedido: " + e.getMessage());
        }

    }
    public PedidoDTO findById(Long id) throws PedidoException {
        Pedido existingPedido = pedidoRepository.findById(id).orElseThrow(() -> new PedidoException(id));
        return PedidoDTO.convertPedidoDTO(existingPedido);
    }

    public void deleteById(Long id) throws PedidoException {
        Pedido existingPedido = pedidoRepository.findById(id).orElseThrow(() -> new PedidoException(id));
        pedidoRepository.deleteById(existingPedido.getId());
    }

    @Transactional
    public PedidoDTO updatePedido(Long id, PedidoDTO pedidoDTO) throws PedidoException {
        Pedido existingPedido = pedidoRepository.findById(id).orElseThrow(() -> new PedidoException(id));
        Pedido updatedPedido = pedidoRepository.save(existingPedido);
        return PedidoDTO.convertPedidoDTO(updatedPedido);
    }
}
