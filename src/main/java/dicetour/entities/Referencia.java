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
@Table(name ="Referencia")

public class Referencia extends Base{

    @NotBlank(message = "Este campo no puede estar vacio")
    @Size(min=4,max=50,message = "La referencia debe estar entre los 4 y 50 caracteres")
    private String nombre;

    private boolean vigente;
}
