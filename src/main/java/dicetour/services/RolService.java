package dicetour.services;

import dicetour.entities.Colectivo;
import dicetour.entities.Rol;

import java.util.List;

public interface RolService extends BaseService <Rol, Long>{

    public List<Rol> findRolesVigentes() throws Exception;

    public Rol findByNombre(String nombre) throws Exception;
}
