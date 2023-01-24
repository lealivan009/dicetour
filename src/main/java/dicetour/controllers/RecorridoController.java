package dicetour.controllers;

import dicetour.entities.Recorrido;
import dicetour.services.ColectivoService;
import dicetour.services.RecorridoService;
import dicetour.services.RecorridoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "recorrido")
public class RecorridoController extends BaseControllerImpl<Recorrido, RecorridoServiceImpl>{


    @Autowired
    private RecorridoService recorridoService;



    @GetMapping("/DirectoPorRobert")  //si quisiera que dos direcciones me llevaran a la misma pagina pondria {"/dir1","/dir2"}
    public String PorRobert(Model model) throws Exception { //EL NOMBRE DEL METODO ES SOLO REPRESENTATIVO, NO SE UTILIZA
        try {
            model.addAttribute("recorridosIda",recorridoService.findById(1L));
            model.addAttribute("recorridosVuelta",recorridoService.findById(2L));
            return "views/horarios"; //ACA TIENE QUE IR EL NOMBRE DE LA PLANTILLA DONDE SE QUIERE USAR
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/UnCuyoPorSanMartin")  //si quisiera que dos direcciones me llevaran a la misma pagina pondria {"/dir1","/dir2"}
    public String PorBarriales(Model model) throws Exception { //EL NOMBRE DEL METODO ES SOLO REPRESENTATIVO, NO SE UTILIZA
        try {
            model.addAttribute("recorridosIda",recorridoService.findById(3L));
            model.addAttribute("recorridosVuelta",recorridoService.findById(4L));
            return "views/horarios"; //ACA TIENE QUE IR EL NOMBRE DE LA PLANTILLA DONDE SE QUIERE USAR
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/crud")  //si quisiera que dos direcciones me llevaran a la misma pagina pondria {"/dir1","/dir2"}
    public String crudHorario(Model model) throws Exception { //EL NOMBRE DEL METODO ES SOLO REPRESENTATIVO, NO SE UTILIZA
        try {
            return "views/admHorarios"; //ACA TIENE QUE IR EL NOMBRE DE LA PLANTILLA DONDE SE QUIERE USAR
        } catch (Exception e) {
            return "error";
        }
    }

}
