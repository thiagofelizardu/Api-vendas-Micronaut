package com.phoebus.service.serviceImpl;

import com.phoebus.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClientServiceImplTest {


    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listAll() {

    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}