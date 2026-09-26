package br.com.fiap.microservices.clienteservice.service;

import br.com.fiap.microservices.clienteservice.model.Cliente;
import br.com.fiap.microservices.clienteservice.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criarCliente(Cliente cliente) {
        logger.info("Criando novo cliente: {}", cliente.getNome());
        if(clienteRepository.findByEmail(cliente.getEmail()).isPresent()){
            throw new RuntimeException("Email ja cadsatrado: " + cliente.getEmail());
        }
        return clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorId(Long id){
        logger.info("Buscando cliente com id: {}", id);
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
    }

    public List<Cliente> listarClientes(){
        logger.info("Listando todos os clientes");
        return clienteRepository.findAll();
    }
}
