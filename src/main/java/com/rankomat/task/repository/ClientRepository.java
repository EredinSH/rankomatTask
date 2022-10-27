package com.rankomat.task.repository;

import com.rankomat.task.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long> {

    @Override
    List<Client> findAll();

    @Override
    Client save(Client client);

    List<Client> findByUpdated(boolean status);

}
