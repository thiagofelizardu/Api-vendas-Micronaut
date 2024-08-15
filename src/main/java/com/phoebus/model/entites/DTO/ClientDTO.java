package com.phoebus.model.entites.DTO;

import com.phoebus.model.entites.Client;
import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Serdeable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String cpf;
    private Long age;
    private AddressDTO address;

    public static ClientDTO convertClientDTO(Client client){
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getAge(),
                AddressDTO.convertAddressToDTO(client.getAddress())
        );
    }
}
