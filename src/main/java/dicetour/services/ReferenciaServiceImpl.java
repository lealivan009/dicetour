package dicetour.services;

import dicetour.entities.Colectivo;
import dicetour.entities.Referencia;
import dicetour.entities.Rol;
import dicetour.repositories.BaseRepository;
import dicetour.repositories.ReferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReferenciaServiceImpl extends BaseServiceImpl<Referencia, Long> implements ReferenciaService {

    @Autowired
    private ReferenciaRepository referenciaRepository;

    public ReferenciaServiceImpl(BaseRepository<Referencia, Long> baseRepository) {
        super(baseRepository);
    }


    @Transactional
    public List<Referencia> findReferenciasVigentes() throws Exception{
        try{
            List<Referencia> referencias=referenciaRepository.findReferenciasVigentes();
            return referencias;
        }catch(Exception e){
            return null;
        }
    }


}
