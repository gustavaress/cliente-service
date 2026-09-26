package br.com.fiap.microservices.clienteservice.controller;

import br.com.fiap.microservices.clienteservice.model.Cliente;
import br.com.fiap.microservices.clienteservice.service.ClienteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente){
        logger.info("POST /api/clientes- Produto: {}", cliente.getNome());
        Cliente clienteCriado = clienteService.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id){
        logger.info("GET /api/clientes/{}", id);
        return ResponseEntity.ok(clienteService.buscarClientePorId((id)));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        logger.info("GET /api/clientes");
        return ResponseEntity.ok(clienteService.listarClientes());
    }
}
