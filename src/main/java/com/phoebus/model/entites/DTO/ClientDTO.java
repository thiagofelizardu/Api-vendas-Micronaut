package com.phoebus.model.entites.DTO;

import com.phoebus.model.entites.Client;
import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.io.Serializable;

@Serdeable
@Data
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String cpf;
    private Long age;
    private AddressDTO address;

    @Creator
    public ClientDTO(Long id, String name, String cpf, Long age, AddressDTO address) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.address = address;
    }

    public static ClientDTO convertClienteDTO(Client client){
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getAge(),
                AddressDTO.convertAddressToDTO(client.getAddress())
        );
    }
}
