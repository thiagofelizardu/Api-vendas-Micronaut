package com.phoebus.model.entites.DTO;

import com.phoebus.model.entites.Address;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Serdeable
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String street;

    private String city;

    public static AddressDTO convertAddressToDTO(Address address){
        return new AddressDTO(
                address.getStreet(),
                address.getCity()
        );
    }
}
