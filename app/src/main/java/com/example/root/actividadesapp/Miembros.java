package com.example.root.actividadesapp;

import java.io.Serializable;

/**
 * Created by root on 5/10/17.
 */

public class Miembros implements Serializable {

    protected String nombre, password, fechaAlta;
    protected boolean tipoUsuario;  //true administrador | false usuario

    public Miembros(String nombreE, String passwordE, String fechaAltaE, boolean tipoUsuarioE) {
        nombre=nombreE;
        password=passwordE;
        fechaAlta=fechaAltaE;
        tipoUsuario=tipoUsuarioE;
    }///Constructor

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isTipoUsuario() {
        return tipoUsuario;
    }//true administrador | false usuario

    public void setTipoUsuario(boolean tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }//true administrador | false usuario

}//Fin de miembros
