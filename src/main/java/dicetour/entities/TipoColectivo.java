package dicetour.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="TipoColectivo")

public class TipoColectivo extends Base{

    @NotBlank(message = "Este campo no puede estar vacio")
    @Size(min=4,max=30,message = "El tipo de Colectivo debe estar entre los 4 y 30 caracteres")
    private String nombreTipoColectivo;

    private boolean vigente;
}
