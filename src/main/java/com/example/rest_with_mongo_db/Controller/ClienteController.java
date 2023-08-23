package com.example.rest_with_mongo_db.Controller;


import com.example.rest_with_mongo_db.Document.Clientes;
import com.example.rest_with_mongo_db.Service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {


    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping(value="/listclientes")
    public ResponseEntity<List<Clientes>> listClientes(){
        List<Clientes> clientes = clienteService.listClientes();
        System.out.println(clientes);
        if (clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/kpideClientes")
    public ResponseEntity<Object> kpideClientes() {
        String resultado = clienteService.kpideClientes(); // Esto debería ser un JSON como una cadena

        // Puedes convertir la cadena JSON en un objeto JSON si es necesario
        // JSONObject jsonResult = new JSONObject(resultado);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }




    @PostMapping(value="/creacliente")
    public ResponseEntity<Clientes> crearCliente(@RequestBody Clientes cliente){

        Clientes crearCliente = clienteService.crearCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(crearCliente);

    }
}
