package com.tiendaparking.dao.impl;

import com.tiendaparking.dao.UsuarioDAO;
import com.tiendaparking.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Usuario> rowMapper = (rs, rowNum) -> new Usuario(
            rs.getLong("id"),
            rs.getString("nombre"),
            rs.getString("email"),
            rs.getString("rol")
    );

    @Override
    public List<Usuario> obtenerTodos() {
        try {
            String sql = "SELECT id, nombre, email, rol FROM usuarios";
            return jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        try {
            String sql = "SELECT id, nombre, email, rol FROM usuarios WHERE id = ?";
            List<Usuario> usuarios = jdbcTemplate.query(sql, rowMapper, id);
            return usuarios.stream().findFirst().orElse(null);
        } catch (Exception e) {
            System.err.println("Error al obtener usuario por ID: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuarios (nombre, email, rol) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getRol());
        } catch (Exception e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Usuario usuario) {
        try {
            String sql = "UPDATE usuarios SET nombre = ?, email = ?, rol = ? WHERE id = ?";
            jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getRol(), usuario.getId());
        } catch (Exception e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            String sql = "DELETE FROM usuarios WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
}
