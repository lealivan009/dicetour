package dicetour.repositories;

import dicetour.entities.TipoColectivo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoColectivoRepository extends BaseRepository<TipoColectivo, Long>{

    @Query(
            value="SELECT * FROM tipo_colectivo WHERE tipo_colectivo.vigente=true",
            nativeQuery = true)
    List<TipoColectivo> findColectivosVigentes();
}
