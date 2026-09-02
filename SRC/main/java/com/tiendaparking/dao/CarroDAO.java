package com.tiendaparking.dao;

import com.tiendaparking.model.Carro;
import java.util.List;

public interface CarroDAO {
    List<Carro> obtenerTodos();
    Carro obtenerPorPlaca(String placa);
    void guardar(Carro carro);
    void actualizar(Carro carro);
    void eliminar(String placa);
}
