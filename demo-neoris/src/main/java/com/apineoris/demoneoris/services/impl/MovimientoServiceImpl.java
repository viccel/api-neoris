package com.apineoris.demoneoris.services.impl;

import com.apineoris.demoneoris.dto.MovimientoDto;
import com.apineoris.demoneoris.entity.Cuenta;
import com.apineoris.demoneoris.entity.Movimiento;
import com.apineoris.demoneoris.entity.enums.TipoMovimiento;
import com.apineoris.demoneoris.repository.CuentaRepository;
import com.apineoris.demoneoris.repository.MovimientoRepository;
import com.apineoris.demoneoris.services.CuentaService;
import com.apineoris.demoneoris.services.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

                if (movimiento.getValor() > 0) {

                    Cuenta upCta = optionalCuenta.get();

                    if (movimiento.getTipoMovimiento() == TipoMovimiento.DEPOSITO) {
                        if (movimiento.getValor() > 0) {

                            double saldoActualizado = upCta.getSaldoInicial() + movimiento.getValor();
                            upCta.setSaldoActual(saldoActualizado);
                            movimiento.setSaldo(saldoActualizado);
                            Movimiento savedMovimiento = repository.save(movimiento);
                            Cuenta updatedCuenta = cuentaRepository.save(upCta);

                            if (updatedCuenta != null) {
                                log.info("Saldo de la cuenta: {}, ha sido actualizado", updatedCuenta.getNumeroCuenta());
                                return savedMovimiento.toDto();
                            }

                        } else {
                            log.warn("No se puede depositar $0 o menos, importe: {}", movimiento.getValor());
                        }
                    }

                    if (movimiento.getTipoMovimiento() == TipoMovimiento.RETIRO) {

                        if (upCta.getSaldoActual() > 0) {
                            if (upCta.getSaldoActual() >= movimiento.getValor()) {

                                double saldoActualizado = upCta.getSaldoActual() - movimiento.getValor();

                                upCta.setSaldoActual(saldoActualizado);
                                movimiento.setSaldo(saldoActualizado);
                                Movimiento savedMovimiento = repository.save(movimiento);

                                if (savedMovimiento != null) {
                                    cuentaRepository.save(upCta);
                                }

                                return savedMovimiento.toDto();

                            } else {
                                log.warn("No dispones de dinero suficiente en tu cuenta con id: {}", upCta.getNumeroCuenta());
                            }
                        } else {
                            log.warn("No dispones de dinero en tu cuenta con id: {}", upCta.getNumeroCuenta());
                        }

                    }
                }

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
    public List<MovimientoDto> getReporte(final long id, final LocalDate fechaIni, final LocalDate fechaFin) {

        List<Movimiento> reporte = repository.getReporte(fechaIni, fechaFin);
        List<Movimiento> clientMovs = reporte.stream().filter(movimiento -> movimiento.getCuenta().getCliente()
                .getIdCliente() == id).collect(Collectors.toList());
        List<MovimientoDto> movimientoDtos = clientMovs.stream().map(r -> r.toDto()).collect(Collectors.toList());

        return movimientoDtos;
    }
}
