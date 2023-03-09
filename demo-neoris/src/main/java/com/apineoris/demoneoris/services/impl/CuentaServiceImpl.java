package com.apineoris.demoneoris.services.impl;

import com.apineoris.demoneoris.dto.CuentaDto;
import com.apineoris.demoneoris.entity.Cliente;
import com.apineoris.demoneoris.entity.Cuenta;
import com.apineoris.demoneoris.repository.ClienteRepository;
import com.apineoris.demoneoris.repository.CuentaRepository;
import com.apineoris.demoneoris.services.CuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements CuentaService {
    @Autowired
    CuentaRepository repository;
    @Autowired
    ClienteRepository clienteRepository;

    Logger log = LoggerFactory.getLogger(CuentaService.class);

    @Override
    public List<CuentaDto> getAllCuentas() {
        List<CuentaDto> emptyList = new ArrayList<>();
        Optional<List<Cuenta>> optCuentas = Optional.of(repository.findAll());

        if (optCuentas.isPresent()) {
            List<Cuenta> cuentas = optCuentas.get();

            List<CuentaDto> cuentaDtos = cuentas.stream().map(cuenta -> {

                        if (cuenta.getCliente() != null) {
                            long idCliente = cuenta.getCliente().getIdCliente();

                            Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);

                            if (optionalCliente.isPresent()) {
                                Cliente cliente = optionalCliente.get();
                                return cuenta.toDto(cliente.getNombre());
                            }

                        }

                        return null;

                    })
                    .collect(Collectors.toList());

            List<CuentaDto> depuredCuentaDtos = cuentaDtos.stream().filter(cuentaDto -> cuentaDto != null).collect(Collectors.toList());

            return depuredCuentaDtos;
        }

        return emptyList;
    }

    @Override
    public CuentaDto saveNewCliente(Cuenta cuenta) {

        if (cuenta.getCliente() != null) {

            Optional<Cliente> optionalCliente = clienteRepository.findById(cuenta.getCliente().getIdCliente());

            if (optionalCliente.isPresent()) {
                Optional<Cuenta> optionalCuenta = Optional.of(repository.save(cuenta));

                if (optionalCuenta.isPresent()) {
                    Cliente cliente = optionalCliente.get();
                    Cuenta cta = optionalCuenta.get();
                    CuentaDto cuentaDto = new CuentaDto(cta.getNumeroCuenta(), cta.getTipoCuenta(), cta.getSaldoInicial(),
                            cta.getEstado(), cliente.getNombre());
                    return cuentaDto;
                }
            }
        }

        return null;
    }

    @Override
    public CuentaDto getCuentaById(long id) {
        Optional<Cuenta> optionalCuenta = repository.findById(id);

        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();

            if (cuenta.getCliente() != null) {
                Optional<Cliente> optionalCliente = clienteRepository.findById(cuenta.getCliente().getIdCliente());

                if (optionalCliente.isPresent()) {
                    Cliente cliente = optionalCliente.get();
                    CuentaDto cuentaDto = cuenta.toDto(cliente.getNombre());
                    return cuentaDto;
                } else {
                    log.warn("La cuenta con id: {}, no tiene un cliente en base de datos", id);
                }

            } else {
                log.warn("La cuenta con id: {}, no tiene un cliente", id);
            }

        }else {
            log.warn("La cuenta con id: {}, no encontrada", id);
        }

        return null;
    }

    @Override
    public CuentaDto updateWholeCuentaById(long id) {

        return null;
    }

    @Override
    public CuentaDto updatePartialCuentaById(long id) {
        return null;
    }

    @Override
    public CuentaDto deleteCuentaById(long id) {

        if (repository.existsById(id)) {

            Optional<Cuenta> optionalCuenta = repository.findById(id);

            if (optionalCuenta.isPresent()) {
                repository.deleteById(id);
                return optionalCuenta.get().toDto(optionalCuenta.get().getCliente().getNombre());
            }

        }

        return null;
    }
}
