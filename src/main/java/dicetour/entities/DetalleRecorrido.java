package dicetour.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="DetalleRecorrido")
public class DetalleRecorrido extends Base{

    private boolean vigente;

    //FORMATOS PARA GUARDAR FECHAS ES .DATE , PARA GUARDAR HORAS ES .TIME , PARA GUARDAR FECHA Y HORA ES .TIMESTAMP
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date horaInicio;


    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date horaFin;

}
