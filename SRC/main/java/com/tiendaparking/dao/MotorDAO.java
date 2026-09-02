package com.tiendaparking.dao;

import com.tiendaparking.model.Motor;
import java.util.List;

public interface MotorDAO {
    List<Motor> obtenerTodos();
    void guardar(Motor motor);
}
