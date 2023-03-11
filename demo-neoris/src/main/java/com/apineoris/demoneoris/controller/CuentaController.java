package com.apineoris.demoneoris.controller;

import com.apineoris.demoneoris.dto.CuentaDto;
import com.apineoris.demoneoris.entity.Cuenta;
import com.apineoris.demoneoris.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService service;

    @GetMapping("/")
    public List<CuentaDto> getAllCuentas() {
        return service.getAllCuentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDto> getCuentaById(@PathVariable(name = "id") long id) {

        CuentaDto cuentaDto = service.getCuentaById(id);

        if (cuentaDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cuentaDto);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<CuentaDto> saveNewCuenta(@RequestBody Cuenta cuenta) {
        CuentaDto cuentaDto = service.saveNewCuenta(cuenta);

        if (cuentaDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cuentaDto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public CuentaDto updateWholeCuenta(@RequestBody Cuenta cuenta, @PathVariable(name = "id") long id) {
        return service.updateWholeCuentaById(id);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public CuentaDto updatePartialCuenta(@RequestBody Cuenta cuenta, @PathVariable(name = "id") long id) {
        return service.updatePartialCuentaById(id);
    }

    @DeleteMapping(value = "/{id}")
    public CuentaDto deleteCuentaById(@PathVariable(name = "id") long id) {
        return service.deleteCuentaById(id);
    }

}
