package dicetour.repositories;

import dicetour.entities.Cuenta;

public interface CuentaRepository extends BaseRepository<Cuenta, Long>{

    public Cuenta findByEmail(String email);
}
