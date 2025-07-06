package com.phoebus.model.utils;

import com.phoebus.model.entites.Client;
import com.phoebus.model.entites.Order;
import com.phoebus.model.entites.OrderItem;
import com.phoebus.model.entites.Product;
import com.phoebus.model.exception.ClientException;
import com.phoebus.model.exception.OrderException;
import com.phoebus.model.exception.OrderItemException;
import com.phoebus.model.exception.ProductException;
import com.phoebus.repository.ClientRepository;
import com.phoebus.repository.OrderItemRepository;
import com.phoebus.repository.OrderRepository;
import com.phoebus.repository.ProductRepository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.function.Function;

public class EntityFinderUtils {

    public static <T, ID, E extends Exception> T findByIdGen(JpaRepository<T, ID> repository, ID id, Function<ID, E> exceptionSupplier
    ) throws E {
        return repository.findById(id).orElseThrow(() -> exceptionSupplier.apply(id));
    }

    public static Client findClientById(ClientRepository clientRepository, Long id) throws ClientException {
        return clientRepository.findById(id)
                .orElseThrow(() ->new ClientException(id));
    }

    public static OrderItem findOrderItemById(OrderItemRepository itemRepository, Long id) throws OrderItemException {
        return itemRepository.findById(id)
                .orElseThrow(()->new OrderItemException(id));
    }

    public static Product findProductByName(ProductRepository productRepository , String name) throws ProductException {
        return productRepository.findByName(name)
                .orElseThrow(() -> new ProductException(name));
    }

        public static Product findProductById(ProductRepository productRepository , Long id) throws ProductException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductException(id));
    }

    public static Order findOrderById(OrderRepository orderRepository , Long id) throws OrderException {
        return orderRepository.findById(id).orElseThrow(() -> new OrderException(id));
    }


}
