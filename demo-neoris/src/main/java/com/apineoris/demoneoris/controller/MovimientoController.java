package com.apineoris.demoneoris.controller;

import com.apineoris.demoneoris.dto.MovimientoDto;
import com.apineoris.demoneoris.entity.Movimiento;
import com.apineoris.demoneoris.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<MovimientoDto> getMovimientoById(@PathVariable(name = "id") long id) {

        MovimientoDto movimientoDto = service.getMovimientoById(id);

        if (movimientoDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(movimientoDto);
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
    public ResponseEntity<MovimientoDto> deleteById(@PathVariable(name = "id") long id) {

        MovimientoDto movimientoDto = service.deleteMovimientoById(id);

        if (movimientoDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(movimientoDto);
    }

    @GetMapping("/report")
    public ResponseEntity<List<MovimientoDto>> getReporte(@RequestParam(value = "inicio")
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate inicio,
                                                          @RequestParam(value = "fin")
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fin) {
        List<MovimientoDto> reporte = service.getReporte(inicio, fin);
        return ResponseEntity.ok(reporte);
    }

}
