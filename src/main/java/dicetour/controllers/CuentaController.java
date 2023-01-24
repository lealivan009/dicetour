package dicetour.controllers;

import dicetour.entities.Cuenta;
import dicetour.entities.Persona;
import dicetour.services.CuentaServiceImpl;
import dicetour.services.PersonaServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "cuenta")
public class CuentaController extends BaseControllerImpl<Cuenta, CuentaServiceImpl>{

    @GetMapping("/signin")  //si quisiera que dos direcciones me llevaran a la misma pagina pondria {"/dir1","/dir2"}
    public String signin(Model model) throws Exception { //EL NOMBRE DEL METODO ES SOLO REPRESENTATIVO, NO SE UTILIZA
        try {
            return "views/signIn"; //ACA TIENE QUE IR EL NOMBRE DE LA PLANTILLA DONDE SE QUIERE USAR
        } catch (Exception e) {
            return "error";
        }
    }

}