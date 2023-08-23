package com.example.rest_with_mongo_db.Repository;

import com.example.rest_with_mongo_db.Document.Clientes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClienteRepository extends MongoRepository<Clientes,String> {

    public List<Clientes> findAll();

}
