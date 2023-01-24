package dicetour.controllers;

import dicetour.entities.Referencia;
import dicetour.entities.Rol;
import dicetour.entities.TipoColectivo;
import dicetour.services.ReferenciaServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "referencia")
public class ReferenciaController extends BaseControllerImpl<Referencia, ReferenciaServiceImpl>{

    //------------------------------------------------------------------------------------
    // Lista de referencias
    @GetMapping("listaReferencias")
    public String listaReferencias(Model model) throws Exception{
        model.addAttribute("referencias", servicio.findAll());
        return "views/crud/referencia/listaReferencias";
    }

    //------------------------------------------------------------------------------------
    // Nueva Referencia
    @GetMapping("/nuevaReferencia")
    public String nuevaReferencia(Model model){
        Referencia referencia=new Referencia();
        model.addAttribute("nuevaReferencia", referencia);
        return "views/crud/referencia/nuevaReferencia";
    }
    @PostMapping("/guardarReferencia")
    public String guardarReferencia(@Valid @ModelAttribute("nuevaReferencia") Referencia referencia, BindingResult result) throws Exception{
        if(result.hasErrors()){
            return "views/crud/referencia/nuevaReferencia";
        }
        referencia.setVigente(true);
        servicio.save(referencia);
        return "redirect:/referencia/listaReferencias";
    }

    //------------------------------------------------------------------------------------
    // Dar de baja un Referencia
    @GetMapping("/confirmarBajaReferencia/{id}")
    public String confirmarBajaReferencia(@PathVariable long id, Model model) {
        try {
            model.addAttribute("referencia", servicio.findById(id));
            return "views/crud/referencia/darDeBajaReferencia";
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/darDeBajaReferencia/{id}")
    public String darDeBajaReferencia( @PathVariable("id") Long id) throws Exception{
        try {
            Referencia referencia=servicio.findById(id);
            referencia.setVigente(false);
            servicio.update(id,referencia);
            return "redirect:/referencia/listaReferencias";
        }catch (Exception e){
            return "error";
        }
    }

    //------------------------------------------------------------------------------------
    // Dar de alta Referencia
    @GetMapping("/confirmarAltaReferencia/{id}")
    public String confirmarAlta(@PathVariable long id, Model model) {
        try {
            model.addAttribute("referencia", servicio.findById(id));
            return "views/crud/referencia/darDeAltaReferencia";
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/darDeAltaReferencia/{id}")
    public String darDeAltaReferencia( @PathVariable("id") Long id) throws Exception{
        try {
            Referencia referencia=servicio.findById(id);
            referencia.setVigente(true);
            servicio.update(id,referencia);
            return "redirect:/referencia/listaReferencias";
        }catch (Exception e){
            return "error";
        }
    }
    /*
        <---------------------------->
        ELIMINAR UN TIPO DE COLECTIVO
        <---------------------------->
     */
    @GetMapping("/confirmarEliminarReferencia/{id}")
    public String confirmarEliminar(@PathVariable long id, Model model) {
        try {
            model.addAttribute("referencia", servicio.findById(id));
            return "views/crud/referencia/eliminarReferencia";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/eliminarReferencia/{id}")
    public String eliminarReferencia(@PathVariable("id") Long id) throws Exception{
        try{
            Referencia referencia=servicio.findById(id);
            servicio.delete(id);
            return "redirect:/referencia/listaReferencias";
        }catch (Exception e){
            return "error";
        }
    }

}
