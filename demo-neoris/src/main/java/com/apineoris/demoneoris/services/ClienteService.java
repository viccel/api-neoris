package com.apineoris.demoneoris.services;

import com.apineoris.demoneoris.dto.ClienteDto;
import com.apineoris.demoneoris.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ClienteService {
    List<ClienteDto> getAllClientes();

    ClienteDto getClienteById(long id);

    ClienteDto saveNewCliente(Cliente cliente);

    ClienteDto updateWholeClienteById(long id);

    ClienteDto updatePartiallyClienteById(long id, Cliente cliente);

    ClienteDto deleteClienteById(long id);
}
