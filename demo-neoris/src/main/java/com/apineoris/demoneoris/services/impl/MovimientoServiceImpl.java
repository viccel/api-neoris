package com.apineoris.demoneoris.services.impl;

import com.apineoris.demoneoris.dto.MovimientoDto;
import com.apineoris.demoneoris.entity.Cliente;
import com.apineoris.demoneoris.entity.Cuenta;
import com.apineoris.demoneoris.entity.Movimiento;
import com.apineoris.demoneoris.repository.ClienteRepository;
import com.apineoris.demoneoris.repository.CuentaRepository;
import com.apineoris.demoneoris.repository.MovimientoRepository;
import com.apineoris.demoneoris.services.CuentaService;
import com.apineoris.demoneoris.services.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Autowired
    private MovimientoRepository repository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    Logger log = LoggerFactory.getLogger(CuentaService.class);

    @Override
    public List<MovimientoDto> getAllMovimientos() {

        List<MovimientoDto> emptyList = new ArrayList<>();
        Optional<List<Movimiento>> optMovimientos = Optional.of(repository.findAll());

        if (optMovimientos.isPresent()) {
            List<Movimiento> movimientos = optMovimientos.get();
            List<MovimientoDto> movimientoDtos = movimientos.stream()
                    .map(movimiento -> {

                                Cliente cliente = movimiento.getCliente();
                                Cuenta cuenta = movimiento.getCuenta();

                                if (cliente != null && cuenta != null) {
                                    long idCliente = cliente.getIdCliente();

                                    Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);

                                    if (optionalCliente.isPresent()) {
                                        long idCuenta = cuenta.getNumeroCuenta();
                                        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(idCuenta);

                                        if (optionalCuenta.isPresent()) {
                                            Cuenta cta = optionalCuenta.get();

                                            if (cta.getCliente().getIdCliente() == idCliente) {
                                                return movimiento.toDto();
                                            }

                                        }

                                    }

                                }
                                return null;
                            }
                    ).collect(Collectors.toList());

            movimientoDtos = movimientoDtos.stream().filter(cta -> cta != null).collect(Collectors.toList());

            return movimientoDtos;
        } else {
            log.warn("No se obtuvieron los movimientos");
        }

        return emptyList;
    }

    @Override
    public MovimientoDto saveNewMovimiento(Movimiento movimiento) {

        if (movimiento != null) {

            Cliente cliente = movimiento.getCliente();

            if (cliente != null) {

                Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getIdCliente());

                if (clienteOptional.isPresent()) {
                    Cuenta cuenta = movimiento.getCuenta();

                    if (cuenta != null) {
                        long numeroCuenta = cuenta.getNumeroCuenta();
                        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(numeroCuenta);

                        if (optionalCuenta.isPresent()) {
                            repository.save(movimiento);
                            log.info("Movimiento guardado.");
                        } else {
                            log.warn("No existe cuenta: {}", numeroCuenta);
                        }

                    }
                } else {
                    log.warn("No existe el cliente con id: {}", cliente.getIdCliente());
                }


            }

            MovimientoDto movimientoDto = movimiento.toDto();

            return movimientoDto;

        } else {
            log.warn("No se pudo guardar el movimiento.");
        }

        return null;
    }

    @Override
    public MovimientoDto getMovimientoById(final long id) {

        Optional<Movimiento> optionalMovimiento = repository.findById(id);

        if (optionalMovimiento.isPresent()) {
            MovimientoDto movimientoDto = optionalMovimiento.get().toDto();
            return movimientoDto;
        }

        return null;
    }

    @Override
    public MovimientoDto updateWholeMovimientoById(final long id) {
        return null;
    }

    @Override
    public MovimientoDto updatePartialMovimientoById(final long id) {
        return null;
    }

    @Override
    public MovimientoDto deleteMovimientoById(final long id) {

        if (repository.existsById(id)) {

            Optional<Movimiento> optionalMovimiento = repository.findById(id);

            if (optionalMovimiento.isPresent()) {
                MovimientoDto movimientoDto = optionalMovimiento.get().toDto();
                repository.deleteById(id);

                if (repository.findById(id).isPresent()) {
                    System.out.println("Falló la eliminación.");
                } else {
                    System.out.println("Movimiento eliminado correctamente");
                }

                return movimientoDto;
            }

        }

        return null;
    }

    @Override
    public List<MovimientoDto> getReporte(LocalDate fechaIni, LocalDate fechaFin) {

        List<Movimiento> reporte = repository.getReporte(fechaIni, fechaFin);
        List<MovimientoDto> movimientoDtos = reporte.stream().map(r -> r.toDto()).collect(Collectors.toList());

        return movimientoDtos;
    }
}
