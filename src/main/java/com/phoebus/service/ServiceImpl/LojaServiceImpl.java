package com.phoebus.service.ServiceImpl;


import com.phoebus.entites.Loja;
import com.phoebus.exception.LojaException;
import io.micronaut.core.annotation.NonNull;

import java.util.List;
import java.util.Optional;

public interface LojaServiceImpl {

    List<Loja> listAll();

    Loja save(Loja loja);

    Optional<Loja> findById(@NonNull Long id) throws LojaException;

    void deletById (Long id) throws LojaException;

    Loja update(@NonNull Long id, Loja loja) throws LojaException;

}
