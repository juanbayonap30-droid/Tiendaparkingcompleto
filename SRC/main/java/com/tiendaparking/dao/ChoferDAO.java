package com.tiendaparking.dao;

import com.tiendaparking.model.Chofer;
import java.util.List;

public interface ChoferDAO {
    List<Chofer> obtenerTodos();
    Chofer obtenerPorCedula(String cedula);
    void guardar(Chofer chofer);
    void actualizar(Chofer chofer);
    void eliminar(String cedula);
}
