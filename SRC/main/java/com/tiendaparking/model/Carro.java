package com.tiendaparking.model;

public class Carro {
    private String marca;
    private String modelo;
    private String placa;
    private Long motorId;

    public Carro() {
    }

    public Carro(String marca, String modelo, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Long getMotorId() {
        return motorId;
    }

    public void setMotorId(Long motorId) {
        this.motorId = motorId;
    }

    @Override
    public String toString() {
        return "Carro [Marca: " + marca + ", Modelo: " + modelo + ", Placa: " + placa + ", MotorId: " + motorId + "]";
    }
}
