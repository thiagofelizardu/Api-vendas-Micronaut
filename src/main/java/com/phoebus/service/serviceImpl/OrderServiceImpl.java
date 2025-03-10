package com.phoebus.service.serviceImpl;

import com.phoebus.model.entites.Client;
import com.phoebus.model.entites.DTO.OrderItemDTO;
import com.phoebus.model.entites.DTO.OrderDTO;
import com.phoebus.model.entites.OrderItem;
import com.phoebus.model.entites.Order;
import com.phoebus.model.entites.Product;
import com.phoebus.model.exception.ClientException;
import com.phoebus.model.exception.OrderException;
import com.phoebus.model.exception.ProductException;
import com.phoebus.model.utils.EntityFinderUtils;
import com.phoebus.repository.ClientRepository;
import com.phoebus.repository.OrderRepository;
import com.phoebus.repository.ProductRepository;
import com.phoebus.service.OrderService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Inject
    private final OrderRepository orderRepository;
    @Inject
    private final ClientRepository clientRepository;
    @Inject
    private final ProductRepository productRepository;

    public Page<OrderDTO> listAll(Pageable pageable) {
        Page<Order> pedidos = orderRepository.findAll(pageable);
        return pedidos.map(OrderDTO::convertOrderDTO);
    }

    public OrderDTO save(Long idCliente, OrderDTO orderDTO) throws ClientException, ProductException {
        Client existingClient = EntityFinderUtils.findClientById(clientRepository, idCliente);
        Order order = new Order();
        order.setClient(existingClient);

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (OrderItemDTO orderItemDTO : orderDTO.getOrderItem()) {
            Product product = EntityFinderUtils.findProductById(productRepository, orderItemDTO.getProductId());
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemDTO.getQuantity());

            double itemAmount = product.getPrice() * orderItemDTO.getQuantity();
            totalAmount += itemAmount;
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        order.setAmount(totalAmount);
        try {
            Order savedOrder = orderRepository.save(order);
            return OrderDTO.convertOrderDTO(savedOrder);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o pedido: " + e.getMessage());
        }
    }

    public OrderDTO findById(Long id) throws OrderException {
        Order existingOrder = EntityFinderUtils.findOrderById(orderRepository,id);
        return OrderDTO.convertOrderDTO(existingOrder);
    }

    public void deleteById(Long id) throws OrderException {
        Order existingOrder = EntityFinderUtils.findOrderById(orderRepository,id);
        orderRepository.deleteById(existingOrder.getId());
    }
    //falta implementar corretamente
    @Transactional
    public OrderDTO updatePedido(Long id, OrderDTO orderDTO) throws OrderException {
        Order existingOrder = EntityFinderUtils.findOrderById(orderRepository,id);
        Order updatedOrder = orderRepository.save(existingOrder);
        return OrderDTO.convertOrderDTO(updatedOrder);
    }
}
