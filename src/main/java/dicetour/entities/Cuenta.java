package dicetour.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="Cuenta",uniqueConstraints = @UniqueConstraint(columnNames = "email")) //de esta forma hago que no se repitan los email
public class Cuenta extends Base{



    @NotBlank(message = "El campo no puede estar vacio")
    @Email
    private String email;

    private String password;

    private boolean vigente;

   // @Temporal(TemporalType.DATE)
    //private Date fechaFinVigencia;

    //@NotNull(message = "El rol no puede estar vacio")
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "cuenta_roles",
            joinColumns = @JoinColumn(name = "cuenta_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
    )
    private Collection<Rol> roles;
}
