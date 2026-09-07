package com.tiendaparking.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tiendaparking.dao.CarroDAO;
import com.tiendaparking.model.Carro;

@Repository
public class CarroDAOImpl implements CarroDAO {

    private final JdbcTemplate jdbcTemplate;

    public CarroDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Carro> rowMapper = (rs, rowNum) -> {
        Carro c = new Carro(
            rs.getString("marca"),
            rs.getString("modelo"),
            rs.getString("placa")
        );
        long motorId = rs.getLong("motor_id");
        if (!rs.wasNull()) {
            c.setMotorId(motorId);
        }
        return c;
    };

    @Override
    public List<Carro> obtenerTodos() {
        try {
            jdbcTemplate.execute(
                "ALTER TABLE carros ADD COLUMN IF NOT EXISTS motor_id BIGINT NULL"
            );
            String sql = "SELECT marca, modelo, placa, motor_id FROM carros";
            return jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            System.err.println("Error al obtener carros: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Carro obtenerPorPlaca(String placa) {
        try {
            String sql = "SELECT marca, modelo, placa, motor_id FROM carros WHERE placa = ?";
            List<Carro> carros = jdbcTemplate.query(sql, rowMapper, placa);
            return carros.stream().findFirst().orElse(null);
        } catch (Exception e) {
            System.err.println("Error al obtener carro por placa: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void guardar(Carro carro) {
        try {
            String sql = "INSERT INTO carros (marca, modelo, placa, motor_id) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, carro.getMarca(), carro.getModelo(), carro.getPlaca(), carro.getMotorId());
        } catch (Exception e) {
            System.err.println("Error al guardar carro: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Carro carro) {
        try {
            String sql = "UPDATE carros SET marca = ?, modelo = ?, motor_id = ? WHERE placa = ?";
            jdbcTemplate.update(sql, carro.getMarca(), carro.getModelo(), carro.getMotorId(), carro.getPlaca());
        } catch (Exception e) {
            System.err.println("Error al actualizar carro: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String placa) {
        try {
            String sql = "DELETE FROM carros WHERE placa = ?";
            jdbcTemplate.update(sql, placa);
        } catch (Exception e) {
            System.err.println("Error al eliminar carro: " + e.getMessage());
        }
    }
}
