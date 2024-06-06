package com.phoebus.service.serviceImpl;

import com.phoebus.entites.DTO.ItemDoPedidoDTO;
import com.phoebus.entites.ItemPedido;
import com.phoebus.entites.Produto;
import com.phoebus.exception.ItemDoPedidoException;
import com.phoebus.exception.ProdutoException;
import com.phoebus.repository.ItemDoPedidoRepository;
import com.phoebus.repository.ProdutoRepository;
import com.phoebus.service.ItemDoPedidoService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class ItemPedidoServiceImpl implements ItemDoPedidoService {

    @Inject
    private final ItemDoPedidoRepository itemDoPedidoRepository;

    @Inject
    private final ProdutoRepository produtoRepository;

    public void deletById(Long id) throws ItemDoPedidoException {
        ItemPedido existingItemPedido = itemDoPedidoRepository.findById(id).orElseThrow(() -> new ItemDoPedidoException(id));
        itemDoPedidoRepository.deleteById(existingItemPedido.getId());
    }

    public ItemDoPedidoDTO updateItemPedido(Long id, ItemDoPedidoDTO itemDoPedidoDTO) throws ItemDoPedidoException, ProdutoException {
        ItemPedido existingPedido = itemDoPedidoRepository.findById(id)
                .orElseThrow(() -> new ItemDoPedidoException(id));
        existingPedido.setQuantidade(itemDoPedidoDTO.getQuantidade());

        Produto produto = produtoRepository.findByNome(itemDoPedidoDTO.getNomeProduto())
                .orElseThrow(() -> new ProdutoException(itemDoPedidoDTO.getNomeProduto()));
        existingPedido.setProduto(produto);

        try {
            ItemPedido updatedPedido = itemDoPedidoRepository.save(existingPedido);
            return ItemDoPedidoDTO.convertItemPedidoDTO(updatedPedido);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o Item do Pedido" + e.getMessage());
        }
    }

}
