package com.tiendaparking.service;

import com.tiendaparking.dao.*;
import com.tiendaparking.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    private final ChoferDAO choferDAO;
    private final CarroDAO carroDAO;
    private final UsuarioDAO usuarioDAO;
    private final MotorDAO motorDAO;
    private final PasajeroDAO pasajeroDAO;
    private final ApiModel apiModel;

    public ParkingService(ChoferDAO choferDAO,
                          CarroDAO carroDAO,
                          UsuarioDAO usuarioDAO,
                          MotorDAO motorDAO,
                          PasajeroDAO pasajeroDAO) {
        this.choferDAO = choferDAO;
        this.carroDAO = carroDAO;
        this.usuarioDAO = usuarioDAO;
        this.motorDAO = motorDAO;
        this.pasajeroDAO = pasajeroDAO;
        this.apiModel = new ApiModel("1234", "admin", "https://api.tiendaparking.com/v1");
    }

    // Choferes
    public List<Chofer> getChoferes() {
        return choferDAO.obtenerTodos();
    }

    public Chofer getChoferPorCedula(String cedula) {
        return choferDAO.obtenerPorCedula(cedula);
    }

    public void addChofer(Chofer chofer) {
        choferDAO.guardar(chofer);
    }

    public void eliminarChofer(String cedula) {
        choferDAO.eliminar(cedula);
    }

    // Carros
    public List<Carro> getCarros() {
        return carroDAO.obtenerTodos();
    }

    public Carro getCarroPorPlaca(String placa) {
        return carroDAO.obtenerPorPlaca(placa);
    }

    public void addCarro(Carro carro) {
        carroDAO.guardar(carro);
    }

    // Usuarios
    public List<Usuario> getUsuarios() {
        return usuarioDAO.obtenerTodos();
    }

    public Usuario getUsuarioPorId(Long id) {
        return usuarioDAO.obtenerPorId(id);
    }

    public void addUsuario(Usuario usuario) {
        usuarioDAO.guardar(usuario);
    }

    // Motores
    public List<Motor> getMotores() {
        return motorDAO.obtenerTodos();
    }

    public void addMotor(Motor motor) {
        motorDAO.guardar(motor);
    }

    // Pasajeros
    public List<Pasajero> getPasajeros() {
        return pasajeroDAO.obtenerTodos();
    }

    public void addPasajero(Pasajero pasajero) {
        pasajeroDAO.guardar(pasajero);
    }

    // API Model
    public ApiModel getApiModel() {
        return apiModel;
    }
}
