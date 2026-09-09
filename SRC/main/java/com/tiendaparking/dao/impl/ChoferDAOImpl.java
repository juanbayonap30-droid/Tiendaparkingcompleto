package com.tiendaparking.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tiendaparking.dao.ChoferDAO;
import com.tiendaparking.model.Chofer;

@Repository
public class ChoferDAOImpl implements ChoferDAO {

    private final JdbcTemplate jdbcTemplate;

    public ChoferDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Chofer> rowMapper = (rs, rowNum) -> new Chofer(
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("cedula")
    );

    @Override
    public List<Chofer> obtenerTodos() {
        try {
            String sql = "SELECT nombre, apellido, cedula FROM choferes";
            return jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            System.err.println("Error al obtener choferes: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Chofer obtenerPorCedula(String cedula) {
        try {
            String sql = "SELECT nombre, apellido, cedula FROM choferes WHERE cedula = ?";
            List<Chofer> choferes = jdbcTemplate.query(sql, rowMapper, cedula);
            return choferes.stream().findFirst().orElse(null);
        } catch (Exception e) {
            System.err.println("Error al obtener chofer por cédula: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void guardar(Chofer chofer) {
        try {
            String sql = "INSERT INTO choferes (nombre, apellido, cedula) VALUES (?, ?, ?)";
            int rows = jdbcTemplate.update(sql, chofer.getNombre(), chofer.getApellido(), chofer.getCedula());
            System.out.println("[ChoferDAO] Filas insertadas: " + rows + " | " + chofer);
        } catch (Exception e) {
            System.err.println("[ChoferDAO] ERROR al guardar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Chofer chofer) {
        try {
            String sql = "UPDATE choferes SET nombre = ?, apellido = ? WHERE cedula = ?";
            int rows = jdbcTemplate.update(sql, chofer.getNombre(), chofer.getApellido(), chofer.getCedula());
            System.out.println("[ChoferDAO] Filas actualizadas: " + rows + " | cedula=" + chofer.getCedula());
        } catch (Exception e) {
            System.err.println("[ChoferDAO] ERROR al actualizar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String cedula) {
        try {
            String sql = "DELETE FROM choferes WHERE cedula = ?";
            jdbcTemplate.update(sql, cedula);
        } catch (Exception e) {
            System.err.println("Error al eliminar chofer: " + e.getMessage());
        }
    }
}
