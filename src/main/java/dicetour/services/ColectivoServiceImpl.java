package dicetour.services;

import dicetour.entities.Colectivo;
import dicetour.repositories.BaseRepository;
import dicetour.repositories.ColectivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ColectivoServiceImpl extends BaseServiceImpl<Colectivo, Long> implements ColectivoService {

    @Autowired
    private ColectivoRepository colectivoRepository;

    public ColectivoServiceImpl(BaseRepository<Colectivo, Long> baseRepository) {
        super(baseRepository);
    }

    @Transactional
    public List<Colectivo> findColectivosVigentes() {
       try{
           return colectivoRepository.findColectivosVigentes();
        }catch(Exception e){
            return null;
        }
    }

    @Transactional
    public List<Colectivo> findByTitle(String q) throws Exception{
        try {
            List<Colectivo> colectivos= colectivoRepository.findByTitle(q);
            return colectivos;
        }catch (Exception e){
            return null;
        }
    }
}
