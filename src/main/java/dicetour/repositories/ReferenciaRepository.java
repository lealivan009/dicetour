package dicetour.repositories;

import dicetour.entities.Referencia;
import dicetour.entities.Rol;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReferenciaRepository extends BaseRepository<Referencia, Long>{
    @Query(
            value="SELECT * FROM referencia WHERE referencia.vigente=true",
            nativeQuery = true)
    List<Referencia> findReferenciasVigentes();
}
