package com.tiendaparking.controller;

import com.tiendaparking.model.*;
import com.tiendaparking.service.ParkingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingRestController {

    private final ParkingService parkingService;

    public ParkingRestController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    // Ruta fija API: Choferes
    @GetMapping("/choferes")
    public List<Chofer> getChoferes() {
        return parkingService.getChoferes();
    }

    // Ruta dinámica API: Chofer por cédula (@PathVariable)
    @GetMapping("/choferes/{cedula}")
    public Chofer getChoferPorCedula(@PathVariable("cedula") String cedula) {
        return parkingService.getChoferPorCedula(cedula);
    }

    @PostMapping("/choferes")
    public Chofer addChofer(@RequestBody Chofer chofer) {
        parkingService.addChofer(chofer);
        return chofer;
    }

    // Ruta fija API: Carros
    @GetMapping("/carros")
    public List<Carro> getCarros() {
        return parkingService.getCarros();
    }

    // Ruta dinámica API: Carro por placa (@PathVariable)
    @GetMapping("/carros/{placa}")
    public Carro getCarroPorPlaca(@PathVariable("placa") String placa) {
        return parkingService.getCarroPorPlaca(placa);
    }

    @PostMapping("/carros")
    public Carro addCarro(@RequestBody Carro carro) {
        parkingService.addCarro(carro);
        return carro;
    }

    // Ruta fija API: Usuarios
    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return parkingService.getUsuarios();
    }

    // Ruta dinámica API: Usuario por ID (@PathVariable)
    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioPorId(@PathVariable("id") Long id) {
        return parkingService.getUsuarioPorId(id);
    }

    @PostMapping("/usuarios")
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        parkingService.addUsuario(usuario);
        return usuario;
    }

    // Motores y Pasajeros
    @GetMapping("/motores")
    public List<Motor> getMotores() {
        return parkingService.getMotores();
    }

    @GetMapping("/pasajeros")
    public List<Pasajero> getPasajeros() {
        return parkingService.getPasajeros();
    }

    @GetMapping("/status")
    public ApiModel getStatus() {
        return parkingService.getApiModel();
    }
}
