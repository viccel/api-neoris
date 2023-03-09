package com.apineoris.demoneoris.services;

import com.apineoris.demoneoris.dto.CuentaDto;
import com.apineoris.demoneoris.dto.MovimientoDto;
import com.apineoris.demoneoris.entity.Cuenta;
import com.apineoris.demoneoris.entity.Movimiento;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface MovimientoService {
    List<MovimientoDto> getAllMovimientos();

    MovimientoDto saveNewMovimiento(Movimiento movimiento);

    MovimientoDto getMovimientoById(long id);

    MovimientoDto updateWholeMovimientoById(long id);

    MovimientoDto updatePartialMovimientoById(long id);

    MovimientoDto deleteMovimientoById(long id);

    List<MovimientoDto> getReporte(LocalDate fechaIni, LocalDate fechaFin);

}
