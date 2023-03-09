package com.apineoris.demoneoris.dto;

import java.time.LocalDate;
import java.util.Date;

public class MovimientoDto {
    private long movimientoId;
    private String tipoMovimiento;
    private double valor;
    private double saldo;
    private LocalDate fecha;

    public MovimientoDto() {
    }

    public MovimientoDto(long movimientoId, String tipoMovimiento, double valor, double saldo, LocalDate fecha) {
        this.movimientoId = movimientoId;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
        this.fecha = fecha;
    }

    public long getMovimientoId() {
        return movimientoId;
    }

    public void setMovimientoId(long movimientoId) {
        this.movimientoId = movimientoId;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
