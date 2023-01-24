package dicetour.repositories;

import dicetour.entities.Colectivo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColectivoRepository extends BaseRepository<Colectivo, Long>{

    @Query(
            value="SELECT * FROM colectivo WHERE colectivo.vigente=true",
            nativeQuery = true)
    List<Colectivo> findColectivosVigentes();

    @Query(
            value = "SELECT * FROM colectivo WHERE colectivo.nombre_colectivo LIKE %:q% and colectivo.vigente=true" ,
            nativeQuery = true)
    List<Colectivo> findByTitle(@Param("q")String q);
}
