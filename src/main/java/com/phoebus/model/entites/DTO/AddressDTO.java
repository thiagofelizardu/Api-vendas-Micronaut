package com.phoebus.model.entites.DTO;

import com.phoebus.model.entites.Address;
import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.io.Serializable;

@Data
@Serdeable
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String street;

    private String city;

    @Creator
    public AddressDTO(String street, String city) {
        this.street = street;
        this.city = city;
    }
    public static AddressDTO convertAddressToDTO(Address address){
        return new AddressDTO(
                address.getStreet(),
                address.getCity()
        );
    }

    public static Address convertDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        return address;
    }
}
