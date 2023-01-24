package dicetour.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="Rol")
public class Rol extends Base{

    @NotBlank(message = "Este campo no puede estar vacio")
    @Size(min=4,max=10,message = "El nombre debe estar entre los 4 y 10 caracteres")
    private String nombre;

    private boolean vigente;
}
