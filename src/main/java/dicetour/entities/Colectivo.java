package dicetour.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="Colectivo")

public class Colectivo extends Base{

    private String nombreColectivo;

    private int nroColectivo;

    private String Observaciones;

    private boolean vigente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tipoColectivo")
    private TipoColectivo tipoColectivo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Recorrido> recorridos=new ArrayList<Recorrido>();
}
