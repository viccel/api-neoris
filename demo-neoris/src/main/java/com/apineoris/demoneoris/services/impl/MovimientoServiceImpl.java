package com.apineoris.demoneoris.services.impl;

import com.apineoris.demoneoris.dto.MovimientoDto;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Autowired
    private MovimientoRepository repository;
    @Autowired
    private CuentaRepository cuentaRepository;

    Logger log = LoggerFactory.getLogger(CuentaService.class);

    @Override
    public List<MovimientoDto> getAllMovimientos() {

        List<Movimiento> movimientos = repository.findAll();
        List<MovimientoDto> movimientoDtos = movimientos.stream()
                .map(movimiento -> movimiento.toDto()).collect(Collectors.toList());

        return movimientoDtos;
    }

    @Override
    public MovimientoDto saveNewMovimiento(Movimiento movimiento) {

        Cuenta cuenta = movimiento.getCuenta();

        if (cuenta != null) {
            long numeroCuenta = cuenta.getNumeroCuenta();

            Optional<Cuenta> optionalCuenta = cuentaRepository.findById(numeroCuenta);

            if (optionalCuenta.isPresent()) {

                Movimiento savedMovimiento = repository.save(movimiento);
                return savedMovimiento.toDto();

            } else {
                log.warn("No se encontró cuenta relacionada al número de cuenta: {}", numeroCuenta);
            }

        } else {
            log.warn("No hay cuenta asociada al movimiento, no se puede realizar el movimiento.");
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
                    log.warn("Falló la eliminación.");
                } else {
                    log.info("Movimiento eliminado correctamente");
                }

                return movimientoDto;
            } else {
                log.warn("No se pudo obtener el movimiento: {} de la bd", id);
            }

        } else {
            log.warn("No existe el movimiento con id: {}", id);
        }

        return null;
    }

    @Override
    public List<MovimientoDto> getReporte(LocalDate fechaIni, LocalDate fechaFin) {

        List<Movimiento> reporte = repository.getReporte(fechaIni, fechaFin);
        List<MovimientoDto> movimientoDtos = reporte.stream().map(r -> r.toDto()).collect(Collectors.toList());

        /* SE DEJA LA OPCIÓN DE OBTNER LOS DATOS FILTRADOS DESDE LA BD; MEJOR PERFORMANCE Y MENOR USO Y TRÁFICO DE DATOS*/
        /*List<Movimiento> movimientos = repository.findAll().stream().filter(movimiento -> movimiento.getFecha().compareTo(fechaIni) >= 0 &&
                movimiento.getFecha().compareTo(fechaFin) <= 0).collect(Collectors.toList());
        List<MovimientoDto> movimientoDtos = movimientos.stream().map(movimiento -> movimiento.toDto()).collect(Collectors.toList());*/

        return movimientoDtos;
    }
}
