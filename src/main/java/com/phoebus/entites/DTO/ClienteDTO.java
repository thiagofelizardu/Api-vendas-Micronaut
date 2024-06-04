package com.phoebus.entites.DTO;

import com.phoebus.entites.Cliente;
import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.io.Serializable;

@Serdeable
@Data
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String cpf;
    private Long idade;
    private EnderecoDTO endereco;

    @Creator
    public ClienteDTO(Long id, String nome, String cpf, Long idade, EnderecoDTO endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.endereco = endereco;
    }

    public static ClienteDTO convertClienteDTO(Cliente cliente){
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getIdade(),
                EnderecoDTO.convertEnderecoDTO(cliente.getEndereco())
        );
    }
}
