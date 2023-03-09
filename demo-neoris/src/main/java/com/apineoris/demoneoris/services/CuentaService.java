package com.apineoris.demoneoris.services;

import com.apineoris.demoneoris.dto.ClienteDto;
import com.apineoris.demoneoris.dto.CuentaDto;
import com.apineoris.demoneoris.entity.Cliente;
import com.apineoris.demoneoris.entity.Cuenta;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CuentaService {
    List<CuentaDto> getAllCuentas();

    CuentaDto saveNewCliente(Cuenta cuenta);

    CuentaDto getCuentaById(long id);

    CuentaDto updateWholeCuentaById(long id);

    CuentaDto updatePartialCuentaById(long id);

    CuentaDto deleteCuentaById(long id);
}
