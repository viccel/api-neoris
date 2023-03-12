package com.apineoris.demoneoris.services;

import com.apineoris.demoneoris.dto.MovimientoDto;
import com.apineoris.demoneoris.entity.Movimiento;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoService {
    List<MovimientoDto> getAllMovimientos();

    MovimientoDto saveNewMovimiento(Movimiento movimiento);

    MovimientoDto getMovimientoById(long id);

    MovimientoDto updateWholeMovimientoById(long id);

    MovimientoDto updatePartialMovimientoById(long id);

    MovimientoDto deleteMovimientoById(long id);

    List<MovimientoDto> getReporte(long id, LocalDate fechaIni, LocalDate fechaFin);

}
