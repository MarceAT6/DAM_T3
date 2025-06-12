package com.example.dam_t3.Modelo;

public class Sede {
    private int idSede;
    private String nombreSede;
    private String direccion;
    private double latitud;
    private double longitud;

    public Sede(int idSede, String nombreSede, String direccion, double latitud, double longitud) {
        this.idSede = idSede;
        this.nombreSede = nombreSede;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Sede(String nombreSede, String direccion, double latitud, double longitud) {
        this.nombreSede = nombreSede;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
