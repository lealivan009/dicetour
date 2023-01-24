package dicetour.services;

import dicetour.entities.Rol;
import dicetour.repositories.BaseRepository;
import dicetour.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RolServiceImpl extends BaseServiceImpl<Rol, Long> implements RolService {

    @Autowired
    private RolRepository rolRepository;

    public RolServiceImpl(BaseRepository<Rol, Long> baseRepository) {
        super(baseRepository);
    }

    @Transactional
    public List<Rol> findRolesVigentes() throws Exception{
        try{
            List<Rol> roles=rolRepository.findRolesVigentes();
            return roles;
        }catch(Exception e){
            return null;
        }
    }

    @Transactional
    public Rol findByNombre(String nombre) throws Exception{
        try {
            Rol rol = rolRepository.findByNombre(nombre);
            return rol;
        }catch (Exception e){
            return null;
        }
    }

}
