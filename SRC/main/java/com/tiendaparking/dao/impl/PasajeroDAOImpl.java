package com.tiendaparking.dao.impl;

import com.tiendaparking.dao.PasajeroDAO;
import com.tiendaparking.model.Pasajero;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class PasajeroDAOImpl implements PasajeroDAO {

    private final JdbcTemplate jdbcTemplate;

    public PasajeroDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Pasajero> rowMapper = (rs, rowNum) -> new Pasajero(
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("cedula")
    );

    @Override
    public List<Pasajero> obtenerTodos() {
        try {
            String sql = "SELECT nombre, apellido, cedula FROM pasajeros";
            return jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            System.err.println("Error al obtener pasajeros: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void guardar(Pasajero pasajero) {
        try {
            String sql = "INSERT INTO pasajeros (nombre, apellido, cedula) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, pasajero.getNombre(), pasajero.getApellido(), pasajero.getCedula());
        } catch (Exception e) {
            System.err.println("Error al guardar pasajero: " + e.getMessage());
        }
    }
}
