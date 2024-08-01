package com.phoebus.service.serviceImpl;

import com.phoebus.model.entites.Client;
import com.phoebus.model.entites.DTO.OrderItemDTO;
import com.phoebus.model.entites.DTO.OrderDTO;
import com.phoebus.model.entites.OrderItem;
import com.phoebus.model.entites.Order;
import com.phoebus.model.entites.Product;
import com.phoebus.model.exception.ClientException;
import com.phoebus.model.exception.PedidoException;
import com.phoebus.model.exception.ProdutoException;
import com.phoebus.repository.ClientRepository;
import com.phoebus.repository.PedidoRepository;
import com.phoebus.repository.ProdutoRepository;
import com.phoebus.service.PedidoService;
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
public class PedidoServiceImpl implements PedidoService {

    @Inject
    private final PedidoRepository pedidoRepository;
    @Inject
    private final ClientRepository clientRepository;
    @Inject
    private final ProdutoRepository produtoRepository;

    public Page<OrderDTO> listAll(Pageable pageable) {
        Page<Order> pedidos = pedidoRepository.findAll(pageable);
        return pedidos.map(OrderDTO::convertPedidoDTO);
    }

    public OrderDTO save(Long idCliente, OrderDTO orderDTO) throws ClientException, ProdutoException {
        Client existingClient = clientRepository.findById(idCliente)
                .orElseThrow(() -> new ClientException(idCliente));

        Order order = new Order();
        order.setClient(existingClient);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO orderItemDTO : orderDTO.getOrderItem()) {
            Product product = produtoRepository.findById(orderItemDTO.getProdutoId())
                    .orElseThrow(() -> new ProdutoException(orderItemDTO.getProdutoId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemDTO.getQuantity());

            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        try {
            Order savedOrder = pedidoRepository.save(order);
            return OrderDTO.convertPedidoDTO(savedOrder);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o pedido: " + e.getMessage());
        }

    }
    public OrderDTO findById(Long id) throws PedidoException {
        Order existingOrder = pedidoRepository.findById(id).orElseThrow(() -> new PedidoException(id));
        return OrderDTO.convertPedidoDTO(existingOrder);
    }

    public void deleteById(Long id) throws PedidoException {
        Order existingOrder = pedidoRepository.findById(id).orElseThrow(() -> new PedidoException(id));
        pedidoRepository.deleteById(existingOrder.getId());
    }

    @Transactional
    public OrderDTO updatePedido(Long id, OrderDTO orderDTO) throws PedidoException {
        Order existingOrder = pedidoRepository.findById(id).orElseThrow(() -> new PedidoException(id));
        Order updatedOrder = pedidoRepository.save(existingOrder);
        return OrderDTO.convertPedidoDTO(updatedOrder);
    }
}
