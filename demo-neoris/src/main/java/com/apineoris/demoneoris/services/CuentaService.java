package com.apineoris.demoneoris.services;

import com.apineoris.demoneoris.dto.CuentaDto;
import com.apineoris.demoneoris.entity.Cuenta;

import java.util.List;

public interface CuentaService {
    List<CuentaDto> getAllCuentas();

    CuentaDto saveNewCuenta(Cuenta cuenta);

    CuentaDto getCuentaById(long id);

    CuentaDto updateWholeCuentaById(long id);

    CuentaDto updatePartialCuentaById(long id);

    CuentaDto deleteCuentaById(long id);
}
