package com.example.reto2moviles;

import java.io.Serializable;
import java.util.List;

public class PeticionRespuesta implements Serializable {

    private int tipo;
    /*
     * tipo = 1 -> se pide una lista
     * tipo = 2 -> se pide una cantidad
     * tipo = 3 -> se pide un resultado único
     * */
    private String hql; //El hql que envía el cliente
    //El servidor devuelve uno de estos tres
    private List lista;
    private long cantidad;
    private Object objeto;

    public PeticionRespuesta() {

    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getHql() {
        return hql;
    }

    public void setHql(String hql) {
        this.hql = hql;
    }

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }


    }

