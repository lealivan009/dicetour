package dicetour.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="Recorrido")

public class Recorrido extends Base{

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<DetalleRecorrido> detalleRecorridos=new ArrayList<DetalleRecorrido>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_origen")
    private Referencia origen;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_destino")
    private Referencia destino;


}
