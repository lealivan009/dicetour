package dicetour.controllers;

import dicetour.entities.*;
import dicetour.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path = "colectivo")
public class ColectivoController extends BaseControllerImpl<Colectivo, ColectivoServiceImpl> {

    @Autowired
    ColectivoService colectivoService;

    @Autowired
    DetalleRecorridoService detalleRecorridoService;

    @Autowired
    TipoColectivoService tipoColectivoService;

    @Autowired
    ReferenciaService referenciaService;

    @GetMapping("/") //Pantalla para usuario comun
    public String mostrarColectivosVigentes(Model model) throws Exception {
        try {
            model.addAttribute("colectivos", colectivoService.findColectivosVigentes());
            return "views/seleccionHorarios";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping(value = "/detalle/{id}") //Pantalla para usuario comun
    public String mostrarHorariosColectivoSeleccionado(Model model, @PathVariable("id") Long id) {
        try {
            Colectivo colectivo = colectivoService.findById(id);
            for (int j = 0; j < colectivo.getRecorridos().size(); j++) {
                for (int i = 0; i < colectivo.getRecorridos().get(j).getDetalleRecorridos().size(); i++) {
                    if (colectivo.getRecorridos().get(j).getDetalleRecorridos().get(i).isVigente() == false) {
                        colectivo.getRecorridos().get(j).getDetalleRecorridos().remove(i);
                    }
                }
                //Funcion para ordenar listas
                colectivo.getRecorridos().get(j).getDetalleRecorridos().sort(Comparator.comparing(DetalleRecorrido::getHoraInicio));
            }
            model.addAttribute("colectivo", colectivo);
            return "views/detalleHorarios";
        } catch (Exception e) {
            return "error";
        }
    }


    @GetMapping("/busqueda")
    public String busqueda(Model model, @RequestParam(value = "query", required = false) String q) {
        try {
            List<Colectivo> colectivos = servicio.findByTitle(q);
            model.addAttribute("colectivos", colectivos);

            return "views/seleccionHorariosBusqueda";
        } catch (Exception e) {
            return "error";
        }
    }


    //---------------------------------->HORARIOS-CRUD<---------------------------------
    //------------------------------------------------------------------------------------
    //MUESTRA UNA LISTA CON TODOS LOS COLECTIVOS PARA LUEGO MOSTRAR LOS HORARIOS
    @GetMapping("/crudHorarios")
    public String mostraColectivosASeleccionar(Model model) throws Exception {
        try {
            model.addAttribute("colectivos", colectivoService.findAll());
            return "views/crud/horario/seleccionHorariosCrud";
        } catch (Exception e) {
            return "error";
        }
    }

    //------------------------------------------------------------------------------------
    // Mostrar los horarios de un colectivo especifico
    @GetMapping(value = "/crudHorarios/{id}")
    public String mostrarHorarioParaUnColectivoSeleccionado(Model model, @PathVariable("id") Long id) {
        try {
            Colectivo colectivo = colectivoService.findById(id);
            for (int j = 0; j < colectivo.getRecorridos().size(); j++) {
                //Funcion para ordenar listas
                colectivo.getRecorridos().get(j).getDetalleRecorridos().sort(Comparator.comparing(DetalleRecorrido::getHoraInicio));
            }
            model.addAttribute("colectivo", colectivo);
            return "views/crud/horario/tablaHorarios";
        } catch (Exception e) {
            return "error";
        }
    }

    //------------------------------------------------------------------------------------
    // Nuevo Horario
    @GetMapping("/nuevoHorario/{idColectivo}/{idRecorrido}")
    public String nuevoDetalleRecorrido(Model model, @PathVariable("idColectivo") Long idColectivo, @PathVariable("idRecorrido") Long idRecorrido) throws Exception {

        //Creacion del detalleRecorrido
        DetalleRecorrido detalleRecorrido = new DetalleRecorrido();
        model.addAttribute("nuevoDetalleRecorrido", detalleRecorrido);

        //Id de recorrido, para saber si es ida o vuelta
        model.addAttribute("idRecorrido", idRecorrido);
        //Envio de informacion del colectivo
        Colectivo colectivo = servicio.findById(idColectivo);
        model.addAttribute("colectivo", colectivo);

        return "views/crud/horario/nuevoHorario";
    }

    @PostMapping("/guardarHorario/{idColectivo}/{idRecorrido}")
    public String guardarDetalleRecorrido(@PathVariable("idColectivo") Long idColectivo, @PathVariable("idRecorrido") Long idRecorrido, @ModelAttribute("nuevoDetalleRecorrido") DetalleRecorrido detalleRecorrido) throws Exception {
        Colectivo colectivoExistente = servicio.findById(idColectivo);
        detalleRecorrido.setVigente(true);
        colectivoExistente.getRecorridos().get(Long.valueOf(idRecorrido).intValue()).getDetalleRecorridos().add(detalleRecorrido);

        servicio.update(colectivoExistente.getId(), colectivoExistente);
        return "redirect:/colectivo/crudHorarios/" + idColectivo;
    }

    //------------------------------------------------------------------------------------
    // Editar Horario
    @GetMapping("/editarHorario/{idColectivo}/{idRecorrido}")
    public String editarDetalleRecorrido(Model model, @PathVariable("idColectivo") Long idColectivo, @PathVariable("idRecorrido") Long idRecorrido) throws Exception {

        //Creacion del detalleRecorrido
        DetalleRecorrido detalleRecorrido = detalleRecorridoService.findById(idRecorrido);
        model.addAttribute("editarDetalleRecorrido", detalleRecorrido);
        //Envio de informacion del colectivo
        Colectivo colectivo = servicio.findById(idColectivo);
        model.addAttribute("colectivo", colectivo);
        return "views/crud/horario/editarHorario";
    }

    @PostMapping("/actualizarHorario/{idColectivo}/{idRecorrido}")
    public String actualizarDetalleRecorrido(@PathVariable("idColectivo") Long idColectivo, @PathVariable("idRecorrido") Long idRecorrido, @ModelAttribute("editarDetalleRecorrido") DetalleRecorrido detalleRecorrido) throws Exception {
        DetalleRecorrido detalleRecorrido1 = detalleRecorridoService.findById(idRecorrido);
        detalleRecorrido1.setHoraInicio(detalleRecorrido.getHoraInicio());
        detalleRecorrido1.setHoraFin(detalleRecorrido.getHoraFin());
        detalleRecorridoService.update(idRecorrido, detalleRecorrido1);
        return "redirect:/colectivo/crudHorarios/" + idColectivo;
    }


    //---------------------------------->COLECTIVOS-CRUD<---------------------------------
    //------------------------------------------------------------------------------------
    // Mostrar todos los colectivos
    @GetMapping("/crudColectivos")
    public String crudColectivos(Model model) throws Exception {
        try {
            model.addAttribute("colectivos", colectivoService.findAll());
            return "views/crud/colectivo/tablaColectivos";
        } catch (Exception e) {
            return "error";
        }
    }

    //------------------------------------------------------------------------------------
    // Agregar un nuevo Colectivo
    @GetMapping("/nuevoColectivo")
    private String nuevoColectivo(Model model) throws Exception {
        Colectivo colectivo = new Colectivo();
        Recorrido recorrido1 = new Recorrido();

        model.addAttribute("nuevoColectivo", colectivo);
        model.addAttribute("recorrido1", recorrido1);

        model.addAttribute("tiposColectivos", tipoColectivoService.findTipoColectivoVigentes());
        model.addAttribute("referencias", referenciaService.findReferenciasVigentes());
        return "views/crud/colectivo/nuevoColectivo";
    }

    @PostMapping("/guardarColectivo")
    public String guardarcolectivo(@ModelAttribute("nuevoColectivo") Colectivo nuevoColectivo, @ModelAttribute("recorrido1") Recorrido recorrido1) throws Exception {

        //Codigo para generar recorrido de vuelta
        Referencia origenVuelta = recorrido1.getDestino();
        Referencia destinoVuelta = recorrido1.getOrigen();
        Recorrido recorrido2 = new Recorrido();
        recorrido2.setOrigen(origenVuelta);
        recorrido2.setDestino(destinoVuelta);

        Colectivo colectivo=new Colectivo();

        colectivo.setVigente(true);
        colectivo.setNombreColectivo(nuevoColectivo.getNombreColectivo().toUpperCase());
        colectivo.setTipoColectivo(nuevoColectivo.getTipoColectivo());
        colectivo.setNroColectivo(nuevoColectivo.getNroColectivo());
        colectivo.setObservaciones(nuevoColectivo.getObservaciones());
        colectivo.getRecorridos().add(recorrido1);
        colectivo.getRecorridos().add(recorrido2);

        colectivoService.save(colectivo);
        return "redirect:/colectivo/crudColectivos";
    }

    //------------------------------------------------------------------------------------
    // Dar de baja un Colectivo
    @GetMapping("/confirmarBajaColectivo/{id}")
    public String confirmarEliminar(@PathVariable long id, Model model) {
        try {
            model.addAttribute("colectivo", servicio.findById(id));

            return "views/crud/colectivo/darDeBajaColectivo";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/darDeBajaColectivo/{id}")
    public String eliminarColectivo(@PathVariable("id") Long id) throws Exception {
        try {
            Colectivo colectivo = servicio.findById(id);
            colectivo.setVigente(false);
            servicio.update(id, colectivo);
            return "redirect:/colectivo/crudColectivos";
        } catch (Exception e) {
            return "error";
        }
    }

    //------------------------------------------------------------------------------------
    // Dar de alta Colectivo
    @GetMapping("/confirmarAltaColectivo/{id}")
    public String confirmarAlta(@PathVariable long id, Model model) {
        try {
            model.addAttribute("colectivo", servicio.findById(id));
            return "views/crud/colectivo/darDeAltaColectivo";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/darDeAltaColectivo/{id}")
    public String darDeAltaColectivo(@PathVariable("id") Long id) throws Exception {
        try {
            Colectivo colectivo = servicio.findById(id);
            colectivo.setVigente(true);
            servicio.update(id, colectivo);
            return "redirect:/colectivo/crudColectivos";
        } catch (Exception e) {
            return "error";
        }
    }
    //------------------------------------------------------------------------------------

}

