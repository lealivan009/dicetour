package dicetour.repositories;

import dicetour.entities.Cuenta;
import dicetour.entities.Rol;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolRepository extends BaseRepository<Rol, Long>{

    @Query(
            value="SELECT * FROM rol WHERE rol.vigente=true",
            nativeQuery = true)
    List<Rol> findRolesVigentes();

    public Rol findByNombre(String nombre);
}
