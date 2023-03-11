package com.apineoris.demoneoris.entity.enums;

public enum TipoMovimiento {
    DEPOSITO("DEPOSITO"),
    RETIRO("RETIRO");

    private final String descripcion;

    TipoMovimiento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
