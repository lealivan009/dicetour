package dicetour.services;

import dicetour.entities.DetalleRecorrido;
import dicetour.entities.Rol;
import dicetour.repositories.BaseRepository;
import dicetour.repositories.DetalleRecorridoRepository;
import dicetour.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleRecorridoServiceImpl extends BaseServiceImpl<DetalleRecorrido, Long> implements DetalleRecorridoService {

    @Autowired
    private DetalleRecorridoRepository detalleRecorridoRepository;

    public DetalleRecorridoServiceImpl(BaseRepository<DetalleRecorrido, Long> baseRepository) {
        super(baseRepository);
    }
}
