package com.tiendaparking.model;

public class Motor {
    private String tipo;
    private String combustible;
    private String potencia;

    public Motor() {
    }

    public Motor(String tipo, String combustible, String potencia) {
        this.tipo = tipo;
        this.combustible = combustible;
        this.potencia = potencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    @Override
    public String toString() {
        return "Motor [Tipo: " + tipo + ", Combustible: " + combustible + ", Potencia: " + potencia + "]";
    }
}
