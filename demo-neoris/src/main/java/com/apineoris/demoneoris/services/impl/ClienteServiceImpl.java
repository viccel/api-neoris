package com.apineoris.demoneoris.services.impl;

import com.apineoris.demoneoris.dto.ClienteDto;
import com.apineoris.demoneoris.entity.Cliente;
import com.apineoris.demoneoris.repository.ClienteRepository;
import com.apineoris.demoneoris.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Override
    public List<ClienteDto> getAllClientes() {
        List<ClienteDto> empyList = new ArrayList<>();
        Optional<List<Cliente>> optAllClientes = Optional.ofNullable(repository.findAll());

        if (optAllClientes.isPresent()) {

            List<Cliente> clientes = optAllClientes.get();
            List<ClienteDto> clienteDtoList = clientes.stream().map(cliente -> {
                        return new ClienteDto(cliente);
                    })
                    .collect(Collectors.toList());

            return clienteDtoList;
        }

        return empyList;
    }

    @Override
    public ClienteDto getClienteById(final long id) {
        Optional<Cliente> optCliente = repository.findById(id);

        if (optCliente.isPresent()) {
            Cliente cliente = optCliente.get();
            return new ClienteDto(cliente);
        }

        return null;
    }

    @Override
    public ClienteDto saveNewCliente(Cliente cliente) {

        Optional<Cliente> optCliente = Optional.of(repository.save(cliente));

        if (optCliente.isPresent()) {
            ClienteDto clienteDto = new ClienteDto(optCliente.get());
            return clienteDto;
        }

        return null;
    }

    @Override
    public ClienteDto updateWholeClienteById(long id) {

        Optional<Cliente> optCliente = Optional.ofNullable(repository.findById(id)).flatMap(cliente -> cliente);

        if (optCliente.isPresent()) {
            ClienteDto clienteDto = new ClienteDto(optCliente.get());
            System.out.println("cliente actualizado completo...");
            return clienteDto;
        }

        return null;
    }

    @Override
    public ClienteDto updatePartiallyClienteById(long id, Cliente cliente) {
        return null;
    }

    @Override
    public ClienteDto deleteClienteById(long id) {

        boolean existsCliente = repository.existsById(id);

        if (existsCliente) {
            Optional<Cliente> optDeletedCliente = repository.findById(id);

            if (optDeletedCliente.isPresent()) {
                repository.delete(optDeletedCliente.get());
                return new ClienteDto(optDeletedCliente.get());
            } else {
                System.out.printf("Falló, no se pudo eliminar.");
            }
        } else {
            System.out.printf("No existe el cliente a eliminar.");
        }

        return null;
    }
}
