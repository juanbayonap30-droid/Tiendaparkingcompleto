package com.tiendaparking.model;

public class Motor {
    private Long id;
    private String tipo;
    private String combustible;
    private String potencia;

    public Motor() {
    }

    public Motor(Long id, String tipo, String combustible, String potencia) {
        this.id = id;
        this.tipo = tipo;
        this.combustible = combustible;
        this.potencia = potencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
