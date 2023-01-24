package dicetour.repositories;

import dicetour.entities.Colectivo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColectivoRepository extends BaseRepository<Colectivo, Long>{

    @Query(
            value="SELECT * FROM colectivo WHERE colectivo.vigente=true",
            nativeQuery = true)
    List<Colectivo> findColectivosVigentes();
}
