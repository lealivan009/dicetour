package dicetour.controllers;

import dicetour.dto.NuevaPersona;
import dicetour.dto.NuevoRol;
import dicetour.entities.Cuenta;
import dicetour.entities.Persona;
import dicetour.entities.Rol;
import dicetour.services.PersonaServiceImpl;
import dicetour.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(path = "persona")
public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl> { //el extends es importante porque me permite ver del navegador en formato json

    @Autowired
    RolService rolService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
        <------------------>
        REGISTRAR UN USUARIO
        <------------------>
     */
    @GetMapping("/signup")
    public String registrarConDTO(Model model) {
        try {
            NuevaPersona nuevaPersona = new NuevaPersona();
            model.addAttribute("nuevaPersona", nuevaPersona);
            return "views/crud/persona/signUp";
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "views/error";
        }
    }

    @PostMapping("/guardarPersona")
    public String registroConDTO(@Valid @ModelAttribute("nuevaPersona") NuevaPersona nuevaPersona, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return "views/crud/persona/signUp";
        }

        Persona persona = new Persona();
        Cuenta cuenta = new Cuenta();

        cuenta.setEmail(nuevaPersona.getEmail().toLowerCase());
        cuenta.setPassword(passwordEncoder.encode(nuevaPersona.getPassword()));
        cuenta.setVigente(true);

        persona.setNombre(nuevaPersona.getNombre().toUpperCase());
        persona.setApellido(nuevaPersona.getApellido().toUpperCase());
        persona.setDni(nuevaPersona.getDni());
        persona.setFechaNacimiento(nuevaPersona.getFechaNacimiento());
        persona.setCiudad(nuevaPersona.getCiudad().toUpperCase());
        persona.setProvincia(nuevaPersona.getProvincia().toUpperCase());
        persona.setDomicilio(nuevaPersona.getDomicilio().toUpperCase());
        persona.setCodigoPostal(nuevaPersona.getCodigoPostal());

        persona.setVigente(true);
        persona.setCuenta(cuenta);

        servicio.save(persona);
        return "redirect:/cuenta/signin?exito";
    }


    /*
        <-------------------------->
        PANTALLA DE CRUD DE PERSONAS
        <-------------------------->
     */
    @GetMapping("/crudPersonas")
    public String crudPersonas(Model model) throws Exception {
        try {
            model.addAttribute("personas", servicio.findAll());
            return "views/crud/persona/tablaPersona";
        } catch (Exception e) {
            return "error";
        }
    }

    /*
        <------------------>
        EDITAR PERSONA
        <------------------>
     */
    @GetMapping("/editar/{id}")
    public String editarPersona(Model model, @PathVariable("id") Long id) throws Exception {

        Persona persona = servicio.findById(id);

        List<Rol> roles = rolService.findRolesVigentes();

        model.addAttribute("persona", persona);

        // En esta linea de código estamos indicando el nuevo formato que queremos para nuestra fecha.
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String fechaTexto = formatter.format(persona.getFechaNacimiento().getTime());

        //Voy a mandar la fecha asi, pero tengo que utilizar un dto en tipo string
        model.addAttribute("fechaNacimiento", fechaTexto);

        return "views/crud/persona/editarPersona";
    }

    @PostMapping("/actualizarPersona/{id}")
    public String actualizarPersona(@PathVariable long id, @Valid @ModelAttribute("persona") Persona persona, BindingResult result, Model modelo) throws Exception {
        if (result.hasErrors()) {
            return "views/crud/persona/editarPersona";
        }
        Persona personaExistente = servicio.findById(id);

        personaExistente.setDomicilio(persona.getDomicilio());
        personaExistente.setCiudad(persona.getCiudad());
        personaExistente.setProvincia(persona.getProvincia());
        personaExistente.setCodigoPostal(persona.getCodigoPostal());
        personaExistente.getCuenta().setEmail(persona.getCuenta().getEmail());
        personaExistente.getCuenta().setPassword(passwordEncoder.encode(persona.getCuenta().getPassword()));
        //personaExistente.getCuenta().setRol(persona.getCuenta().getRol());
        servicio.update(id, personaExistente);
        return "redirect:/persona/crudPersonas";
    }

    /*
        <------------------->
        AGREGAR ROL A PERSONA
        <------------------->
     */
    @GetMapping("/agregarRol/{id}")
    public String agregarRol(Model model, @PathVariable("id") Long id) throws Exception {
        Persona persona = servicio.findById(id);
        List<Rol> roles = rolService.findAll();

        //utilizo la persona para extraer el id
        model.addAttribute("persona", persona);

        NuevoRol nuevoRol = new NuevoRol();
        model.addAttribute("nuevoRol", nuevoRol);


        model.addAttribute("roles", roles);
        return "views/crud/persona/agregarRol";
    }

    @PostMapping("/actualizarRol/{idPersona}")
    public String actualizarRol(@PathVariable("idPersona") long idPersona, @ModelAttribute("nuevoRol") NuevoRol nuevoRol) throws Exception {
        Persona personaExistente = servicio.findById(idPersona);

        if(personaExistente.getCuenta().getRoles().contains(rolService.findById(nuevoRol.getRol().getId()))){
            throw new Exception("Este rol ya fue ingresado antes");
        }else{
            personaExistente.getCuenta().getRoles().add(rolService.findById(nuevoRol.getRol().getId()));
            servicio.save(personaExistente);
            return "redirect:/persona/crudPersonas";
        }

    }
    /*
        <------------------->
        QUITAR ROL A PERSONA
        <------------------->
     */
    @GetMapping("/confirmarQuitarRol/{idPersona}/{idRol}")
    public String confirmarQuitarRol(Model model,@PathVariable("idPersona") Long idPersona,@PathVariable("idRol") Long idRol)throws Exception{
        Persona persona=servicio.findById(idPersona);
        Rol rol=rolService.findById(idRol);

        model.addAttribute("persona", persona);
        model.addAttribute("rol", rol);

        return "views/crud/persona/quitarRol";
    }

    @GetMapping("/quitarRol/{idPersona}/{idRol}")
    public String quitarRol(@PathVariable("idPersona") Long idPersona,@PathVariable("idRol") Long idRol) throws Exception{
        Persona persona=servicio.findById(idPersona);
        Rol rol=rolService.findById(idRol);

        persona.getCuenta().getRoles().remove(rol);
        servicio.update(idPersona,persona);

        return "redirect:/persona/editar/"+idPersona;
    }


    /*
        <------------------->
        DAR DE BAJA A PERSONA
        <------------------->
     */
    @GetMapping("/confirmarBajaPersona/{id}")
    public String confirmarEliminar(@PathVariable long id, Model model) {
        try {
            model.addAttribute("persona", servicio.findById(id));
            return "views/crud/persona/darDeBajaPersona";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/darDeBajaPersona/{id}")
    public String darDeBajaPersona(@PathVariable("id") Long id) throws Exception {
        try {
            Persona persona = servicio.findById(id);
            persona.setVigente(false);
            persona.getCuenta().setVigente(false);
            servicio.update(id, persona);
            return "redirect:/persona/crudPersonas";
        } catch (Exception e) {
            return "error";
        }
    }

    /*
        <------------------->
        DAR DE ALTA A PERSONA
        <------------------->
     */
    @GetMapping("/confirmarAltaPersona/{id}")
    public String confirmarAlta(@PathVariable long id, Model model) {
        try {
            model.addAttribute("persona", servicio.findById(id));
            return "views/crud/persona/darDeAltaPersona";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/darDeAltaPersona/{id}")
    public String darDeAltaRol(@PathVariable("id") Long id) throws Exception {
        try {
            Persona persona = servicio.findById(id);
            persona.setVigente(true);
            persona.getCuenta().setVigente(true);
            servicio.update(id, persona);
            return "redirect:/persona/crudPersonas";
        } catch (Exception e) {
            return "error";
        }
    }
}


