package edu.pw2.superloja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pw2.superloja.model.cliente.Cliente;
import edu.pw2.superloja.model.cliente.ClienteData;
import edu.pw2.superloja.model.cliente.ClienteRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepo;

    @GetMapping
    public Page<ClienteData> getAllClientes(@PageableDefault(sort = {"nome"}, size = 10) Pageable paginacao){
        return clienteRepo.findAll(paginacao).map(ClienteData::new);
    }

    @GetMapping("/{id}")
    public ClienteData getCliente(@PathVariable Long id){
        if(id != null){
            Cliente c = clienteRepo.getReferenceById(id);
            ClienteData cd = new ClienteData(c);
            return cd;
        }
        return null;
    }

    @PostMapping
    @Transactional
    public void saveCliente(@RequestBody ClienteData dados){
        Cliente c = new Cliente(dados);
        clienteRepo.save(c);
    }
}
