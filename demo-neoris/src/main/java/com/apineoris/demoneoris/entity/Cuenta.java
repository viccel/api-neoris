package com.apineoris.demoneoris.entity;

import com.apineoris.demoneoris.dto.CuentaDto;
import com.apineoris.demoneoris.entity.enums.TipoCuenta;

import javax.persistence.*;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_cuenta")
    private long numeroCuenta;
    @Column(name = "tipo_cuenta")
    private TipoCuenta tipoCuenta;
    @Column(name = "saldo_inicial")
    private double saldoInicial;
    @Column(name = "saldo_actual")
    private double saldoActual;
    @Column(name = "estado")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Cuenta(TipoCuenta tipoCuenta, double saldoInicial, String estado) {
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
    }

    public Cuenta() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CuentaDto toDto(String nombreCliente) {
        return new CuentaDto(getNumeroCuenta(), getTipoCuenta().getDescripcion(), getSaldoInicial(), getEstado(), nombreCliente);
    }
}
