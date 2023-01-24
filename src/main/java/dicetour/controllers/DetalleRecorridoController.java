package dicetour.controllers;

import dicetour.entities.DetalleRecorrido;
import dicetour.services.ColectivoService;
import dicetour.services.DetalleRecorridoService;
import dicetour.services.DetalleRecorridoServiceImpl;
import dicetour.services.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "detalleRecorrido")
public class DetalleRecorridoController extends BaseControllerImpl<DetalleRecorrido, DetalleRecorridoServiceImpl>{

    @Autowired
    ColectivoService colectivoService;

    //-----------------------EDITAR ---------------------------------
    @GetMapping("/editarHorario/{id}")
    public String editarHorario(Model model, @PathVariable("id") Long id)  {
        try {
            DetalleRecorrido detalleRecorrido=servicio.findById(id);
            model.addAttribute("detalleRecorrido", detalleRecorrido);
            return "views/crud/editarHorario";
        } catch (Exception e) {
            return "error";
        }
    }

    //------------------------------------------------------------------------------------
    //Dar de baja un DetalleRecorrido
    @GetMapping("/confirmarBajaDetalleRecorrido/{idColectivo}/{idRecorrido}")
    public String confirmarEliminar(@PathVariable("idColectivo") Long idColectivo,@PathVariable("idRecorrido")Long idRecorrido, Model model) {
        try {
            model.addAttribute("colectivo",colectivoService.findById(idColectivo));
            model.addAttribute("detalleRecorrido", servicio.findById(idRecorrido));
            return "views/crud/horario/darDeBajaHorario";
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/darDeBajaDetalleRecorrido/{idColectivo}/{idRecorrido}")
    public String darDeBajaDetalleRecorrido(@PathVariable("idColectivo") Long idColectivo,@PathVariable("idRecorrido")Long idRecorrido) throws Exception {
        try {
            DetalleRecorrido detalleRecorrido1=servicio.findById(idRecorrido);
            detalleRecorrido1.setVigente(false);
            servicio.update(idRecorrido, detalleRecorrido1);
            return "redirect:/colectivo/crudHorarios/"+idColectivo;
        } catch (Exception e) {
            return "error";
        }
    }
    //------------------------------------------------------------------------------------
    //Dar de alta un DetalleRecorrido
    @GetMapping("/confirmarAltaDetalleRecorrido/{idColectivo}/{idRecorrido}")
    public String confirmarAlta(@PathVariable("idColectivo") Long idColectivo,@PathVariable("idRecorrido")Long idRecorrido, Model model) {
        try {
            model.addAttribute("colectivo",colectivoService.findById(idColectivo));
            model.addAttribute("detalleRecorrido", servicio.findById(idRecorrido));
            return "views/crud/horario/darDeAltaHorario";
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/darDeAltaDetalleRecorrido/{idColectivo}/{idRecorrido}")
    public String darDeAltaDetalleRecorrido(@PathVariable("idColectivo") Long idColectivo,@PathVariable("idRecorrido")Long idRecorrido) throws Exception {
        try {
            DetalleRecorrido detalleRecorrido1=servicio.findById(idRecorrido);
            detalleRecorrido1.setVigente(true);
            servicio.update(idRecorrido, detalleRecorrido1);
            return "redirect:/colectivo/crudHorarios/"+idColectivo;
        } catch (Exception e) {
            return "error";
        }
    }



}