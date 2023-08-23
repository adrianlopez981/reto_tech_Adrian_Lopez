package com.example.rest_with_mongo_db.Service;

import com.example.rest_with_mongo_db.Document.Clientes;

import java.util.List;


public interface ClienteService {

    public List<Clientes> listClientes();

    public Clientes crearCliente(Clientes clientes);

    public String kpideClientes();

}
