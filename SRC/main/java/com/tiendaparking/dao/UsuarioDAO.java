package com.tiendaparking.dao;

import com.tiendaparking.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    List<Usuario> obtenerTodos();
    Usuario obtenerPorId(Long id);
    void guardar(Usuario usuario);
    void actualizar(Usuario usuario);
    void eliminar(Long id);
}
