package com.apineoris.demoneoris.entity;

import com.apineoris.demoneoris.dto.MovimientoDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private long movimientoId;
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    @Column(name = "valor")
    private double valor;
    @Column(name = "saldo")
    private double saldo;
    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;*/

    public Movimiento(long movimientoId, String tipoMovimiento, double valor, double saldo, LocalDate fecha) {
        this.movimientoId = movimientoId;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
        this.fecha = fecha;
    }

    public Movimiento() {
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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

    public MovimientoDto toDto() {
        return new MovimientoDto(getMovimientoId(), getTipoMovimiento(), getValor(), getSaldo(), getFecha());
    }
}
