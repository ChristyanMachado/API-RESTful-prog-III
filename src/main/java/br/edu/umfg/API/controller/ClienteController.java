package br.edu.umfg.API.controller;

import br.edu.umfg.API.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        cliente.setId(proximoId++);
        clientes.add(cliente);
        return cliente;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clientes;
    }

    @GetMapping("/{id}")
    public Cliente obterClientePorId(@PathVariable int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
}