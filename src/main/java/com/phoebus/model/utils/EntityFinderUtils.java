package com.phoebus.model.utils;

import com.phoebus.model.entites.Client;
import com.phoebus.model.entites.OrderItem;
import com.phoebus.model.entites.Product;
import com.phoebus.model.exception.ClientException;
import com.phoebus.model.exception.OrderItemException;
import com.phoebus.model.exception.ProductException;
import com.phoebus.repository.ClientRepository;
import com.phoebus.repository.OrderItemRepository;
import com.phoebus.repository.ProductRepository;

public class EntityFinderUtils {

    public static Client findClientById(ClientRepository clientRepository, Long id) throws ClientException {
        return clientRepository.findById(id)
                .orElseThrow(() ->new ClientException(id));
    }

    public static OrderItem findOrderItem (OrderItemRepository itemRepository, Long id) throws OrderItemException {
        return itemRepository.findById(id)
                .orElseThrow(()->new OrderItemException(id));
    }

    public static Product findByName(ProductRepository productRepository , String name) throws ProductException {
        return productRepository.findByName(name)
                .orElseThrow(() -> new ProductException(name));
    }
}
