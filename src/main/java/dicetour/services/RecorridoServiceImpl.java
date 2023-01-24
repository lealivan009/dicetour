package dicetour.services;

import dicetour.entities.Colectivo;
import dicetour.entities.Recorrido;
import dicetour.repositories.BaseRepository;
import dicetour.repositories.ColectivoRepository;
import dicetour.repositories.RecorridoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecorridoServiceImpl extends BaseServiceImpl<Recorrido, Long> implements RecorridoService {

    @Autowired
    private RecorridoRepository recorridoRepository;

    public RecorridoServiceImpl(BaseRepository<Recorrido, Long> baseRepository) {
        super(baseRepository);
    }
}
