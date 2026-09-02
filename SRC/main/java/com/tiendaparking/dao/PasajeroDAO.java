package com.tiendaparking.dao;

import com.tiendaparking.model.Pasajero;
import java.util.List;

public interface PasajeroDAO {
    List<Pasajero> obtenerTodos();
    void guardar(Pasajero pasajero);
}
