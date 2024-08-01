package com.phoebus.service.serviceImpl;

import com.phoebus.model.entites.DTO.OrderItemDTO;
import com.phoebus.model.entites.OrderItem;
import com.phoebus.model.entites.Product;
import com.phoebus.model.exception.ItemDoPedidoException;
import com.phoebus.model.exception.ProdutoException;
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
        OrderItem existingOrderItem = itemDoPedidoRepository.findById(id).orElseThrow(() -> new ItemDoPedidoException(id));
        itemDoPedidoRepository.deleteById(existingOrderItem.getId());
    }

    public OrderItemDTO updateItemPedido(Long id, OrderItemDTO orderItemDTO) throws ItemDoPedidoException, ProdutoException {
        OrderItem existingPedido = itemDoPedidoRepository.findById(id)
                .orElseThrow(() -> new ItemDoPedidoException(id));
        existingPedido.setQuantity(orderItemDTO.getQuantity());

        Product product = produtoRepository.findByName(orderItemDTO.getNameProduct())
                .orElseThrow(() -> new ProdutoException(orderItemDTO.getNameProduct()));
        existingPedido.setProduct(product);

        try {
            OrderItem updatedPedido = itemDoPedidoRepository.save(existingPedido);
            return OrderItemDTO.convertOrderItemDTO(updatedPedido);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o Item do Pedido" + e.getMessage());
        }
    }

}
