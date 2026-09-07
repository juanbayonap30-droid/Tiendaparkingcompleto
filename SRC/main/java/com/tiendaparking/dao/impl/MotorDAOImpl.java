package com.tiendaparking.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tiendaparking.dao.MotorDAO;
import com.tiendaparking.model.Motor;

@Repository
public class MotorDAOImpl implements MotorDAO {

    private final JdbcTemplate jdbcTemplate;

    public MotorDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Motor> rowMapper = (rs, rowNum) -> {
        Motor m = new Motor(
            rs.getLong("id"),
            rs.getString("tipo"),
            rs.getString("combustible"),
            rs.getString("potencia")
        );
        return m;
    };

    @Override
    public List<Motor> obtenerTodos() {
        try {
            // Asegurar que la columna id existe
            jdbcTemplate.execute(
                "ALTER TABLE motores ADD COLUMN IF NOT EXISTS id BIGINT AUTO_INCREMENT PRIMARY KEY FIRST"
            );
            String sql = "SELECT id, tipo, combustible, potencia FROM motores";
            return jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            System.err.println("Error al obtener motores: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void guardar(Motor motor) {
        try {
            String sql = "INSERT INTO motores (tipo, combustible, potencia) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, motor.getTipo(), motor.getCombustible(), motor.getPotencia());
        } catch (Exception e) {
            System.err.println("Error al guardar motor: " + e.getMessage());
        }
    }
}
