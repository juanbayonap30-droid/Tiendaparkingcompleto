package com.tiendaparking.dao;

import java.util.List;

import com.tiendaparking.model.Pasajero;

public interface PasajeroDAO {
    List<Pasajero> obtenerTodos();
    Pasajero obtenerPorCedula(String cedula);
    void guardar(Pasajero pasajero);
    void actualizar(Pasajero pasajero);
    void eliminar(String cedula);
}
