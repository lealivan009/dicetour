package dicetour.controllers;

import dicetour.entities.Referencia;
import dicetour.entities.Rol;
import dicetour.entities.TipoColectivo;
import dicetour.services.TipoColectivoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "tipoColectivo")
public class TipoColectivoController extends BaseControllerImpl<TipoColectivo, TipoColectivoServiceImpl>{

    //------------------------------------------------------------------------------------
    // Lista de tipos de colectivos
    @GetMapping("listaTiposColectivos")
    public String listaTiposColectivos(Model model) throws Exception{
        model.addAttribute("tiposColectivos", servicio.findAll());
        return "views/crud/tipo_colectivo/listaTipoColectivo";
    }

    //------------------------------------------------------------------------------------
    // Nuevo TipoColectivo
    @GetMapping("/nuevoTipoColectivo")
    public String nuevoTipoColectivo(Model model){
        TipoColectivo tipoColectivo=new TipoColectivo();
        model.addAttribute("nuevoTipoColectivo", tipoColectivo);
        return "views/crud/tipo_colectivo/nuevoTipoColectivo";
    }
    @PostMapping("/guardarTipoColectivo")
    public String guardarTipoColectivo(@Valid @ModelAttribute("nuevoTipoColectivo") TipoColectivo tipoColectivo, BindingResult result) throws Exception{
        if(result.hasErrors()){
            return "views/crud/tipo_colectivo/nuevoTipoColectivo";
        }
        tipoColectivo.setVigente(true);
        servicio.save(tipoColectivo);
        return "redirect:/tipoColectivo/listaTiposColectivos";
    }

    //------------------------------------------------------------------------------------
    // Dar de baja un TipoColectivo
    @GetMapping("/confirmarBajaTipoColectivo/{id}")
    public String confirmarBajaTipoColectivo(@PathVariable long id, Model model) {
        try {
            model.addAttribute("tipoColectivo", servicio.findById(id));
            return "views/crud/tipo_colectivo/darDeBajaTipoColectivo";
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/darDeBajaTipoColectivo/{id}")
    public String darDeBajaTipoColectivo( @PathVariable("id") Long id) throws Exception{
        try {
            TipoColectivo tipoColectivo=servicio.findById(id);
            tipoColectivo.setVigente(false);
            servicio.update(id,tipoColectivo);
            return "redirect:/tipoColectivo/listaTiposColectivos";
        }catch (Exception e){
            return "error";
        }
    }

    //------------------------------------------------------------------------------------
    // Dar de alta TipoColectivo
    @GetMapping("/confirmarAltaTipoColectivo/{id}")
    public String confirmarAlta(@PathVariable long id, Model model) {
        try {
            model.addAttribute("tipoColectivo", servicio.findById(id));
            return "views/crud/tipo_colectivo/darDeAltaTipoColectivo";
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/darDeAltaTipoColectivo/{id}")
    public String darDeAltaTipoColectivo( @PathVariable("id") Long id) throws Exception{
        try {
            TipoColectivo tipoColectivo=servicio.findById(id);
            tipoColectivo.setVigente(true);
            servicio.update(id,tipoColectivo);
            return "redirect:/tipoColectivo/listaTiposColectivos";
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
            model.addAttribute("tipoColectivo", servicio.findById(id));
            return "views/crud/tipo_colectivo/eliminarTipoColectivo";
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/eliminarTipoColectivo/{id}")
    public String eliminarTipoColectivo(@PathVariable("id") Long id) throws Exception{
        try{
            TipoColectivo tipoColectivo=servicio.findById(id);
            servicio.delete(id);
            return "redirect:/tipoColectivo/listaTiposColectivos";
        }catch (Exception e){
            return "error";
        }
    }

}
