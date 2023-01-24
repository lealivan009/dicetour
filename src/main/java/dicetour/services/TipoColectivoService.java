package dicetour.services;

import dicetour.entities.Colectivo;
import dicetour.entities.TipoColectivo;

import java.util.List;

public interface TipoColectivoService extends BaseService <TipoColectivo, Long>{

    public List<TipoColectivo> findTipoColectivoVigentes();
}
