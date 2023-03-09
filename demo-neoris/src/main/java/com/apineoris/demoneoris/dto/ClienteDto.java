package com.apineoris.demoneoris.dto;

import com.apineoris.demoneoris.entity.Cliente;

public class ClienteDto {
    /*
    *     "nombre": "Victor Rodriguez",
    "direccion": "Cuernavaca Morelos, México",
    "telefono": "1234567890",
    "contraseña": "123",
    "estado": "True"
    * */
    private String nombre;
    private String direccion;
    private long clienteId;
    private String telefono;
    private String constraseña;
    private String estado;

    public ClienteDto(Cliente cliente){
        this.clienteId = cliente.getIdCliente();
        this.constraseña = cliente.getContrasena();
        this.nombre = cliente.getNombre();
        this.direccion = cliente.getDireccion();
        this.telefono = cliente.getTelefono();
        this.estado = cliente.getEstado();
    }

    public ClienteDto(String nombre, String direccion, long clienteId, String telefono, String constraseña, String estado) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.clienteId = clienteId;
        this.telefono = telefono;
        this.constraseña = constraseña;
        this.estado = estado;
    }

    public ClienteDto() {
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public String getConstraseña() {
        return constraseña;
    }

    public void setConstraseña(String constraseña) {
        this.constraseña = constraseña;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
