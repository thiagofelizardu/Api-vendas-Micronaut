package com.phoebus.service.serviceImpl;

import com.phoebus.entites.Endereco;
import com.phoebus.exception.EnderecoException;
import com.phoebus.repository.EnderecoRepository;
import com.phoebus.service.EnderecoService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;


@Singleton
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    private final EnderecoRepository enderecoRepository;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listAll() {
        return enderecoRepository.findAll();
    }


    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }


    public Optional<Endereco> findById(Long id) throws EnderecoException {
        Optional<Endereco> existingEndereco = enderecoRepository.findById(id);
        if(existingEndereco.isEmpty()){
            throw  new EnderecoException("Endereco Not Found with id: "+id);
        }
        return existingEndereco;
    }


    public void deletById(Long id) throws EnderecoException {
        Optional<Endereco> existingEndereco = enderecoRepository.findById(id);
        if(existingEndereco.isEmpty()){
            throw  new EnderecoException("Endereco Not Found with id: "+id);
        }
         enderecoRepository.deleteById(id);
    }
}
