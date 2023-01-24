package dicetour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NuevaPersona {
    @NotBlank(message = "El campo no puede estar vacio")
    @Size(min=4,max=20,message="El tamaño del nombre debe estar entre 4 y 20 caracteres")
    private String nombre;

    @NotBlank(message = "El campo no puede estar vacio")
    @Size(min=4,max=30,message="El tamaño del apellido debe estar entre 4 y 30 caracteres")
    private String apellido;

    @NotBlank(message = "El campo no puede estar vacio")
    @Size(min=7,max=8,message = "El dni debe estar entre 7 y 8 digitos")
    private String dni;

    @PastOrPresent(message = "La fecha no puede ser superior a hoy")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar fechaNacimiento;

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

    @NotBlank(message = "El campo no puede estar vacio")
    private String email;

    @NotBlank(message = "El campo no puede estar vacio")
    //colocar un patron para formar la contraseña
    private String password;
}
