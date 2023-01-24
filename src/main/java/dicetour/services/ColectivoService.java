package dicetour.services;

import dicetour.entities.Colectivo;

import java.util.List;

public interface ColectivoService extends BaseService <Colectivo, Long>{

    public List<Colectivo> findColectivosVigentes();
    public List<Colectivo> findByTitle(String q) throws Exception;
}
