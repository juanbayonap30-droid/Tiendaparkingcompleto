package com.tiendaparking.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tienda_parking?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void inicializarTablasMySQL() {
        try {
            JdbcTemplate jdbcTemplate = jdbcTemplate(dataSource());

            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS choferes (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(100) NOT NULL, " +
                    "apellido VARCHAR(100) NOT NULL, " +
                    "cedula VARCHAR(20) NOT NULL)");

            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS motores (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "tipo VARCHAR(50) NOT NULL, " +
                    "combustible VARCHAR(50) NOT NULL, " +
                    "potencia VARCHAR(50) NOT NULL)");

            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS carros (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "marca VARCHAR(100) NOT NULL, " +
                    "modelo VARCHAR(100) NOT NULL, " +
                    "placa VARCHAR(20) NOT NULL, " +
                    "motor_id INT NULL)");

            // Agregar motor_id si la tabla carros ya existía sin esa columna
            try {
                jdbcTemplate.execute("ALTER TABLE carros ADD COLUMN motor_id INT NULL");
            } catch (Exception ignored) {}

            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL, " +
                    "rol VARCHAR(50) NOT NULL)");

            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS pasajeros (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(100) NOT NULL, " +
                    "apellido VARCHAR(100) NOT NULL, " +
                    "cedula VARCHAR(20) NOT NULL)");

            System.out.println("Tablas de MySQL verificadas/creadas exitosamente.");
        } catch (Exception e) {
            System.err.println("Aviso al verificar tablas MySQL: " + e.getMessage());
        }
    }
}
