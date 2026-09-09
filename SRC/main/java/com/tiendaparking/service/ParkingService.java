package com.tiendaparking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tiendaparking.dao.CarroDAO;
import com.tiendaparking.dao.ChoferDAO;
import com.tiendaparking.dao.MotorDAO;
import com.tiendaparking.dao.PasajeroDAO;
import com.tiendaparking.dao.UsuarioDAO;
import com.tiendaparking.model.ApiModel;
import com.tiendaparking.model.Carro;
import com.tiendaparking.model.Chofer;
import com.tiendaparking.model.Motor;
import com.tiendaparking.model.Pasajero;
import com.tiendaparking.model.Usuario;

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

    public void actualizarChofer(Chofer chofer) {
        choferDAO.actualizar(chofer);
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

    public void actualizarCarro(Carro carro) {
        carroDAO.actualizar(carro);
    }

    public void eliminarCarro(String placa) {
        carroDAO.eliminar(placa);
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

    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.actualizar(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioDAO.eliminar(id);
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

    public Pasajero getPasajeroPorCedula(String cedula) {
        return pasajeroDAO.obtenerPorCedula(cedula);
    }

    public void addPasajero(Pasajero pasajero) {
        pasajeroDAO.guardar(pasajero);
    }

    public void actualizarPasajero(Pasajero pasajero) {
        pasajeroDAO.actualizar(pasajero);
    }

    public void eliminarPasajero(String cedula) {
        pasajeroDAO.eliminar(cedula);
    }

    // API Model
    public ApiModel getApiModel() {
        return apiModel;
    }
}
