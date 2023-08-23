package com.example.rest_with_mongo_db.Service;

import com.example.rest_with_mongo_db.Document.Clientes;
import com.example.rest_with_mongo_db.Repository.ClienteRepository;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<Clientes> listClientes() {

        System.out.println(clienteRepository.findAll());

        return clienteRepository.findAll();
    }

    @Override
    public Clientes crearCliente(Clientes clientes) {
        return clienteRepository.save(clientes);
    }

    @Override
    public String kpideClientes() {

        List<Clientes> listaClientes = clienteRepository.findAll();

        double PromedioEdades = calcularPromedio(listaClientes);

        double DesviacionEstandarEdades = calcularDesvEst(listaClientes);

        JSONObject OutputJSON = new JSONObject();

        try {
            OutputJSON.put("Promedio de las edades de todos los clientes: ",PromedioEdades);
            OutputJSON.put("Desviacion Estandar de las edades de todos los clientes", DesviacionEstandarEdades);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }






        return OutputJSON.toString();
    }


    public static double calcularPromedio(List<Clientes> listaClientes) {
        int sumador_edades = 0;

        int cant_clientes = listaClientes.size();

        for (Clientes clientes : listaClientes) {
            int edad = clientes.getEdad();
            sumador_edades = sumador_edades + edad;
        }

        double promedio_edades = sumador_edades / cant_clientes;
        return promedio_edades;
    }

    public static double calcularDesvEst(List<Clientes> listaClientes) {

        double PromedioEdades = calcularPromedio(listaClientes);

        double suma_de_restas_al_cuadrado = 0;


        int sumador_edades = 0;

        int cant_clientes = listaClientes.size();

        for (Clientes clientes : listaClientes) {
            int edad = clientes.getEdad();

            double resta = edad - PromedioEdades;
            suma_de_restas_al_cuadrado += Math.pow(resta,2);

        }

        double varianza = suma_de_restas_al_cuadrado / (listaClientes.size()-1);
        double desviacionEstandar = Math.sqrt(varianza);

        return desviacionEstandar;
    }
}
