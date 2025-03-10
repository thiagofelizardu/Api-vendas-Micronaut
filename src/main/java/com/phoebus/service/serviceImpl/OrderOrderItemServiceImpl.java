package com.phoebus.service.serviceImpl;

import com.phoebus.model.entites.DTO.OrderItemDTO;
import com.phoebus.model.entites.OrderItem;
import com.phoebus.model.entites.Product;
import com.phoebus.model.exception.OrderItemException;
import com.phoebus.model.exception.ProductException;
import com.phoebus.model.utils.EntityFinderUtils;
import com.phoebus.repository.OrderItemRepository;
import com.phoebus.repository.ProductRepository;
import com.phoebus.service.OrderItemService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class OrderOrderItemServiceImpl implements OrderItemService {

    @Inject
    private final OrderItemRepository orderItemRepository;

    @Inject
    private final ProductRepository productRepository;

    public void deletById(Long id) throws OrderItemException {
        OrderItem existingOrderItem = EntityFinderUtils.findOrderItemById(orderItemRepository, id);
        orderItemRepository.deleteById(existingOrderItem.getId());
    }

    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) throws OrderItemException, ProductException {
        OrderItem existingOrder = EntityFinderUtils.findOrderItemById(orderItemRepository, id);
        existingOrder.setQuantity(orderItemDTO.getQuantity());
        Product product = EntityFinderUtils.findProductByName(productRepository,orderItemDTO.getNameProduct());
        existingOrder.setProduct(product);

        try {
            OrderItem updatedOrder = orderItemRepository.save(existingOrder);
            return OrderItemDTO.convertOrderItemDTO(updatedOrder);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o Item do Pedido" + e.getMessage());
        }
    }

}
