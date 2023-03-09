package com.apineoris.demoneoris.controller;

import com.apineoris.demoneoris.dto.MovimientoDto;
import com.apineoris.demoneoris.entity.Movimiento;
import com.apineoris.demoneoris.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService service;

    @GetMapping(value = "/")
    public List<MovimientoDto> getAllMovimientos() {
        return service.getAllMovimientos();
    }

    @GetMapping(value = "/{id}")
    public MovimientoDto getMovimientoById(@PathVariable(name = "id") long id) {
        return service.getMovimientoById(id);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public MovimientoDto saveNewMovimiento(@RequestBody Movimiento movimiento) {
        return service.saveNewMovimiento(movimiento);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public MovimientoDto updateWholeMovimiento(@RequestBody Movimiento movimiento, @PathVariable(name = "id") long id) {
        return service.updateWholeMovimientoById(id);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public MovimientoDto updatePartialMovimiento(@RequestBody Movimiento movimiento, @PathVariable(name = "id") long id) {
        return service.updatePartialMovimientoById(id);
    }

    @DeleteMapping(value = "/{id}")
    public MovimientoDto deleteById(@PathVariable(name = "id") long id){
        return service.deleteMovimientoById(id);
    }

}
