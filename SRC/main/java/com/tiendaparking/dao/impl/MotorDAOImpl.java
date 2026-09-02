package com.tiendaparking.dao.impl;

import com.tiendaparking.dao.MotorDAO;
import com.tiendaparking.model.Motor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class MotorDAOImpl implements MotorDAO {

    private final JdbcTemplate jdbcTemplate;

    public MotorDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Motor> rowMapper = (rs, rowNum) -> new Motor(
            rs.getString("tipo"),
            rs.getString("combustible"),
            rs.getString("potencia")
    );

    @Override
    public List<Motor> obtenerTodos() {
        try {
            String sql = "SELECT tipo, combustible, potencia FROM motores";
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
