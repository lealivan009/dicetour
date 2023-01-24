package dicetour.services;

import dicetour.entities.Cuenta;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CuentaService extends BaseService <Cuenta, Long>, UserDetailsService {
}
