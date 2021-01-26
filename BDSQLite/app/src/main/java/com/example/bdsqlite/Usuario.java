package com.example.bdsqlite;

public class Usuario {

    private Integer id;
    private String nombre;
    private String domicilio;
    private String email;
    private String password;

    /*public Usuario(Integer id, String nombre, String domicilio, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.email = email;
        this.password = password;
    }*/

    public Usuario() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';



}
}
