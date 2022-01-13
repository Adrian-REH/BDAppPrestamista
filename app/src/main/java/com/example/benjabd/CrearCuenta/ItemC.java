package com.example.benjabd.CrearCuenta;

public class ItemC {
private  String  Usuario, Contraseña,Id;

    public ItemC(String id, String usuario, String contraseña) {
        Id = id;
        Usuario = usuario;
        Contraseña = contraseña;

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }
}
