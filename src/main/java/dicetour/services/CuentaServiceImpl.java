package dicetour.services;

import dicetour.entities.Cuenta;
import dicetour.entities.Rol;
import dicetour.repositories.BaseRepository;
import dicetour.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl extends BaseServiceImpl<Cuenta, Long> implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public CuentaServiceImpl(BaseRepository<Cuenta, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cuenta cuenta = cuentaRepository.findByEmail(email);
        if(cuenta == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        System.out.println(new User(cuenta.getEmail(),cuenta.getPassword(), mapearAutoridadesRoles(cuenta.getRoles())));
        return new User(cuenta.getEmail(),cuenta.getPassword(), mapearAutoridadesRoles(cuenta.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }
}
