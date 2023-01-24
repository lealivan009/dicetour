package dicetour.services;

import dicetour.entities.Colectivo;
import dicetour.entities.Referencia;
import dicetour.entities.TipoColectivo;
import dicetour.repositories.BaseRepository;
import dicetour.repositories.ReferenciaRepository;
import dicetour.repositories.TipoColectivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TipoColectivoServiceImpl extends BaseServiceImpl<TipoColectivo, Long> implements TipoColectivoService {

    @Autowired
    private TipoColectivoRepository tipoColectivoRepository;

    public TipoColectivoServiceImpl(BaseRepository<TipoColectivo, Long> baseRepository) {
        super(baseRepository);
    }

    @Transactional
    public List<TipoColectivo> findTipoColectivoVigentes() {
        try{
            return tipoColectivoRepository.findColectivosVigentes();
        }catch(Exception e){
            return null;
        }
    }

}
