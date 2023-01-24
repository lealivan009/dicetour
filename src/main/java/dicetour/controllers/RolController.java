package dicetour.controllers;

import dicetour.entities.Colectivo;
import dicetour.entities.Persona;
import dicetour.entities.Rol;
import dicetour.services.PersonaService;
import dicetour.services.PersonaServiceImpl;
import dicetour.services.RolService;
import dicetour.services.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "rol")
public class RolController extends BaseControllerImpl<Rol, RolServiceImpl> {

    /*
        <-------------------------->
        LISTA DE ROLES
        <-------------------------->
     */
    @GetMapping("listaRoles")
    public String listaRoles(Model model) throws Exception {
        model.addAttribute("roles", servicio.findAll());
        return "views/crud/rol/listaRoles";
    }

    /*
        <------------------>
        AGREGAR UN NUEVO ROL
        <------------------>
    */
    @GetMapping("/nuevoRol")
    public String nuevoRol(Model model) {
        Rol rol = new Rol();
        model.addAttribute("nuevoRol", rol);
        return "views/crud/rol/nuevoRol";
    }

    @PostMapping("/guardarRol")
    public String guardarRol(@Valid @ModelAttribute("nuevoRol") Rol rol, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "views/crud/rol/nuevoRol";
        }
        rol.setVigente(true);
        servicio.save(rol);
        return "redirect:/rol/listaRoles";
    }

    /*
        <------------------->
        DAR DE BAJA A UN ROL
        <------------------->
    */
    @GetMapping("/confirmarBajaRol/{id}")
    public String confirmarBajaRol(@PathVariable long id, Model model) {
        try {
            model.addAttribute("rol", servicio.findById(id));
            return "views/crud/rol/darDeBajaRol";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/darDeBajaRol/{id}")
    public String darDeBajaRol(@PathVariable("id") Long id) throws Exception {
        try {
            Rol rol = servicio.findById(id);
            rol.setVigente(false);
            servicio.update(id, rol);
            return "redirect:/rol/listaRoles";
        } catch (Exception e) {
            return "error";
        }
    }

    /*
        <-------------------------->
        DAR DE ALTA A UN ROL
        <-------------------------->
    */
    @GetMapping("/confirmarAltaRol/{id}")
    public String confirmarAlta(@PathVariable long id, Model model) {
        try {
            model.addAttribute("rol", servicio.findById(id));
            return "views/crud/rol/darDeAltaRol";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/darDeAltaRol/{id}")
    public String darDeAltaRol(@PathVariable("id") Long id) throws Exception {
        try {
            Rol rol = servicio.findById(id);
            rol.setVigente(true);
            servicio.update(id, rol);
            return "redirect:/rol/listaRoles";
        } catch (Exception e) {
            return "error";
        }
    }

    /*
        <-------------------------->
        ELIMINAR UN ROL
        <-------------------------->
     */
    @GetMapping("/confirmarEliminarRol/{id}")
    public String confirmarEliminar(@PathVariable long id, Model model) {
        try {
            model.addAttribute("rol", servicio.findById(id));
            return "views/crud/rol/eliminarRol";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/eliminarRol/{id}")
    public String eliminarRol(@PathVariable("id") Long id) throws Exception{
        try{
            Rol rol=servicio.findById(id);
            servicio.delete(id);
            return "redirect:/rol/listaRoles";
        }catch (Exception e){
            return "error";
        }
    }

}