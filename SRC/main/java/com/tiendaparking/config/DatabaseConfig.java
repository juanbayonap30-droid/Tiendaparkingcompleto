package com.tiendaparking.config;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    private final JdbcTemplate jdbc;

    public DatabaseConfig(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @PostConstruct
    public void inicializarTablasMySQL() {
        try {
            jdbc.execute("CREATE TABLE IF NOT EXISTS choferes (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(100) NOT NULL, " +
                    "apellido VARCHAR(100) NOT NULL, " +
                    "cedula VARCHAR(20) NOT NULL)");

            jdbc.execute("CREATE TABLE IF NOT EXISTS motores (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "tipo VARCHAR(50) NOT NULL, " +
                    "combustible VARCHAR(50) NOT NULL, " +
                    "potencia VARCHAR(50) NOT NULL)");

            jdbc.execute("CREATE TABLE IF NOT EXISTS carros (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "marca VARCHAR(100) NOT NULL, " +
                    "modelo VARCHAR(100) NOT NULL, " +
                    "placa VARCHAR(20) NOT NULL, " +
                    "motor_id INT NULL)");

            try {
                jdbc.execute("ALTER TABLE carros ADD COLUMN motor_id INT NULL");
            } catch (Exception ignored) {}

            jdbc.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL, " +
                    "rol VARCHAR(50) NOT NULL)");

            jdbc.execute("CREATE TABLE IF NOT EXISTS pasajeros (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(100) NOT NULL, " +
                    "apellido VARCHAR(100) NOT NULL, " +
                    "cedula VARCHAR(20) NOT NULL)");

            System.out.println("[DB] Todas las tablas verificadas/creadas exitosamente.");
        } catch (Exception e) {
            System.err.println("[DB] ERROR al inicializar tablas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
