package com.tiendaparking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tiendaparking.model.Carro;
import com.tiendaparking.model.Chofer;
import com.tiendaparking.model.Motor;
import com.tiendaparking.model.Pasajero;
import com.tiendaparking.model.Usuario;
import com.tiendaparking.service.ParkingService;

@Controller
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    // Ruta fija: Dashboard Principal
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("totalChoferes", parkingService.getChoferes().size());
        model.addAttribute("totalCarros", parkingService.getCarros().size());
        model.addAttribute("totalUsuarios", parkingService.getUsuarios().size());
        model.addAttribute("totalMotores", parkingService.getMotores().size());
        model.addAttribute("totalPasajeros", parkingService.getPasajeros().size());
        model.addAttribute("apiStatus", parkingService.getApiModel().validarConexion() ? "Conectado" : "Desconectado");
        return "index";
    }

    // Ruta fija: Choferes
    @GetMapping("/choferes")
    public String choferes(Model model) {
        model.addAttribute("choferes", parkingService.getChoferes());
        model.addAttribute("nuevoChofer", new Chofer());
        return "choferes";
    }

    // Ruta dinámica: Detalle de Chofer por Cédula (@PathVariable)
    @GetMapping("/choferes/{cedula}")
    public String detalleChofer(@PathVariable("cedula") String cedula, Model model) {
        Chofer chofer = parkingService.getChoferPorCedula(cedula);
        model.addAttribute("chofer", chofer);
        model.addAttribute("cedulaBuscada", cedula);
        return "chofer-detalle";
    }

    @PostMapping("/choferes")
    public String guardarChofer(@ModelAttribute Chofer chofer) {
        parkingService.addChofer(chofer);
        return "redirect:/choferes";
    }

    // Ruta fija: Carros
    @GetMapping("/carros")
    public String carros(Model model) {
        model.addAttribute("carros", parkingService.getCarros());
        model.addAttribute("nuevoCarro", new Carro());
        model.addAttribute("motores", parkingService.getMotores());
        return "carros";
    }

    // Ruta dinámica: Detalle de Carro por Placa (@PathVariable)
    @GetMapping("/carros/{placa}")
    public String detalleCarro(@PathVariable("placa") String placa, Model model) {
        Carro carro = parkingService.getCarroPorPlaca(placa);
        model.addAttribute("carro", carro);
        model.addAttribute("placaBuscada", placa);
        return "carro-detalle";
    }

    @PostMapping("/carros")
    public String guardarCarro(@ModelAttribute Carro carro) {
        parkingService.addCarro(carro);
        return "redirect:/carros";
    }

    // Ruta fija: Usuarios
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", parkingService.getUsuarios());
        model.addAttribute("nuevoUsuario", new Usuario());
        return "usuarios";
    }

    // Ruta dinámica: Detalle de Usuario por ID (@PathVariable)
    @GetMapping("/usuarios/{id}")
    public String detalleUsuario(@PathVariable("id") Long id, Model model) {
        Usuario usuario = parkingService.getUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("idBuscado", id);
        return "usuario-detalle";
    }

    @PostMapping("/usuarios")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        parkingService.addUsuario(usuario);
        return "redirect:/usuarios";
    }

    // Ruta fija: Motores
    @GetMapping("/motores")
    public String motores(Model model) {
        model.addAttribute("motores", parkingService.getMotores());
        model.addAttribute("nuevoMotor", new Motor());
        return "motores";
    }

    @PostMapping("/motores")
    public String guardarMotor(@ModelAttribute Motor motor) {
        parkingService.addMotor(motor);
        return "redirect:/motores";
    }

    // Ruta fija: Pasajeros
    @GetMapping("/pasajeros")
    public String pasajeros(Model model) {
        model.addAttribute("pasajeros", parkingService.getPasajeros());
        model.addAttribute("nuevoPasajero", new Pasajero());
        return "pasajeros";
    }

    @PostMapping("/pasajeros")
    public String guardarPasajero(@ModelAttribute Pasajero pasajero) {
        parkingService.addPasajero(pasajero);
        return "redirect:/pasajeros";
    }
}
