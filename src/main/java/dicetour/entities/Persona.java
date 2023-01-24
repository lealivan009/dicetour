package dicetour.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="Persona")

public class Persona extends Base {
    //@NotBlank(message = "El campo no puede estar vacio")
    //@Size(min=4,max=20,message="El tamaño del nombre debe estar entre 4 y 20 caracteres")
    private String nombre;

    //@NotBlank(message = "El campo no puede estar vacio")
    //@Size(min=4,max=30,message="El tamaño del apellido debe estar entre 4 y 30 caracteres")
    private String apellido;

    //@NotBlank(message = "El campo no puede estar vacio")
    //@Size(min=7,max=8,message = "El dni debe estar entre 7 y 8 digitos")
    private String dni;

    @NotBlank(message = "El campo no puede estar vacio")
    @Size(min=5,max=50,message = "El domicilio debe estar entre 5 y 50 caracteres")
    private String domicilio;

    @NotBlank(message = "El campo no puede estar vacio")
    @Size(min=5,max=30,message = "La ciudad debe estar entre 5 y 30 caracteres")
    private String ciudad;

    @NotBlank(message = "El campo no puede estar vacio")
    @Size(min=5,max=30,message = "La provincia debe estar entre 5 y 30 caracteres")
    private String provincia;

    @NotBlank(message = "El campo no puede estar vacio")
    @Size(min=4,max=4,message = "El codigo postal debe estar formado por 4 digitos")
    private String codigoPostal;

    private boolean vigente;

    @PastOrPresent(message = "La fecha no puede ser superior a hoy")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar fechaNacimiento;

    @NotNull(message = "El campo no puede estar vacio")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cuenta")
    private Cuenta cuenta;
}
