package com.apineoris.demoneoris.entity.enums;

public enum TipoCuenta {
    AHORRO("AHORRO"),
    DEBITO("DEBITO");
    private final String descripcion;

    TipoCuenta(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }


}
