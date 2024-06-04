package com.phoebus.entites.DTO;

import com.phoebus.entites.Endereco;
import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.io.Serializable;

@Data
@Serdeable
public class EnderecoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String logradouro;

    private String cidade;

    @Creator
    public EnderecoDTO(String logradouro, String cidade) {
        this.logradouro = logradouro;
        this.cidade = cidade;
    }
    public static EnderecoDTO convertEnderecoDTO(Endereco endereco){
        return new EnderecoDTO(
                endereco.getLogradouro(),
                endereco.getCidade()
        );
    }

    public static Endereco convertToEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setCidade(enderecoDTO.getCidade());
        return endereco;
    }
}
