package com.apineoris.demoneoris.dto;

public class CuentaDto {
    //Numero Cuenta
    private long numeroCuenta;
    //Tipo
    private String tipo;
    //Saldo Inicial
    private double saldoInicial;
    //Estado
    private String estado;

    private String nombreCliente;


    public CuentaDto(long numeroCuenta, String tipo, double saldoInicial, String estado, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
    }

    public CuentaDto(){}

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
