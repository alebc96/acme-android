package com.alebc96.acme_travel_app;

import java.io.Serializable;

public class ListElement implements Serializable {
    public String color;
    public String city_name;
    public String fecha_llegada;
    public String fecha_salida;
    public String precio;

    public String lugar_salida;
    public Boolean favorito = false;

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public int imgResource;

    public ListElement(String color, String city_name, String fecha_llegada, String fecha_salida, String precio, int imgResource, Boolean favorito, String lugar_salida) {
        this.color = color;
        this.city_name = city_name;
        this.fecha_llegada = fecha_llegada;
        this.fecha_salida = fecha_salida;
        this.precio = precio;
        this.imgResource = imgResource;
        this.favorito = favorito;
        this.lugar_salida = lugar_salida;
    }

    public String getLugar_salida() {
        return lugar_salida;
    }

    public void setLugar_salida(String lugar_salida) {
        this.lugar_salida = lugar_salida;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(String fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
